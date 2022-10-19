package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/anunciantes")
public class AnuncianteController {

    @Autowired
    private AnuncianteRepository anuncianteRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarAnunciante(@RequestBody Anunciante novoAnunciante){
        return ResponseEntity.status(201).body(this.anuncianteRepository.save(novoAnunciante));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Anunciante>> listarAnunciantes(){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        return anunciantes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anunciantes);
    }

    @PostMapping("/tornarPremium/{email}/{senha}")
    public ResponseEntity<Anunciante> tornarAnunciantePremium(@PathVariable String email, @PathVariable String senha) {
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        for (Anunciante anunciante: anunciantes ) {
            if (anunciante.getEmail().equals(email) && anunciante.getSenha().equals(senha)){
                anunciante.setPremium(true);
                return ResponseEntity.status(200).body(anunciante);
            }
        }
        return ResponseEntity.status(400).build();
    }

    @PostMapping("{idAnunciante}/cadastrarImovel")
    public ResponseEntity<Imovel> cadastrarImovel(@PathVariable int idAnunciante,@RequestBody Imovel novoImovel){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        for (Anunciante anunciante: anunciantes){
            if (anunciante.getId() == idAnunciante){
                return ResponseEntity.status(201).body(this.imovelRepository.save(novoImovel));
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("{idAnunciante}/AnunciarImovel/{idImovel}")
    public ResponseEntity<Anuncio> anunciarImovel(@PathVariable int idAnunciante, @PathVariable int idImovel,
                                                    @RequestBody Anuncio novoanuncio){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        for (Anunciante anunciante: anunciantes){
            if (anunciante.getId() == idAnunciante){
                List<Imovel> imoveis = imovelRepository.findAll();
                for (Imovel movel: imoveis ) {
                    if(movel.getId() == idImovel){
                        novoanuncio.setImovels(imoveis);
                        return ResponseEntity.status(201).body(this.anuncioRepository.save(novoanuncio));
                    }
                }
            }
        }
        return ResponseEntity.status(404).build();
    }

}

