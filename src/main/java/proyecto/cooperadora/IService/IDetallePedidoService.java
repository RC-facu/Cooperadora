package proyecto.cooperadora.IService;
import java.util.List;
import proyecto.cooperadora.Entidades.DetallePedido;

public interface IDetallePedidoService {
public List<DetallePedido> findAllDetallePedidos();
public DetallePedido saveDetallePedido(DetallePedido detallePedido);
}
