package proyecto.cooperadora.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.cooperadora.IService.IProductoService;
import proyecto.cooperadora.Entidades.Producto;
import proyecto.cooperadora.Repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService {
@Autowired
private ProductoRepository pRepository;
public List<Producto> findAllProductos() {
return pRepository.findAll();
}
public Producto saveProducto(Producto producto) {
return pRepository.save(producto);
}
}
