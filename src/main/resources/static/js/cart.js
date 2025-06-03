// /static/js/cart.js
document.addEventListener('DOMContentLoaded', function() {
    const cartIconCount = document.getElementById('cart-item-count');
    let cart = []; // Array para almacenar los ítems del carrito

    // --- Funciones de Carga y Guardado del Carrito ---
    function loadCart() {
        const storedCart = localStorage.getItem('cart');
        if (storedCart) {
            cart = JSON.parse(storedCart);
            updateCartIcon();
        }
    }

    function saveCart() {
        localStorage.setItem('cart', JSON.stringify(cart));
    }

    // --- Funciones de Actualización de UI ---
    function updateCartIcon() {
        const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
        cartIconCount.textContent = totalItems;
        // Muestra el contador solo si hay ítems
        cartIconCount.style.display = totalItems > 0 ? 'inline-block' : 'none';
    }

    // --- Lógica para Añadir al Carrito ---
    function addToCart(bookId, title, price, imageUrl) {
        const existingItemIndex = cart.findIndex(item => item.bookId === bookId);

        if (existingItemIndex > -1) {
            cart[existingItemIndex].quantity += 1;
        } else {
            cart.push({
                bookId: bookId,
                title: title,
                price: price,
                imageUrl: imageUrl,
                quantity: 1
            });
        }
        saveCart();
        updateCartIcon();
        alert(`"${title}" añadido al carrito!`);
    }

    // --- Event Listeners ---
    // Delegación de eventos para los botones "Añadir al carrito"
    // Esto es CLAVE: el listener se adjunta al body y funcionará para TODOS los elementos
    // con la clase 'add-to-cart-btn', sin importar cuándo fueron añadidos al DOM.
    document.body.addEventListener('click', function(event) {
        if (event.target.classList.contains('add-to-cart-btn')) {
            const button = event.target;
            // Asegúrate de que los data-atributos se lean correctamente
            const bookId = parseInt(button.dataset.bookId);
            const title = button.dataset.bookTitle;
            const price = parseFloat(button.dataset.bookPrice);
            const imageUrl = button.dataset.bookImage;

            if (!isNaN(bookId) && title && !isNaN(price)) {
                addToCart(bookId, title, price, imageUrl);
            } else {
                console.error('Datos del libro incompletos para añadir al carrito:', button.dataset);
                alert('No se pudo añadir el libro al carrito debido a datos faltantes.');
            }
        }
    });

    // --- Inicialización ---
    loadCart(); // Cargar el carrito al inicio de la página, una vez que el DOM está listo
});