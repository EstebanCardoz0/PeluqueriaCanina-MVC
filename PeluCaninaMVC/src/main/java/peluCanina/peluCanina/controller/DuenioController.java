package peluCanina.peluCanina.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.service.IDuenioService;

@RestController
@RequestMapping("/duenios")
public class DuenioController {

    @Autowired
    IDuenioService duenSer;

    @PostMapping("/crear")
    public String crearDuenio(@RequestBody Duenio duen) {

        duenSer.crearDuenio(duen);
        return "El dueño se creó correctamente";
    }

    @GetMapping("/traer/{id}")
    public DTODuenio traerDuenio(@PathVariable Long id) {
        return duenSer.traerDuenioDTO(id);

    }

    @GetMapping("/listar")
    public List<DTODuenio> listarDuenio() {

        return duenSer.listarDueniosDTO();
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarDuenio(@PathVariable Long id) {

        duenSer.borrarDuenio(id);
        return "Dueño borrado con éxito";
    }

    @PutMapping("/editar/{id}")
    public DTODuenio editarDuenio(@PathVariable Long id, @RequestParam String nombre,
            @RequestParam String celular, @RequestParam String direccion) {

        Duenio duen = new Duenio();
        duen.setNombre(nombre);
        duen.setDireccion(direccion);
        duen.setCelular(celular);
        duen.setId(id);

   

        duenSer.editarDuenio(duen);

        return duenSer.traerDuenioDTO(id);

    }

}
