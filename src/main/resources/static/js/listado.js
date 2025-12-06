document.addEventListener('DOMContentLoaded', function() {

    // --- Lógica para el Modal de Edición ---
    const editModalElement = document.getElementById('editModal');
    if (editModalElement) { // Verifica si el modal de edición existe en la página
        editModalElement.addEventListener('show.bs.modal', event => {
            // Botón que disparó el modal
            const button = event.relatedTarget;

            // Extraer datos de los atributos data-
            const title = button.getAttribute('data-title');
            const author = button.getAttribute('data-author');
            const isbn = button.getAttribute('data-isbn');
            // Asegúrate de pasar todos los datos necesarios para la edición, como el bookId
            const bookId = button.getAttribute('data-book-id'); // Necesitarás este para la API de edición

            // Obtener los campos del formulario del modal de edición
            const editTitleInput = editModalElement.querySelector('#editTitle');
            const editAuthorInput = editModalElement.querySelector('#editAuthor'); // Esto debería ser un select para authorId
            const editISBNInput = editModalElement.querySelector('#editISBN');
            const editBookIdHiddenInput = editModalElement.querySelector('#editBookId'); // Campo oculto para el ID del libro

            // Rellenar los campos del modal de edición
            editTitleInput.value = title; // Usar .value para inputs
            // Para el autor, necesitarías seleccionar la opción correcta en un <select>
            // editAuthorInput.value = authorId; // Si 'author' es el ID
            editISBNInput.value = isbn;
            if (editBookIdHiddenInput) {
                editBookIdHiddenInput.value = bookId; // Establecer el ID para la edición
            }


            // Placeholder ya no es necesario si rellenas el valor directamente.
            // editTitleInput.placeholder = title;
            // editAuthorInput.placeholder = author;
            // editISBNInput.placeholder = isbn;
        });
    }


    // --- Lógica para el Botón de Guardar Libro (Agregar Nuevo Libro) ---
    // Asociamos el listener al botón con ID 'btnGuardarLibro' que definimos antes
    const formAgregarLibro = document.getElementById('formAgregarLibro');
    const btnGuardarLibro = document.getElementById('btnGuardarLibro'); // Este es el botón del modal 'Agregar Libro'

    if (btnGuardarLibro) { // Verifica si el botón existe (es decir, si estamos en la página de listado)
        btnGuardarLibro.addEventListener('click', async function() {
            if (!formAgregarLibro.checkValidity()) {
                formAgregarLibro.classList.add('was-validated'); // Añade la clase para mostrar validación de Bootstrap
                return; // Detiene si el formulario no es válido
            }

            // Recolectar datos del formulario, alineado con BookRequest DTO
            const bookRequest = {
                title: document.getElementById('titulo').value,
                sinopsis: document.getElementById('sinopsis').value, // Asegúrate que tu HTML lo pida como 'sinopsis'
                price: parseFloat(document.getElementById('precio').value),
                isbn: document.getElementById('isbn').value,
                description: document.getElementById('descripcion').value, // Asegúrate que tu HTML lo pida como 'descripcion'
                url_img: document.getElementById('urlImagen').value, // Tu HTML debería usar 'urlImagen'
                stock: parseInt(document.getElementById('stock').value), // Tu HTML debería usar 'stock'
                dimension: document.getElementById('dimension').value, // Tu HTML debería usar 'dimension'
                author_id: parseInt(document.getElementById('autorId').value), // Tu HTML debería usar 'autorId'
                category_id: parseInt(document.getElementById('categoriaId').value), // Tu HTML debería usar 'categoriaId'
                editorial_id: parseInt(document.getElementById('editorialId').value), // Tu HTML debería usar 'editorialId'
                format_id: parseInt(document.getElementById('formatId').value) // Tu HTML debería usar 'formatId'
            };

            console.log("Datos del libro a enviar:", bookRequest);

            try {
                // Endpoint para crear un libro, asumo que es '/api/books' como en la respuesta anterior
                const response = await fetch('/api/books', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        // 'X-Requested-With': 'XMLHttpRequest' // Esto es para peticiones AJAX antiguas, generalmente no es necesario con fetch
                    },
                    body: JSON.stringify(bookRequest)
                });

                if (response.ok) {
                    const data = await response.json();
                    alert('Libro "' + data.title + '" guardado correctamente con ID: ' + data.id);
                    // Cierra el modal y limpia el formulario
                    const modalInstance = bootstrap.Modal.getInstance(document.getElementById('agregarLibroModal'));
                    if (modalInstance) {
                        modalInstance.hide();
                    }
                    formAgregarLibro.reset();
                    formAgregarLibro.classList.remove('was-validated'); // Quita la validación visual

                    location.reload(); // Recarga la página para mostrar el nuevo libro
                } else {
                    const error = await response.json();
                    let errorMessage = error.message || 'Error desconocido al guardar el libro.';
                    if (error.errors && error.errors.length > 0) { // Si hay errores de validación de Spring
                        errorMessage += "\nDetalles:\n";
                        error.errors.forEach(err => {
                            errorMessage += `- ${err.field}: ${err.defaultMessage}\n`;
                        });
                    }
                    alert('Error al guardar el libro: ' + errorMessage);
                    console.error('Error del servidor:', error);
                }
            } catch (error) {
                console.error('Error en la solicitud:', error);
                alert('Error de conexión al guardar el libro: ' + error.message);
            }
        });
    }

    // Opcional: Limpiar el formulario al abrir el modal de agregar libro
    const agregarLibroModalElement = document.getElementById('agregarLibroModal');
    if (agregarLibroModalElement) {
        agregarLibroModalElement.addEventListener('show.bs.modal', function () {
            formAgregarLibro.reset();
            formAgregarLibro.classList.remove('was-validated'); // Asegúrate de quitar la clase de validación
            // Resetear la selección de los <select> a la opción por defecto (vacía)
            document.getElementById('autorId').value = "";
            document.getElementById('categoriaId').value = "";
            document.getElementById('editorialId').value = "";
            document.getElementById('formatId').value = "";
        });
    }

    // --- Lógica para Eliminar Libro ---
    // Esta función necesita ser global o accesible para el 'onclick' de tus botones de eliminar en la tabla
    window.deleteBook = async function(bookId) {
        if (confirm('¿Estás seguro de que deseas eliminar este libro? Esta acción es irreversible.')) {
            try {
                // Asumo que tu endpoint DELETE es /api/books/{id}
                const response = await fetch('/listado/' + bookId, {
                    method: 'DELETE'
                });

                if (response.ok) {
                    alert('Libro eliminado correctamente.');
                    location.reload(); // Recarga la página para reflejar el cambio
                } else {
                    const errorData = await response.json();
                    alert('Error al eliminar el libro: ' + (errorData.message || 'Error desconocido.'));
                    console.error('Error del servidor:', errorData);
                }
            } catch (error) {
                console.error('Error en la solicitud de eliminación:', error);
                alert('Error de conexión al eliminar el libro. Por favor, intenta de nuevo.');
            }
        }
    };


    // --- Lógica para Guardar Cambios en el Modal de Edición ---
    // Asume que tienes un botón con ID 'btnGuardarEdicion' dentro de tu modal de edición
    const btnGuardarEdicion = document.getElementById('btnGuardarEdicion');
    if (btnGuardarEdicion) {
        btnGuardarEdicion.addEventListener('click', async function() {
            const formEditarLibro = document.getElementById('formEditarLibro'); // Asume un ID para el formulario de edición
            if (!formEditarLibro || !formEditarLibro.checkValidity()) {
                if (formEditarLibro) formEditarLibro.classList.add('was-validated');
                return;
            }

            // Recolectar datos del formulario de edición, incluyendo el ID del libro
            const editedBookRequest = {
                // Obtener el ID del libro de un campo oculto en el modal de edición
                id: parseInt(document.getElementById('editBookId').value), // Asegúrate de tener este campo en tu modal de edición
                title: document.getElementById('editTitle').value,
                sinopsis: document.getElementById('editSinopsis').value, // Asume que tienes estos campos en tu modal de edición
                price: parseFloat(document.getElementById('editPrice').value),
                isbn: document.getElementById('editISBN').value,
                description: document.getElementById('editDescription').value,
                urlImg: document.getElementById('editUrlImagen').value,
                stock: parseInt(document.getElementById('editStock').value),
                dimension: document.getElementById('editDimension').value,
                authorId: parseInt(document.getElementById('editAutorId').value),
                categoryId: parseInt(document.getElementById('editCategoriaId').value),
                editorialId: parseInt(document.getElementById('editEditorialId').value),
                formatId: parseInt(document.getElementById('editFormatId').value)
            };

            console.log("Datos del libro a editar:", editedBookRequest);

            try {
                // Asumo que tu endpoint PUT para actualizar un libro es /api/books/{id}
                const response = await fetch('/api/books/' + editedBookRequest.id, {
                    method: 'PUT', // Método PUT para actualización
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(editedBookRequest)
                });

                if (response.ok) {
                    const updatedBook = await response.json();
                    alert('Libro "' + updatedBook.title + '" actualizado correctamente.');
                    const editModal = bootstrap.Modal.getInstance(editModalElement);
                    if (editModal) {
                        editModal.hide();
                    }
                    location.reload(); // Recargar para ver los cambios
                } else {
                    const error = await response.json();
                    let errorMessage = error.message || 'Error desconocido al actualizar el libro.';
                    if (error.errors && error.errors.length > 0) {
                        errorMessage += "\nDetalles:\n";
                        error.errors.forEach(err => {
                            errorMessage += `- ${err.field}: ${err.defaultMessage}\n`;
                        });
                    }
                    alert('Error al actualizar el libro: ' + errorMessage);
                    console.error('Error del servidor:', error);
                }
            } catch (error) {
                console.error('Error en la solicitud de actualización:', error);
                alert('Error de conexión al actualizar el libro: ' + error.message);
            }
        });
    }

    // --- Funciones auxiliares que estaban en el último script (no necesarias si usas la lógica anterior) ---
    // function confirmDelete(bookId) { /* ... */ } // Ya cubierta por window.deleteBook
    // function saveChanges() { /* ... */ } // Ya cubierta por btnGuardarEdicion.addEventListener

});