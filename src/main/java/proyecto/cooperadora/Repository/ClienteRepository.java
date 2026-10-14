package proyecto.cooperadora.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.cooperadora.Entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
