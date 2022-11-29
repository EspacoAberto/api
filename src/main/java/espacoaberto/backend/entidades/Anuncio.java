package espacoaberto.backend.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Anuncio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnuncio;

    private Integer idAnunciante;
    private Integer idImovel;
    private double preco;
    private String descricao;
    private String titulo;
    @Column(columnDefinition = "int default 0")
    private Integer curtidas;
    private Integer visualizacoes;
    @JsonIgnore
    @Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    private byte[] foto;

    //Getters and Setters

    public byte[] getFoto() {return foto;}

    public void setFoto(byte[] foto) {this.foto = foto;}
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
