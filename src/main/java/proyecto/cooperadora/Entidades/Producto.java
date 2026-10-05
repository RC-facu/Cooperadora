package proyecto.cooperadora.Entidades;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;
    private String descripcion;
    private String talle;
    private String color;
    private String categoria;
    private Double precio;
    private Integer stock;

    // Un producto puede aparecer en varias lineas de detalle de pedido (1 a N)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("producto") // corta el loop del lado de DetallePedido
    private List<DetallePedido> detalles;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, String talle, String color,
                     String categoria, Double precio, Integer stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.talle = talle;
        this.color = color;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters____________________________________________________

    public Long getId_producto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTalle() {
        return talle;
    }

    public String getColor() {
        return color;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    // Setters____________________________________________________

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
