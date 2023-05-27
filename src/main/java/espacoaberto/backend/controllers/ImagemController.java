package espacoaberto.backend.controllers;
/*
import espacoaberto.backend.dto.ImagemDTO;
import espacoaberto.backend.entidades.Imagem;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.ImagemRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrar/{idBase64}")
    public ResponseEntity<Imagem> cadastrarImagem(
            @PathVariable String idBase64,
            @RequestBody ImagemDTO imagem
    ){
        Integer idDecodificado;

        try{
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));

            Optional<Imovel> im = imovelRepository.findById(idDecodificado);

            if(im.isEmpty()){
                return ResponseEntity.status(404).build();
            }



            Imagem imagemCadastrar = new Imagem(im.get(), imagem.getImagem(), imagem.getTipoArquivo());

            this.imagemRepository.save(imagemCadastrar);

            return ResponseEntity.status(200).body(imagemCadastrar);
        } catch(Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }

        return ResponseEntity.status(404).build();


    }
}
*/