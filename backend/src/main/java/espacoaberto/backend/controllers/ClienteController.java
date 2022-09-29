package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.entidades.Cliente;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/espacoAberto")
public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Imovel> imoveis = new ArrayList<>();


    // EndPoints do Cliente --------------------------------------------------------------------
    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes(){ return clientes;}

    @GetMapping("/listarImoveis")
    public List<Imovel> listariomveis(){ return imoveis;}

    @PostMapping("/cadastrarCliente")
    public Cliente cadastrarCliente(@RequestBody Cliente cliente){
        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);
        return cliente;
    }

    @PostMapping("/cliente/tornarPremium/{email}/{senha}")
    public Cliente tornarClientePremium(@PathVariable String email, @PathVariable String senha) {
        for (Cliente cliente: clientes ) {
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)){
                cliente.setPremium(true);
                return cliente;
            }
        }
        return null;
    }

    @PostMapping("/alugar/{nome}/{dataInicio}/{dataFim}/{imovel}")
    public String alugar( @PathVariable String nome, @PathVariable Integer dataInicio,
                          @PathVariable Integer dataFim, @PathVariable String imovel){
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nome)){
                return String.format("Imovel %s, alugado de %d até %d", imovel, dataInicio, dataFim);
            }
        }
        return "Usuario não encontrado";
    }

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

