package espacoaberto.backend.dto;

import espacoaberto.backend.entidades.Imovel;

import javax.persistence.*;

@Entity
public class ImagemDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagem;
    private byte[] imagem;
    private String tipoArquivo;
    @ManyToOne
    private espacoaberto.backend.entidades.Imovel Imovel;


    public ImagemDTO(String imagem, String tipoArquivo) {
        this.imagem = imagem.getBytes();
        this.tipoArquivo = tipoArquivo;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }


}
