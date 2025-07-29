package com.bebidas.bebidas_artesanales.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebidas.bebidas_artesanales.model.DetalleCompra;
import com.bebidas.bebidas_artesanales.service.DetalleCompraService;

@RestController
@RequestMapping("/api/detalles")
@CrossOrigin(origins = "*")
public class DetalleCompraController {

    private final DetalleCompraService detalleCompraService;

    public DetalleCompraController(DetalleCompraService detalleCompraService) {
        this.detalleCompraService = detalleCompraService;
    }

    // Obtener detalles por ID de compra
    @GetMapping("/compra/{compraId}")
    public ResponseEntity<List<DetalleCompra>> obtenerPorCompra(@PathVariable Long compraId) {
        List<DetalleCompra> detalles = detalleCompraService.obtenerPorCompra(compraId);
        if (detalles == null || detalles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalles);
    }
}
