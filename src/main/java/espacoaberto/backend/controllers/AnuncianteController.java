package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.service.RandomString;
import espacoaberto.backend.service.SendEmailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import espacoaberto.backend.service.ServiceBase64;


import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ServiceBase64 serviceBase64;

    @PostMapping("/cadastrar")
    public ResponseEntity<Anunciante> cadastrarAnunciante(@RequestBody Anunciante novoAnunciante){
        return ResponseEntity.status(201).body(this.anuncianteRepository.save(novoAnunciante));
    }

    @GetMapping()
    public ResponseEntity<List<Anunciante>> listarAnunciantes(){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        return anunciantes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anunciantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anunciante> listarAnunciantePorId(@PathVariable int id){
        Optional<Anunciante> anunciante = anuncianteRepository.findById(id);

        if(anunciante.isEmpty()){
            return ResponseEntity.status(404).build();
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


    @GetMapping("/{cpf}")
        public ResponseEntity<Anunciante> listarPorCpf(@PathVariable String cpf){


        String cpfDecodificado = ServiceBase64.descriptografaBase64(cpf);

        /*
        // Decodificando o CPF que vem na requisição
        byte[] decodedBytes = Base64.getDecoder().decode(cpf);
        String cpfDecodificado = new String(decodedBytes);

         */

            Optional<Anunciante> anunciante = anuncianteRepository.findByCpf(cpfDecodificado);

            if (anunciante.isEmpty()){
                return ResponseEntity.status(204).build();
            }
            return ResponseEntity.status(200).body(anunciante.get());
        }



    @PutMapping ("/atualizar/{cpf}")
    public ResponseEntity<Anunciante> atualizarUsuario(@RequestBody Anunciante usuario, @PathVariable String cpf){


        String cpfDecodificado = ServiceBase64.descriptografaBase64(cpf);

        Optional<Anunciante> usuarioASerAtualizadoOP  = anuncianteRepository.findByCpf(cpfDecodificado);

        if(usuarioASerAtualizadoOP.isPresent()){
            Anunciante usuarioASerAtualizado = usuarioASerAtualizadoOP.get();
            usuarioASerAtualizado.setNome(usuario.getNome());
            usuarioASerAtualizado.setEmail(usuario.getEmail());
            usuarioASerAtualizado.setDataNascimento(usuario.getDataNascimento());
            return ResponseEntity.status(200).body(anuncianteRepository.save(usuarioASerAtualizado));
        }

        return ResponseEntity.status(404).build();




    }
}






