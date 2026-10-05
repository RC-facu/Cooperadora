package proyecto.cooperadora.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_donacion;

    // Muchas donaciones pertenecen a un cliente (N a 1)
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"pedidos", "donaciones"}) // corta el loop del lado de Cliente
    private Cliente cliente;

    private Double monto;
    private String frecuencia; // unica / mensual
    private LocalDate fecha_inicio;
    private String estado; // activa / cancelada / pendiente

    // Una donacion (suscripcion mensual) genera varios pagos a lo largo del tiempo (1 a N)
    @OneToMany(mappedBy = "donacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("donacion") // corta el loop del lado de Pago
    private List<Pago> pagos = new ArrayList<>();

    public Donacion() {
    }

    public Donacion(Cliente cliente, Double monto, String frecuencia, LocalDate fecha_inicio, String estado) {
        this.cliente = cliente;
        this.monto = monto;
        this.frecuencia = frecuencia;
        this.fecha_inicio = fecha_inicio;
        this.estado = estado;
    }

    // Getters____________________________________________________

    public Long getId_donacion() {
        return id_donacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getMonto() {
        return monto;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public String getEstado() {
        return estado;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    // Setters____________________________________________________

    public void setId_donacion(Long id_donacion) {
        this.id_donacion = id_donacion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}
