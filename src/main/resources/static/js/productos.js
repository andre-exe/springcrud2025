let modal = new bootstrap.Modal(document.getElementById('modalProducto'));

function cargarTabla() {
    fetch('/productos/listar')
        .then(res => res.json())
        .then(data => {
            let tbody = document.querySelector("#tabla-productos tbody");
            tbody.innerHTML = "";
            data.forEach(p => {
                tbody.innerHTML += `
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nombre}</td>
                        <td>${p.descripcion}</td>
                        <td>${p.precio}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editar(${p.id})">Editar</button>
                            <button class="btn btn-danger btn-sm" onclick="eliminar(${p.id})">Eliminar</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function abrirModalNuevo() {
    document.getElementById('id').value = "";
    document.getElementById('nombre').value = "";
    document.getElementById('descripcion').value = "";
    document.getElementById('precio').value = "";
    document.getElementById('tituloModal').innerText = "Nuevo Producto";
    modal.show();
}

function editar(id) {
    fetch(`/productos/obtener/${id}`)
        .then(res => res.json())
        .then(p => {
            document.getElementById('id').value = p.id;
            document.getElementById('nombre').value = p.nombre;
            document.getElementById('descripcion').value = p.descripcion;
            document.getElementById('precio').value = p.precio;
            document.getElementById('tituloModal').innerText = "Editar Producto";
            modal.show();
        });
}

function guardar() {
    let producto = {
        id: document.getElementById('id').value,
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: document.getElementById('precio').value
    };

    fetch('/productos/guardar', {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(producto)
    })
    .then(res => res.json())
    .then(() => {
        modal.hide();
        Swal.fire("Éxito", "Producto guardado correctamente", "success");
        cargarTabla();
    });
}

function eliminar(id) {
    Swal.fire({
        title: "¿Estás seguro?",
        text: "Se eliminará el producto",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar"
    }).then(result => {
        if (result.isConfirmed) {
            fetch(`/productos/eliminar/${id}`, { method: "DELETE" })
                .then(() => {
                    Swal.fire("Eliminado", "Producto eliminado", "success");
                    cargarTabla();
                });
        }
    });
}

document.addEventListener("DOMContentLoaded", cargarTabla);
