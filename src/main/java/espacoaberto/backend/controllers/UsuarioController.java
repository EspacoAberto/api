package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.Usuario.Input.InLogin;
import espacoaberto.backend.dto.Usuario.Output.OutLogin;
import espacoaberto.backend.entidades.Carteira;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.CarteiraRepository;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.UsuarioRepository;
import espacoaberto.backend.service.ServiceBase64;
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
    @Autowired
    private ServiceBase64 serviceBase64;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CarteiraRepository carteiraRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @GetMapping()
    public ResponseEntity<List<Usuario>> consultarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(usuarios);
    }

    @PutMapping("tornarPremium/{id}")
    public ResponseEntity<Usuario> tornarPremium(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()){
                usuario.get().setIsPremium(true);
                usuarioRepository.save(usuario.get());
                return ResponseEntity.status(200).body(usuario.get());
            }


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
                    ,usuario.get().getNome()
                    ,usuario.get().getEmail()
                    ,usuario.get().getCpf()
                    ,usuario.get().getIsPremium()
                    ,""
            );

            if (anuncianteRepository.findByEmail(entrada.getEmail()).isPresent()) {
                response.setTipoUsuario("anunciante");
            }

            if (clienteRepository.findByEmail(entrada.getEmail()).isPresent()) {
                response.setTipoUsuario("cliente");

            }
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(404).build();
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

    @GetMapping("{idUsuarioBase64}/carteira")
    public ResponseEntity<Carteira> consultarCarteira(@PathVariable String idUsuarioBase64){
        Integer idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idUsuarioBase64));
        Optional<Usuario> usuario = usuarioRepository.findById(idDecodificado);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get().getCarteira())
                : ResponseEntity.status(404).build();
    }

    @PutMapping("{idUsuarioBase64}/carteira/depositar/{valor}")
    public ResponseEntity<Carteira> despositarCateira(@PathVariable String idUsuarioBase64, @PathVariable Double valor){
        if(valor < 1) {
            return ResponseEntity.status(406).build();
        }

        Integer idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idUsuarioBase64));

        Optional<Usuario> usuario =  usuarioRepository.findById(idDecodificado);

        if (usuario.isPresent()) {
            Optional<Carteira> carteira = carteiraRepository.findById(usuario.get().getCarteira().getId_carteira());

            if(carteira.isPresent()) {
                carteira.get().setSaldo(carteira.get().getSaldo() + valor);
                return ResponseEntity.status(200).body(carteiraRepository.save(carteira.get()));
            }

            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(404).build();
    }
}