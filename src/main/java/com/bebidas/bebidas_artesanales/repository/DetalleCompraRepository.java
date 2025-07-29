package com.bebidas.bebidas_artesanales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bebidas.bebidas_artesanales.model.DetalleCompra;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
    List<DetalleCompra> findByCompraId(Long compraId);

    // Métodos personalizados si los necesitas
}
