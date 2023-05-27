package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Usuario;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Curtida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurtidaRepository extends JpaRepository<Curtida, Integer> {
    List<Curtida> findByUsuario(Usuario usuario);


}
