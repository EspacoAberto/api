package espacoaberto.backend.controllers;
/*
import espacoaberto.backend.entidades.Acomodacao;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AcomodacaoRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/acomodacoes")
public class AcomodacaoController {
    @Autowired
    private AcomodacaoRepository acomodacaoRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrar/{idBase64}")
    public ResponseEntity<Acomodacao> cadastrarAcomodacao(@PathVariable String idBase64, @RequestBody Acomodacao acomodacao){
        Integer idDecodificado;

        try{
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<Imovel> im = imovelRepository.findById(idDecodificado);

            if(im.isEmpty()){
                return ResponseEntity.status(404).build();
            }

            Acomodacao a = new Acomodacao(im.get(), acomodacao.getDescricao());


            return ResponseEntity.status(200).body( this.acomodacaoRepository.save(a));

        }catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }


        return ResponseEntity.status(404).build();

    }
}
*/
