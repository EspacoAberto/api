package espacoaberto.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comodo {
    @Id
    private Integer id;
    private String nome;
    private int quantidade;
    @ManyToOne
    private Imovel imovel;

    public Comodo(String nome, int quantidade, Imovel imovel) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.imovel = imovel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public String toString() {
        return "Comodo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", imovel=" + imovel +
                '}';
    }
}
