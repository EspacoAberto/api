package espacoaberto.backend.entidades;

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
    private Anunciante anunciante;
    @ManyToOne
    private Anuncio anuncio;

}
