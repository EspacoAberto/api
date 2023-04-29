package espacoaberto.backend.repository;

import espacoaberto.backend.entidades.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
