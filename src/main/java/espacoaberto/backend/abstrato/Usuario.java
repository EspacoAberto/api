package espacoaberto.backend.abstrato;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import espacoaberto.backend.entidades.Carteira;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Past
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    @JsonManagedReference
    private Carteira carteira;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    private Boolean isPremium = false;
    @Email
    private String email;
    private String senha;
    private String telefone;
    private String codigo;
    public abstract void tornarPremium();
}