package proyecto.cooperadora.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    // Un pago puede venir de un Pedido (compra) ...
    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = true)
    @JsonIgnoreProperties({"detalles", "pago", "cliente"}) // corta el loop del lado de Pedido
    private Pedido pedido;

    // ... o de una Donacion, pero nunca de ambos a la vez
    @ManyToOne
    @JoinColumn(name = "id_donacion", nullable = true)
    @JsonIgnoreProperties({"pagos", "cliente"}) // corta el loop del lado de Donacion
    private Donacion donacion;

    private String metodo_pago; // tarjeta / transferencia / efectivo / mercadopago
    private Double monto;
    private LocalDate fecha_pago;
    private String estado; // pendiente / aprobado / rechazado
    private String id_transaccion_externa;

    public Pago() {
    }

    public Pago(Pedido pedido, Donacion donacion, String metodo_pago, Double monto,
                LocalDate fecha_pago, String estado) {
        this.pedido = pedido;
        this.donacion = donacion;
        this.metodo_pago = metodo_pago;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.estado = estado;
    }

    // Getters____________________________________________________

    public Long getId_pago() {
        return id_pago;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public Double getMonto() {
        return monto;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public String getEstado() {
        return estado;
    }

    public String getId_transaccion_externa() {
        return id_transaccion_externa;
    }

    // Setters____________________________________________________

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setDonacion(Donacion donacion) {
        this.donacion = donacion;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId_transaccion_externa(String id_transaccion_externa) {
        this.id_transaccion_externa = id_transaccion_externa;
    }

    // Chequeo de negocio: el pago viene de un pedido O de una donacion, nunca de ambos ni de ninguno.
    // (Se valida tambien a nivel de servicio antes de guardar, ver PagoService)
    public boolean origenValido() {
        return (pedido != null && donacion == null) || (pedido == null && donacion != null);
    }
}
