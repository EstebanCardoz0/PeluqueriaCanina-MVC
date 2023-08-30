package peluCanina.peluCanina.service;

import java.util.List;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.entity.Duenio;

public interface IDuenioService {

    public void crearDuenio(Duenio duen);

    public Duenio traerDuenio(Long id);

    public List<Duenio> listarDuenios();

    public void borrarDuenio(Long id);

    public void editarDuenio(Duenio duen);

    public List<DTODuenio> listarDueniosDTO();
    

    public DTODuenio traerDuenioDTO(Long id);
}
