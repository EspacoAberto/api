package espacoaberto.backend.controllers;
/*
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.VisualizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visualizacoes")
public class VisualizacaoController {
    @Autowired
    private VisualizacaoRepository visualizacaoRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

    /*@PatchMapping("aumentarVisualizacoes/{idAnuncio}")
    public ResponseEntity<Anuncio> aumentarVisualizacoes(@PathVariable Integer idAnuncio){
        if (anuncioRepository.existsById(idAnuncio)){
            List<Anuncio> anuncios = anuncioRepository.findAll();

            for (Anuncio a : anuncios){
                if(a.getIdAnuncio() == idAnuncio){
                    Visualizacao v = new Visualizacao(a.getImovel(), a.getAnunciante(), a);
                    visualizacaoRepository.save(v);
                    return ResponseEntity.status(200).body(a);
                }

            }

        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Visualizacao>> listaVisualizacoes(){
        List<Visualizacao> lista = visualizacaoRepository.findAll();

        return (lista.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(lista));
    }

    @GetMapping("/mensal")
    public ResponseEntity<List<Visualizacao>> listaPorMes(){

    }
}
*/