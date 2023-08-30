package peluCanina.peluCanina.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peluCanina.peluCanina.entity.Duenio;

@Repository
public interface IDuenioRepository extends JpaRepository <Duenio, Long> {

//    @Query("SELECT DISTINCT d FROM Duenio d LEFT JOIN FETCH d.mascotas")
//    List<Duenio> findAllWithMascotas();
}
