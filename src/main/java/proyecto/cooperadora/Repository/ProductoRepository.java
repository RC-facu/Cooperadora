package proyecto.cooperadora.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.cooperadora.Entidades.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
