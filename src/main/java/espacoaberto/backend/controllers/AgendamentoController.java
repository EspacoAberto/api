package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.entidades.Agendamento;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AnuncianteRepository anuncianteRepository;


    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;


    @PostMapping("/cadastrar")
    public ResponseEntity<Agendamento> cadastrarAgendamento(@RequestBody Agendamento novoAgendamento){

        Optional<Cliente> usuario = clienteRepository.findById(novoAgendamento.getUsuario().getId());

        Optional<Anuncio> anuncio = anuncioRepository.findById(novoAgendamento.getAnuncio().getIdAnuncio());

        novoAgendamento.setAnuncio(anuncio.get());
        novoAgendamento.setUsuario(usuario.get());


        return ResponseEntity.status(201).body(this.agendamentoRepository.save(novoAgendamento));
    }

    @GetMapping()
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(agendamentos);
    }
}
