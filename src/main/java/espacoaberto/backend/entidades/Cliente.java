package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {
//    private List<Imovel> favoritos;


    @ManyToOne
    @JsonProperty
    private Agendamento agendamento;

    //constructor


    //methods
    @Override
    public void tornarPremium() {
        setIsPremium(true);
    }

    public Boolean autenticar(String email, String senha) {
        return email.equals(this.getEmail()) && senha.equals(this.getSenha());
    }

    //toString
    @Override
    public String toString() {
        return "Cliente{" +
//                "favoritos=" + favoritos +
                '}';
    }
}

