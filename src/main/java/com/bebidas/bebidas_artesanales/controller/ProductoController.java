package com.bebidas.bebidas_artesanales.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebidas.bebidas_artesanales.dto.ProductoDTO;
import com.bebidas.bebidas_artesanales.model.Producto;
import com.bebidas.bebidas_artesanales.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // Listar todos los productos (público)
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        List<ProductoDTO> productos = service.listarDTO();
        return ResponseEntity.ok(productos);
    }

    // Crear un producto (solo admin)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO productoDTO) {
        Producto producto = service.convertirAEntidad(productoDTO);
        Producto guardado = service.guardar(producto);
        ProductoDTO dtoGuardado = service.convertirADTO(guardado);
        return ResponseEntity.ok(dtoGuardado);
    }

    // Obtener producto por ID (público)
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtener(@PathVariable Long id) {
        Producto producto = service.obtenerPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        ProductoDTO dto = service.convertirADTO(producto);
        return ResponseEntity.ok(dto);
    }

    // Actualizar producto por ID (solo admin)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        Producto existente = service.obtenerPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNombre(productoDTO.getNombre());
        existente.setDescripcion(productoDTO.getDescripcion());
        existente.setPrecio(productoDTO.getPrecio());
        existente.setStock(productoDTO.getStock());
        existente.setTipo(productoDTO.getTipo());
        existente.setImagen(productoDTO.getImagen());

        Producto actualizado = service.guardar(existente);
        ProductoDTO dtoActualizado = service.convertirADTO(actualizado);
        return ResponseEntity.ok(dtoActualizado);
    }

    // Eliminar producto por ID (solo admin)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Producto producto = service.obtenerPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
