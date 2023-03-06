package espacoaberto.backend.entidades;




import com.fasterxml.jackson.annotation.JsonBackReference;
import espacoaberto.backend.abstrato.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Carteira {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JsonBackReference
    private Usuario fkUsuario;

    private Double saldo = 0.0;



}