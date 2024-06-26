package espacoaberto.backend.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Anuncio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnuncio;
    @ManyToOne
    private Anunciante anunciante;
    @ManyToOne
    private Imovel imovel;
    private double preco;
    private String descricao;
    private String titulo;


    private String disponibilidade;
    @Column(columnDefinition = "int default 0")
    private Integer curtidas;
    @Column(columnDefinition = "int default 0")
    private Integer visualizacoes;
    @Column(columnDefinition = "double default 5.0")
    private Double avaliacao;
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

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
