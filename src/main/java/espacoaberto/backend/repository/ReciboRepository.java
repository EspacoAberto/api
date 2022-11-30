package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Recibo;
import espacoaberto.backend.service.ReciboService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReciboRepository extends JpaRepository<Recibo, Integer> {
    @Modifying
    @Transactional
    @Query("update Recibo p set p.reciboCsv = ?2 where p.id = ?1")
    void setRelatorio(Integer id, byte[] foto);

    @Query("select p.reciboCsv from Recibo p where p.id = ?1")
    byte[] getRelatorio(Integer id);
    List<Recibo> findAllById (Integer id);
}
