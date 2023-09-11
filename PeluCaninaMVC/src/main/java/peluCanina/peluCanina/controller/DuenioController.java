package peluCanina.peluCanina.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.entity.Mascota;
import peluCanina.peluCanina.exceptions.MiException;
import peluCanina.peluCanina.service.IDuenioService;
import peluCanina.peluCanina.service.IMascotaService;

@Controller
@RequestMapping("/duenios")
public class DuenioController {

    @Autowired
    IDuenioService duenSer;

    @Autowired
    IMascotaService mascoSer;

    @GetMapping("/alta")
    public String crearDuenio(ModelMap modelo) {

        return "duenioAlta.html";
    }

    @PostMapping("/crearrrrr")
    public String crearDuenio(@RequestBody Duenio duen) {

        try {
            duenSer.crearDuenio(duen);
        } catch (MiException ex) {
            Logger.getLogger(DuenioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "El dueño se creó correctamente";
    }

    @PostMapping("/crear")
    public String crearDuenio(@RequestParam String nombre, @RequestParam String celular, @RequestParam String direccion,
            ModelMap modelo) throws MiException {

        try {
            Duenio duen = new Duenio();
            duen.setNombre(nombre);
            duen.setCelular(celular);
            duen.setDireccion(direccion);

            duenSer.crearDuenio(duen);

            modelo.put("exito", "mascota creada correctamente");

            return "index.html";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "duenioAlta.html";

        }

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
