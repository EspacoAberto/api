package espacoaberto.backend.abstrato;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
public abstract class Usuario {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @Past
    private String dataNascimento;
    private String cpf;
    private Boolean isPremium;
    @Email
    private String email;

    private String senha;

    //constructor
    public Usuario(Integer id,String nome, String dataNascimento, String cpf, Boolean isPremium, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.isPremium = false;
        this.email = email;
        this.senha = senha;
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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

    //toString()

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", cpf='" + cpf + '\'' +
                ", isPremium=" + isPremium +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
