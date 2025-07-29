package com.bebidas.bebidas_artesanales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bebidas.bebidas_artesanales.model.DetalleCompra;
import com.bebidas.bebidas_artesanales.repository.DetalleCompraRepository;

@Service
public class DetalleCompraService {

    private final DetalleCompraRepository detalleCompraRepository;

    public DetalleCompraService(DetalleCompraRepository detalleCompraRepository) {
        this.detalleCompraRepository = detalleCompraRepository;
    }

    public List<DetalleCompra> obtenerPorCompra(Long compraId) {
        return detalleCompraRepository.findByCompraId(compraId);
    }
}