// URL de la API para tomar los datos
const apiProdUrl = "http://localhost:8080/api/productos";
const apiArtUrl = "http://localhost:8080/api/articulos";
const apiUsrUrl = "http://localhost:8080/api/usuarios";

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
  document.getElementById("IniciarSesion").classList.add("visible");
  document.getElementById("PaginaPrincipal").classList.remove("visible");
}

function mostrarPrincipal() {
  document.getElementById("IniciarSesion").classList.remove("visible");
  document.getElementById("PaginaPrincipal").classList.add("visible");
  fetchProductos();
}

// Función para consumir la API y tomar los usuarios

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


function fetchArticulos() {
  fetch(apiArtUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      // Verifica si `data` es un array directamente o está dentro de otra propiedad
      const articulos = Array.isArray(data) ? data : data.datos;
      let productos = fetchProductos();
      productos.forEach((producto) => {
        cargarArticulos(producto, articulos);
      });
    });
}

function fetchProductos() {
  fetch(apiProdUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      // Verifica si `data` es un array directamente o está dentro de otra propiedad
      const productos = Array.isArray(data) ? data : data.datos;

      // Verifica si es iterable
      if (!Array.isArray(productos)) {
        throw new Error("La respuesta no contiene un array de productos.");
      }

      tomarProductos(productos);
    })
    .catch((error) => console.error("Error al obtener los productos:", error));
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

    if (usuarioEncontrado) {
      console.log("Usuario autenticado:", usuarioEncontrado);

      const login = document.getElementById("IniciarSesion");
      login.classList.remove("visible");

      // Mostrar la pagina principal
      const principal = document.getElementById("PaginaPrincipal");
      principal.classList.add("visible");
      filtrarProductos("Todo");
      buscar("Todo");

      localStorage.setItem("usuario", JSON.stringify({ correo }));

      // Cargar productos
      fetchProductos();
    } else {
      console.warn("Credenciales incorrectas.");
      alert("Correo o contraseña incorrectos. Intenta de nuevo.");
    }
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
    alert("Ocurrió un error al intentar iniciar sesión. Intenta nuevamente.");
  }
}
function cerrarSesion() {
  localStorage.removeItem("usuario");
  mostrarLogin();
}

// se llama a la funcion para cargar los datos

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
    botonCarrito.innerHTML =
      "<box-icon name='cart-download' type='solid' color='rgba(255,255,255,0.98)' ></box-icon>"; // Ícono de carrito
    botonCarrito.onclick = function () {
      agregarAlCarrito(producto.id, carrito.id);
    };
    container.appendChild(botonCarrito);

    carta.appendChild(container);

    document.getElementById("productos").appendChild(carta);
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

//funcion del boton ver carrito
function verCarrito(productos) {
  const carrito = document.getElementById("Carrito");
  const productosWrapper = document.querySelector(".wrapper");

  // Alterna la clase activa del carrito
  carrito.classList.toggle("visible");

  // Agrega o elimina la clase para ajustar el margen de los productos
  if (carrito.classList.contains("visible")) {
    productosWrapper.classList.add("carrito-visible");
    fetchArticulos(productos);
  } else {
    productosWrapper.classList.remove("carrito-visible");
  }
}

function cargarArticulos(producto, articulos, id_carrito) {
  const nombreArt = producto.nombre_producto;
  const imagenArt = producto.imagen;
  const precioArt = producto.precio;

  for (let articulo of articulos) {
    if (
      articulo.id_producto == producto.id &&
      articulo.id_carrito == carrito.id
    ) {
      //imagenes de cada division
      let imgContainer = document.createElement("div");
      imgContainer.classList.add("image-container");
      //tag de la imagen
      let imagen = document.createElement("img");
      imagen.setAttribute("src", imagenArt);
      imgContainer.appendChild(imagen);
      carta.appendChild(imgContainer);

      //contenedor
      let container = document.createElement("div");
      container.classList.add("container");

      //nombre del producto
      let nombre = document.createElement("h5");
      nombre.classList.add("articulo-nombre");
      nombre.innerText = nombreArt.toUpperCase();
      container.appendChild(nombre);

      // Precio
      let precio = document.createElement("p");
      precio.classList.add("articulo-precio");
      precio.innerText = `$${precioArt.toFixed(2)}`;
      container.appendChild(precio);
      carta.appendChild(container);

      document.getElementById("cont-carrito").appendChild(articulo);
    }
  }
}
