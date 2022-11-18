package espacoaberto.backend.entidades;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Acomodacao {
    @Id
    private Integer id;
    private String descricao;
    @ManyToOne
    private Imovel imovel;

    public Acomodacao(Integer id, String descricao, Imovel imovel) {
        this.id = id;
        this.descricao = descricao;
        this.imovel = imovel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public String toString() {
        return "Acomodacao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", imovel=" + imovel +
                '}';
    }
}
