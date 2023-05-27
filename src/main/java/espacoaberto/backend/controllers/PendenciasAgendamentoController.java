package espacoaberto.backend.controllers;


import espacoaberto.backend.dto.AgendamentoDTO;
import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import espacoaberto.backend.entidades.Agendamento;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pendencias")
public class PendenciasAgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private PendenciaAgendamentoDTORepository  pendenciaAgendamentoDTORepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

    @GetMapping
    public ResponseEntity<List<PendenciaAgendamentoDTO>> listarPendencias(){
        List<PendenciaAgendamentoDTO> pendencias = pendenciaAgendamentoDTORepository.findAll();

        if (pendencias.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(pendencias);
        }

    }

    @PostMapping()
    public ResponseEntity<PendenciaAgendamentoDTO> cadastrarAgendamento(@RequestBody AgendamentoDTO novoAgendamentoDTO){

        // Antes de partir para qualquer validação, vamos verificar se há algum agendamento que bata com as datas da pendencia
        // Obtendo todos os agendamentos daquele anuncio
        List<Agendamento> agendamentos = agendamentoRepository.findByAnuncio(anuncioRepository.findById(novoAgendamentoDTO.getIdAnuncio()).get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        // Aqui devemos verificar se os agendamentos presentes batem com a data
        if (!agendamentos.isEmpty()) {
           for (Agendamento agendamento : agendamentos) {
               if (LocalDateTime.parse(novoAgendamentoDTO.getDataCheckinAgendamento(), formatter).isAfter(LocalDateTime.parse(agendamento.getDataCheckin(), formatter)) || LocalDateTime.parse(novoAgendamentoDTO.getDataCheckoutAgendamento(), formatter).isBefore(LocalDateTime.parse(agendamento.getDataCheckout(), formatter))) {
                   // Se isso for verdadeiro, significa que a pendencia a ser criada está com o checkin durante outro agendamento, por isso o agendamento não deve rolar
                   return ResponseEntity.status(411).build();
               }
           }
        }

        // Validação de data (o checkin não pode ser anterior à data atual)
        if (LocalDateTime.parse(novoAgendamentoDTO.getDataCheckinAgendamento(), formatter).isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(412).build();
        }

        // Validando se o checkin é maior que o checkout
        if (LocalDateTime.parse(novoAgendamentoDTO.getDataCheckinAgendamento(), formatter).isAfter(LocalDateTime.parse(novoAgendamentoDTO.getDataCheckoutAgendamento(), formatter))) {
            return ResponseEntity.status(413).build();
        }

        // Antes de cadastrar o agendamento, precisamos verificar se existe uma pendência existente para aquele usuário
        // Buscando o usuário enviado na DTO
        // Verificando se é cliente ou anunciante
        Optional<PendenciaAgendamentoDTO> optionalPendenciaAgendamentoDTO = pendenciaAgendamentoDTORepository.findByIdUsuario(novoAgendamentoDTO.getIdUsuario());


        // Agora, devemos verificar se existe ou não pendência para esse usuário
        if (optionalPendenciaAgendamentoDTO.isPresent()){
            // Entrará nesse bloco caso tenha pendência
            // Precisamos verificar a quantidade de dias da última pendência
            PendenciaAgendamentoDTO pendenciaEncontrada = optionalPendenciaAgendamentoDTO.get();

            // Convertendo a data que o usuário fez a pendencia de agendamento para LocalDateTime
            LocalDateTime dataPendencia = LocalDateTime.parse(pendenciaEncontrada.getDataAgendamento(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            // Pegando a data atual
            LocalDateTime agora = LocalDateTime.now();

            // Calculando a diferença
            Duration duracao = Duration.between(dataPendencia, LocalDateTime.parse(novoAgendamentoDTO.getDataAgendamento(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            long diferencaEmDia = duracao.toDays();

            if (diferencaEmDia >= 7){
                // Verificando se o anúncio a ser agendado existe
                Optional<Anuncio> anuncio = anuncioRepository.findById(novoAgendamentoDTO.getIdAnuncio());

                if (anuncio.isPresent()) {
                    // Antes de adicionar a nova pendência, devemos excluir a última pendência dele
                    pendenciaAgendamentoDTORepository.deleteById(pendenciaEncontrada.getId());
                    PendenciaAgendamentoDTO pendenciaCriada = new PendenciaAgendamentoDTO(novoAgendamentoDTO.getIdAnuncio(), novoAgendamentoDTO.getIdUsuario(), novoAgendamentoDTO.getDataAgendamento(),  novoAgendamentoDTO.getDataCheckinAgendamento(), novoAgendamentoDTO.getDataCheckoutAgendamento(), novoAgendamentoDTO.getValorAgendamento());
                    return ResponseEntity.status(201).body(pendenciaAgendamentoDTORepository.save(pendenciaCriada));

                } else {
                    return ResponseEntity.status(404).build();
                }
            } else{
                return ResponseEntity.status(401).build();
            }



        } else {
            // Caso não tenha, deverá prosseguir por aqui. Como não tem pendência, o agendamento deve seguir livre
            // Verificando se o anúncio a ser agendado existe
            Optional<Anuncio> anuncio = anuncioRepository.findById(novoAgendamentoDTO.getIdAnuncio());

            if (anuncio.isPresent()) {
                PendenciaAgendamentoDTO pendenciaCriada = new PendenciaAgendamentoDTO(novoAgendamentoDTO.getIdAnuncio(), novoAgendamentoDTO.getIdUsuario(), novoAgendamentoDTO.getDataAgendamento(),  novoAgendamentoDTO.getDataCheckinAgendamento(), novoAgendamentoDTO.getDataCheckoutAgendamento(), novoAgendamentoDTO.getValorAgendamento());
                return ResponseEntity.status(201).body(pendenciaAgendamentoDTORepository.save(pendenciaCriada));

            } else {
                return ResponseEntity.status(404).build();
            }

        }

    }

    @GetMapping("/anuncios/{id}")
    public ResponseEntity<List<PendenciaAgendamentoDTO>> listarPendenciasPorAnuncio(@PathVariable Integer id) {

            List<PendenciaAgendamentoDTO> pendencias = pendenciaAgendamentoDTORepository.findByIdAnuncio(id);
            if (pendencias.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.status(200).body(pendencias);
            }

    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<PendenciaAgendamentoDTO> listarPendenciasPorUsuario(@PathVariable Integer id) {



            Optional<PendenciaAgendamentoDTO> pendencia = pendenciaAgendamentoDTORepository.findByIdUsuario(id);
            if (pendencia.isPresent()) {
                return ResponseEntity.status(200).body(pendencia.get());
            } else {
                return ResponseEntity.status(204).build();
            }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PendenciaAgendamentoDTO> deletePendencia(@PathVariable Integer id) {
            Optional<PendenciaAgendamentoDTO> pendencia_excluida = pendenciaAgendamentoDTORepository.findById(id);
                pendenciaAgendamentoDTORepository.deleteById(id);
                // Pendencia que foi excluida
                return ResponseEntity.ok().body(pendencia_excluida.get());
    }

}
