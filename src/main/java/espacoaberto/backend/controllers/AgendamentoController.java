package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Agendamento;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.repository.AgendamentoRepository;
import espacoaberto.backend.repository.AnuncianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {


    @Autowired
    private AgendamentoRepository agendamentoRepository;


    @PostMapping("/cadastrar")
    public ResponseEntity<Agendamento> cadastrarAgendamento(@RequestBody Agendamento novoAgendamento){
        return ResponseEntity.status(201).body(this.agendamentoRepository.save(novoAgendamento));
    }

    @GetMapping("/listarAgendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(agendamentos);
    }
}
