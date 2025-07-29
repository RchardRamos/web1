package com.bebidas.bebidas_artesanales.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @Positive(message = "El precio debe ser positivo")
    private double precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

    @NotBlank(message = "El tipo de bebida es obligatorio")
    private String tipo;

    private String imagen;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleCompra> detalles;

    // Constructor vacío
    public Producto() {}

    // Constructor completo
    public Producto(Long id, String nombre, String descripcion, double precio, int stock, String tipo, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    // Getters y setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }

    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public List<DetalleCompra> getDetalles() { return detalles; }

    public void setDetalles(List<DetalleCompra> detalles) { this.detalles = detalles; }
}
