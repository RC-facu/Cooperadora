package proyecto.cooperadora.IService;
import java.util.List;
import proyecto.cooperadora.Entidades.Pago;

public interface IPagoService {
public List<Pago> findAllPagos();
public Pago savePago(Pago pago);
}
