package proyecto.cooperadora.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.cooperadora.IService.IDonacionService;
import proyecto.cooperadora.Entidades.Donacion;
import proyecto.cooperadora.Repository.DonacionRepository;

@Service
public class DonacionService implements IDonacionService {
@Autowired
private DonacionRepository pRepository;
public List<Donacion> findAllDonaciones() {
return pRepository.findAll();
}
public Donacion saveDonacion(Donacion donacion) {
return pRepository.save(donacion);
}
}
