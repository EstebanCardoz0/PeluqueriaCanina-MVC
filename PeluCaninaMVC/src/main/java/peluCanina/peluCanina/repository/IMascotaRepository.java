package peluCanina.peluCanina.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peluCanina.peluCanina.entity.Mascota;

@Repository

public interface IMascotaRepository extends JpaRepository <Mascota, Long> {
}
