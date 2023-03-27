package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
