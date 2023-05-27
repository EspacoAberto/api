package espacoaberto.backend.controllers;
/*
import espacoaberto.backend.entidades.Endereco;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.EnderecoRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.ServiceBase64;
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

    @PostMapping("/cadastrar/{idBase64}")
    public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable String idBase64, @RequestBody Endereco endereco){
        Integer idDecodificado;

        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<Imovel> im = imovelRepository.findById(idDecodificado);

            if(im.isEmpty()){
                return ResponseEntity.status(404).build();
            }

            Endereco e = new Endereco(endereco.getCep(), endereco.getEstado(), endereco.getCidade(), endereco.getLogradouro(), endereco.getNumero(), im.get());


            return ResponseEntity.status(200).body( this.enderecoRepository.save(e));

        } catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }

        return ResponseEntity.status(404).build();
    }
}
*/