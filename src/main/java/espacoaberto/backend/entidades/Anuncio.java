package espacoaberto.backend.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Anuncio implements Serializable {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "anunciante_id")
//    private Anunciante anunciante;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anuncio")
    private List<Imovel> imovels;


    private double preco;
    private String descricao;
    private String titulo;
    private Integer curtidas;

    //Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
//    public Anunciante getAnunciante() {
//        return anunciante;
//    }
//    public void setAnunciante(Anunciante anunciante) {
//        this.anunciante = anunciante;
//    }
//    public List<Imovel> getImovels() {
//        return imovels;
//    }
    public void setImovels(List<Imovel> imovels) {
        this.imovels = imovels;
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
}
