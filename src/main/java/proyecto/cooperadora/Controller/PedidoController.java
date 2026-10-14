package proyecto.cooperadora.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.cooperadora.IService.IPedidoService;
import proyecto.cooperadora.Entidades.Pedido;

@RestController
@RequestMapping("/api/Pedido")
public class PedidoController {
@Autowired
private IPedidoService pService;
@GetMapping
public List<Pedido> getAllPedidos() {
return pService.findAllPedidos();
}
@PostMapping
public Pedido createPedido(@RequestBody Pedido pedido) {
return pService.savePedido(pedido);
}
}
