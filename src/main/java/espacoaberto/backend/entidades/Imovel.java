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

    public Imovel(Integer id, Endereco endereco) {
        this.id = id;
        this.endereco = endereco;
    }

    public Imovel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", endereco=" + endereco +
                '}';
    }
}

