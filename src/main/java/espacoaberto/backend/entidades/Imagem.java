package espacoaberto.backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagem;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="anuncio_id")
    private Anuncio anuncio;

    @JsonIgnore
    @Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    private byte[] foto;
}
