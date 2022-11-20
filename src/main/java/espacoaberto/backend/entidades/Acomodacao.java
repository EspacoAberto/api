package espacoaberto.backend.entidades;

<<<<<<< HEAD
import javax.persistence.*;
import javax.validation.constraints.NotNull;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
>>>>>>> main

@Entity
public class Acomodacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
<<<<<<< HEAD
    @ManyToOne
    @NotNull
    private Imovel imovel;

    public Acomodacao(Integer id, String descricao, Imovel imovel) {
        this.id = id;
        this.descricao = descricao;
        this.imovel = imovel;
    }
=======
>>>>>>> main

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
<<<<<<< HEAD

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
=======
}

>>>>>>> main
