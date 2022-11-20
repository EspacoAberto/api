package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PutMapping("/tornarPremium/{id}")
    public ResponseEntity<Usuario> tornarPremium(@PathVariable Integer id) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario: usuarios ) {
            if (usuario.getId() == id){
                usuario.setPremium(true);
                usuarioRepository.save(usuario);
                return ResponseEntity.status(200).body(usuario);
            }
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("/autenticacao/{email}/{senha}")
    public ResponseEntity<Usuario> logonUsuario(@PathVariable String email,
                                                @PathVariable String senha) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuarioAtual : usuarios) {
            if (usuarioAtual.autenticar(email, senha)) {
                usuarioAtual.setAutenticado(true);
                usuarioRepository.save(usuarioAtual);
                return ResponseEntity.ok(usuarioAtual);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/autenticacao/{email}")
    public ResponseEntity<String> logoffUsuario(@PathVariable String email) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuarioAtual : usuarios) {
            if(usuarioAtual.getEmail().equals(email)) {
                if (usuarioAtual.getAutenticado()) {
                    usuarioAtual.setAutenticado(false);
                    usuarioRepository.save(usuarioAtual);
                    return ResponseEntity.ok().
                            body(String.format("Logoff do cliente %s concluído", usuarioAtual.getEmail()));
                } else {
                    return ResponseEntity.status(401).body(String.format("Cliente %s NÃO está autenticado", usuarioAtual.getEmail()));
                }
            }
        }
        return ResponseEntity.status(404).body(String.format("Cliente %s não encontrado", email));
    }

    @GetMapping("/autenticados")
    public ResponseEntity<List<Usuario>> getUsuariosAutenticados() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuarioAtual : usuarios){
            if(usuarioAtual.getAutenticado()){
                return ResponseEntity.ok().body(usuarios.stream()
                        .filter(Usuario::getAutenticado)
                        .collect(Collectors.toList()));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/logoff-todos-usuarios")
    public ResponseEntity<List<Usuario>> logoffGeral() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            usuario.setAutenticado(false);
            usuarioRepository.save(usuario);
        }
        return ResponseEntity.ok().body(usuarios);
    }

}

