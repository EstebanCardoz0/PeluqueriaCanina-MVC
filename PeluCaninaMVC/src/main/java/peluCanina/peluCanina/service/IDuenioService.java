package peluCanina.peluCanina.service;

import java.util.List;
import peluCanina.peluCanina.DTO.DTODuenio;
import peluCanina.peluCanina.entity.Duenio;
import peluCanina.peluCanina.exceptions.MiException;

public interface IDuenioService {

    public void crearDuenio(Duenio duen) throws MiException;

    public Duenio traerDuenio(Long id);

    public List<Duenio> listarDuenios();

    public void borrarDuenio(Long id);

    public void editarDuenio(Duenio duen);

    public List<DTODuenio> listarDueniosDTO();
    

    public DTODuenio traerDuenioDTO(Long id);
    
    public void validar(String nombre, String celular, String direccion) throws MiException;
}
