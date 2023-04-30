package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import espacoaberto.backend.abstrato.Usuario;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;

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
    private Usuario usuario;
    @ManyToOne
    private Anuncio anuncio;

    public Agendamento(String dataCheckin, String dataCheckout, Usuario usuario, Anuncio anuncio) {
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.usuario = usuario;
        this.anuncio = anuncio;
    }

    public Agendamento(){}
}
