// Coopera.js

// Inicializar el carrito buscando datos guardados, o crear un array vacío
let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

// Número de WhatsApp de la Cooperadora.
// Formato: 54 (Argentina) + 9 (celular) + 261 (código de área) + número, todo junto, sin espacios.
const WHATSAPP_COOPERADORA = '5492612792001';

// Función global para actualizar el número del ícono del carrito en el header
function actualizarContadorCarrito() {
    const contador = document.getElementById('cart-count');
    if (contador) {
        contador.innerText = carrito.length;
    }
}

// ---------------------------------------------------------------
// Catálogo: los productos, precios por talle e imágenes salen de la
// base de datos a través de GET /api/Producto
// ---------------------------------------------------------------
const IMG_PLACEHOLDER = '🛍️';
let productoActual = null; // producto que se está viendo en Producto.html

function esc(texto) {
    return String(texto ?? '').replace(/[&<>"']/g,
        c => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[c]));
}

function formatearPrecio(valor) {
    return '$' + Number(valor).toLocaleString('es-AR');
}

// "images/campera.png" (como está en la BD) -> "/images/campera.png"
function urlImagen(url) {
    return /^(https?:)?\/\//.test(url) || url.startsWith('/') ? url : '/' + url;
}

async function obtenerProductos() {
    const respuesta = await fetch('/api/Producto');
    if (!respuesta.ok) {
        throw new Error('No se pudo leer /api/Producto (' + respuesta.status + ')');
    }
    return respuesta.json();
}

// Crea un <img>; si el archivo no existe, muestra un ícono en su lugar
function crearImagen(url, alt, clase) {
    const img = document.createElement('img');
    img.className = clase;
    img.alt = alt;
    img.src = urlImagen(url);
    img.addEventListener('error', () => {
        const ph = document.createElement('span');
        ph.className = 'img-placeholder';
        ph.textContent = IMG_PLACEHOLDER;
        img.replaceWith(ph);
    });
    return img;
}

function textoPrecioDesde(producto) {
    const valores = (producto.precios || []).map(x => x.precio);
    if (!valores.length) return '';
    const minimo = Math.min(...valores);
    const hayVarios = Math.max(...valores) !== minimo;
    return (hayVarios ? 'Desde ' : '') + formatearPrecio(minimo);
}

// Tarjeta de un producto (Index y lista de Producto.html). Lleva a Producto.html?id=...
function crearTarjetaProducto(p) {
    const card = document.createElement('div');
    card.className = 'card-producto';

    const contImg = document.createElement('div');
    contImg.className = 'img-producto';
    if (p.url) {
        contImg.appendChild(crearImagen(p.url, p.nombre, 'img-producto-foto'));
    } else {
        contImg.textContent = IMG_PLACEHOLDER;
    }

    const titulo = document.createElement('h4');
    titulo.textContent = p.nombre;

    const precio = document.createElement('p');
    precio.className = 'precio-desde';
    precio.textContent = textoPrecioDesde(p);

    const enlace = document.createElement('a');
    enlace.href = 'Producto.html?id=' + p.id_producto;
    const boton = document.createElement('button');
    boton.className = 'btn-ver';
    boton.textContent = 'Ver detalle';
    enlace.appendChild(boton);

    card.append(contImg, titulo, precio, enlace);
    return card;
}

function llenarGrilla(grilla, productos) {
    if (!productos.length) {
        grilla.textContent = 'Todavía no hay productos cargados.';
        return;
    }
    grilla.replaceChildren(...productos.map(crearTarjetaProducto));
}

// Muestra la imagen grande del producto
function mostrarImagenPrincipal(url, nombre) {
    const cont = document.getElementById('imagen-principal');
    if (url) {
        cont.replaceChildren(crearImagen(url, nombre, 'img-detalle'));
    } else {
        cont.textContent = IMG_PLACEHOLDER;
    }
}

function actualizarPrecioDetalle() {
    const talle = document.getElementById('talle').value;
    const fila = (productoActual.precios || []).find(x => x.talle === talle);
    document.getElementById('precio-producto').textContent =
        fila ? 'Precio: ' + formatearPrecio(fila.precio) : 'Precio no disponible';
}

// Dibuja la página de detalle con los datos del producto elegido
function mostrarDetalle(p) {
    productoActual = p;
    document.title = 'Cooperadora ECMZ - ' + p.nombre;
    document.getElementById('producto-nombre').textContent = p.nombre;
    document.getElementById('producto-descripcion').textContent = p.descripcion || '';

    // Imagen: sale de la columna url de la tabla producto
    mostrarImagenPrincipal(p.url, p.nombre);

    // Talles y precio: salen de la tabla "precio"
    const precios = p.precios || [];
    const selectTalle = document.getElementById('talle');
    selectTalle.replaceChildren(...precios.map(x => new Option(x.talle, x.talle)));
    document.getElementById('campo-talle').hidden = precios.length <= 1; // un solo talle: no hace falta elegir
    selectTalle.onchange = actualizarPrecioDetalle;
    actualizarPrecioDetalle();

    // Stock
    const btn = document.getElementById('btn-agregar-carrito');
    const sinStock = (p.stock ?? 0) <= 0;
    document.getElementById('stock-producto').textContent = sinStock ? 'Sin stock por el momento' : '';
    btn.disabled = sinStock || !precios.length;
}

// Texto "(talle M)" para el carrito; tolera ítems viejos que tenían color
function detalleItem(p) {
    const partes = [];
    if (p.color) partes.push(p.color);
    if (p.talle && p.talle !== 'Estándar') partes.push('talle ' + p.talle);
    return partes.length ? ` (${partes.join(', ')})` : '';
}

// Arma el texto del pedido, agrupando los ítems repetidos (2 x Remera...)
function construirMensajeWhatsApp(datos) {
    const grupos = {};
    carrito.forEach(p => {
        const clave = `${p.nombre}|${p.color}|${p.talle}|${p.precio}`;
        grupos[clave] = grupos[clave] || { ...p, cantidad: 0 };
        grupos[clave].cantidad++;
    });
    const lineas = Object.values(grupos).map(g =>
        `• ${g.cantidad} x ${g.nombre}${detalleItem(g)} - $${g.precio * g.cantidad}`);
    const total = carrito.reduce((t, p) => t + p.precio, 0);

    return [
        'Hola! Quiero hacer un pedido desde la web de la Cooperadora ECMZ:',
        '',
        ...lineas,
        '',
        `Total: $${total}`,
        '',
        `Familia: ${datos.tutor}`,
        `Alumno/a: ${datos.alumno} (${datos.curso})`,
        '',
        'Quedo a la espera para coordinar el pago. Gracias!'
    ].join('\n');
}

// Ejecutar cuando el documento HTML esté completamente cargado
document.addEventListener('DOMContentLoaded', () => {

    // 1. Actualizar el contador apenas carga cualquier página
    actualizarContadorCarrito();

    // 2. Catálogo y detalle de productos (datos desde la base de datos)

    // 2a. Index.html: tarjetas de productos
    const grillaIndex = document.getElementById('grid-productos');
    if (grillaIndex) {
        obtenerProductos()
            .then(ps => llenarGrilla(grillaIndex, ps))
            .catch(() => { grillaIndex.textContent = 'No se pudieron cargar los productos.'; });
    }

    // 2b. Producto.html: sin ?id= muestra la lista; con ?id=4 muestra ese producto
    const seccionCatalogo = document.getElementById('catalogo');
    const seccionDetalle = document.getElementById('detalle-producto');
    if (seccionCatalogo && seccionDetalle) {
        const id = new URLSearchParams(window.location.search).get('id');
        obtenerProductos()
            .then(productos => {
                const elegido = id && productos.find(x => String(x.id_producto) === id);
                if (elegido) {
                    seccionDetalle.hidden = false;
                    mostrarDetalle(elegido);
                } else {
                    seccionCatalogo.hidden = false;
                    llenarGrilla(document.getElementById('grid-catalogo'), productos);
                }
            })
            .catch(() => {
                seccionCatalogo.hidden = false;
                document.getElementById('grid-catalogo').textContent = 'No se pudieron cargar los productos.';
            });
    }

    // 2c. Botón "Agregar al carrito" del detalle
    const btnAgregar = document.getElementById('btn-agregar-carrito');
    if (btnAgregar) {
        btnAgregar.addEventListener('click', () => {
            if (!productoActual) return;
            const talle = document.getElementById('talle').value;
            const fila = (productoActual.precios || []).find(x => x.talle === talle);
            if (!fila) {
                alert('Este producto todavía no tiene precio cargado.');
                return;
            }

            carrito.push({
                id: Date.now(), // ID temporal único
                id_producto: productoActual.id_producto,
                nombre: productoActual.nombre,
                talle: talle,
                precio: fila.precio
            });
            localStorage.setItem('carrito', JSON.stringify(carrito));

            actualizarContadorCarrito();
            alert('¡Producto agregado al carrito!');
        });
    }

    // 3. Lógica para el formulario de Donación (Donacion.html)
    const btnDonar = document.getElementById('btn-donar-whatsapp');
    if (btnDonar) {
        btnDonar.addEventListener('click', () => {
            const tutor = document.getElementById('nombre-tutor').value.trim();
            const alumno = document.getElementById('nombre-alumno').value.trim();
            const curso = document.getElementById('curso-alumno').value.trim();
            const monto = document.getElementById('monto-donacion').value.trim();

            if (!tutor || !alumno || !curso) {
                alert('Completá tu nombre, el del alumno/a y el curso.');
                return;
            }

            const mensaje = [
                'Hola! Quiero donar' + (monto ? ` $${monto}` : '') + ' a la Cooperadora ECMZ.',
                '',
                `Familia: ${tutor}`,
                `Alumno/a: ${alumno} (${curso})`,
                '',
                'Quedo a la espera para coordinar la donación. Gracias!'
            ].join('\n');

            const url = `https://wa.me/${WHATSAPP_COOPERADORA}?text=${encodeURIComponent(mensaje)}`;
            const ventana = window.open(url, '_blank');
            if (!ventana) window.location.href = url; // si el navegador bloquea la ventana nueva
        });
    }

    // 4. Lógica para el formulario de Registro (Registro.html)
    const formRegistro = document.getElementById('form-registro');
    if (formRegistro) {
        formRegistro.addEventListener('submit', (e) => {
            e.preventDefault();

            const nombre = document.getElementById('nombre').value;
            const email = document.getElementById('email').value;

            console.log(`Registrando nuevo usuario: ${nombre} - ${email}`);
            alert('¡Registro exitoso! Ya podés iniciar sesión.');

            formRegistro.reset();
        });
    }

    // 5. Lógica para la página del Carrito (carrito.html)
    const listaCarrito = document.getElementById('lista-carrito');
    if (listaCarrito) {
        const render = () => {
            listaCarrito.innerHTML = carrito.length
                ? carrito.map(p => `<div class="item-carrito"><span>${esc(p.nombre)}${esc(detalleItem(p))}</span> <span>$${p.precio}</span></div>`).join('')
                : '<p>Tu carrito está vacío.</p>';
            document.getElementById('total-carrito').innerText =
                'Suma Total: $' + carrito.reduce((t, p) => t + p.precio, 0);
            actualizarContadorCarrito();
        };

        // Vaciar carrito
        document.getElementById('btn-vaciar').addEventListener('click', () => {
            carrito = [];
            localStorage.setItem('carrito', JSON.stringify(carrito));
            render();
        });

        // Enviar pedido por WhatsApp
        document.getElementById('btn-whatsapp').addEventListener('click', () => {
            if (!carrito.length) {
                alert('Tu carrito está vacío.');
                return;
            }
            const datos = {
                tutor: document.getElementById('nombre-tutor').value.trim(),
                alumno: document.getElementById('nombre-alumno').value.trim(),
                curso: document.getElementById('curso-alumno').value.trim()
            };
            if (!datos.tutor || !datos.alumno || !datos.curso) {
                alert('Completá tus datos para que la Cooperadora sepa a quién entregar el pedido.');
                return;
            }
            const url = `https://wa.me/${WHATSAPP_COOPERADORA}?text=${encodeURIComponent(construirMensajeWhatsApp(datos))}`;
            window.open(url, '_blank', 'noopener');
        });

        render();
    }
});