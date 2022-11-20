package espacoaberto.backend.entidades;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotNull
    private Endereco endereco;

<<<<<<< HEAD
    public Imovel(Integer id, Endereco endereco) {
        this.id = id;
        this.endereco = endereco;
    }

    public Imovel() {

    }
=======
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @OneToOne
    private Endereco endereco;

    @ManyToOne
    private Comodo comodo;

    @ManyToOne
    private Acomodacao acomodacao;

    //Getters and Setters

>>>>>>> main

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

>>>>>>> main
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
<<<<<<< HEAD

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", endereco=" + endereco +
                '}';
    }
=======
>>>>>>> main
}

