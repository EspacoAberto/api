package espacoaberto.backend.repository;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    List<Avaliacao> findByUsuario(Usuario usuario);
    List<Avaliacao> findByAnuncio(Anuncio anuncio);
}
