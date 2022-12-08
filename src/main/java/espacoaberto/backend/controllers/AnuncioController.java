package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.entidades.Imagem;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.exceptions.FotoNaoEncontradaException;
import espacoaberto.backend.exceptions.ReciboNaoEncontradoException;
import espacoaberto.backend.listaObj.FilaObj;
import espacoaberto.backend.listaObj.ListaObj;
import espacoaberto.backend.listaObj.PilhaObj;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    FilaObj<Anuncio> fila = new FilaObj<Anuncio>(100);
    PilhaObj pilha = new PilhaObj<>(100);
    @PostMapping("/cadastrar")
    public ResponseEntity<Anuncio> cadastrar(@RequestBody Anuncio novoAnuncio){
        fila.insert(novoAnuncio);
        return ResponseEntity.status(201).body(novoAnuncio);
    }
    @PostMapping("/executarAgendamento/{qtd}")
    public ResponseEntity<Void> executar(@PathVariable Integer qtd){
        if (qtd < 1 || qtd > fila.getTamanho()){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < qtd; i++){
            Anuncio anuncio = fila.poll();
            this.anuncioRepository.save(anuncio);
        }

        return ResponseEntity.status(200).build();
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Anuncio>> listar() {

        List<Anuncio> anuncios = anuncioRepository.findAll();
        return anuncios.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anuncios);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Anuncio> listarPorId(@PathVariable Integer id) {

        Optional<Anuncio> a = anuncioRepository.findById(id);

        return (a.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(a.get()));

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

    @GetMapping("/listarAnunciosPremium")
    public ResponseEntity<List<Anuncio>> listarAnunciosPremium(){
        List<Anunciante> usuariosPremium = anuncianteRepository.findByIsPremiumTrue();
        List<Anunciante> usuariosPremiumFiltro = new ArrayList<>();

        // pegando os seis primeiros anunciantes
        for (int i = 0; i < 6; i++) {
            if(usuariosPremium.get(i) != null){
                usuariosPremiumFiltro.add(usuariosPremium.get(i));
            }
        }

        List<Anuncio> anunciosSelecionados = new ArrayList<>();

        for (Anunciante a : usuariosPremiumFiltro) {
           List<Anuncio> anuncios =  anuncioRepository.findByAnuncianteId(a.getId());

           if(!anuncios.isEmpty()){
               anunciosSelecionados.add(anuncios.get(0));
           }
        }

        return ResponseEntity.status(200).body(anunciosSelecionados);
        
    }

    @GetMapping("/teste/{id}")
    public ResponseEntity<List<Anuncio>> teste(@PathVariable int id){
        return ResponseEntity.status(200).body(anuncioRepository.findByAnuncianteId(id));
    }

}
