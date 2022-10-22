package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

}
