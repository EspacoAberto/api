package espacoaberto.backend.repository;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import espacoaberto.backend.entidades.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {


}
