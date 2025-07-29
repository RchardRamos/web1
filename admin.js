const token = localStorage.getItem('token');

if (!token) {
  alert('Acceso restringido. Inicia sesión como administrador.');
  window.location.href = "index.html";
}

const tabla = document.getElementById("tablaProductos");

function cargarProductos() {
  fetch('http://localhost:8080/api/productos', {
    headers: {
      'Authorization': 'Bearer ' + token
    }
  })
  .then(res => res.json())
  .then(productos => {
    tabla.innerHTML = '';
    productos.forEach(prod => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${prod.nombre}</td>
        <td>$${prod.precio.toFixed(2)}</td>
        <td>${prod.stock}</td>
        <td>${prod.tipo}</td>
        <td>
          ${prod.imagen ? `<img src="${prod.imagen}" class="producto-img">` : 'Sin imagen'}
        </td>
        <td class="acciones">
          <button onclick="eliminarProducto(${prod.id})">Eliminar</button>
        </td>
      `;
      tabla.appendChild(tr);
    });
  })
  .catch(() => {
    alert("No se pudieron cargar los productos. Verifica el servidor.");
  });
}

cargarProductos();

document.getElementById("formAgregarProducto").addEventListener("submit", function(e) {
  e.preventDefault();

  const nuevo = {
    nombre: document.getElementById("nombre").value.trim(),
    descripcion: document.getElementById("descripcion").value.trim(),
    precio: parseFloat(document.getElementById("precio").value),
    stock: parseInt(document.getElementById("stock").value),
    tipo: document.getElementById("tipo").value.trim(),
    imagen: document.getElementById("imagen").value.trim()
  };

  if (nuevo.precio < 0 || nuevo.stock < 0) {
    alert("Precio y stock deben ser mayores o iguales a 0.");
    return;
  }

  fetch('http://localhost:8080/api/productos', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token
    },
    body: JSON.stringify(nuevo)
  })
  .then(response => {
    if (!response.ok) {
      return response.json().then(err => {
        throw new Error(err.message || 'Error al agregar producto');
      });
    }
    return response.json();
  })
  .then(() => {
    alert("Producto agregado correctamente");
    this.reset();
    cargarProductos();
  })
  .catch(err => alert("Error: " + err.message));
});

function eliminarProducto(id) {
  if (!confirm("¿Estás seguro de eliminar este producto?")) return;

  fetch(`http://localhost:8080/api/productos/${id}`, {
    method: 'DELETE',
    headers: {
      'Authorization': 'Bearer ' + token
    }
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("No se pudo eliminar el producto.");
    }
    alert("Producto eliminado correctamente.");
    cargarProductos();
  })
  .catch(err => alert("Error: " + err.message));
}
