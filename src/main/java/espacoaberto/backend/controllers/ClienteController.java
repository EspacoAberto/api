package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.repository.UsuarioRepository;
import espacoaberto.backend.service.RandomString;
import espacoaberto.backend.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {


    @Autowired
    private RandomString randomString;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente novoCliente){
        novoCliente.setCodigo(randomString.gerarCodigoAlfanumerico());
        sendEmailService.enviar(novoCliente.getEmail(), novoCliente.getNome(), novoCliente.getCodigo());
        return ResponseEntity.status(201).body(this.clienteRepository.save(novoCliente));
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> listarPorCpf(@PathVariable String cpf){
        // Decodificando o CPF que vem na requisição
        byte[] decodedBytes = Base64.getDecoder().decode(cpf);
        String cpfDecodificado = new String(decodedBytes);

        Optional<Cliente> cliente = clienteRepository.findByCpf(cpfDecodificado);

        if (cliente.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cliente.get());
    }



    @PutMapping ("/atualizar/{cpf}")
    public ResponseEntity<Cliente> atualizarUsuario(@RequestBody Cliente usuario, @PathVariable String cpf){


        // Decodificando o CPF que vem na requisição
        byte[] decodedBytes = Base64.getDecoder().decode(cpf);
        String cpfDecodificado = new String(decodedBytes);

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

