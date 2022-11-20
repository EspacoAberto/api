package espacoaberto.backend.entidades;

<<<<<<< HEAD
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> main

@Entity
public class Comodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
<<<<<<< HEAD
    @NotNull
    private String nome;
    @Min(0)
    private int quantidade;
    @ManyToOne
    @NotNull
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

=======

    private String nome;

    private Integer quantidade;

    // getter and setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
>>>>>>> main
    public void setNome(String nome) {
        this.nome = nome;
    }

<<<<<<< HEAD
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
=======
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
>>>>>>> main
}
