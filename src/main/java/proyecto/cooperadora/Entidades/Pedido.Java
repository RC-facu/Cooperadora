package proyecto.cooperadora.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    // Muchos pedidos pertenecen a un cliente (N a 1)
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"pedidos", "donaciones"}) // corta el loop del lado de Cliente
    private Cliente cliente;

    private LocalDate fecha;
    private String estado; // pendiente / pagado / entregado / cancelado
    private Double total;

    // Un pedido tiene varias lineas de detalle (1 a N)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("pedido") // corta el loop del lado de DetallePedido
    private List<DetallePedido> detalles;

    // Un pedido genera como maximo un pago (1 a 0/1)
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pedido") // corta el loop del lado de Pago
    private Pago pago;

    public Pedido() {
    }

    public Pedido(Cliente cliente, LocalDate fecha, String estado, Double total) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    // Getters____________________________________________________

    public Long getId_pedido() {
        return id_pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Double getTotal() {
        return total;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Pago getPago() {
        return pago;
    }

    // Setters____________________________________________________

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
