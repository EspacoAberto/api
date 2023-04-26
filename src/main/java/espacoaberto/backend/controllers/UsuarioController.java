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
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Usuario>> getUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return (usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(usuarios));

    }

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

    @PostMapping("/login")
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

    @PutMapping ("/atualizar/{cpf}")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable String cpf){



        Optional<Usuario> usuarioASerAtualizadoOP  = usuarioRepository.findByCpf(cpf);

        if(usuarioASerAtualizadoOP.isPresent()){
            Usuario usuarioASerAtualizado = usuarioASerAtualizadoOP.get();
            usuarioASerAtualizado.setNome(usuario.getNome());
            usuarioASerAtualizado.setEmail(usuario.getEmail());
            usuarioASerAtualizado.setDataNascimento(usuario.getDataNascimento());
            return ResponseEntity.status(200).body(usuarioRepository.save(usuarioASerAtualizado));
        }

        return ResponseEntity.status(404).build();




    }

    @GetMapping("/{id_usuario}/carteira/consultarSaldo")
    public ResponseEntity<Carteira> consultaSaldoCarteira(@PathVariable String id_usuario){

        List<Usuario> allUsers = usuarioRepository.findAll();

        String idDecodificado = ServiceBase64.descriptografaBase64(id_usuario.toString());

        int idDecodificadoInt = Integer.parseInt(idDecodificado);

        for (Usuario user: allUsers) {
            if(user.getId() == idDecodificadoInt){
                return ResponseEntity.status(200).body(user.getCarteira());
            }
        }
        return ResponseEntity.status(404).build();

    }

    @PutMapping("/{id_usuario}/carteira/depositar/{valor}")
    public ResponseEntity<Carteira> despositarSaldoCateira(@PathVariable String id_usuario, @PathVariable Double valor){
        if(valor < 1) {
            return ResponseEntity.status(406).build();
        }

        String idDecodificado = ServiceBase64.descriptografaBase64(id_usuario.toString());

        int idDecodificadoInt = Integer.parseInt(idDecodificado);

        List<Usuario> allUsers = usuarioRepository.findAll();

        for (Usuario user: allUsers) {
            if(user.getId() == idDecodificadoInt){
                Optional<Carteira> carteiraASerAtualizadaOP = carteiraRepository.findById(user.getCarteira().getId_carteira());
                Carteira carteiraAtualizada = carteiraASerAtualizadaOP.get();
                carteiraAtualizada.setSaldo(carteiraAtualizada.getSaldo() + valor);
                return ResponseEntity.status(200).body(this.carteiraRepository.save(carteiraAtualizada));
            }
        }
        return ResponseEntity.status(404).build();

    }

}