package espacoaberto.backend.entidades;


import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Anunciante extends Usuario {
//    private List<Imovel> imoveisCadastrados;
    //constructor

    //methods
    @Override
    public void tornarPremium() { // -> abstratic
        setPremium(true);
    }

    public String atualizarImovel(Integer n, Imovel i){
        return null;
    }

}

