package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Endereco;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.EnderecoRepository;
import espacoaberto.backend.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrar/{id}")
    public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable int id, @RequestBody Endereco endereco){
        Optional<Imovel> im = imovelRepository.findById(id);

        if(im.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Endereco e = new Endereco(endereco.getCep(), endereco.getEstado(), endereco.getCidade(), endereco.getLogradouro(), endereco.getNumero(), im.get());


        return ResponseEntity.status(200).body( this.enderecoRepository.save(e));
    }
}
