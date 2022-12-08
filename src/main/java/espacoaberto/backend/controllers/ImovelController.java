package espacoaberto.backend.controllers;

import com.sun.xml.txw2.Document;
import espacoaberto.backend.abstrato.Usuario;
//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.dto.DocumentoDTO;
import espacoaberto.backend.dto.ImagemDTO;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.entidades.Imagem;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.listaObj.ListaObj;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
import org.apache.coyote.Response;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Anuncio>> listar(
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,
            @RequestParam(required = false) String disponibilidade
    ) {

        // Se vier os três parametros
        if (precoMin != null && precoMax != null && disponibilidade != null) {
            List<Anuncio> anuncios = anuncioRepository.getAnunciosFiltrados(precoMin, precoMax, disponibilidade);
            return ResponseEntity.status(200).body(anuncios);
        }

        // Se vier apenas os preços
        if (disponibilidade == null && precoMax != null && precoMin != null) {
            List<Anuncio> anuncios = anuncioRepository.getAnunciosFiltradosSemDisp(precoMin, precoMax);
            return ResponseEntity.status(200).body(anuncios);
        }
        // Se vier apenas o preço maximo
        if (precoMin == null && disponibilidade != null && precoMax != null) {
            List<Anuncio> anuncios = anuncioRepository.getAnunciosFiltrados(0.0, precoMax, disponibilidade);
            return ResponseEntity.status(200).body(anuncios);
        }
        // Se vier apenas o preço mínimo
        if (precoMax == null && precoMin != null && disponibilidade != null) {
            List<Anuncio> anuncios = anuncioRepository.getAnunciosFiltrados(0.0, precoMax, disponibilidade);
            return ResponseEntity.status(200).body(anuncios);
        }

        /*if (precoMax == null && precoMin == null && disponibilidade != null) {
            List<Anuncio> anuncios = imovelRepository.findByDisponibilidade(disponibilidade);
            return ResponseEntity.status(200).body(anuncios);
        }*/

        List<Anuncio> anuncios = anuncioRepository.findAll();
        return ResponseEntity.status(200).body(anuncios);

    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel novoImovel) {
        return ResponseEntity.status(201).body(this.imovelRepository.save(novoImovel));
    }

    @PostMapping("/gerarCsv/{nomeArq}")
    public ResponseEntity gerarCsv(@PathVariable String nomeArq) {
        List<Imovel> listImoveis = imovelRepository.findAll();
        ListaObj<Imovel> imoveis = new ListaObj<>(listImoveis.size());

        for (Imovel Im : listImoveis) {
            imoveis.adiciona(Im);
        }

//        ExportacaoCsv.gravarArquivoCsvImovel(imoveis, nomeArq);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/cadastrarDocumento/{id}")
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
