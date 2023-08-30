package peluCanina.peluCanina.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Duenio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private String nombre;
    private String celular;
    private String direccion;
    @OneToMany(mappedBy = "duen")
    private List <Mascota> mascotas;

}
