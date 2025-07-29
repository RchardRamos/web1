package com.bebidas.bebidas_artesanales.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bebidas.bebidas_artesanales.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
