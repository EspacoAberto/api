package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Recibo;
import espacoaberto.backend.exceptions.ReciboNaoEncontradoException;
import espacoaberto.backend.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/recibos")
public class ReciboController {
    @Autowired
    ReciboRepository reciboRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Recibo> cadastroRecibo(@RequestBody Recibo novoRecibo){
        return ResponseEntity.status(201).body(this.reciboRepository.save(novoRecibo));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Recibo>> listarRecibo (){
        List<Recibo> recibos = reciboRepository.findAll();
        return recibos.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(recibos);
    }
    @PatchMapping(value = "/relatorio/{idRecibo}", consumes = "text/csv")
    public ResponseEntity<Void> patchRelatorio(@PathVariable int idRecibo, @RequestBody byte[] novoRecibo) {
        if (!reciboRepository.existsById(idRecibo)) {
            throw new ReciboNaoEncontradoException();
        }

        reciboRepository.setRelatorio(idRecibo, novoRecibo);

        return ResponseEntity.status(200).build();
    }
    @GetMapping(value = "/relatorio/{idRecibo}", produces = "text/csv")
    public ResponseEntity<byte[]> getRelatorio(@PathVariable int idRecibo) {
        if (!reciboRepository.existsById(idRecibo)) {
            throw new ReciboNaoEncontradoException();
        }

        byte[] relatorio = reciboRepository.getRelatorio(idRecibo);

        // esse header "content-disposition" indica o nome do arquivo em caso de download em navegador
        return ResponseEntity.status(200).header("content-disposition", "attachment; filename=\"relatorio-planta.csv\"").body(relatorio);
    }
}
