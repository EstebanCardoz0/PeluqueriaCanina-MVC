package peluCanina.peluCanina.service;

import java.util.ArrayList;
import java.util.List;
import static java.util.Optional.empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.DTO.DTOMascota;
import peluCanina.peluCanina.entity.Mascota;
import peluCanina.peluCanina.repository.IMascotaRepository;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    IMascotaRepository mascoRepo;

    @Override
    public void crearMascota(Mascota mas) {
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
    public void editarMascota(Mascota mas) {

        Mascota masco = this.traerMascota(mas.getId());
        masco.setNombre(mas.getNombre());
        masco.setRaza(mas.getRaza());
        masco.setColor(mas.getColor());
        masco.setAlergico(mas.getAlergico());
        masco.setAtencionEspecial(mas.getAtencionEspecial());
        masco.setObservaciones(mas.getObservaciones());
        masco.setDuen(mas.getDuen());
        this.crearMascota(masco);
    }
}
