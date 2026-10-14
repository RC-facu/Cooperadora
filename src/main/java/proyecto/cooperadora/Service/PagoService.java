package proyecto.cooperadora.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.cooperadora.IService.IPagoService;
import proyecto.cooperadora.Entidades.Pago;
import proyecto.cooperadora.Repository.PagoRepository;

@Service
public class PagoService implements IPagoService {
@Autowired
private PagoRepository pRepository;
public List<Pago> findAllPagos() {
return pRepository.findAll();
}
public Pago savePago(Pago pago) {
return pRepository.save(pago);
}
}
