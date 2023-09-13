package peluCanina.peluCanina.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import peluCanina.peluCanina.DTO.DTOMascota;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.entity.Mascota;
import peluCanina.peluCanina.exceptions.MiException;
import peluCanina.peluCanina.service.IDuenioService;
import peluCanina.peluCanina.service.IMascotaService;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    IMascotaService mascoSer;

    @Autowired
    IDuenioService duenSer;

    @GetMapping("/alta")
    public String crearMascota(ModelMap modelo) {
        List<Duenio> duenios = duenSer.listarDuenios();

        modelo.addAttribute("duenios", duenios);

        return "mascotaAlta.html";
    }

    @PostMapping("/crear")
    public String crearMascota(@RequestParam String nombre, @RequestParam String color, @RequestParam String raza,
            @RequestParam String alergico, @RequestParam String atencionEspecial,
            @RequestParam(required = false) String observaciones, @RequestParam(required = false) Duenio duen,
            ModelMap modelo) {

        try {
            Mascota mas = new Mascota();
            mas.setNombre(nombre);
            mas.setColor(color);
            mas.setRaza(raza);
            mas.setAlergico(alergico);
            mas.setAtencionEspecial(atencionEspecial);
            mas.setObservaciones(observaciones);
            mas.setDuen(duen);

            mascoSer.crearMascota(mas);

            modelo.put("exito", "mascota creada correctamente");

            return "redirect:/mascotas/listar";

        } catch (MiException ex) {
            List<Duenio> duenios = duenSer.listarDuenios();
            modelo.addAttribute("duenios", duenios);

            modelo.put("error", ex.getMessage());
            return "mascotaAlta.html";

        }

    }

    @GetMapping("/traer")
    public String idTraer(@RequestParam String id, ModelMap modelo) throws Exception {

        return "redirect:/mascotas/traer/" + id;

    }

    @GetMapping("/traer/{id}")
    public String traerMascota(@PathVariable Long id, ModelMap modelo) throws Exception {

        try {
            DTOMascota masco = mascoSer.traerMascotaDTO(id);
            modelo.addAttribute("masco", masco);

            return "mascotaTraer.html";
        } catch (Exception e) {

            return "errorMascotaTraer.html";

        }

    }

    @GetMapping("/listar")
    public String listarMascotas(ModelMap modelo) throws MiException {

        List<Mascota> mascotas = mascoSer.listarMascotas();

        modelo.addAttribute("mascotas", mascotas);

        return "mascotaLista.html";
    }

    @GetMapping("/borrar/{id}")
    public String borrarMascota(@PathVariable Long id) {

        if (duenSer.traerDuenio(id).getMascotas() == null ) {
            mascoSer.borrarMascota(id);
            return "redirect:/mascotas/listar";
        } else {
//            System.out.println("g");
            return "errorDuenioBorrar.html";

        }

    }

    @GetMapping("/editar/{id}")
    public String editarMascota(@PathVariable Long id, ModelMap modelo) {

        modelo.addAttribute("duenios", duenSer.listarDuenios());

        modelo.put("mascota", mascoSer.traerMascota(id));

        return "mascotaEditar.html";
    }

    @PostMapping("/editar/{id}")
    public String editarMascota(@PathVariable Long id, @RequestParam String nombre,
            @RequestParam String color, @RequestParam String raza, @RequestParam String atencionEspecial, @RequestParam String alergico,
            @RequestParam(required = false) String observaciones,
            @RequestParam(required = false) Duenio duen, ModelMap modelo) throws MiException {

        try {
            Mascota mas = new Mascota(id, nombre, color, raza, atencionEspecial, alergico, observaciones, duen);
            mascoSer.editarMascota(mas);

            return "redirect:../listar";

        } catch (MiException ex) {

            return "redirect:../editar/" + id;
        }
    }

}//final
