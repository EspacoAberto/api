package espacoaberto.backend.controllers;

//import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.Usuario.Input.InCadastro;
//import espacoaberto.backend.dto.Usuario.Input.InLogin;
//import espacoaberto.backend.dto.Usuario.Output.OutLogin;
//import espacoaberto.backend.entidades.Carteira;
import espacoaberto.backend.dto.Usuario.Input.InLogin;
import espacoaberto.backend.dto.Usuario.Output.OutLogin;
import espacoaberto.backend.entidades.Usuario;
import espacoaberto.backend.repository.UsuarioRepository;
//import espacoaberto.backend.service.ServiceBase64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    // @Autowired
    //private ServiceBase64 serviceBase64;
    @Autowired
    private UsuarioRepository usuarioRepository;
    //@Autowired
    //private CarteiraRepository carteiraRepository;

    @GetMapping()
    public ResponseEntity<List<Usuario>> consultarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultarUsuariosPorID(@PathVariable Integer id) {
       Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.isEmpty()
                ? ResponseEntity.status(404).build()
                : ResponseEntity.status(200).body(usuario.get());
    }

    @PostMapping()
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        String email = usuario.getEmail();
        Optional<Usuario> opUsuario = usuarioRepository.findByEmail(email);

        if (opUsuario.isPresent()) {
            return ResponseEntity.status(409).build();
        }

        // Converter DTO para Objeto Usuario
        //Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getCpf(), email, usuarioDTO.getSenha(), usuarioDTO.getTelefone());


        return ResponseEntity.status(201).body(this.usuarioRepository.save(usuario));
    }


    @PostMapping("login")
    public ResponseEntity<OutLogin> login(@RequestBody InLogin entrada) {
        OutLogin response;
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(
                entrada.getEmail(),
                entrada.getSenha()
        );

        if (usuario.isPresent()) {
            response = new OutLogin(
                    usuario.get().getId()
                    , usuario.get().getNome()
                    , usuario.get().getEmail()
                    , usuario.get().getCpf()
                    , usuario.get().getIsPremium()
                    , ""
            );


            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(404).build();
    }



    @PatchMapping("tornarPremium/{id}")
    public ResponseEntity<Usuario> tornarPremium(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()){
                usuario.get().setPremium(true);
                usuarioRepository.save(usuario.get());
                return ResponseEntity.status(200).body(usuario.get());
            }

    return ResponseEntity.notFound().build();
    }


    @PutMapping ("atualizar/{cpf}")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario entrada, @PathVariable String cpf){
        Optional<Usuario> usuario  = usuarioRepository.findByCpf(cpf);

        if(usuario.isPresent()){
            Usuario usuarioAtualizado = usuario.get();
            usuarioAtualizado.setNome(entrada.getNome());
            usuarioAtualizado.setEmail(entrada.getEmail());
            usuarioAtualizado.setDataNascimento(entrada.getDataNascimento());
            return ResponseEntity.status(200).body(usuarioRepository.save(usuarioAtualizado));
        }

        return ResponseEntity.status(404).build();
    }
}


