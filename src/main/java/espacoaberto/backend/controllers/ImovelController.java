package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
//import espacoaberto.backend.csv.ExportacaoCsv;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.listaObj.ListaObj;
import espacoaberto.backend.repository.ImovelRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("imoveis")
public class ImovelController {
    @Autowired
    private ImovelRepository imovelRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Imovel>> listar() {
        List<Imovel> imoveis = imovelRepository.findAll();
        return imoveis.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(imoveis);
    }



    @PostMapping("/cadastrar")
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel novoImovel){
        return ResponseEntity.status(201).body(this.imovelRepository.save(novoImovel));
    }

    @PostMapping("/gerarCsv/{nomeArq}")
    public ResponseEntity gerarCsv(@PathVariable String nomeArq){
         List<Imovel> listImoveis = imovelRepository.findAll();
         ListaObj<Imovel> imoveis = new ListaObj<>(listImoveis.size());

         for (Imovel Im  : listImoveis){
             imoveis.adiciona(Im);
         }

//        ExportacaoCsv.gravarArquivoCsvImovel(imoveis, nomeArq);
        return ResponseEntity.status(200).build();
    }


}
