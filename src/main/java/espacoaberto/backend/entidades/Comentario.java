package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;
import org.springframework.core.serializer.Serializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;

    @Id
    private int idUsuario;

    @Id
    private int idAnuncio;

    private String comentario;

    // get and set

}
