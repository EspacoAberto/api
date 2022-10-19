package espacoaberto.backend.entidades;


import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Anunciante extends Usuario {
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anunciante")
//    private List<Anuncio> anuncios;
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

