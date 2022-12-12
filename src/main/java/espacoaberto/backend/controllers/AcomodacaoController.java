package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Acomodacao;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AcomodacaoRepository;
import espacoaberto.backend.repository.ImovelRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/acomodacoes")
public class AcomodacaoController {
    @Autowired
    private AcomodacaoRepository acomodacaoRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrarAcomodacao/{id}")
    public ResponseEntity<Acomodacao> cadastrarAcomodacao(@PathVariable int id, @RequestBody Acomodacao acomodacao){
        Optional<Imovel> im = imovelRepository.findById(id);

        if(im.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Acomodacao a = new Acomodacao(im.get(), acomodacao.getDescricao());


        return ResponseEntity.status(200).body( this.acomodacaoRepository.save(a));
    }
}
