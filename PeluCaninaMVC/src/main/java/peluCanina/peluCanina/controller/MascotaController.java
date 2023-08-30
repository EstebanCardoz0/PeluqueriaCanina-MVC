package peluCanina.peluCanina.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import peluCanina.peluCanina.DTO.DTOMascota;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.entity.Mascota;
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
    public String crearMascota(@RequestBody Mascota mas) {

        mascoSer.crearMascota(mas);
        return "La mascota se creó correctamente";
    }

    @GetMapping("/traer/{id}")
    public DTOMascota traerMascota(@PathVariable Long id) {
        return mascoSer.traerMascotaDTO(id);

    }

//    @GetMapping("/listar")
//    public List<DTOMascota> listarMascotas() {
//
//        return mascoSer.listarMascotasDTO();
//    }
    @GetMapping("/listar")
    public String listarMascotas(ModelMap modelo) {

        List<Mascota> mascotas = mascoSer.listarMascotas();

        modelo.addAttribute("mascotas", mascotas);

        return "mascotaLista.html";
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarMascota(@PathVariable Long id) {

        mascoSer.borrarMascota(id);
        return "Mascota borrada con éxito";
    }

    @PutMapping("/editar/{id}")
    public DTOMascota editarMascota(@PathVariable Long id, @RequestParam String nombre,
            @RequestParam String color, @RequestParam String raza, @RequestParam String atencionEspecial, @RequestParam String alergico,
            @RequestParam String observaciones, @RequestParam Long duen) {

        Duenio du = duenSer.traerDuenio(duen);

        Mascota mas = new Mascota(id, nombre, color, raza, atencionEspecial, alergico, observaciones, du);

        mascoSer.editarMascota(mas);

        return mascoSer.traerMascotaDTO(id);

    }

}//final
