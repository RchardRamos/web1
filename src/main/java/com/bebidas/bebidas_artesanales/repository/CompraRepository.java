package com.bebidas.bebidas_artesanales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bebidas.bebidas_artesanales.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
   List<Compra> findByUsuarioUsername(String username);

}
