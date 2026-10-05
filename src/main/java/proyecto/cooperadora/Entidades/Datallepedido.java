package proyecto.cooperadora.Entidades;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;

    // Muchos detalles pertenecen a un pedido (N a 1)
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    @JsonIgnoreProperties({"detalles", "pago", "cliente"}) // corta el loop del lado de Pedido
    private Pedido pedido;

    // Muchos detalles referencian a un producto (N a 1)
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    @JsonIgnoreProperties("detalles") // corta el loop del lado de Producto
    private Producto producto;

    private Integer cantidad;
    private Double precio_unitario;

    public DetallePedido() {
    }

    public DetallePedido(Pedido pedido, Producto producto, Integer cantidad, Double precio_unitario) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }
        // Getters____________________________________________________

    public Long getId_detalle() {
        return id_detalle;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    // Setters____________________________________________________

    public void setId_detalle(Long id_detalle) {
        this.id_detalle = id_detalle;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
