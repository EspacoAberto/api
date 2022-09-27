package espacoaberto.backend;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/espacoAberto")
public class AnuncianteController {

    @Autowired
    private AnuncianteRepository anuncianteRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrarAnunciante")
    public ResponseEntity<Usuario> cadastrarAnunciante(@RequestBody Anunciante novoAnunciante){
        return ResponseEntity.status(201).body(this.anuncianteRepository.save(novoAnunciante));
    }

    @GetMapping("/listarAnunciantes")
    public ResponseEntity<List<Anunciante>> listarAnunciantes(){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        return anunciantes.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anunciantes);
    }

    @PostMapping("/anunciante/tornarPremium/{email}/{senha}")
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

    @PostMapping("{idUsuario}/cadastrarImovel")
    public ResponseEntity<Imovel> cadastrarImovel( @PathVariable Integer idUsuario,@RequestBody Imovel imovel){
        List<Anunciante> anunciantes = anuncianteRepository.findAll();
        for (Anunciante anum: anunciantes) {
            if(anum.getId() == idUsuario ){
                anum.cadastrarImovel(imovel);
                this.imovelRepository.save(imovel);
                return ResponseEntity.status(201).body(imovel);
            }
        }
        return ResponseEntity.status(400).build();
    }

}

