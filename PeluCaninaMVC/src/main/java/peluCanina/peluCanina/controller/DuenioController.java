package peluCanina.peluCanina.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.entity.Duenio;
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

            return "redirect:/duenios/listar";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "duenioAlta.html";

        }

    }

    @GetMapping("/traer")
    public String idtraer(@RequestParam String id, ModelMap modelo) throws Exception {

        return "redirect:/duenios/traer/" + id;
    }

    @GetMapping("/traer/{id}")
    public String traerDuenio(@PathVariable Long id, ModelMap modelo) throws Exception {

        try {
            DTODuenio duen = duenSer.traerDuenioDTO(id);
            modelo.put("duenio", duen);

            return "duenioTraer.html";
        } catch (Exception e) {
            System.out.println("el id dto es: " +id);
            return "errorDuenioTraer.html";
        }

    }

    @GetMapping("/listar")
    public String listarDuenio(ModelMap modelo)  {

        modelo.put("duenios", duenSer.listarDuenios());

        return "duenioLista.html";

    }

    @GetMapping("/borrar/{id}")
    public String borrarDuenio(@PathVariable Long id) {

        try {
            duenSer.borrarDuenio(id);
            return "redirect:/duenios/listar";
        } catch (Exception e) {
            return "errorDuenioBorrar.html";
        }
    }

    @GetMapping("editar/{id}")
    public String editarDuenio(@PathVariable Long id, ModelMap modelo) {

        modelo.put("duenio", duenSer.traerDuenio(id));

        return "duenioEditar.html";
    }

    @PostMapping("/editar/{id}")
    public String editarDuenio(@PathVariable Long id, @RequestParam String nombre,
            @RequestParam String celular, @RequestParam String direccion) throws MiException {

        try {
            duenSer.editarDuenio(new Duenio(id, nombre, celular, direccion, duenSer.traerDuenio(id).getMascotas()));
            return "redirect:../listar";
        } catch (MiException e) {
            return "redirect:../editar/" + id;
        }

    }

}
