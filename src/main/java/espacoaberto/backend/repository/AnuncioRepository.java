package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    List<Anuncio> findByPrecoBetween(Double preco1, Double preco2);

}
