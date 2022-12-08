package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {


    List<Anuncio> findByPrecoBetween(Double preco1, Double preco2);
    @Modifying
    @Transactional
    @Query("update Anuncio p set p.foto = ?2 where p.idAnuncio = ?1")
    void setFoto(Integer id, byte[] foto);

    @Query("select p.foto from Anuncio p where p.idAnuncio = ?1")
    byte[] getFoto(Integer id);

    @Query("select a from Anuncio a inner join a.imovel im where a.preco > ?1 and a.preco < ?2 and im.disponibilidade = ?3")
    List<Anuncio> getAnunciosFiltrados(Double preco1, Double preco2, String disponibilidade);

    @Query("select a from Anuncio a inner join a.imovel im where a.preco > ?1 and a.preco < ?2")
    List<Anuncio> getAnunciosFiltradosSemDisp(Double preco1, Double preco2);

    @Query("select an from Anuncio an inner join an.anunciante a where a.id = ?1")
    List<Anuncio> getAnunciosPorAnunciante(int idAnunciante);

    List<Anuncio> findByAnuncianteId(int idAnunciante);



}
