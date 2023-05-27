package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {


    // List<Imovel> findByDisponibilidade(String disponibilidade);

   // @Modifying
   /// @Transactional
   // @Query("UPDATE Imovel Im SET Im.comprovante = ?2 where Im.id = ?1")
    //void atualizarDocumentoPorId(int id, byte[] comprovante);

   // @Modifying
   // @Transactional
   // @Query("UPDATE Imovel Im SET Im.tipoArquivoComprovante = ?1 where Im.id = ?2")
  //  void atualizarTipoArquivoDocumentoPorId(String tipoArquivo, Integer id);







}
