package proyecto.cooperadora.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.cooperadora.IService.IPagoService;
import proyecto.cooperadora.Entidades.Pago;

@RestController
@RequestMapping("/api/Pago")
public class PagoController {
@Autowired
private IPagoService pService;
@GetMapping
public List<Pago> getAllPagos() {
return pService.findAllPagos();
}
@PostMapping
public Pago createPago(@RequestBody Pago pago) {
return pService.savePago(pago);
}
}
