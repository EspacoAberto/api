package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "imovel")
    @JsonManagedReference
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    @JsonManagedReference
    private List<Acomodacao> acomodacoes;



    @Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    private byte[] comprovante;

    private String tipoArquivoComprovante;

    private Integer qtdQuartos;
    private Integer qtdBanheiros;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imovel")
    @JsonManagedReference
    private List<Imagem> fotos;


    public List<Acomodacao> getAcomodacoes() {
        return acomodacoes;
    }

    public void setAcomodacoes(List<Acomodacao> acomodacoes) {
        this.acomodacoes = acomodacoes;
    }

    public byte[] getComprovante() {
        return comprovante;
    }

    public void setComprovante(byte[] comprovante) {
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

    public String getTipoArquivoComprovante() {
        return tipoArquivoComprovante;
    }

    public void setTipoArquivoComprovante(String tipoArquivoComprovante) {
        this.tipoArquivoComprovante = tipoArquivoComprovante;
    }


    public List<Imagem> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagem> fotos) {
        this.fotos = fotos;
    }
}

