package proyecto.cooperadora.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.cooperadora.IService.IDetallePedidoService;
import proyecto.cooperadora.Entidades.DetallePedido;
import proyecto.cooperadora.Repository.DetallePedidoRepository;

@Service
public class DetallePedidoService implements IDetallePedidoService {
@Autowired
private DetallePedidoRepository pRepository;
public List<DetallePedido> findAllDetallePedidos() {
return pRepository.findAll();
}
public DetallePedido saveDetallePedido(DetallePedido detallePedido) {
return pRepository.save(detallePedido);
}
}
