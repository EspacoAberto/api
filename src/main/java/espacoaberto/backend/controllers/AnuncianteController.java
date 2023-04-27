package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Carteira;
import espacoaberto.backend.repository.AnuncianteRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import espacoaberto.backend.service.ServiceBase64;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anunciantes")
public class AnuncianteController {
    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @PostMapping("cadastrar")
    public ResponseEntity<Anunciante> cadastrarAnunciante(@RequestBody Anunciante novoAnunciante){
        String email = novoAnunciante.getEmail();
        Optional<Anunciante> opAnunciante = anuncianteRepository.findByEmail(email);

        if(opAnunciante.isPresent()){
            return ResponseEntity.status(409).build();
        }
        Carteira newCarteira = new Carteira( novoAnunciante, 0.0);

        novoAnunciante.setCarteira(newCarteira);


        return ResponseEntity.status(201).body(this.anuncianteRepository.save(novoAnunciante));
    }

    @GetMapping()
    public ResponseEntity<List<Anunciante>> consultarAnunciantes(){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();

        return anunciantes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anunciantes);
    }

    @GetMapping("{idBase64}")
    public ResponseEntity<Anunciante> consultarAnunciante(@PathVariable String idBase64){
        Integer idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));

        Optional<Anunciante> anunciante = anuncianteRepository.findById(idDecodificado);

        if(anunciante.isPresent()){
            return ResponseEntity.status(200).body(anunciante.get());
        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/listarPremium")
    public ResponseEntity<List<Anunciante>> consultarAnunciantesPremium(){
        List<Anunciante> anunciantesPremium = anuncianteRepository.findByIsPremiumTrue();

        if (anunciantesPremium.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(anunciantesPremium);
    }

    @GetMapping("listarPorCpf/{cpfBase64}")
        public ResponseEntity<Anunciante> listarPorCpf(@PathVariable String cpfBase64){
        String cpfDecodificado = ServiceBase64.descriptografaBase64(cpfBase64);

        Optional<Anunciante> anunciante = anuncianteRepository.findByCpf(cpfDecodificado);

        if (anunciante.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(anunciante.get());
    }

    @PutMapping ("atualizar/{cpfBase64}")
    public ResponseEntity<Anunciante> atualizarUsuario(@RequestBody Anunciante usuario, @PathVariable String cpfBase64){
        String cpfDecodificado = ServiceBase64.descriptografaBase64(cpfBase64);

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