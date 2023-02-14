package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.RandomString;
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
    private RandomString randomString;
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Anunciante> cadastrarAnunciante(@RequestBody Anunciante novoAnunciante){
        novoAnunciante.setCodigo(randomString.gerarCodigoAlfanumerico());
        sendEmailService.enviar(novoAnunciante.getEmail(), novoAnunciante.getNome(), novoAnunciante.getCodigo() );
        return ResponseEntity.status(201).body(this.anuncianteRepository.save(novoAnunciante));
    }

    @GetMapping()
    public ResponseEntity<List<Anunciante>> listarAnunciantes(){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        return anunciantes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anunciantes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Anunciante> listarAnunciantePorId(@PathVariable int id){
        Optional<Anunciante> anunciante = anuncianteRepository.findById(id);

        if(anunciante.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(anunciante.get());
    }

    @GetMapping("/listarPremium")
    public ResponseEntity<List<Anunciante>> listarAnunciantesPremium(){
        List<Anunciante> anunciantesPremium = anuncianteRepository.findByIsPremiumTrue();

        if (anunciantesPremium.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(anunciantesPremium);
    }

    /*
    @GetMapping("/{cpf}")
        public ResponseEntity<Anunciante> listarPorCpf(@PathVariable String cpf){
            Optional<Anunciante> anunciante = anuncianteRepository.findByCpf(cpf);

            if (anunciante.isEmpty()){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(anunciante.get());
        }
    }

    @PutMapping ("/atualizar/{cpf}")
    public ResponseEntity<Anunciante> atualizarUsuario(@RequestBody Anunciante usuario, @PathVariable String cpf){



        Optional<Anunciante> usuarioASerAtualizadoOP  = anuncianteRepository.findByCpf(cpf);

        if(usuarioASerAtualizadoOP.isPresent()){
            Anunciante usuarioASerAtualizado = usuarioASerAtualizadoOP.get();
            usuarioASerAtualizado.setNome(usuario.getNome());
            usuarioASerAtualizado.setEmail(usuario.getEmail());
            usuarioASerAtualizado.setDataNascimento(usuario.getDataNascimento());
            return ResponseEntity.status(200).body(anuncianteRepository.save(usuarioASerAtualizado));
        }

        return ResponseEntity.status(404).build();




    }*/



}

