package espacoaberto.backend.abstrato;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Past
    private LocalDate dataNascimento;
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;
    private Boolean isPremium = false;
    @Email
    private String email;
    private String senha;

    private String codigo;

    private Boolean Login = false;
    private Boolean isAutenticado = false;


    public abstract void tornarPremium();
}
