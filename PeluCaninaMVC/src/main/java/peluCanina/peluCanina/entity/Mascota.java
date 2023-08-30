package peluCanina.peluCanina.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String color;
    private String raza;
    private String atencionEspecial;
    private String alergico;
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "duenio")
    private Duenio duen;

}
