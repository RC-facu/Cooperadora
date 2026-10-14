package proyecto.cooperadora.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.cooperadora.IService.IDonacionService;
import proyecto.cooperadora.Entidades.Donacion;

@RestController
@RequestMapping("/api/Donacion")
public class DonacionController {
@Autowired
private IDonacionService pService;
@GetMapping
public List<Donacion> getAllDonaciones() {
return pService.findAllDonaciones();
}
@PostMapping
public Donacion createDonacion(@RequestBody Donacion donacion) {
return pService.saveDonacion(donacion);
}
}
