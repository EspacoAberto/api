package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;

import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.exceptions.FotoNaoEncontradaException;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;

import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {


    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Anuncio> cadastrar(@RequestBody Anuncio novoAnuncio) {
        return ResponseEntity.status(201).body(novoAnuncio);
    }

    /* @PostMapping("/executarAgendamento/{qtd}")
    public ResponseEntity<Void> executar(@PathVariable Integer qtd){
        if (qtd < 1 || qtd > fila.getTamanho()){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < qtd; i++){
            Anuncio anuncio = fila.poll();
            this.anuncioRepository.save(anuncio);
        }

        return ResponseEntity.status(200).build();
    }*/
    @GetMapping()
    public ResponseEntity<List<Anuncio>> listar(
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,
            @RequestParam(required = false) String disponibilidade
    ) {

        // Se vier os três parametros
        if (precoMin != null && precoMax != null && disponibilidade != null) {
            List<Anuncio> anuncios = anuncioRepository.findByDisponibilidadeAndPrecoBetween(disponibilidade, precoMin, precoMax);

            if (anuncios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(anuncios);
        }

        // Se vier apenas os preços
        if (disponibilidade == null && precoMax != null && precoMin != null) {
            List<Anuncio> anuncios = anuncioRepository.findByPrecoBetween(precoMin, precoMax);

            if (anuncios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(anuncios);
        }
        // Se vier apenas o preço maximo
        if (precoMin == null && disponibilidade == null && precoMax != null) {
            List<Anuncio> anuncios = anuncioRepository.findByPrecoLessThan(precoMin);

            if (anuncios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(anuncios);
        }
        // Se vier apenas o preço mínimo
        if (precoMax == null && precoMin != null && disponibilidade == null) {
            List<Anuncio> anuncios = anuncioRepository.findByPrecoGreaterThan(precoMin);

            if (anuncios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }



            return ResponseEntity.status(200).body(anuncios);
        }

        // Se vier apenas disponibilidade
        if (precoMax == null && precoMin == null && disponibilidade != null) {
            List<Anuncio> anuncios = anuncioRepository.findByDisponibilidade(disponibilidade);

            if (anuncios.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(anuncios);
        }

        List<Anuncio> anuncios = anuncioRepository.findAll();
        if (anuncios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(anuncios);

    }

    @GetMapping("/{idBase64}")
    public ResponseEntity<Anuncio> listarPorId(@PathVariable String idBase64) {
        String idDecodificado;

        try {
            idDecodificado = ServiceBase64.descriptografaBase64(idBase64);
            Optional<Anuncio> a = anuncioRepository.findById(Integer.parseInt(idDecodificado));
            return (a.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(a.get()));

        } catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }

        return ResponseEntity.status(404).build();

    }


    @PatchMapping("aumentarCurtidas/{idAnuncio}")
    public ResponseEntity<Anuncio> aumentarCurtidas(@PathVariable Integer idAnuncio) {

        List<Anuncio> anuncios = anuncioRepository.findAll();

        for (int i = 0; i < anuncios.size(); i++) {
            if (anuncios.get(i).getIdAnuncio() == idAnuncio) {
                anuncios.get(i).setCurtidas(anuncios.get(i).getCurtidas() + 1);
                return ResponseEntity.status(200).body(anuncios.get(i));
            }
        }

        return ResponseEntity.status(204).build();
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

    @GetMapping("/listarAnunciosPremium")
    public ResponseEntity<List<Anuncio>> listarAnunciosPremium() {
        List<Anunciante> usuariosPremium = anuncianteRepository.findByIsPremiumTrue();
        List<Anunciante> usuariosPremiumFiltro = new ArrayList<>();

        // pegando os seis primeiros anunciantes
        for (int i = 0; i < 6; i++) {
            if (usuariosPremium.get(i) != null) {
                usuariosPremiumFiltro.add(usuariosPremium.get(i));
            }
        }

        List<Anuncio> anunciosSelecionados = new ArrayList<>();

        for (Anunciante a : usuariosPremiumFiltro) {
            List<Anuncio> anuncios = anuncioRepository.findByAnuncianteId(a.getId());

            if (!anuncios.isEmpty()) {
                anunciosSelecionados.add(anuncios.get(0));
            }
        }

        return ResponseEntity.status(200).body(anunciosSelecionados);

    }


}
