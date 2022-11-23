package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.SendEmailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anunciantes")
public class AnuncianteController {
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Anunciante> cadastrarAnunciante(@RequestBody Anunciante novoAnunciante){
        novoAnunciante.setAutenticado(false);
        sendEmailService.enviar(novoAnunciante.getEmail(), novoAnunciante.getNome(), novoAnunciante.getSenha());
        return ResponseEntity.status(201).body(this.anuncianteRepository.save(novoAnunciante));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Anunciante>> listarAnunciantes(){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        return anunciantes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anunciantes);
    }

}

