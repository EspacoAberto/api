package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario {
//    private List<Imovel> favoritos;

    //constructor

    //methods
    @Override
    public void tornarPremium() {
        setPremium(true);
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

