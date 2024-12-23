// URL de la API para tomar los datos
const apiProdUrl = "http://localhost:8080/api/productos";
const apiArtUrl = "http://localhost:8080/api/articulos";
const apiUsrUrl = "http://localhost:8080/api/usuarios";
const apiCartUrl = "http://localhost:8080/api/carritos";

window.onload = () => {
  verificarSesion();
};

function verificarSesion() {
  const usuario = localStorage.getItem("usuario");
  if (usuario) {
    mostrarPrincipal(); // Si hay sesión activa, mostrar la página principal
  } else {
    mostrarLogin(); // Si no hay sesión, mostrar el login
  }
}

function mostrarLogin() {
  document.getElementById("PaginaInicial").classList.add("centrada");
  document.getElementById("PaginaPrincipal").classList.remove("visible");
}

function mostrarPrincipal() {
  document.getElementById("PaginaInicial").classList.remove("centrada");
  document.getElementById("PaginaPrincipal").classList.add("visible");

  const usuario = JSON.parse(localStorage.getItem("usuario"));
  //buscar el carrito que pertenece al usuario autenticado
  fetchCarrito(usuario.id).then((carrito) => {
    if (carrito) {
      // Guardar más información del carrito en localStorage
      localStorage.setItem(
        "carrito",
        JSON.stringify({ id: carrito.id, usuarioId: usuario.id })
      );
      // Cargar los artículos relacionados con el carrito
      fetchProductos().then((productos) => {
        if (productos) {
          tomarProductos(productos);
          recuperarArticulos(productos);
        } else {
          console.error("No se pudo obtener la lista de productos.");
        }
      });
    } else {
      console.log("No se encontró carrito para este usuario.");
    }
  });
}

function mostrarRegistrar() {
  document.getElementById("PaginaInicial").classList.add("centrada");
  document.getElementById("PaginaPrincipal").classList.remove("visible");
  document.getElementById("PaginaRegistro").classList.add("visible");
}

async function fetchUsuarios() {
  try {
    const response = await fetch(apiUsrUrl);

    // Verificar si la respuesta HTTP fue exitosa
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }

    const responseData = await response.json();

    // Determinar si los datos están directamente en la respuesta o en una propiedad `datos`
    const usuarios = Array.isArray(responseData)
      ? responseData
      : responseData.datos;

    // Validar que `usuarios` sea un arreglo
    if (!Array.isArray(usuarios)) {
      throw new Error("La respuesta no contiene un arreglo de usuarios.");
    }

    return usuarios;
  } catch (error) {
    console.error("Error al obtener los usuarios:", error);
    return null; // Devolver null en caso de error
  }
}

async function fetchArticulos() {
  return fetch(apiArtUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      // Verifica si `data` es un array directamente o está dentro de otra propiedad
      const articulos = Array.isArray(data) ? data : data.datos;
      return articulos;
    });
}

async function fetchProductos() {
  return fetch(apiProdUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      // Verifica si `data` es un array directamente o está dentro de otra propiedad
      const productos = Array.isArray(data) ? data : (data.datos || []);

      // Verifica si es iterable
      if (!Array.isArray(productos)) {
        throw new Error("La respuesta no contiene un array de productos.");
      }
      return productos;
    })
    .catch((error) => console.error("Error al obtener los productos:", error));
}

async function fetchCarrito(id_usuario) {
  try {
    const response = await fetch(apiCartUrl); // Obtener todos los carritos
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }
    const responseData = await response.json();
    const carritos = Array.isArray(responseData) ? responseData : responseData.datos;

    if (!Array.isArray(carritos)) {
      throw new Error("La respuesta no contiene un arreglo de carritos.");
    }

    // Filtrar el carrito del usuario específico
    const carritoUsuario = carritos.find(
      (carrito) => carrito.usuario.id === id_usuario
    );

    if (!carritoUsuario) {
      console.warn(
        `No se encontró carrito para el usuario con ID: ${id_usuario}`
      );
      return null;
    }

    console.log("Carrito encontrado:", carritoUsuario);
    return carritoUsuario; // Devolver el carrito del usuario
  } catch (error) {
    console.error("Error al obtener el carrito:", error);
    return null;
  }
}

//comprueba que el usuario exista y que la contraseña sea correcta
async function iniciarSesion() {
  try {
    const usuarios = await fetchUsuarios();
    if (!usuarios) {
      console.error("No se pudo obtener la lista de usuarios.");
      return; // Salir si no se cargan los usuarios
    }

    // Obtener correo y contraseña ingresados por el usuario
    const correo = document.getElementById("txtCorreo").value.trim();
    const contrasena = document.getElementById("txtContrasena").value.trim();

    // Buscar el usuario en la lista
    const usuarioEncontrado = usuarios.find(
      (usuario) => usuario.correo === correo && usuario.contra === contrasena
    );
    console.log("Usuario encontrado:", usuarioEncontrado);
    if (usuarioEncontrado) {
      console.log("Usuario autenticado:", usuarioEncontrado);
      // Guardar información del usuario en localStorage
      localStorage.setItem(
        "usuario",
        JSON.stringify({
          id: usuarioEncontrado.id,
          correo: usuarioEncontrado.correo,
        })
      );
      // Mostrar la página principal
      mostrarPrincipal();
      
      filtrarProductos("Todo");
      buscar("Todo");
    } else {
      console.warn("Credenciales incorrectas.");
      alert("Correo o contraseña incorrectos. Intenta de nuevo.");
    }
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
    alert("Ocurrió un error al intentar iniciar sesión. Intenta nuevamente.");
    window.location.reload();
  }
}

function cerrarSesion() {
  localStorage.removeItem("usuario");
  mostrarLogin();
}

function recuperarArticulos(productos) {
  const carrito = JSON.parse(localStorage.getItem("carrito"));
  fetchArticulos()
    .then((articulos) => {
      if (articulos) {
        for (let producto of productos) {
          cargarArticulos(producto, articulos, carrito.id);
        }
      } else {
        console.error("No se pudo obtener la lista de artículos.");
      }
    })
    .catch((error) => {
      console.error("Error al obtener los artículos:", error);
      return null; // Devolver null en caso de error
    });
}

//toma los productos y genera una carta por cada uno
function tomarProductos(productos) {
  // Obtén el contenedor donde se mostrarán los productos
  const contenedorProductos = document.getElementById("productos");

  // Limpia el contenedor antes de renderizar (por si ya tiene contenido previo)
  contenedorProductos.innerHTML = "";

  // Recorre cada producto y crea su carta
  for (let producto of productos) {
    //crear carta
    let carta = document.createElement("div");
    //categroria de la carta
    carta.classList.add("carta");
    carta.classList.add(producto.categoria);
    //imagenes de cada division
    let imgContainer = document.createElement("div");
    imgContainer.classList.add("image-container");
    //tag de la imagen
    let imagen = document.createElement("img");
    imagen.setAttribute("src", producto.imagen);
    imgContainer.appendChild(imagen);
    carta.appendChild(imgContainer);

    //contenedor
    let container = document.createElement("div");
    container.classList.add("container");

    //nombre del producto
    let nombre = document.createElement("h5");
    nombre.classList.add("producto-nombre");
    nombre.innerText = producto.nombre_producto.toUpperCase();
    container.appendChild(nombre);

    // Precio
    let precio = document.createElement("p");
    precio.classList.add("producto-precio");
    precio.innerText = `$${producto.precio.toFixed(2)}`;
    container.appendChild(precio);

    let botonCarrito = document.createElement("button");
    botonCarrito.classList.add("btn-carrito");
    botonCarrito.setAttribute("data-id", producto.id); // Identificador del producto
    botonCarrito.addEventListener("click", () => {
      const productoId = botonCarrito.getAttribute("data-id"); // Obtener ID del producto desde el botón
      const carritoId = JSON.parse(localStorage.getItem("carrito")).id; // ID del carrito almacenado
      agregarAlCarrito(productoId, carritoId);
    });
    botonCarrito.innerHTML =
      "<box-icon name='cart-download' type='solid' color='rgba(255,255,255,0.98)' ></box-icon>"; // Ícono de carrito
    container.appendChild(botonCarrito);

    carta.appendChild(container);

    contenedorProductos.appendChild(carta);
  }
}

//funcion para buscar siempre y cuando pertenezca a la categoria seleccionada
function buscar(categoria) {
  document.getElementById("btnBuscarP").addEventListener("click", () => {
    let productoBuscado = document
      .getElementById("txtBusqueda")
      .value.toUpperCase();
    let productos = document.querySelectorAll(".producto-nombre");
    let cartas = document.querySelectorAll(".carta");
    //loop para tomar los productos
    productos.forEach((element, index) => {
      let perteneceCategoria =
        categoria === "Todo" || cartas[index].classList.contains(categoria);
      if (
        element.innerText.includes(productoBuscado.toUpperCase()) &&
        perteneceCategoria
      ) {
        cartas[index].classList.remove("hide");
      } else {
        cartas[index].classList.add("hide");
      }
    });
  });
}

//Parametro que se pasa por el boton de categoria
function filtrarProductos(valorCategoria) {
  //al seleccionar los botones btnCategoria cambia de color
  let botones = document.querySelectorAll(".btnCategoria");
  botones.forEach((button) => {
    if (valorCategoria.toUpperCase() == button.innerText.toUpperCase()) {
      button.classList.add("active");
    } else {
      button.classList.remove("active");
    }
  });

  //seleccionar categoria y filtrar productos
  let cartas = document.querySelectorAll(".carta");
  //se hace un for por cada elemento
  cartas.forEach((element) => {
    //muestra todas las cartas de productos si es "Todo" sino no muestra nada
    if (valorCategoria == "Todo") {
      element.classList.remove("hide");
      buscar(valorCategoria);
    } else if (element.classList.contains(valorCategoria)) {
      element.classList.remove("hide");
      buscar(valorCategoria);
    } else {
      element.classList.add("hide");
    }
  });
}

//funcion del boton ver carrito alternadamente
/*
function verCarrito() {
  const carrito = document.getElementById("Carrito");
  const productosWrapper = document.querySelector(".wrapper");

  // Alterna la clase activa del carrito
  carrito.classList.toggle("visible");

  // Agrega o elimina la clase para ajustar el margen de los productos
  if (carrito.classList.contains("visible")) {
    productosWrapper.classList.add("carrito-visible");
    const carritoUsr = localStorage.getItem("carrito");
    fetchArticulos(carritoUsr.id);
  } else {
    productosWrapper.classList.remove("carrito-visible");
  }
}
  */

function verCarrito() {
  const carrito = document.getElementById("Carrito");
  const productosWrapper = document.querySelector(".wrapper");

  // Muestra el carrito
  carrito.classList.add("visible");

  // Ajusta la clase de los productos para dar espacio al carrito
  productosWrapper.classList.add("carrito-visible");

}

function cerrarCarrito() {
  const carrito = document.getElementById("Carrito");
  const productosWrapper = document.querySelector(".wrapper");

  // Oculta el carrito
  carrito.classList.remove("visible");

  // Ajusta la clase de los productos
  productosWrapper.classList.remove("carrito-visible");
}


async function agregarAlCarrito(productoId, carritoId) {
  try {
    // Recuperar el carrito desde el localStorage
    const carritoData = JSON.parse(localStorage.getItem("carrito"));
    if (!carritoData || !carritoData.id) {
      alert(
        "No se encontró un carrito activo. Por favor, inicia sesión nuevamente."
      );
      return;
    }

    // Crear el objeto del nuevo artículo
    const nuevoArticulo = {
      cantidad_Articulo: 1, // Cantidad por defecto
      producto: { id: productoId }, // Referencia al producto
      carrito: { id: carritoId }, // Referencia al carrito
    };

    // Hacer una solicitud POST para registrar el nuevo artículo
    const response = await fetch(`${apiArtUrl}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(nuevoArticulo),
    });

    // Verificar si el POST fue exitoso
    if (!response.ok) {
      const errorText = await response.text(); // Leer el mensaje de error del servidor
      throw new Error(`Error al agregar el artículo: ${errorText}`);
    }

    // Obtener la respuesta del servidor
    const data = await response.json();
    console.log("Respuesta del servidor:", data);
    
    fetchProductos().then((productos) => {  
      if (productos) {
        recuperarArticulos(productos);
      } else {
        console.error("No se pudo obtener la lista de productos.");
      }
    });
    
    // Actualizar visualización del carrito (opcional)
    verCarrito();
  } catch (error) {
    alert(
      "Ocurrió un error al agregar el producto al carrito. Intenta de nuevo."
    );
    console.error("Error al agregar el producto al carrito:", error);
  }
}

function cargarArticulos(producto, articulos, id_carrito) {
  // Información del producto
  const { nombre_producto, imagen, precio, id } = producto;

  // Filtrar artículos que coincidan con el producto y el carrito actual
  const articulosFiltrados = articulos.filter(
    (articulo) =>
      articulo.producto.id === id && articulo.carrito.id === id_carrito
  );

  // Crear tarjetas para los artículos filtrados
  articulosFiltrados.forEach(() => {
    // Crear contenedor principal de la carta
    const carta = document.createElement("div");
    carta.classList.add("carta-carrito");

    // Contenedor de la imagen
    const imgContainer = document.createElement("div");
    imgContainer.classList.add("image-container");

    const imagenTag = document.createElement("img");
    imagenTag.setAttribute("src", imagen); // Usar la URL de la imagen
    imgContainer.appendChild(imagenTag);

    // Añadir el contenedor de la imagen a la carta
    carta.appendChild(imgContainer);

    // Contenedor de información
    const container = document.createElement("div");
    container.classList.add("container");

    // Nombre del producto
    const nombre = document.createElement("h5");
    nombre.classList.add("articulo-nombre");
    nombre.innerText = nombre_producto.toUpperCase();
    container.appendChild(nombre);

    // Precio del producto
    const precioTag = document.createElement("p");
    precioTag.classList.add("articulo-precio");
    precioTag.innerText = `$${precio.toFixed(2)}`;
    container.appendChild(precioTag);

    // Añadir el contenedor de información a la carta
    carta.appendChild(container);

    // Añadir la carta al contenedor principal del carrito
    document.getElementById("cont-carrito").appendChild(carta);
  });
}
