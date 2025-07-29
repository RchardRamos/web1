package com.bebidas.bebidas_artesanales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bebidas.bebidas_artesanales.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Puedes añadir consultas personalizadas si necesitas
}
