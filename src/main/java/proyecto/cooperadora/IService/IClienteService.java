package proyecto.cooperadora.IService;
import java.util.List;
import proyecto.cooperadora.Entidades.Cliente;

public interface IClienteService {
public List<Cliente> findAllClientes();
public Cliente saveCliente(Cliente cliente);
}
