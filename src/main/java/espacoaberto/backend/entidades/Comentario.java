package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;
import org.springframework.core.serializer.Serializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @ManyToOne
    @NotNull
    private Anuncio anuncio;

    private String comentario;


}
