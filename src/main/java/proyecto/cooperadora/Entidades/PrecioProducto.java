package proyecto.cooperadora.Entidades;

import jakarta.persistence.Embeddable;

// Una fila de la tabla "precio": el precio de un producto para un talle.
// No es una entidad con id propio: vive dentro de Producto (ver Producto.precios).
@Embeddable
public class PrecioProducto {

    private String talle;
    private Double precio;

    public PrecioProducto() {
    }

    public PrecioProducto(String talle, Double precio) {
        this.talle = talle;
        this.precio = precio;
    }

    public String getTalle() {
        return talle;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
