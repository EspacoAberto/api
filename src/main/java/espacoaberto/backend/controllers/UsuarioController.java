package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
                usuario.setIsPremium(true);
                usuarioRepository.save(usuario);
                return ResponseEntity.status(200).body(usuario);
            }
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/autenticacao/{email}/{codigo}")
    public ResponseEntity<Usuario> autenticarConta(@PathVariable String email,
                                                @PathVariable String codigo) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuarioAtual : usuarios) {
            if (email.equals(usuarioAtual.getEmail())) {
                if (usuarioAtual.getIsAutenticado() == false){
                    if(codigo.equals(usuarioAtual.getCodigo())){
                        usuarioAtual.setIsAutenticado(true);
                        usuarioRepository.save(usuarioAtual);
                        return ResponseEntity.status(200).body(usuarioAtual);
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login/{email}/{senha}")
    public ResponseEntity<Usuario> logonUsuario(@PathVariable String email,
                                                @PathVariable String senha) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuarioAtual : usuarios) {
            if (email.equals(usuarioAtual.getEmail())) {
                if(senha.equals(usuarioAtual.getSenha())){
                    usuarioAtual.setLogin(true);
                    usuarioRepository.save(usuarioAtual);
                    return ResponseEntity.ok(usuarioAtual);

                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/logoff/{email}")
    public ResponseEntity<String> logoffUsuario(@PathVariable String email) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuarioAtual : usuarios) {
            if(usuarioAtual.getEmail().equals(email)) {
                if (usuarioAtual.getLogin()) {
                    usuarioAtual.setLogin(false);
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
            if(usuarioAtual.getIsAutenticado()){
                return ResponseEntity.ok().body(usuarios.stream()
                        .filter(Usuario::getIsAutenticado)
                        .collect(Collectors.toList()));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/logoff-todos-usuarios")
    public ResponseEntity<List<Usuario>> logoffGeral() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            usuario.setIsAutenticado(false);
            usuarioRepository.save(usuario);
        }
        return ResponseEntity.ok().body(usuarios);
    }

}

