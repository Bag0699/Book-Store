// src/main/resources/static/js/admin-common.js

document.addEventListener('DOMContentLoaded', function() {
    // Ejemplo: funcionalidad para ocultar/mostrar la barra lateral (si implementas un botón de toggle)
    // const sidebarToggle = document.getElementById('sidebarToggle');
    // if (sidebarToggle) {
    //     sidebarToggle.addEventListener('click', event => {
    //         event.preventDefault();
    //         document.body.classList.toggle('sb-sidenav-toggled');
    //     });
    // }

    // Función para activar el elemento de menú actual
    const currentPath = window.location.pathname;
    const sidebarLinks = document.querySelectorAll('#sidebar-wrapper .list-group-item');

    sidebarLinks.forEach(link => {
        // Normaliza las rutas para comparación, eliminando / al final si existe
        const linkPath = link.getAttribute('href').replace(/\/$/, '');
        const normalizedCurrentPath = currentPath.replace(/\/$/, '');

        // Activa el enlace si la ruta actual coincide exactamente o si es la base (e.g., /admin y /admin/dashboard)
        if (linkPath === normalizedCurrentPath ||
            (linkPath === '/admin/dashboard' && normalizedCurrentPath === '/admin')) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
});