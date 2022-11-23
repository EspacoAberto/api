package espacoaberto.backend.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Anuncio implements Serializable {
    @Id
    private Integer idAnuncio;
    private Integer idAnunciante;
    private Integer idImovel;
    private double preco;
    private String descricao;
    private String titulo;
    private Integer curtidas;
    private Integer visualizacoes;

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
    public Integer getVisualizacoes() {
        return visualizacoes;
    }
    public void setVisualizacoes(Integer visualizacoes) {
        this.visualizacoes = visualizacoes;
    }
}
