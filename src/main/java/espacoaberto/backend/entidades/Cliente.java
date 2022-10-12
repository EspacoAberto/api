package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Cliente extends Usuario {
//    private List<Imovel> favoritos;

    //constructor

    //methods
    @Override
    public void tornarPremium() {
        setPremium(true);
    }

    //toString
    @Override
    public String toString() {
        return "Cliente{" +
//                "favoritos=" + favoritos +
                '}';
    }
}

