package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Recibo;
import espacoaberto.backend.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
