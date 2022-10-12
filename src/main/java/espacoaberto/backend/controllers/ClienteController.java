package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ImovelRepository imovelRepository;


    // EndPoints do Cliente --------------------------------------------------------------------
    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(clientes);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Cliente novoCliente){
        return ResponseEntity.status(201).body(this.clienteRepository.save(novoCliente));
    }

    @PutMapping("/tornarPremium/{id}")
    public ResponseEntity<Cliente> tornarPremium(@PathVariable Integer id) {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente: clientes ) {
            if (cliente.getId() == id){
                cliente.setPremium(true);
                return ResponseEntity.status(200).body(cliente);
            }
        }
        return ResponseEntity.status(400).build();
    }


    @PostMapping("/comentar")
    public String comentar(){//@RequestBody Comentario c){
        return "HI";
    }

}

