package espacoaberto.backend.entidades;

import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Cliente extends Usuario {
//    private List<Imovel> favoritos;

    //constructor


    public Cliente(String nome, LocalDate dataNascimento, String cpf,
                    String email, String senha) {
        super(nome, dataNascimento, cpf, email, senha);
//        this.favoritos = favoritos;
    }

    //methods
    @Override
    public void tornarPremium() {
        setPremium(true);
    }


//    public void addFavoritos(Imovel i){
//        favoritos.add(i);
//    }

    //get and set
//    public List<Imovel> getFavoritos() {
//        return favoritos;
//    }

//    public void setFavoritos(List<Imovel> favoritos) {
//        this.favoritos = favoritos; //** Comentado pois não é possivel fazer a criação do banco com lista como atributos **
//    }

    //toString
    @Override
    public String toString() {
        return "Cliente{" +
//                "favoritos=" + favoritos +
                '}';
    }
}

