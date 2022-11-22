package espacoaberto.backend.abstrato;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
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
    private Boolean isPremium;
    @Email
    private String email;
    private String senha;
    private Boolean isAutenticado;

    // private Boolean isAnunciante;

    //constructor

    //methods abstract
    public abstract void tornarPremium();

    //get and sett
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAutenticado() {return isAutenticado;}

    public void setAutenticado(Boolean autenticado) {isAutenticado = autenticado;}

    public abstract Boolean autenticar(String email, String senha);

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", cpf='" + cpf + '\'' +
                ", isPremium=" + isPremium +
                ", email='" + email + '\'' +
                ", isAutenticado=" + isAutenticado +
                ", senha='" + senha + '\'' +
                '}';
    }
}
