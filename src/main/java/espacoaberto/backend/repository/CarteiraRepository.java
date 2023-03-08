package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {

    Optional<Carteira> findById(Integer id);
}
