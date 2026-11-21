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
    private String categoria;
    private Integer stock;
    private String url; // ruta de la imagen dentro de static, ej: /images/campera.png

    // Precio por talle: tabla "precio" (id_producto, talle, precio)
    @ElementCollection
    @CollectionTable(name = "precio", joinColumns = @JoinColumn(name = "id_producto"))
    private List<PrecioProducto> precios;

    // Un producto puede aparecer en varias lineas de detalle de pedido (1 a N)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("producto") // corta el loop del lado de DetallePedido
    private List<DetallePedido> detalles;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, String categoria, Integer stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public List<PrecioProducto> getPrecios() {
        return precios;
    }

    public String getUrl() {
        return url;
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrecios(List<PrecioProducto> precios) {
        this.precios = precios;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
