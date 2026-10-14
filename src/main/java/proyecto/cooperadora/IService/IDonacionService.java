package proyecto.cooperadora.IService;
import java.util.List;
import proyecto.cooperadora.Entidades.Donacion;

public interface IDonacionService {
public List<Donacion> findAllDonaciones();
public Donacion saveDonacion(Donacion donacion);
}
