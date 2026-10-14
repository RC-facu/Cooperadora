package proyecto.cooperadora.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.cooperadora.IService.IDetallePedidoService;
import proyecto.cooperadora.Entidades.DetallePedido;

@RestController
@RequestMapping("/api/DetallePedido")
public class DetallePedidoController {
@Autowired
private IDetallePedidoService pService;
@GetMapping
public List<DetallePedido> getAllDetallePedidos() {
return pService.findAllDetallePedidos();
}
@PostMapping
public DetallePedido createDetallePedido(@RequestBody DetallePedido detallePedido) {
return pService.saveDetallePedido(detallePedido);
}
}
