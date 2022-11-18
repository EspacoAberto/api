package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.listaObj.ListaObj;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @PostMapping("/cadastrar")
    public ResponseEntity<Anuncio> cadastrar(@RequestBody @Valid Anuncio novoAnuncio){
        return ResponseEntity.status(201).body(this.anuncioRepository.save(novoAnuncio));
    }
/*
    @PostMapping("/gerarCsv/{nomeArq}")
    public ResponseEntity gerarCsv(@PathVariable String nomeArq){
        List<Anuncio> listAnuncios = anuncioRepository.findAll();
        ListaObj<Anuncio> anuncios = new ListaObj<>(listAnuncios.size());

        for (Anuncio A  : listAnuncios){
            anuncios.adiciona(A);
        }

        ExportacaoCsv.gravarArquivoCsvAnuncio(anuncios, nomeArq);
        return ResponseEntity.status(200).build();
    }        
*/
}
