package espacoaberto.backend.repository;

import espacoaberto.backend.abstrato.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
        Optional<Usuario> findByCpf(String cpf);
        Optional<Usuario> findByEmailAndSenha(String email, String Senha);
}