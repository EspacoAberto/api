package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
    Optional<Imovel> findById(Integer integer);
}
