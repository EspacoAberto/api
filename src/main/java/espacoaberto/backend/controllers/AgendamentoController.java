package espacoaberto.backend.controllers;


import java.time.*;

import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import espacoaberto.backend.entidades.Agendamento;

import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.repository.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/agendamentos")
public class AgendamentoController {


    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private PendenciaAgendamentoDTORepository  pendenciaAgendamentoDTORepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;



    @GetMapping()
    public ResponseEntity<List<Agendamento>> listarAgendamentos(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(agendamentos);
    }

    @GetMapping("/anuncios/{id}")
    public ResponseEntity<List<Agendamento>> listarAgendamentosPorId(@PathVariable Integer id){


            // Verificando se o anuncio existe
            Optional<Anuncio> opAnuncio = anuncioRepository.findById(id);

            Anuncio anuncioEncontrado;
            if (opAnuncio.isPresent()) {
                anuncioEncontrado = opAnuncio.get();
            } else {
                return ResponseEntity.notFound().build();
            }

            List<Agendamento> agendamentos = agendamentoRepository.findByAnuncio(anuncioEncontrado);
            
            if (agendamentos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok().body(agendamentos);
            }



    }

    // Recebe uma pendencia para efetivar
    @PostMapping("/efetivar/{id}")
    public ResponseEntity<Agendamento> efetivarAgendamento(@PathVariable Integer id) {

            Optional<PendenciaAgendamentoDTO> opPendencia = pendenciaAgendamentoDTORepository.findById(id);

            if (opPendencia.isEmpty()) {
                return ResponseEntity.status(404).build();
            }

            PendenciaAgendamentoDTO pendencia = opPendencia.get();

            Agendamento agendamento_efetivado = new Agendamento(pendencia.getCheckinAgendamento(), pendencia.getCheckoutAgendamento(), usuarioRepository.findById(pendencia.getIdUsuario()).get(), anuncioRepository.findById(pendencia.getIdAnuncio()).get());

            //Excluindo pendencia que virou agendamento
            pendenciaAgendamentoDTORepository.deleteById(pendencia.getId());

            return ResponseEntity.status(201).body(agendamentoRepository.save(agendamento_efetivado));

    }
}
