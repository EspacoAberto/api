package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReciboRepository extends JpaRepository<Recibo, Integer> {
    @Modifying
    @Transactional
    @Query("update Recibo p set p.relatorioExcel = ?2 where p.id = ?1")
    void setRelatorio(Integer id, byte[] foto);

    @Query("select p.relatorioExcel from Recibo p where p.id = ?1")
    byte[] getRelatorio(Integer id);
}
