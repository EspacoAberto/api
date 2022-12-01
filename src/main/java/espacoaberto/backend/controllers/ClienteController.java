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
import java.util.List;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(clientes);
    }

}

