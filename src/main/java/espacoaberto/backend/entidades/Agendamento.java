package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import espacoaberto.backend.abstrato.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dataCheckin;
    private String dataCheckout;

    @ManyToOne
    private Cliente usuario;
    @ManyToOne
    private Anuncio anuncio;

}
