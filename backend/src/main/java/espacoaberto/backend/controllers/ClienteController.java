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
@RequestMapping("/espacoAberto")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ImovelRepository imovelRepository;


    // EndPoints do Cliente --------------------------------------------------------------------
    @GetMapping("/listarClientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(clientes);
    }
//    @GetMapping("/listarImoveis")
//    public List<Imovel> listariomveis(){ return ;}

    @PostMapping("/cadastrarCliente")
    public ResponseEntity<Usuario> cadastrarAnunciante(@RequestBody Cliente novoCliente){
        return ResponseEntity.status(201).body(this.clienteRepository.save(novoCliente));
    }

    @PostMapping("/cliente/tornarPremium/{email}/{senha}")
    public ResponseEntity<Cliente> tornarClientePremium(@PathVariable String email, @PathVariable String senha) {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente: clientes ) {
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)){
                cliente.setPremium(true);
                return ResponseEntity.status(200).body(cliente);
            }
        }
        return ResponseEntity.status(400).build();
    }

//    @PostMapping("/alugar/{nome}/{dataInicio}/{dataFim}/{imovel}")
//    public String alugar( @PathVariable String nome, @PathVariable Integer dataInicio,
//                          @PathVariable Integer dataFim, @PathVariable String imovel){
//        for (Cliente cliente : clientes) {
//            if (cliente.getNome().equals(nome)){
//                return String.format("Imovel %s, alugado de %d até %d", imovel, dataInicio, dataFim);
//            }
//        }
//        return "Usuario não encontrado";
//    }

    @PostMapping("/comentar")
    public String comentar(){//@RequestBody Comentario c){
        return "HI";
    }

//    @PostMapping("{nome}/favoritar/{nomeImovel}")
//    public String favoritar( @PathVariable String nome,@PathVariable String nomeImovel ){
//        for (Cliente cliente: clientes) {
//            if (cliente.getNome().equals(nome)){
//                for (Imovel imovel: imoveis) {
//                    if (imovel.getNome().equals(nomeImovel)){
////                        cliente.addFavoritos(imovel);
//                        return String.format("O Imovel %s foi para lista de favoritos", imovel.getNome());
//                    }
//                }
//            }
//        }
//        return "Impossivel favoritar";
//    }

//    @GetMapping("{nomeCliente}/favoritos")
//    public String listarFavoritos(@PathVariable Cliente nomeCliente){
//        for (Cliente cliente: clientes) {
//            if (cliente.getNome().equals(nomeCliente)){
//                return cliente.getFavoritos().toString();
//            }
//        }
//        return "Impossivel efeturar requisição"; //** Comentado pois não é possivel fazer a criação do banco com lista como atributos **
//    }
}

