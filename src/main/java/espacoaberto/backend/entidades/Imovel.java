package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @OneToOne
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    @JsonManagedReference
    private List<Acomodacao> acomodacoes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    @JsonManagedReference
    private List<Comodo> comodo;


    private boolean disponibilidade;



    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Acomodacao> getAcomodacoes() {
        return acomodacoes;
    }

    public void setAcomodacoes(List<Acomodacao> acomodacoes) {
        this.acomodacoes = acomodacoes;
    }

}

