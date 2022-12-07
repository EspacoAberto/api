package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Anunciante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncianteRepository extends JpaRepository<Anunciante, Integer> {

    List<Anunciante> findByIsPremiumTrue();
}
