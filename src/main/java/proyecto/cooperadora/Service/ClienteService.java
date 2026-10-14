package proyecto.cooperadora.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.cooperadora.IService.IClienteService;
import proyecto.cooperadora.Entidades.Cliente;
import proyecto.cooperadora.Repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {
@Autowired
private ClienteRepository pRepository;
public List<Cliente> findAllClientes() {
return pRepository.findAll();
}
public Cliente saveCliente(Cliente cliente) {
return pRepository.save(cliente);
}
}
