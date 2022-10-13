package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
}