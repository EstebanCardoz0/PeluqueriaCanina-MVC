package peluCanina.peluCanina.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peluCanina.peluCanina.DTO.DTOMascota;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.entity.Mascota;
import peluCanina.peluCanina.exceptions.MiException;
import peluCanina.peluCanina.repository.IMascotaRepository;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    IMascotaRepository mascoRepo;

//    @Autowired @Lazy
//    IDuenioService duenSer;
    @Override
    public void crearMascota(Mascota mas) throws MiException {

        validar(mas.getNombre(), mas.getColor(), mas.getRaza(), mas.getAlergico(),
                mas.getAtencionEspecial(), mas.getDuen());

        mascoRepo.save(mas);
    }

    @Override
    public Mascota traerMascota(Long id) {
        return mascoRepo.findById(id).orElse(null);
    }

    public DTOMascota traerMascotaDTO(Long id) {

        Mascota masco = this.traerMascota(id);

        DTOMascota dtoMasco = new DTOMascota();

        dtoMasco.setIdDTOMascota(id);
        dtoMasco.setNombre(masco.getNombre());
        dtoMasco.setColor(masco.getColor());
        dtoMasco.setRaza(masco.getRaza());
        dtoMasco.setAlergico(masco.getAlergico());
        dtoMasco.setAtencionEspecial(masco.getAtencionEspecial());
        dtoMasco.setObservaciones(masco.getObservaciones());
        if (masco.getDuen() != null) {
            dtoMasco.setIdDuenio(masco.getDuen().getId().toString());
            dtoMasco.setNombreDuenio(masco.getDuen().getNombre());

        } else {
            dtoMasco.setIdDuenio("-");
            dtoMasco.setNombreDuenio("No tiene");
        }

        return dtoMasco;
    }

    @Override
    public List<Mascota> listarMascotas() {
        return mascoRepo.findAll();
    }

    @Override
    public List<DTOMascota> listarMascotasDTO() {

        List<DTOMascota> listarMascotas = new ArrayList();

        for (Mascota masco : mascoRepo.findAll()) {

            DTOMascota dtoMasco = new DTOMascota();

            dtoMasco.setIdDTOMascota(masco.getId());
            dtoMasco.setNombre(masco.getNombre());
            dtoMasco.setColor(masco.getColor());
            dtoMasco.setRaza(masco.getRaza());
            dtoMasco.setAlergico(masco.getAlergico());
            dtoMasco.setAtencionEspecial(masco.getAtencionEspecial());
            dtoMasco.setObservaciones(masco.getObservaciones());

            if (masco.getDuen() != null) {
                dtoMasco.setNombreDuenio(masco.getDuen().getNombre());
                dtoMasco.setIdDuenio(masco.getDuen().getId().toString());
            } else {
                dtoMasco.setNombreDuenio("No tiene");
                dtoMasco.setIdDuenio("-");

            }

            listarMascotas.add(dtoMasco);
        }

        return listarMascotas;
    }

    @Override
    public void borrarMascota(Long id) {
        mascoRepo.deleteById(id);
    }

    @Override
    public void editarMascota(Mascota mas) throws MiException {

        this.validar(mas.getNombre(), mas.getColor(), mas.getRaza(), mas.getAlergico(),
                mas.getAtencionEspecial(), mas.getDuen());

        mascoRepo.save(mas);

    }

    @Override
    public void validar(String nombre, String color, String raza, String alergico, String atencionEspecial,
            Duenio duenio) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
        if (color.isEmpty() || color == null) {
            throw new MiException("El color no puede ser nulo o estar vacío");
        }
        if (raza.isEmpty() || raza == null) {
            throw new MiException("La raza no puede ser nula o estar vacía");
        }
        if (alergico.isEmpty() || alergico == null) {
            throw new MiException("Se debe indicar si la mascota el alérgica o no");
        }
        if (atencionEspecial.isEmpty() || atencionEspecial == null) {
            throw new MiException("Se debe indicar si la mascota requiere atención especial o no");
        }
        if (duenio == null) {
            throw new MiException("Se debe indicar el dueño de la mascota");
        }

    }
}
