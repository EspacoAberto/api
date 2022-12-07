package espacoaberto.backend.controllers;

import espacoaberto.backend.dto.ImagemDTO;
import espacoaberto.backend.entidades.Imagem;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.ImagemRepository;
import espacoaberto.backend.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    @PostMapping("/cadastrarImagem/{id}")
    public ResponseEntity<Imagem> cadastrarImagem(
            @PathVariable Integer id,
            @RequestBody ImagemDTO imagem
    ){
        Optional<Imovel> im = imovelRepository.findById(id);

        if(im.isEmpty()){
            return ResponseEntity.status(404).build();
        }



        Imagem imagemCadastrar = new Imagem(im.get(), imagem.getImagem(), imagem.getTipoArquivo());

        this.imagemRepository.save(imagemCadastrar);

        return ResponseEntity.status(200).body(imagemCadastrar);


    }
}
