package peluCanina.peluCanina.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.DTO.DTOMascota;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.exceptions.MiException;
import peluCanina.peluCanina.repository.IDuenioRepository;

@Service
public class DuenioService implements IDuenioService {

    @Autowired
    IDuenioRepository duenioRepo;

    @Autowired
    IMascotaService mascoService;

    @Override
    public void crearDuenio(Duenio duen) throws MiException {

        this.validar(duen.getNombre(), duen.getCelular(), duen.getDireccion());

        duenioRepo.save(duen);
    }

    @Override
    public Duenio traerDuenio(Long id) {

        return duenioRepo.findById(id).orElse(null);
    }

    @Override
    public DTODuenio traerDuenioDTO(Long id) {

        Duenio duen = this.traerDuenio(id);

        DTODuenio dtoDuen = new DTODuenio();

        dtoDuen.setIdDTODuenio(id);
        dtoDuen.setNombre(duen.getNombre());
        dtoDuen.setDireccion(duen.getDireccion());
        dtoDuen.setCelular(duen.getCelular());
        dtoDuen.setMascosDTO(null);

        List<DTOMascota> mascosDTO = new ArrayList();

        for (DTOMascota masco : mascoService.listarMascotasDTO()) {

            if (masco.getIdDuenio().equals(id.toString())) {

                mascosDTO.add(masco);

            }

        }

        dtoDuen.setMascosDTO(mascosDTO);
        return dtoDuen;
    }

    @Override
    public List<Duenio> listarDuenios() {

        return duenioRepo.findAll();
    }

    @Override
    public void borrarDuenio(Long id) {

        duenioRepo.deleteById(id);
    }

    @Override
    public void editarDuenio(Duenio duen) throws MiException {

        this.validar(duen.getNombre(), duen.getCelular(), duen.getDireccion());

        duenioRepo.save(duen);
    }

    @Override
    public void validar(String nombre, String celular, String direccion) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
        if (celular.isEmpty() || celular == null) {
            throw new MiException("El celular no puede ser nulo o estar vacío");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("La dirección no puede ser nula o estar vacía");
        }

    }

}
