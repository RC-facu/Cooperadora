package proyecto.cooperadora.Entidades;
import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    
    
    private String nombre;
    private String apellido;
    private int curso_alumno;
    private String email;
    private int telefono;
   
   ///Constructores
    public Cliente(Long id_cliente, String nombre, String apellido, int curso_alumno, String email, int telefono) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso_alumno = curso_alumno;
        this.email = email;
        this.telefono = telefono;
    }
    
    
    ///getters
 
    public Long getId_cliente() {
        return id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getCurso_alumno() {
        return curso_alumno;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefono() {
        return telefono;
    }

    ///Setters
    
    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public void setCurso_alumno(int curso_alumno) {
        this.curso_alumno = curso_alumno;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
