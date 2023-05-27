package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.UsuarioRepository;
import espacoaberto.backend.service.RandomString;
import espacoaberto.backend.service.SendEmailService;
import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private RandomString randomString;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente novoCliente){
        // Validando se o e-mail cadastrado já existe
        String email = novoCliente.getEmail();
        Optional<Usuario> opCliente = usuarioRepository.findByEmail(email);

        if (opCliente.isPresent()) {
            return ResponseEntity.status(409).build();
        }
        novoCliente.setSaldo(0.0);
        return ResponseEntity.status(201).body(clienteRepository.save(novoCliente));
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(clientes);
    }


    @GetMapping("/{idBase64}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable String idBase64){
        Integer idDecodificado;

        try{
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<Cliente> c = clienteRepository.findById(idDecodificado);
            return (c.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(c.get()));
        }catch (Exception e){
            System.out.println("Não foi possível converter o ID de base 64");
        }

        return ResponseEntity.status(404).build();

    }

    @GetMapping("/listarPorCpf/{cpf}")
    public ResponseEntity<Cliente> listarPorCpf(@PathVariable String cpf){
        String cpfDecodificado = ServiceBase64.descriptografaBase64(cpf);

        Optional<Cliente> cliente = clienteRepository.findByCpf(cpfDecodificado);

        if (cliente.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cliente.get());
    }



    @PutMapping ("/atualizar/{cpf}")
    public ResponseEntity<Cliente> atualizarUsuario(@RequestBody Cliente usuario, @PathVariable String cpf){


        String cpfDecodificado = ServiceBase64.descriptografaBase64(cpf);

        Optional<Cliente> usuarioASerAtualizadoOP  = clienteRepository.findByCpf(cpfDecodificado);

        if(usuarioASerAtualizadoOP.isPresent()){
            Cliente usuarioASerAtualizado = usuarioASerAtualizadoOP.get();
            usuarioASerAtualizado.setNome(usuario.getNome());
            usuarioASerAtualizado.setEmail(usuario.getEmail());
            usuarioASerAtualizado.setDataNascimento(usuario.getDataNascimento());
            return ResponseEntity.status(200).body(clienteRepository.save(usuarioASerAtualizado));
        }

        return ResponseEntity.status(404).build();




    }

}

