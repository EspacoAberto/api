package espacoaberto.backend.controllers;
/*
import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.AgendamentoDTO;

import java.time.*;

import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import espacoaberto.backend.entidades.Agendamento;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.*;
import espacoaberto.backend.service.ServiceBase64;
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
    private ClienteRepository clienteRepository;
    @Autowired
    private AnuncianteRepository anuncianteRepository;


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

    @GetMapping("/anuncios/{idBase64}")
    public ResponseEntity<List<Agendamento>> listarAgendamentosPorId(@PathVariable String idBase64){
        Integer idDecodificado;

        try{
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));

            // Verificando se o anuncio existe
            Optional<Anuncio> opAnuncio = anuncioRepository.findById(idDecodificado);

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

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }


    }

    // Recebe uma pendencia para efetivar
    @PostMapping("/efetivar/{idBase64}")
    public ResponseEntity<Agendamento> efetivarAgendamento(@PathVariable String idBase64) {
        Integer idDecodificado;
        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<PendenciaAgendamentoDTO> opPendencia = pendenciaAgendamentoDTORepository.findById(idDecodificado);

            PendenciaAgendamentoDTO pendencia = opPendencia.get();

            Agendamento agendamento_efetivado = new Agendamento(pendencia.getCheckinAgendamento(), pendencia.getCheckoutAgendamento(), usuarioRepository.findById(pendencia.getIdUsuario()).get(), anuncioRepository.findById(pendencia.getIdAnuncio()).get());

            //Excluindo pendencia que virou agendamento
            pendenciaAgendamentoDTORepository.deleteById(pendencia.getId());

            return ResponseEntity.status(201).body(agendamentoRepository.save(agendamento_efetivado));
        } catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }
        return ResponseEntity.status(400).build();

    }
}*/
