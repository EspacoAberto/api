package espacoaberto.backend.repository;

import espacoaberto.backend.abstrato.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
