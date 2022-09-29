package espacoaberto.backend.entidades;


import espacoaberto.backend.abstrato.Usuario;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Anunciante extends Usuario {
//    private List<Imovel> imoveisCadastrados;
    //constructor
    public Anunciante(Integer id, String nome, String dataNascimento, String cpf,
                      Boolean isPremium, String email, String senha, List<Imovel> imoveisCadastrados) {
        super(id, nome, dataNascimento, cpf, isPremium, email, senha);
//        this.imoveisCadastrados = imoveisCadastrados;
    }

    //methods
    @Override
    public void tornarPremium() { // -> abstratic
        setPremium(true);
    }

//    public void cadastrarImovel(Imovel i){
//        imoveisCadastrados.add(i);
//    }

//    public String removerImovel(Imovel i){
//        imoveisCadastrados.remove(i);
//        return "imovel removido com sucesso";
//    }

    public String atualizarImovel(Integer n, Imovel i){
        return null;
    }

//    public String listarImoveis(){
//        return imoveisCadastrados.toString();
//    }


    //get and sett


//    public List<Imovel> getImoveisCadastrados() {
//        return imoveisCadastrados;
//    }

//    public void setImoveisCadastrados(List<Imovel> imoveisCadastrados) {
//        this.imoveisCadastrados = imoveisCadastrados;
//    } //** Comentado pois não é possivel fazer a criação do banco com lista como atributos **
}

