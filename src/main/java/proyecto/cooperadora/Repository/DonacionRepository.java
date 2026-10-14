package proyecto.cooperadora.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.cooperadora.Entidades.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
}
