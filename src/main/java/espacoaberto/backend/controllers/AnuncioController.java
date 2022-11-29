package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.listaObj.ListaObj;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {


    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Anuncio>> listar() {

        List<Anuncio> anuncios = anuncioRepository.findAll();
        return anuncios.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anuncios);
    }

    @GetMapping("/listarFiltradoPorPreco/{precoMin}/{precoMax}")
    public ResponseEntity<List<Anuncio>> listarPorIntervaloDePreco(@PathVariable Double precoMin, @PathVariable Double precoMax){
        List<Anuncio> lista = anuncioRepository.findByPrecoBetween(precoMin, precoMax);

        if(lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.status(200).body(lista);
        }
    }

    @PatchMapping("aumentarCurtidas/{idAnuncio}")
    public ResponseEntity<Anuncio> aumentarCurtidas(@PathVariable Integer idAnuncio){

        List<Anuncio> anuncios = anuncioRepository.findAll();

        for (int i = 0; i < anuncios.size(); i++) {
            if(anuncios.get(i).getIdAnuncio() == idAnuncio){
                anuncios.get(i).setCurtidas(anuncios.get(i).getCurtidas() + 1);
                return ResponseEntity.status(200).body(anuncios.get(i));
            }
        }

        return ResponseEntity.status(204).build();
    }



    @PostMapping("/cadastrar")
    public ResponseEntity<Anuncio> cadastrar(@RequestBody Anuncio novoAnuncio){
        return ResponseEntity.status(201).body(this.anuncioRepository.save(novoAnuncio));
    }

    @PostMapping("/gerarCsv/{nomeArq}")
    public ResponseEntity gerarCsv(@PathVariable String nomeArq){
        List<Anuncio> listAnuncios = anuncioRepository.findAll();
        ListaObj<Anuncio> anuncios = new ListaObj<>(listAnuncios.size());

        for (Anuncio A  : listAnuncios){
            anuncios.adiciona(A);
        }

//        ExportacaoCsv.gravarArquivoCsvAnuncio(anuncios, nomeArq);
        return ResponseEntity.status(200).build();
    }        

}
