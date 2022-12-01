package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "dataVisualizacao"
)
public class Visualizacao {
    @Id
    private LocalDateTime dataVisualizacao;
    @ManyToOne
    private Imovel imovel;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Anuncio anuncio;

    public Visualizacao(Imovel imovel, Usuario usuario, Anuncio anuncio) {
        this.dataVisualizacao = LocalDateTime.now();
        this.imovel = imovel;
        this.usuario = usuario;
        this.anuncio = anuncio;
    }

    public Visualizacao() {

    }

    // Getters e Setters


    public LocalDateTime getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(LocalDateTime dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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
}
