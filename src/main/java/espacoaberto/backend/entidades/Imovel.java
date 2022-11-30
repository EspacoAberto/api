package espacoaberto.backend.entidades;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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

    @ManyToOne
    private Comodo comodo;

    @ManyToOne
    private Acomodacao acomodacao;

    private boolean disponibilidade;


}

