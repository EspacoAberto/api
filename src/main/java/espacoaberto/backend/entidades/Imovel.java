package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import espacoaberto.backend.dto.ImgurApiResponse;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    private List<Acomodacao> acomodacoes;



    //@Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    private String comprovante;


    private Integer qtdQuartos;
    private Integer qtdBanheiros;
    private String cep;
    private String estado;
    private String cidade;
    private String logradouro;
    private String numero;

    @ElementCollection
    @CollectionTable(name = "linkfotos")
    private List<String> linkFotos;


    public List<Acomodacao> getAcomodacoes() {
        return acomodacoes;
    }

    public void setAcomodacoes(List<Acomodacao> acomodacoes) {
        this.acomodacoes = acomodacoes;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public Integer getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(Integer qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public Integer getQtdBanheiros() {
        return qtdBanheiros;
    }

    public void setQtdBanheiros(Integer qtdBanheiros) {
        this.qtdBanheiros = qtdBanheiros;
    }


    public List<String> getLinkFotos() {
        return linkFotos;
    }

    public void setLinkFotos(List<String> linkFotos) {
        this.linkFotos = linkFotos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}

