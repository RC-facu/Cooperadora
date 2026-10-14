package proyecto.cooperadora.IService;
import java.util.List;
import proyecto.cooperadora.Entidades.Producto;

public interface IProductoService {
public List<Producto> findAllProductos();
public Producto saveProducto(Producto producto);
}
