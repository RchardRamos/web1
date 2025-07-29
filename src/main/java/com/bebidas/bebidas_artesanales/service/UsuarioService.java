package com.bebidas.bebidas_artesanales.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bebidas.bebidas_artesanales.model.Usuario;
import com.bebidas.bebidas_artesanales.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void registrarUsuarioConRolUser(Usuario usuario) {
        // Lógica para asignar rol USER (ya deberías tenerla implementada)
        guardar(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
