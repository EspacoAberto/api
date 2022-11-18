package espacoaberto.backend.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Anuncio implements Serializable {

    //    @ManyToMany
//    @JoinColumn(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnuncio;
    private Integer idAnunciante;
    //    @ManyToMany
//    @JoinColumn(name = "id")
 
    private Integer idImovel;
    private double preco;

    private String descricao;
    private String titulo;
    private Integer curtidas;

    //Getters and Setters
    public Integer getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(Integer idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Integer getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(Integer idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public Integer getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Integer idImovel) {
        this.idImovel = idImovel;
    }


//    public Anunciante getAnunciante() {
//        return anunciante;
//    }
//
//    public void setAnunciante(Anunciante anunciante) {
//        this.anunciante = anunciante;
//    }
//
//    public Imovel getImovel() {
//        return imovel;
//    }
//
//    public void setImovel(Imovel imovel) {
//        this.imovel = imovel;
//    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getCurtidas() {
        return curtidas;
    }
    public void setCurtidas(Integer curtidas) {
        this.curtidas = curtidas;
    }

}
