function mostrarFactura(carrito) {
  if (carrito.length === 0) {
    ocultarFactura();
    return;
  }

  const facturaDetalle = document.getElementById("factura-detalle");
  facturaDetalle.innerHTML = "";

  let subtotal = 0;
  const ivaPorcentaje = 0.15;

  // Crear tabla
  const tabla = document.createElement("table");

  // Cabecera tabla
  const thead = document.createElement("thead");
  thead.innerHTML = `
    <tr>
      <th>Producto</th>
      <th class="center">Cantidad</th>
      <th class="center">Precio Unitario</th>
      <th class="center">Total</th>
    </tr>
  `;
  tabla.appendChild(thead);

  // Cuerpo tabla
  const tbody = document.createElement("tbody");

  carrito.forEach(producto => {
    const subtotalProducto = producto.precio * producto.cantidad;
    subtotal += subtotalProducto;

    const fila = document.createElement("tr");
    fila.innerHTML = `
      <td>${producto.nombre}</td>
      <td class="center">${producto.cantidad}</td>
      <td class="center">$${producto.precio.toFixed(2)}</td>
      <td class="center">$${subtotalProducto.toFixed(2)}</td>
    `;
    tbody.appendChild(fila);
  });

  tabla.appendChild(tbody);
  facturaDetalle.appendChild(tabla);

  // Mostrar totales con mejor formato
  const iva = subtotal * ivaPorcentaje;
  const total = subtotal + iva;

  // Limpiar y crear contenedor para totales alineados a la derecha
  const facturaSubtotal = document.getElementById("factura-subtotal");
  const facturaIVA = document.getElementById("factura-iva");
  const facturaTotal = document.getElementById("factura-total");

  facturaSubtotal.innerHTML = `<strong>Subtotal:</strong> <span>$${subtotal.toFixed(2)}</span>`;
  facturaIVA.innerHTML = `<strong>IVA (15%):</strong> <span>$${iva.toFixed(2)}</span>`;
  facturaTotal.innerHTML = `<strong>Total:</strong> <span>$${total.toFixed(2)}</span>`;

  // Aplicar estilo a contenedor de totales para alinearlos como en el modelo
  facturaSubtotal.parentElement.style.maxWidth = "320px";
  facturaSubtotal.parentElement.style.margin = "0 auto";
  facturaSubtotal.parentElement.style.textAlign = "right";

  facturaSubtotal.style.display = "flex";
  facturaSubtotal.style.justifyContent = "space-between";
  facturaSubtotal.style.marginBottom = "6px";

  facturaIVA.style.display = "flex";
  facturaIVA.style.justifyContent = "space-between";
  facturaIVA.style.marginBottom = "6px";

  facturaTotal.style.display = "flex";
  facturaTotal.style.justifyContent = "space-between";
  facturaTotal.style.fontSize = "20px";
  facturaTotal.style.marginTop = "12px";

  document.getElementById("factura").style.display = "block";
}
