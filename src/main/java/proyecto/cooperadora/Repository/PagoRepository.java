package proyecto.cooperadora.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.cooperadora.Entidades.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
