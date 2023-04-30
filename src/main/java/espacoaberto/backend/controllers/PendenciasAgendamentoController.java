package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.AgendamentoDTO;
import espacoaberto.backend.dto.PendenciaAgendamentoDTO;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.*;
import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pendencias")
public class PendenciasAgendamentoController {
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

    @GetMapping
    public ResponseEntity<List<PendenciaAgendamentoDTO>> listarPendencias(){
        List<PendenciaAgendamentoDTO> pendencias = pendenciaAgendamentoDTORepository.findAll();

        if (pendencias.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(pendencias);
        }

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PendenciaAgendamentoDTO> cadastrarAgendamento(@RequestBody AgendamentoDTO novoAgendamentoDTO){

        // Antes de cadastrar o agendamento, precisamos verificar se existe uma pendência existente para aquele usuário
        // Buscando o usuário enviado na DTO
        // Verificando se é cliente ou anunciante
        Optional<PendenciaAgendamentoDTO> optionalPendenciaAgendamentoDTO;
        Optional<Anunciante> ant = anuncianteRepository.findById(novoAgendamentoDTO.getIdUsuario());

        // É anunciante
        if(ant.isPresent()){
            Anunciante antEncontrado = ant.get();
            optionalPendenciaAgendamentoDTO = pendenciaAgendamentoDTORepository.findByIdUsuario(antEncontrado.getId());


        }else if (clienteRepository.findById(novoAgendamentoDTO.getIdUsuario()).isPresent()){
            // É cliente
            Cliente cliEncontrado = clienteRepository.findById(novoAgendamentoDTO.getIdUsuario()).get();
            optionalPendenciaAgendamentoDTO = pendenciaAgendamentoDTORepository.findByIdUsuario(cliEncontrado.getId());

        } else{
            return ResponseEntity.status(404).build();
        }

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

    @GetMapping("/anuncios/{idBase64}")
    public ResponseEntity<List<PendenciaAgendamentoDTO>> listarPendenciasPorAnuncio(@PathVariable String idBase64) {
        Integer idDecodificado;

        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            List<PendenciaAgendamentoDTO> pendencias = pendenciaAgendamentoDTORepository.findByIdAnuncio(idDecodificado);
            if (pendencias.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.status(200).body(pendencias);
            }
        } catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("/usuarios/{idBase64}")
    public ResponseEntity<PendenciaAgendamentoDTO> listarPendenciasPorUsuario(@PathVariable String idBase64) {
        Integer idDecodificado;

        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<PendenciaAgendamentoDTO> pendencia = pendenciaAgendamentoDTORepository.findByIdUsuario(idDecodificado);
            if (pendencia.isPresent()) {
                return ResponseEntity.status(200).body(pendencia.get());
            } else {
                return ResponseEntity.status(204).build();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("/usuarios/{idBase64}")
    public ResponseEntity<PendenciaAgendamentoDTO> deletePendenciaPorUsuario(@PathVariable String idBase64) {
        Integer idDecodificado;

        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            // Verificando se o usuário existe
            Optional<PendenciaAgendamentoDTO> pendencia_usuario = pendenciaAgendamentoDTORepository.findByIdUsuario(idDecodificado);
            if (pendencia_usuario.isPresent()) {
                pendenciaAgendamentoDTORepository.deleteById(idDecodificado);
                // Pendencia que foi excluida
                return ResponseEntity.ok().body(pendencia_usuario.get());
            } else {
                return ResponseEntity.notFound().build();
            }

            // Esse código veio do chatgpt skajdjskadk
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idBase64}")
    public ResponseEntity<PendenciaAgendamentoDTO> deletePendencia(@PathVariable String idBase64) {
        Integer idDecodificado;

        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<PendenciaAgendamentoDTO> pendencia_excluida = pendenciaAgendamentoDTORepository.findById(idDecodificado);
                pendenciaAgendamentoDTORepository.deleteById(idDecodificado);
                // Pendencia que foi excluida
                return ResponseEntity.ok().body(pendencia_excluida.get());
            // Esse código veio do chatgpt skajdjskadk
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

   /* @DeleteMapping("/excluir/{idBase64}")
    public ResponseEntity<PendenciaAgendamentoDTO> excluirAgendamento(@PathVariable String idBase64) {
        Integer idDecodificado;

        try {
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            // Antes de excluir, precisamos verificar se ele ja existe ou não


            pendenciaAgendamentoDTORepository.deleteById(idDecodificado);


        } catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
            return ResponseEntity.status(400).build();
        }

        Optional<Anunciante> ant = anuncianteRepository.findById(novoAgendamentoDTO.getIdUsuario());

        // É anunciante
        if(ant.isPresent()){
            Anunciante antEncontrado = ant.get();
            optionalPendenciaAgendamentoDTO = pendenciaAgendamentoDTORepository.findByIdUsuario(antEncontrado.getId());


        }else if (clienteRepository.findById(novoAgendamentoDTO.getIdUsuario()).isPresent()){
            // É cliente
            Cliente cliEncontrado = clienteRepository.findById(novoAgendamentoDTO.getIdUsuario()).get();
            optionalPendenciaAgendamentoDTO = pendenciaAgendamentoDTORepository.findByIdUsuario(cliEncontrado.getId());

        } else{
            return ResponseEntity.status(404).build();
        }
    } */
}
