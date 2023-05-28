package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Usuario;
import espacoaberto.backend.dto.CurtidaDTO;

import espacoaberto.backend.entidades.Anuncio;

import espacoaberto.backend.entidades.Curtida;

import espacoaberto.backend.repository.AnuncioRepository;

import espacoaberto.backend.repository.CurtidaRepository;
import espacoaberto.backend.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/curtidas")
public class CurtidaController {
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private CurtidaRepository curtidaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping("cadastrarCurtida")
    public ResponseEntity<Curtida> cadastrarCurtida(@RequestBody CurtidaDTO curtidaDTO){


        // Verificando se a DTO veio vazia
        if(curtidaDTO.estaVazio()){
            return ResponseEntity.status(400).build();
        }
        Curtida curtida = new Curtida();
        // Convertendo a curtidaDTO em curtida
        // Verificando se o anuncio passado na DTO existe
        Optional<Anuncio> opAnuncio = anuncioRepository.findById(curtidaDTO.getIdAnuncio());
        if (opAnuncio.isEmpty()){
            return ResponseEntity.status(404).build();
        }else{
            // Chegando aqui, o anuncio existe
            curtida.setAnuncio(opAnuncio.get());
        }
        // Verificando se o usuário passado na DTO existe
        Optional<Usuario> opUsuario = usuarioRepository.findById(curtidaDTO.getIdUsuario());

        if (opUsuario.isPresent()) {
            curtida.setUsuario(opUsuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        List<Curtida> anunciosCurtidosPorUsuario = new ArrayList<>();
        anunciosCurtidosPorUsuario = curtidaRepository.findByUsuario(opUsuario.get());

        // Verificando se o usuário já curtiu o anuncio
        for (int i = 0; i < anunciosCurtidosPorUsuario.size(); i++) {
            if(anunciosCurtidosPorUsuario.get(i).getAnuncio().getId() == curtidaDTO.getIdAnuncio()){
                // Se cairmos aqui, significa que o usuário já curtiu o anúncio
                // Logo, apenas retornar um código específico
                // para que o frontend manipule e chame o endpoint
                return ResponseEntity.status(409).build();
            }

        }



        // Definindo o localDateTime da curtida
        curtida.setMomentoCurtida(LocalDateTime.now());
        // Na DTO também
        curtidaDTO.setMomentoCurtida(LocalDateTime.now());


        // Aumentando as curtidas  do anuncio
        Optional<Anuncio> anuncio = anuncioRepository.findById(curtidaDTO.getIdAnuncio());

        anuncio.get().setCurtidas(anuncio.get().getCurtidas() + 1);

        // Cadastrando a curtida no repository
        return ResponseEntity.status(200).body(curtidaRepository.save(curtida));





    }

    @GetMapping
    public ResponseEntity<List<CurtidaDTO>> listarCurtidas(){
        List<Curtida> curtidas = curtidaRepository.findAll();
        List<CurtidaDTO> curtidasDTO = new ArrayList<>();

        if(curtidas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        // Convertendo curtida normal para DTO
        CurtidaDTO curtidaDTO = new CurtidaDTO(null, null, null);
        for (int i = 0; i < curtidas.size(); i++) {
            curtidaDTO.setIdUsuario(curtidas.get(i).getUsuario().getId());
            curtidaDTO.setIdAnuncio(curtidas.get(i).getAnuncio().getId());
            curtidaDTO.setMomentoCurtida(curtidas.get(i).getMomentoCurtida());
            curtidasDTO.add(curtidaDTO);
        }

        return ResponseEntity.status(200).body(curtidasDTO);


    }

    @DeleteMapping
    public ResponseEntity<Curtida> dessassociarCurtida(@RequestBody CurtidaDTO curtidaDTO) {
        // Verificando se a DTO veio vazia
        if(curtidaDTO.estaVazio()){
            return ResponseEntity.status(400).build();
        }

        Curtida curtida = new Curtida();

        // Encontrando a curtida que o usuário enviou
        // Descobrindo se o anuncio existe
        Optional<Anuncio> opAnuncio = anuncioRepository.findById(curtidaDTO.getIdAnuncio());
        Anuncio anuncioEncontrado;

        if(opAnuncio.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            anuncioEncontrado = opAnuncio.get();
        }
        
        Optional<Usuario> opUsuario = usuarioRepository.findById(curtidaDTO.getIdUsuario());

        // Listar todas as curtidas para que seja possível encontrar uma curtida que tenha o usuario e anuncio que temos
        List<Curtida> curtidasCadastradas = curtidaRepository.findAll();

        for (Curtida curtidaCadastrada : curtidasCadastradas) {
            if (curtidaCadastrada.getUsuario().getId() == curtidaDTO.getIdUsuario() && curtidaCadastrada.getAnuncio().getId() == curtidaDTO.getIdAnuncio()) {
                // Curtida encontrada!
                curtidaRepository.deleteById(curtidaCadastrada.getId());
                // Reduzindo a curtida do anuncio
                anuncioEncontrado.setCurtidas(anuncioEncontrado.getCurtidas() - 1);
                anuncioRepository.save(anuncioEncontrado);
                return ResponseEntity.ok().body(curtidaCadastrada);
            }
        }

        return ResponseEntity.notFound().build();

    }
}
