package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
}
