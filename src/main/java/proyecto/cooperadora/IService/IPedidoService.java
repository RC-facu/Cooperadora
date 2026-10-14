package proyecto.cooperadora.IService;
import java.util.List;
import proyecto.cooperadora.Entidades.Pedido;

public interface IPedidoService {
public List<Pedido> findAllPedidos();
public Pedido savePedido(Pedido pedido);
}
