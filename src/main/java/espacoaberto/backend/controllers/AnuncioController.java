package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    @Autowired
    AnuncioRepository anuncioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Anuncio> cadastrarAnuncio(@RequestBody Anuncio novoAnuncio){
        return ResponseEntity.status(201).body(this.anuncioRepository.save(novoAnuncio));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Anuncio>> listarAnuncio(){
        List<Anuncio> anuncios = anuncioRepository.findAll();
        return anuncios.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anuncios);
    }
}
