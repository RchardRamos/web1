package com.bebidas.bebidas_artesanales.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebidas.bebidas_artesanales.model.Compra;
import com.bebidas.bebidas_artesanales.model.Usuario;
import com.bebidas.bebidas_artesanales.service.CompraService;
import com.bebidas.bebidas_artesanales.service.UsuarioService;
// CompraController.java
@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraService compraService;
    private final UsuarioService usuarioService;

    public CompraController(CompraService compraService, UsuarioService usuarioService) {
        this.compraService = compraService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> registrarCompra(@RequestBody Compra compra, Principal principal) {
        try {
            Usuario usuario = usuarioService.findByUsername(principal.getName());
            if (usuario == null) {
                return ResponseEntity.status(401).body("Usuario no encontrado");
            }

            compra.setUsuario(usuario);
            Compra compraGuardada = compraService.registrarCompra(compra);

            // Devolver la compra con usuario y detalles
            return ResponseEntity.ok(compraGuardada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Compra>> historialUsuario(Principal principal) {
        List<Compra> compras = compraService.historialDeUsuario(principal.getName());
        return ResponseEntity.ok(compras);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Compra>> listarTodas() {
        List<Compra> compras = compraService.listarTodas();
        return ResponseEntity.ok(compras);
    }
}
