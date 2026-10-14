package proyecto.cooperadora.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.cooperadora.IService.IClienteService;
import proyecto.cooperadora.Entidades.Cliente;

@RestController
@RequestMapping("/api/Cliente")
public class ClienteController {
@Autowired
private IClienteService pService;
@GetMapping
public List<Cliente> getAllClientes() {
return pService.findAllClientes();
}
@PostMapping
public Cliente createCliente(@RequestBody Cliente cliente) {
return pService.saveCliente(cliente);
}
}
