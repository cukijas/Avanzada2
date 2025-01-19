// URL de la API para tomar los datos
const apiProdUrl = "http://localhost:8080/api/productos";
const apiArtUrl = "http://localhost:8080/api/articulos";
const apiUsrUrl = "http://localhost:8080/api/usuarios";
const apiCartUrl = "http://localhost:8080/api/carritos";
const apiPedUrl = "http://localhost:8080/api/pedidos";
const apiDetUrl = "http://localhost:8080/api/detalle_de_pedidos";

//tomo la pagina inicial para cambiar el fondo
const paginaInicial = document.querySelector("#PaginaInicial");

// Lista de imágenes
const imagenes = [
  "../images/fondo1.png",
  "../images/fondo2.png",
  "../images/fondo3.png",
  "../images/fondo4.png",
  "../images/fondo5.png",
  "../images/fondo6.png",
];

let indiceActual = 0;

// Cambia la imagen de fondo cada 5 segundos
setInterval(() => {
  const siguienteIndice = (indiceActual + 1) % imagenes.length;

  // Configura las imágenes actuales y siguientes
  paginaInicial.style.setProperty(
    "--current-image",
    `url(${imagenes[indiceActual]})`
  );
  paginaInicial.style.setProperty(
    "--next-image",
    `url(${imagenes[siguienteIndice]})`
  );

  // Activa la transición
  paginaInicial.classList.add("transition-active");

  // Espera a que termine la transición para preparar la siguiente
  setTimeout(() => {
    paginaInicial.classList.remove("transition-active");
    paginaInicial.style.setProperty(
      "--current-image",
      `url(${imagenes[siguienteIndice]})`
    );
    paginaInicial.style.setProperty("--next-image", "");
    indiceActual = siguienteIndice;
  }, 1000); // Duración de la transición
}, 5000); // Cambia cada 5 segundos

window.onload = () => {
  //establece el fondo al cargar la pagina
  paginaInicial.style.setProperty(
    "--current-image",
    `url(${imagenes[indiceActual]})`
  );
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
  document.getElementById("PaginaRegistro").classList.remove("centrada");
  document.getElementById("PaginaInicial").classList.add("centrada");
  document.getElementById("PaginaPrincipal").classList.remove("visible");
}

function mostrarRegistrar() {
  const PaginaLogin = document.getElementById("PaginaInicial");
  PaginaLogin.classList.remove("centrada");
  PaginaLogin.classList.remove("visible");
  document.getElementById("PaginaRegistro").classList.add("centrada");
}

function mostrarPrincipal() {
  document.getElementById("PaginaInicial").classList.remove("centrada");
  document.getElementById("PaginaPrincipal").classList.add("visible");
  document.getElementById("PaginaCompra").classList.remove("visible");
  document.getElementById("PaginaPedidos").classList.remove("visible");

  const totalContainer = document.getElementById("total");
  const TotalValor = document.createElement("div");
  TotalValor.classList.add("total-articulos");
  TotalValor.id = "total-valor";
  totalContainer.appendChild(TotalValor);

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
      const productos = Array.isArray(data) ? data : data.datos || [];

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
    const carritos = Array.isArray(responseData)
      ? responseData
      : responseData.datos;

    if (!Array.isArray(carritos)) {
      throw new Error("La respuesta no contiene un arreglo de carritos.");
    }

    // Filtrar el carrito del usuario específico
    const carritoUsuario = carritos.find(
      (carrito) => carrito.usuario && carrito.usuario.id === id_usuario
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

async function fetchPedidos() {
  try {
    const response = await fetch(apiPedUrl);
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }
    const responseData = await response.json();
    const pedidos = Array.isArray(responseData)
      ? responseData
      : responseData.datos;

    if (!Array.isArray(pedidos)) {
      throw new Error("La respuesta no contiene un arreglo de pedidos.");
    }

    return pedidos;
  } catch (error) {
    console.error("Error al obtener los pedidos:", error);
    return null;
  }
}

async function fetchDetallesPedido() {
  try {
    const response = await fetch(apiDetUrl);
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }
    const responseData = await response.json();
    const detalles = Array.isArray(responseData)
      ? responseData
      : responseData.datos;

    if (!Array.isArray(detalles)) {
      throw new Error(
        "La respuesta no contiene un arreglo de detalles de pedido."
      );
    }

    return detalles;
  } catch (error) {
    console.error("Error al obtener los detalles de pedido:", error);
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
      return;
    }
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
    alert("Ocurrió un error al intentar iniciar sesión. Intenta nuevamente.");
    window.location.reload();
  }
}

function cerrarSesion() {
  localStorage.removeItem("usuario");
  localStorage.removeItem("carrito");
  localStorage.removeItem("articulo-encontrado");
  mostrarLogin();
}

function registrarUsuario() {
  const nombre = document.getElementById("txtNombreR").value.trim();
  const direccion = document.getElementById("txtDireccionR").value.trim();
  const correo = document.getElementById("txtCorreoR").value.trim();
  const contrasena = document.getElementById("txtContrasenaR").value.trim();

  if (!correo || !contrasena) {
    alert("Por favor, completa todos los campos.");
    return;
  } else {
    fetchUsuarios().then((usuarios) => {
      if (!usuarios) {
        console.error("No se pudo obtener la lista de usuarios.");
        return; // Salir si no se cargan los usuarios
      }
      // Buscar el usuario en la lista
      const usuarioRegistrado = usuarios.find(
        (usuario) => usuario.correo === correo
      );
      if (usuarioRegistrado) {
        alert(
          "El correo ingresado ya está registrado. Intenta con otro correo."
        );
        return;
      } else {
        const nuevoUsuario = {
          direccion: direccion,
          contra: contrasena,
          nombre: nombre,
          correo: correo,
        };

        fetch(apiUsrUrl, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(nuevoUsuario),
        })
          .then((response) => {
            if (!response.ok) {
              throw new Error(`Error HTTP: ${response.status}`);
            }
            return response.json();
          })
          .then((data) => {
            console.log("Usuario registrado:", data);
            // Guardar información del usuario en localStorage
            localStorage.setItem(
              "usuario",
              JSON.stringify({
                id: data.id,
                correo: data.correo,
              })
            );
            alert("Usuario registrado exitosamente.");
            asignarCarrito(data.id);
            localStorage.removeItem("usuario");
            //mostrarAlerta();
          })
          .catch((error) => {
            alert(
              "Ocurrió un error al registrar el usuario. Intenta de nuevo."
            );
            console.error("Error al registrar el usuario:", error);
          });
      }
    });
  }
}

function mostrarAlerta() {
  const ventanaRegistro = document.getElementById("PaginaRegistro");
  const alerta = document.getElementById("alerta");

  ventanaRegistro.classList.add("fondo");

  // Muestra la alerta
  alerta.classList.remove("ocultar");
  alerta.classList.add("mostrar");
}

function asignarCarrito(id_usuario) {
  const nuevoCarrito = {
    usuario: { id: id_usuario },
  };
  fetch(apiCartUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(nuevoCarrito),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Carrito asignado:", data);
      //mostrarAlerta();
    })
    .catch((error) => {
      alert("Ocurrió un error al asignar el carrito. Intenta de nuevo.");
      console.error("Error al asignar el carrito:", error);
    });
}

function recuperarArticulos(productos) {
  const carrito = JSON.parse(localStorage.getItem("carrito"));
  fetchArticulos()
    .then((articulos) => {
      if (articulos) {
        let total = 0;
        const panelCarrito = document.getElementById("cont-carrito");
        panelCarrito.innerHTML = ""; // Limpiar el contenedor antes de renderizar

        const totalContainer = document.getElementById("total-valor");
        totalContainer.innerHTML = ""; // Limpiar el contenedor del total antes de agregar el nuevo valor

        productos.forEach((producto) => {
          cargarArticulos(producto, articulos, carrito.id);
          total += calcularTotal(producto, articulos, carrito.id);
        });

        totalContainer.innerHTML = `$${total.toFixed(2)}`;
        localStorage.setItem("total", total);
        //alert("Artículos recuperados correctamente.");
      } else {
        console.error("No se pudo obtener la lista de artículos.");
        alert("Ocurrió un error al recuperar los artículos. Intenta de nuevo.");
      }
    })
    .catch((error) => {
      alert("Ocurrió un error al recuperar los artículos. Intenta de nuevo.");
      console.error("Error al obtener los artículos:", error);
      return null; // Devolver null en caso de error
    });
}

//toma los productos y genera una carta por cada uno
function tomarProductos(productos) {
  // Obtén el contenedor donde se mostrarán los productos
  const fragmentoProductos = document.createDocumentFragment();
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
      console.log("Producto agregado al carrito.");
      const productoId = botonCarrito.getAttribute("data-id"); // Obtener ID del producto desde el botón
      const carritoId = JSON.parse(localStorage.getItem("carrito")).id; // ID del carrito almacenado
      verificarArticulo(productoId, carritoId).then((existe) => {
        if (existe) {
          //alert("El artículo ya está en el carrito. Aumentando cantidad...");
          const articuloEncontrado = JSON.parse(
            localStorage.getItem("articulo-encontrado")
          );
          const nuevaCantidad = articuloEncontrado.cantidad_Articulo + 1;
          actualizarCantidadArticulo(
            articuloEncontrado.id_articulo,
            nuevaCantidad
          );
        } else {
          agregarAlCarrito(productoId, carritoId);
        }
        verCarrito();
      });
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

function verPedidos() {
  document.getElementById("PaginaPrincipal").classList.remove("visible");
  document.getElementById("PaginaPedidos").classList.add("visible");
  fetchPedidos().then((pedidos) => {
    if (pedidos) {
      const usuario = JSON.parse(localStorage.getItem("usuario"));
      const pedidosUsuario = pedidos.filter(
        (pedido) => pedido.usuario.id === usuario.id
      );
      console.log("Pedidos del usuario:", pedidosUsuario);
      cargarPedidos(pedidosUsuario);
    } else {
      console.error("No se pudo obtener la lista de pedidos.");
    }
  });
}

function cargarPedidos(pedidos) {
  // Selecciona el <tbody> de la tabla
  const tbody = document.querySelector("table tbody");
  if (!tbody) {
    console.error("No se encontró el elemento <tbody> en el DOM.");
    return;
  }

  // Limpia el contenido previo del <tbody>
  tbody.innerHTML = "";

  // Recorre los pedidos y genera las filas dinámicamente
  pedidos.forEach((pedido) => {
    const fila = document.createElement("tr");
    fila.innerHTML = `
      <td>$${pedido.total}</td>
      <td>${pedido.fecha}</td>
      <td>
        <span class="badge bg-${
          pedido.estado === "Entregado"
            ? "success"
            : pedido.estado === "Enviado"
            ? "warning text-dark"
            : "danger"
        }">
          ${pedido.estado}
        </span>
      </td>
    `;
    fila.addEventListener("click", () => {
      console.log("Pedido seleccionado:", pedido);
      mostrarDetallePedido(pedido.id);
    });
    tbody.appendChild(fila);
  });
}

function mostrarDetallePedido(id_pedido) {
  fetchDetallesPedido().then((detalles) => {
    if (detalles) {
      const detallesPedido = detalles.filter(
        (detalle) => detalle.pedido.id === id_pedido
      );
      console.log("Detalles del pedido:", detallesPedido);
      const contenedor = document.querySelector("#tabla-detalle tbody");
      contenedor.innerHTML = ""; // Clear previous content
      detallesPedido.forEach((detalle) => {
        const fila = document.createElement("tr");
        fila.innerHTML = `
        <td>$${detalle.precio_de_envio}</td>
        <td>${detalle.cantidad_de_Articulo}</td>
        <td>$${detalle.total_por_Articulo}</td>
        `;
        contenedor.appendChild(fila);
      });
    } else {
      console.error("No se pudo obtener la lista de detalles de pedido.");
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

  fetchProductos().then((productos) => {
    if (productos) {
      recuperarArticulos(productos);
    } else {
      console.error("No se pudo obtener la lista de productos.");
    }
  });
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
  //const fragmentoArticulos = document.createDocumentFragment();
  const contenidoCarrrito = document.getElementById("cont-carrito");

  // Crear tarjetas para los artículos filtrados
  articulosFiltrados.forEach((articulo) => {
    // Crear contenedor principal de la carta
    const carta = document.createElement("div");
    carta.classList.add("carta-articulo");

    // Contenedor de la imagen
    const imgContainer = document.createElement("div");
    imgContainer.classList.add("containedor-imagen");

    const imagenTag = document.createElement("img");
    imagenTag.setAttribute("src", imagen); // Usar la URL de la imagen
    imagenTag.classList.add("imagen-articulo");
    imgContainer.appendChild(imagenTag);

    // Añadir el contenedor de la imagen a la carta
    carta.appendChild(imgContainer);

    // Contenedor de información
    const container = document.createElement("div");
    container.classList.add("contenedor-articulo");

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

    // Contenedor para cantidad y botón de eliminar
    const cantidadYEliminarContainer = document.createElement("div");
    cantidadYEliminarContainer.classList.add("cantidad-eliminar-container");

    // Cantidad del producto
    const cantidad = document.createElement("input");
    cantidad.setAttribute("type", "number");
    cantidad.setAttribute("min", "1");
    cantidad.setAttribute("value", articulo.cantidad_Articulo);
    cantidad.classList.add("articulo-cantidad");
    cantidad.setAttribute("data-id", articulo.id_articulo);
    cantidad.addEventListener("change", (event) => {
      const nuevaCantidad = event.target.value;
      const id = cantidad.getAttribute("data-id");
      actualizarCantidadArticulo(id, nuevaCantidad);
      fetchProductos().then((productos) => {
        if (productos) {
          recuperarArticulos(productos);
        } else {
          console.error("No se pudo obtener la lista de productos.");
        }
      });
    });
    cantidadYEliminarContainer.appendChild(cantidad);

    //Botón para eliminar el artículo
    const botonEliminar = document.createElement("button");
    botonEliminar.classList.add("eliminar-articulo");
    botonEliminar.innerHTML = '<i class="bx bx-trash"></i>';
    botonEliminar.addEventListener("click", () => {
      eliminarArticulo(articulo.id_articulo); // Llama la función de eliminar
    });
    cantidadYEliminarContainer.appendChild(botonEliminar);

    // Añadir el contenedor de cantidad y eliminar a la carta
    container.appendChild(cantidadYEliminarContainer);

    // Añadir el contenedor de información a la carta
    carta.appendChild(container);

    // Añadir la carta al contenedor principal del carrito
    contenidoCarrrito.appendChild(carta);
  });
}

function calcularTotal(producto, articulos, id_carrito) {
  const { precio, id } = producto;
  let subtotal = 0;
  if (!articulos || !Array.isArray(articulos)) {
    return 0;
  }
  articulos.forEach((articulo) => {
    if (id_carrito === articulo.carrito.id && id === articulo.producto.id) {
      subtotal += precio * articulo.cantidad_Articulo;
    }
  });
  return subtotal;
}

async function verificarArticulo(id_producto, id_carrito) {
  try {
    // Validar parámetros
    if (!id_producto || !id_carrito) {
      throw new Error("id_producto o id_carrito no son válidos.");
    }

    const response = await fetch(`${apiArtUrl}`);
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }

    const responseData = await response.json();
    console.log("Estructura de la respuesta del servidor:", responseData);

    // Verificar si la respuesta contiene un arreglo válido
    const articulos = Array.isArray(responseData)
      ? responseData
      : responseData?.datos || [];
    if (!Array.isArray(articulos)) {
      throw new Error(
        "La respuesta del servidor no es un arreglo de artículos."
      );
    }

    // Buscar el artículo con las condiciones dadas
    const articuloEncontrado = articulos.find(
      (articulo) =>
        articulo &&
        articulo.producto &&
        String(articulo.producto.id) === String(id_producto) &&
        articulo.carrito &&
        String(articulo.carrito.id) === String(id_carrito)
    );

    console.log("Artículo encontrado:", articuloEncontrado);

    if (articuloEncontrado) {
      //alert("El artículo ya está en el carrito.");
      localStorage.setItem(
        "articulo-encontrado",
        JSON.stringify(articuloEncontrado)
      );
      return true;
    } else {
      //alert("El artículo no está en el carrito.");
      return false;
    }
  } catch (error) {
    alert("Ocurrió un error al verificar el artículo. Intenta de nuevo.");
    console.error("Error al verificar el artículo:", error);
    return false; // Manejar errores correctamente
  }
}

function actualizarCantidadArticulo(id, nuevaCantidad) {
  const articulo = {
    cantidad_Articulo: nuevaCantidad,
  };

  fetch(`${apiArtUrl}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(articulo),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Artículo actualizado:", data);
      fetchProductos().then((productos) => {
        if (productos) {
          recuperarArticulos(productos);
        } else {
          console.error("No se pudo obtener la lista de productos.");
        }
      });
    })
    .catch((error) => {
      alert("Ocurrió un error al actualizar el artículo. Intenta de nuevo.");
      console.error("Error al actualizar el artículo:", error);
    });
}

function eliminarArticulo(id) {
  if (!id) {
    alert("El ID del artículo no es válido.");
    return;
  }
  fetch(`${apiArtUrl}/${id}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      // Si la respuesta tiene contenido
      if (response.status !== 204) {
        return response.json();
      }
    })
    .then(() => {
      fetchProductos().then((productos) => {
        if (productos) {
          recuperarArticulos(productos);
        } else {
          console.error("No se pudo obtener la lista de productos.");
        }
      });
    })
    .catch((error) => {
      alert("Ocurrió un error al eliminar el artículo. Intenta de nuevo.");
      console.error("Error al eliminar el artículo:", error);
    });
}

function comprar() {
  const total = localStorage.getItem("total");
  if (total <= 0) {
    alert("Añada un producto al carrito para poder finalizar su compra");
    return;
  } else {
    const totalValor = document.getElementById("total-valor-f");
    totalValor.innerHTML = "";
    totalValor.innerHTML = `$${total}`;

    document.getElementById("PaginaPrincipal").classList.remove("visible");
    document.getElementById("PaginaCompra").classList.add("visible");
    listarArticulos().then(() => {
      console.log("Artículos listados correctamente.");
    });
  }
}

async function listarArticulos() {
  try {
    // Espera a que se obtengan los artículos
    const articulos = await fetchArticulos();

    // Selecciona el <tbody> de la tabla
    const tbody = document.querySelector("#articulos-seleccionados");
    if (!tbody) {
      console.error("No se encontró el elemento <tbody> en el DOM.");
      return;
    }

    // Limpia el contenido previo del <tbody>
    tbody.innerHTML = "";

    // Recorre los artículos y genera las filas dinámicamente
    articulos.forEach((articulo) => {
      if (
        articulo.carrito.id === JSON.parse(localStorage.getItem("carrito")).id
      ) {
        const subTotal = articulo.producto
          ? articulo.producto.precio * articulo.cantidad_Articulo
          : "N/A";
        const fila = document.createElement("tr");
        fila.innerHTML = `
        <td>${
          articulo.producto
            ? articulo.producto.nombre_producto
            : "Producto no disponible"
        }</td>
        <td>${articulo.cantidad_Articulo}</td>
        <td>${articulo.producto ? `$${articulo.producto.precio}` : "N/A"}</td>
        <td>${subTotal !== "N/A" ? `$${subTotal}` : "N/A"}</td>
      `;

        // Agrega clases condicionales para resaltar casos específicos
        if (!articulo.producto) {
          fila.classList.add("text-danger"); // Resalta en rojo si el producto no está disponible
        }
        tbody.appendChild(fila);
        console.log("Artículo listado:", articulo);
      }
    });
  } catch (error) {
    console.error("Error al listar los artículos:", error);
  }
}

function finalizarCompra() {
  const usuario = JSON.parse(localStorage.getItem("usuario"));
  const total = localStorage.getItem("total");

  const fechaActual = obtenerFechaActual();
  const estadoInicial = "Pendiente";

  const nuevoPedido = {
    usuario: { id: usuario.id },
    total: total,
    fecha: fechaActual,
    estado: estadoInicial,
  };

  fetch(apiPedUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(nuevoPedido),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Pedido registrado:", data);
      encontrarArticulos().then((articulos) => {
        articulos.forEach((articulo) => {
          generarDetalle(articulo, data.id);
          eliminarArticulo(articulo.id_articulo);
        });
      });
      alert("Compra realizada con éxito.");
      mostrarPrincipal();
    })
    .catch((error) => {
      alert("Ocurrió un error al finalizar la compra. Intenta de nuevo.");
      console.error("Error al finalizar la compra:", error);
    });
}

function obtenerFechaActual() {
  const fecha = new Date();

  // Obtener el día, mes y año
  const dia = String(fecha.getDate()).padStart(2, "0"); // Asegura dos dígitos
  const mes = String(fecha.getMonth() + 1).padStart(2, "0"); // Mes comienza desde 0
  const anio = fecha.getFullYear();

  // Formatear la fecha en DD-MM-AAAA
  return `${dia}-${mes}-${anio}`;
}

function generarDetalle(articulo, id_pedido) {
  const precio_envio = 10.00;
  const total = articulo.cantidad_Articulo * articulo.producto.precio + precio_envio;
  console.log("Total por artículo:", total);

  const detalle = {
    cantidad_de_Articulo: articulo.cantidad_Articulo,
    precio_de_envio: precio_envio,
    total_por_Articulo: total,
    producto: { id: articulo.producto.id },
    pedido: { id: id_pedido },
  };

  fetch(apiDetUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(detalle),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error HTTP: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      console.log("Detalle registrado:", data);
    })
    .catch((error) => {
      console.error("Error al registrar el detalle:", error);
    });
}

async function encontrarArticulos() {
  const carrito = JSON.parse(localStorage.getItem("carrito"));

  try {
    const productos = await fetchProductos();
    if (!productos) {
      console.error("No se pudo obtener la lista de productos.");
      return [];
    }

    const articulos = await fetchArticulos();
    if (!articulos) {
      console.error("No se pudo obtener la lista de artículos.");
      return [];
    }

    const articulosFiltrados = articulos.filter(
      (articulo) =>
        articulo.carrito.id === carrito.id &&
        productos.some((producto) => producto.id === articulo.producto.id)
    );

    return articulosFiltrados;
  } catch (error) {
    console.error("Error al encontrar los artículos:", error);
    return [];
  }
}
