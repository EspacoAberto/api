package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.ImageUploadExample;
import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import espacoaberto.backend.service.ImgurApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("imoveis")
public class ImovelController {
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private ImgurApiClient imgurApiClient;



    @GetMapping()
    public ResponseEntity<List<Imovel>> listar(

    ) {

        List<Imovel> imoveis = imovelRepository.findAll();

        if(imoveis.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(imoveis);

    }

    @GetMapping("/{idBase64}")
    public ResponseEntity<Imovel> listarporId(@PathVariable String idBase64){
        Integer idDecodificado;

        try{
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));

            Optional<Imovel> im = imovelRepository.findById(idDecodificado);

            return (im.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(im.get()));


        } catch(Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }

        return ResponseEntity.status(404).build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel novoImovel) {
        novoImovel.setAcomodacoes(new ArrayList<>());
        novoImovel.setLinkFotos(new ArrayList<>());
        return ResponseEntity.status(201).body(this.imovelRepository.save(novoImovel));
    }

    /*@PatchMapping("/cadastrar/{id}")
    public ResponseEntity<Imovel> cadastrarDocumento(
            @PathVariable Integer id,
            @RequestBody DocumentoDTO documento
    ) {
        Optional<Imovel> im = imovelRepository.findById(id);

        if(im.isEmpty()){
            return ResponseEntity.status(404).build();
        }

      imovelRepository.atualizarDocumentoPorId( id, documento.getComprovante());
      imovelRepository.atualizarTipoArquivoDocumentoPorId(documento.getTipoArquivo(), id);

        Optional<Imovel> imAtualizado = imovelRepository.findById(id);


        return ResponseEntity.status(200).body(imAtualizado.get());

    }*/

    private static final String CLIENT_ID = "2bf2c8257645521";

    @PostMapping("/upload/{id}")
    public ResponseEntity<Imovel> uploadImage(@RequestBody byte[] imageData, @PathVariable Integer id) {

            Optional<Imovel> opImovel = imovelRepository.findById(id);

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


}
