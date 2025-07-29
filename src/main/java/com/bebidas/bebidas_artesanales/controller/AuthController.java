package com.bebidas.bebidas_artesanales.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebidas.bebidas_artesanales.dto.LoginRequest;
import com.bebidas.bebidas_artesanales.dto.RegistroRequest;
import com.bebidas.bebidas_artesanales.model.Usuario;
import com.bebidas.bebidas_artesanales.security.JwtUtil;
import com.bebidas.bebidas_artesanales.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegistroRequest request) {
        if (usuarioService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El nombre de usuario ya está en uso"));
        }

        if (usuarioService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "El correo ya está en uso"));
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(request.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevoUsuario.setEmail(request.getEmail());

        usuarioService.registrarUsuarioConRolUser(nuevoUsuario);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.findByUsername(loginRequest.getUsername());

            if (usuario == null || !passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
            }

            String token = jwtUtil.generarToken(usuario.getUsername());

            // DEVOLVEMOS TOKEN Y USERNAME
            return ResponseEntity.ok(Map.of(
                "token", "Bearer " + token,
                "username", usuario.getUsername()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Ocurrió un error en el servidor"));
        }
    }
}
