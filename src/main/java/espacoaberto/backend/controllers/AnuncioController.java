package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.exceptions.FotoNaoEncontradaException;
import espacoaberto.backend.exceptions.ReciboNaoEncontradoException;
import espacoaberto.backend.listaObj.ListaObj;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @CrossOrigin("*")
    @PatchMapping(value = "/foto/{id}", consumes = "image/jpeg")
    public ResponseEntity<Void> patchFoto(@PathVariable int id, @RequestBody byte[] novaFoto) {
        if (!anuncioRepository.existsById(id)) {
            throw new FotoNaoEncontradaException();
        }

        anuncioRepository.setFoto(id, novaFoto);

        return ResponseEntity.status(200).build();
    }

    @GetMapping(value = "/foto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getFoto(@PathVariable int id) {
        if (!anuncioRepository.existsById(id)) {
            throw new FotoNaoEncontradaException();
        }

        byte[] foto = anuncioRepository.getFoto(id);

        return ResponseEntity.status(200).header("content-disposition",
                "attachment; filename=\"foto-anuncio.jpg\"").body(foto);
    }

}
