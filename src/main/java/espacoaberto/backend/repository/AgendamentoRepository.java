package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
