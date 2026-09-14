// main.js

// Inicializar el carrito buscando datos guardados, o crear un array vacío
let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

// Función global para actualizar el número del ícono del carrito en el header
function actualizarContadorCarrito() {
    const contador = document.getElementById('cart-count');
    if (contador) {
        contador.innerText = carrito.length;
    }
}

// Ejecutar cuando el documento HTML esté completamente cargado
document.addEventListener('DOMContentLoaded', () => {
    
    // 1. Actualizar el contador apenas carga cualquier página
    actualizarContadorCarrito();

    // 2. Lógica para la vista del Producto (producto.html)
    const btnAgregar = document.getElementById('btn-agregar-carrito');
    if (btnAgregar) {
        btnAgregar.addEventListener('click', () => {
            const colorSeleccionado = document.getElementById('color').value;
            const talleSeleccionado = document.getElementById('talle').value;
            
            // Crear el objeto producto
            const nuevoProducto = {
                id: Date.now(), // ID temporal único
                nombre: 'Remera Oficial ECMZ',
                color: colorSeleccionado,
                talle: talleSeleccionado,
                precio: 15000 // Precio de ejemplo
            };

            // Guardar en el array y actualizar localStorage
            carrito.push(nuevoProducto);
            localStorage.setItem('carrito', JSON.stringify(carrito));
            
            actualizarContadorCarrito();
            alert('¡Producto agregado al carrito!');
        });
    }

    // 3. Lógica para el formulario de Donación (donacion.html)
    const formPago = document.getElementById('form-pago');
    if (formPago) {
        formPago.addEventListener('submit', (e) => {
            e.preventDefault(); // Evita que la página se recargue
            
            // Aquí capturarías los datos (DNI, Tarjeta, etc.) para enviarlos a tu backend en Java
            const dni = document.getElementById('dni-estudiante').value;
            
            console.log(`Procesando donación para el DNI: ${dni}`);
            alert('¡Gracias por tu aporte para la escuela!');
            
            formPago.reset();
        });
    }

    // 4. Lógica para el formulario de Registro (registro.html)
    const formRegistro = document.getElementById('form-registro');
    if (formRegistro) {
        formRegistro.addEventListener('submit', (e) => {
            e.preventDefault();
            
            // Ideal para conectar con tus REST controllers más adelante
            const nombre = document.getElementById('nombre').value;
            const email = document.getElementById('email').value;
            
            console.log(`Registrando nuevo usuario: ${nombre} - ${email}`);
            alert('¡Registro exitoso! Ya podés iniciar sesión.');
            
            formRegistro.reset();
        });
    }
});