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
    private Integer id_carteira;
    @OneToOne
    @JsonBackReference
    private Usuario fkUsuario;

    private Double saldo = 0.0;


    public Carteira(Usuario fkUsuario, Double saldo) {
        this.fkUsuario = fkUsuario;
        this.saldo = saldo;
    }
}
