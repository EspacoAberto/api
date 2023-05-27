package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {


    List<Anuncio> findByPrecoBetween(Double preco1, Double preco2);

    //@Query("select a from Anuncio a inner join a.imovel im where a.preco > ?1 and a.preco < ?2 and im.disponibilidade = ?3")
    //List<Anuncio> getAnunciosFiltrados(Double preco1, Double preco2, String disponibilidade);

    List<Anuncio> findByPrecoGreaterThan(double preco);
    List<Anuncio> findByPrecoLessThan(double preco);
    List<Anuncio> findByPrecoBetween(double precoMin, double precoMax);
    List<Anuncio> findByDisponibilidade(String disponibilidade);
    List<Anuncio> findByDisponibilidadeAndPrecoBetween(String disponibilidade, double precoMin, double precoMax);

   // @Query("select a from Anuncio a " +
           // "inner join a.imovel im where a.preco > ?1 and a.preco < ?2")
    //List<Anuncio> getAnunciosFiltradosSemDisp(Double preco1, Double preco2);

   // @Query("select an from Anuncio an inner join an.usuario a where a.id = ?1")
    //List<Anuncio> getAnunciosPorAnunciante(int idAnunciante);

    //List<Anuncio> findByUsuarioId(int idAnunciante);
    List<Anuncio> findByUsuario(Usuario usuario);



}
