package proyecto.cooperadora.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.cooperadora.IService.IPedidoService;
import proyecto.cooperadora.Entidades.Pedido;
import proyecto.cooperadora.Entidades.DetallePedido;
import proyecto.cooperadora.Repository.PedidoRepository;

@Service
public class PedidoService implements IPedidoService {
@Autowired
private PedidoRepository pRepository;
public List<Pedido> findAllPedidos() {
return pRepository.findAll();
}
public Pedido savePedido(Pedido pedido) {
// Las lineas del JSON no traen el pedido: se lo asignamos para que se guarde el id_pedido
if (pedido.getDetalles() != null) {
for (DetallePedido d : pedido.getDetalles()) {
d.setPedido(pedido);
}
}
return pRepository.save(pedido);
}
}
