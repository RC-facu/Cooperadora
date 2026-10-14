package proyecto.cooperadora.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.cooperadora.Entidades.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
