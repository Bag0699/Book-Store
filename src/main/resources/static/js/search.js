document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInputNav');
    const suggestionsDropdown = document.getElementById('suggestionsDropdown');
    let controller = null; // Para manejar el abort de peticiones anteriores

    // Función para obtener sugerencias del servidor
    async function getSuggestions(searchTerm) {
        if (!searchTerm.trim()) return [];

        // Cancelar petición anterior si existe
        if (controller) {
            controller.abort();
        }
        controller = new AbortController();

        try {
            const response = await fetch(`/buscar/sugerencia?query=${encodeURIComponent(searchTerm)}`, {
                signal: controller.signal
            });

            if (!response.ok) {
                throw new Error('Error al obtener sugerencias');
            }

            const data = await response.json();
            return data; // El endpoint ya devuelve la lista de libros con título
        } catch (error) {
            if (error.name === 'AbortError') {
                console.log('Petición cancelada');
                return [];
            }
            console.error('Error:', error);
            return [];
        } finally {
            controller = null;
        }
    }

    // Función para mostrar las sugerencias
    function showSuggestions(books) {
        const dropdown = new bootstrap.Dropdown(searchInput);
        const dropdownMenu = suggestionsDropdown;

        // Limpiar sugerencias anteriores
        dropdownMenu.innerHTML = '';

        if (!books || books.length === 0) {
            const noResults = document.createElement('li');
            noResults.className = 'dropdown-item disabled';
            noResults.textContent = 'No se encontraron resultados';
            dropdownMenu.appendChild(noResults);
        } else {
            books.forEach(book => {
                const item = document.createElement('li');
                const link = document.createElement('a');
                link.className = 'dropdown-item';
                link.href = '#';
                link.textContent = book.title; // Accedemos a la propiedad title del objeto book
                link.addEventListener('click', function(e) {
                    e.preventDefault();
                    searchInput.value = book.title;
                    // Redirigir a la página de detalles del libro o de búsqueda
                    window.location.href = `/libro/${book.id}`; // O la ruta que uses para mostrar un libro
                });
                item.appendChild(link);
                dropdownMenu.appendChild(item);
            });
        }

        // Mostrar el dropdown
        dropdown.show();
    }

    // Evento de entrada en el buscador
    searchInput.addEventListener('input', async function() {
        const searchTerm = this.value.trim();
        const suggestions = await getSuggestions(searchTerm);
        showSuggestions(suggestions);
    });

    // Cerrar el dropdown al hacer clic fuera
    document.addEventListener('click', function(e) {
        if (!searchInput.contains(e.target) && !suggestionsDropdown.contains(e.target)) {
            const dropdown = bootstrap.Dropdown.getInstance(searchInput);
            if (dropdown) dropdown.hide();
        }
    });
});