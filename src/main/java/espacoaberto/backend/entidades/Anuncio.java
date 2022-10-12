package espacoaberto.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.io.Serializable;

@Entity
public class Anuncio implements Serializable {
    //    @ManyToMany
//    @JoinColumn(name = "id")
    @Id
    private int idAnunciante;
    //    @ManyToMany
//    @JoinColumn(name = "id")
    @Id
    private int idImovel;
    private double preco;
    private String descricao;
    private String titulo;
    private Integer curtidas;

    //Getters and Setters

    public int getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(int idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

    public int getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(int idImovel) {
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
