package peluCanina.peluCanina.service;

import java.util.List;
import peluCanina.peluCanina.DTO.DTOMascota;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.entity.Mascota;
import peluCanina.peluCanina.exceptions.MiException;

public interface IMascotaService {

    public void crearMascota(Mascota mas, Long duen) throws MiException;

    public Mascota traerMascota(Long id);

    public List<Mascota> listarMascotas();

    public List<DTOMascota> listarMascotasDTO();

    public DTOMascota traerMascotaDTO(Long id);

    public void borrarMascota(Long id);

    public void editarMascota(Mascota mas)throws MiException;

    public void validar(String nombre, String color, String raza, String alergico, String atencionEspecial, Long duenio) throws MiException;

}
