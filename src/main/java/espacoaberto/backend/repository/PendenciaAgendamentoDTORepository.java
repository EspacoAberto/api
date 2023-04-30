package espacoaberto.backend.repository;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PendenciaAgendamentoDTORepository extends JpaRepository<PendenciaAgendamentoDTO, Integer> {
    Optional<PendenciaAgendamentoDTO> findByIdUsuario(Integer idUsuario);
    List<PendenciaAgendamentoDTO> findByIdAnuncio(Integer idAnuncio);




}
