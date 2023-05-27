package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
//import espacoaberto.backend.dto.DocumentoDTO;
import espacoaberto.backend.entidades.Imovel;
//import espacoaberto.backend.repository.AnuncioRepository;
//import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.ImageUploadExample;
//import espacoaberto.backend.service.ServiceBase64;
import espacoaberto.backend.repository.ImovelRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import espacoaberto.backend.service.ImgurApiClient;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("imoveis")
public class ImovelController {
    @Autowired
    private ImovelRepository imovelRepository;
    //@Autowired
    //private AnuncioRepository anuncioRepository;
    //@Autowired
    // private ImgurApiClient imgurApiClient;


    @GetMapping()
    public ResponseEntity<List<Imovel>> listar(

    ) {

        List<Imovel> imoveis = imovelRepository.findAll();

        if (imoveis.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(imoveis);

    }

    @PostMapping()
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel novoImovel) {
        return ResponseEntity.status(201).body(this.imovelRepository.save(novoImovel));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Imovel> listarporId(@PathVariable Integer id) {

            Optional<Imovel> im = imovelRepository.findById(id);

            return (im.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(im.get()));

    }




    private static final String CLIENT_ID = "2bf2c8257645521";

    @PostMapping("/upload/{idImovel}")
    public ResponseEntity<Imovel> uploadImage(@RequestBody byte[] imageData, @PathVariable Integer idImovel) {

            Optional<Imovel> opImovel = imovelRepository.findById(idImovel);

            if (opImovel.isPresent()) {
                String downloadLink = ImageUploadExample.uploadImage(imageData, CLIENT_ID);

                Imovel imovel = opImovel.get();

                List<String> imagensDoImovel = imovel.getLinkFotos();

                imagensDoImovel.add(downloadLink);

                imovel.setLinkFotos(imagensDoImovel);

                return ResponseEntity.ok(imovelRepository.save(imovel));
            } else {
                return ResponseEntity.notFound().build();
            }



    }


    @PostMapping("/uploadComprovante/{id}")
    public ResponseEntity<Imovel> uploadImageComprovante(@RequestBody byte[] imageData, @PathVariable Integer id) {
        Optional<Imovel> opImovel = imovelRepository.findById(id);

        if (opImovel.isPresent()) {
            String downloadLink = ImageUploadExample.uploadImage(imageData, CLIENT_ID);


            Imovel imovel = opImovel.get();

            // List<String> imagensDoImovel = imovel.getLinkFotos();

            imovel.setComprovante(downloadLink);

            //imovel.setLinkFotos(imagensDoImovel);

            return ResponseEntity.ok(imovelRepository.save(imovel));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/imagens/{id}")
    public ResponseEntity<List<String>> getImagensPorImovel(@PathVariable Integer id) {
        Optional<Imovel> opImovel = imovelRepository.findById(id);

        if (opImovel.isPresent()) {
            Imovel imovelEncontrado = opImovel.get();

            List<String> links = imovelEncontrado.getLinkFotos();

            if (links.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(links);
        }

            return ResponseEntity.notFound().build();

    }

    @GetMapping("/comprovante/{id}")
    public ResponseEntity<String> getComprovantePorImovel(@PathVariable Integer id) {
        Optional<Imovel> opImovel = imovelRepository.findById(id);

        if (opImovel.isPresent()) {
            Imovel imovelEncontrado = opImovel.get();

            String comprovanteLink = imovelEncontrado.getComprovante();

            if (comprovanteLink == null) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(comprovanteLink);
        }

        return ResponseEntity.notFound().build();

    }


}
