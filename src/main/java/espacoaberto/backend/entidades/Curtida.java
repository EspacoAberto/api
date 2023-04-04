package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Curtida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Anuncio anuncio;
    private LocalDateTime momentoCurtida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public LocalDateTime getMomentoCurtida() {
        return momentoCurtida;
    }

    public void setMomentoCurtida(LocalDateTime momentoCurtida) {
        this.momentoCurtida = momentoCurtida;
    }
}
