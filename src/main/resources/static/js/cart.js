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
        // Puedes cambiar esto por un modal o una notificación más sutil
        alert(`"${title}" añadido al carrito!`);
    }

    // --- Lógica para Eliminar del Carrito ---
    function removeFromCart(bookId) {
        cart = cart.filter(item => item.bookId !== bookId);
        saveCart();
        updateCartIcon();
        // Recargar la lista de ítems si estás en la página del carrito
        if (window.location.pathname === '/carrito') {
            displayCartItems();
        }
    }

    // --- Lógica para Actualizar Cantidad en Carrito ---
    function updateItemQuantity(bookId, newQuantity) {
        const item = cart.find(item => item.bookId === bookId);
        if (item) {
            if (newQuantity <= 0) {
                removeFromCart(bookId); // Si la cantidad es 0 o menos, eliminar
            } else {
                item.quantity = newQuantity;
                saveCart();
                updateCartIcon();
                // Recargar la lista de ítems si estás en la página del carrito
                if (window.location.pathname === '/carrito') {
                    displayCartItems();
                }
            }
        }
    }

    function clearCart() {
        cart = [];
        saveCart();
        updateCartIcon();
    }

    // --- Event Listeners para la página principal de la galería ---
    document.body.addEventListener('click', function(event) {
        if (event.target.classList.contains('add-to-cart-btn')) {
            const button = event.target;
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

    // Hacemos las funciones accesibles globalmente para usarlas en la página del carrito
    window.cartFunctions = {
        loadCart: loadCart,
        saveCart: saveCart,
        addToCart: addToCart,
        removeFromCart: removeFromCart,
        updateItemQuantity: updateItemQuantity,
        getCart: () => cart, // Función para obtener el carrito actual
        clearCart: clearCart
    };

    // --- Inicialización ---
    loadCart(); // Cargar el carrito al inicio de la página
});

