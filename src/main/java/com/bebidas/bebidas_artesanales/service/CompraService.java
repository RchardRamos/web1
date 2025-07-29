package com.bebidas.bebidas_artesanales.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bebidas.bebidas_artesanales.model.Compra;
import com.bebidas.bebidas_artesanales.model.DetalleCompra;
import com.bebidas.bebidas_artesanales.model.Producto;
import com.bebidas.bebidas_artesanales.repository.CompraRepository;
import com.bebidas.bebidas_artesanales.repository.ProductoRepository;
// CompraService.java
@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;

    public CompraService(CompraRepository compraRepository, ProductoRepository productoRepository) {
        this.compraRepository = compraRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Compra registrarCompra(Compra compra) {
        double total = 0;

        for (DetalleCompra detalle : compra.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getProducto().getId()));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            double subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
            total += subtotal;

            detalle.setCompra(compra);
        }

        double iva = total * 0.19;
        double totalConIVA = total + iva;

        compra.setTotal(total);
        compra.setTotalConIVA(totalConIVA);
        compra.setFechaCompra(LocalDateTime.now());

        return compraRepository.save(compra);
    }

    public List<Compra> historialDeUsuario(String username) {
        return compraRepository.findByUsuarioUsername(username);
    }

    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }
}
