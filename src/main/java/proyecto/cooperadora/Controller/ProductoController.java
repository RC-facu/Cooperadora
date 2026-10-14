package proyecto.cooperadora.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.cooperadora.IService.IProductoService;
import proyecto.cooperadora.Entidades.Producto;

@RestController
@RequestMapping("/api/Producto")
public class ProductoController {
@Autowired
private IProductoService pService;
@GetMapping
public List<Producto> getAllProductos() {
return pService.findAllProductos();
}
@PostMapping
public Producto createProducto(@RequestBody Producto producto) {
return pService.saveProducto(producto);
}
}
