package espacoaberto.backend.repository;

import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import espacoaberto.backend.entidades.Agendamento;
import espacoaberto.backend.entidades.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByAnuncio(Anuncio anuncio);
}
