package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.dto.DocumentoDTO;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<List<Imovel>> listar(

    ) {

        List<Imovel> imoveis = imovelRepository.findAll();

        if(imoveis.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(imoveis);

    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel novoImovel) {
        return ResponseEntity.status(201).body(this.imovelRepository.save(novoImovel));
    }

    @PatchMapping("/cadastrar/{id}")
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

    }






}
