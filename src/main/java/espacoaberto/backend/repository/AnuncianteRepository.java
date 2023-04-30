package espacoaberto.backend.repository;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnuncianteRepository extends JpaRepository<Anunciante, Integer> {

    List<Anunciante> findByIsPremiumTrue();
    Optional<Anunciante> findByCpf(String cpf);
    Optional<Anunciante> findByEmail(String email);

}
