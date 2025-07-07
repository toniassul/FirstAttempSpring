// URL base para las APIs
const baseUrl = 'http://localhost:8080/api/articulos';

// Cargar artículos al iniciar la página
document.addEventListener('DOMContentLoaded', () => {
    cargarArticulos();
    setupAutocomplete();
});

// Función para cargar todos los artículos
async function cargarArticulos() {
    try {
        const response = await fetch(baseUrl);
        const articulos = await response.json();
        mostrarArticulosEnTabla(articulos);
    } catch (error) {
        console.error('Error al cargar artículos:', error);
        alert('Error al cargar los artículos');
    }
}

// Función para mostrar artículos en la tabla
function mostrarArticulosEnTabla(articulos) {
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = '';
    if (!Array.isArray(articulos)) return;
    articulos.forEach(articulo => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${articulo.id_Articulo ?? ''}</td>
            <td>${articulo.nombreArticulo ?? ''}</td>
            <td>${articulo.marca ?? ''}</td>
            <td>${articulo.precio ?? ''}</td>
            <td>${articulo.stock ?? ''}</td>
        `;
        tr.addEventListener('click', () => seleccionarArticulo(articulo));
        tbody.appendChild(tr);
    });
}

// Función para seleccionar un artículo
function seleccionarArticulo(articulo) {
    document.getElementById('idArticulo').value = articulo.id_Articulo ?? '';
    document.getElementById('nombreArticulo').value = articulo.nombreArticulo ?? '';
    document.getElementById('marca').value = articulo.marca ?? '';
    document.getElementById('precio').value = articulo.precio ?? '';
    document.getElementById('stock').value = articulo.stock ?? '';
    document.querySelectorAll('tbody tr').forEach(tr => tr.classList.remove('selected'));
    // highlight selected row
    const rows = document.querySelectorAll('tbody tr');
    rows.forEach(tr => {
        if (tr.children[0].textContent === String(articulo.id_Articulo ?? '')) {
            tr.classList.add('selected');
        }
    });
}

// Función para insertar un nuevo artículo
async function insertarArticulo() {
    const articulo = obtenerDatosFormulario();
    try {
        const response = await fetch(baseUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(articulo)
        });
        if (response.ok) {
            alert('Artículo insertado correctamente');
            cargarArticulos();
            limpiarFormulario();
        } else {
            const errorText = await response.text();
            alert('Error al insertar el artículo: ' + errorText);
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al insertar el artículo');
    }
}

// Función para actualizar un artículo
async function actualizarArticulo() {
    const id = document.getElementById('idArticulo').value;
    if (!id) {
        alert('Seleccione un artículo para actualizar');
        return;
    }
    const articulo = obtenerDatosFormulario();
    try {
        const response = await fetch(`${baseUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(articulo)
        });
        if (response.ok) {
            alert('Artículo actualizado correctamente');
            cargarArticulos();
        } else {
            const errorText = await response.text();
            alert('Error al actualizar el artículo: ' + errorText);
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al actualizar el artículo');
    }
}

// Función para eliminar un artículo
async function eliminarArticulo() {
    const id = document.getElementById('idArticulo').value;
    if (!id) {
        alert('Seleccione un artículo para eliminar');
        return;
    }
    if (confirm('¿Está seguro de eliminar este artículo?')) {
        try {
            const response = await fetch(`${baseUrl}/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Artículo eliminado correctamente');
                cargarArticulos();
                limpiarFormulario();
            } else {
                const errorText = await response.text();
                alert('Error al eliminar el artículo: ' + errorText);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error al eliminar el artículo');
        }
    }
}

// Función para obtener los datos del formulario
function obtenerDatosFormulario() {
    return {
        id_Articulo: document.getElementById('idArticulo').value || null,
        nombreArticulo: document.getElementById('nombreArticulo').value,
        marca: document.getElementById('marca').value,
        precio: parseFloat(document.getElementById('precio').value),
        stock: parseInt(document.getElementById('stock').value)
    };
}

// Función para limpiar el formulario
function limpiarFormulario() {
    document.getElementById('idArticulo').value = '';
    document.getElementById('nombreArticulo').value = '';
    document.getElementById('marca').value = '';
    document.getElementById('precio').value = '';
    document.getElementById('stock').value = '';
    document.querySelectorAll('tbody tr').forEach(tr => tr.classList.remove('selected'));
}

// Función para filtrar artículos
async function filtrarArticulos() {
    const filtro = document.getElementById('filtroInput').value.trim();
    if (!filtro) {
        cargarArticulos();
        return;
    }
    try {
        const response = await fetch(`${baseUrl}/buscar?nombreArticulo=${encodeURIComponent(filtro)}`);
        if (!response.ok) throw new Error(await response.text());
        const articulos = await response.json();
        mostrarArticulosEnTabla(articulos);
    } catch (error) {
        console.error('Error al filtrar artículos:', error);
        if (error.message.includes('400')) {
            alert('Por favor ingrese un texto para buscar');
        } else {
            alert('Error al filtrar los artículos: ' + error.message);
        }
    }
}

// Autocomplete para el filtro de nombre
function setupAutocomplete() {
    const filtroInput = document.getElementById('filtroInput');
    filtroInput.setAttribute('autocomplete', 'off');
    filtroInput.addEventListener('input', async function() {
        const value = this.value;
        if (value.length < 1) return;
        try {
            const response = await fetch(baseUrl);
            const articulos = await response.json();
            const nombres = articulos.map(a => a.nombreArticulo ?? '').filter(n => n.toLowerCase().includes(value.toLowerCase()));
            closeAutocompleteList();
            if (nombres.length > 0) {
                const list = document.createElement('div');
                list.setAttribute('id', 'autocomplete-list');
                list.setAttribute('class', 'autocomplete-items');
                list.style.position = 'absolute';
                list.style.background = '#fff';
                list.style.border = '1px solid #888';
                list.style.zIndex = 99;
                list.style.width = filtroInput.offsetWidth + 'px';
                list.style.maxHeight = '120px';
                list.style.overflowY = 'auto';
                filtroInput.parentNode.appendChild(list);
                nombres.forEach(nombre => {
                    const item = document.createElement('div');
                    item.innerHTML = nombre;
                    item.style.padding = '4px';
                    item.style.cursor = 'pointer';
                    item.addEventListener('mousedown', function(e) {
                        filtroInput.value = nombre;
                        closeAutocompleteList();
                        filtrarArticulos();
                    });
                    list.appendChild(item);
                });
            }
        } catch (e) { /* ignore */ }
    });
    document.addEventListener('click', function (e) {
        if (e.target !== filtroInput) closeAutocompleteList();
    });
    function closeAutocompleteList() {
        const items = document.querySelectorAll('.autocomplete-items');
        items.forEach(i => i.parentNode.removeChild(i));
    }
}