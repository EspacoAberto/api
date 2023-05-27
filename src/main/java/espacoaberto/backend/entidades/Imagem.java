package espacoaberto.backend.entidades;
/*
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Imagem {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Anuncio anuncio;
    @ManyToOne
    @JsonBackReference
    private Imovel imovel;
    @Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    private byte[] photo;
    private String tipoImagem;

    public Imagem() {
    }

    public Imagem(Imovel imovel, byte[] photo, String tipoImagem) {
        this.imovel = imovel;
        this.photo = photo;
        this.tipoImagem = tipoImagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getTipoImagem() {
        return tipoImagem;
    }

    public void setTipoImagem(String tipoImagem) {
        this.tipoImagem = tipoImagem;
    }
} */