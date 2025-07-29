package com.bebidas.bebidas_artesanales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @Positive(message = "El precio unitario debe ser positivo")
    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Constructor vacío
    public DetalleCompra() {}

    // Constructor completo (opcional)
    public DetalleCompra(Integer cantidad, Double precioUnitario, Compra compra, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.compra = compra;
        this.producto = producto;
    }

    // Getters y setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }

    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Compra getCompra() { return compra; }

    public void setCompra(Compra compra) { this.compra = compra; }

    public Producto getProducto() { return producto; }

    public void setProducto(Producto producto) { this.producto = producto; }
}
