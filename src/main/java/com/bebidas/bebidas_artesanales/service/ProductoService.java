package com.bebidas.bebidas_artesanales.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bebidas.bebidas_artesanales.dto.ProductoDTO;
import com.bebidas.bebidas_artesanales.model.Producto;
import com.bebidas.bebidas_artesanales.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listar productos como entidades
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // Listar productos como DTOs
    public List<ProductoDTO> listarDTO() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Guardar producto (recibe entidad)
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener producto por ID (entidad)
    public Producto obtenerPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    // Eliminar producto por ID
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    // Convertir entidad Producto a DTO
    public ProductoDTO convertirADTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getTipo(),
                producto.getImagen()
        );
    }

    // Convertir DTO a entidad Producto
    public Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setTipo(dto.getTipo());
        producto.setImagen(dto.getImagen());
        return producto;
    }
}
