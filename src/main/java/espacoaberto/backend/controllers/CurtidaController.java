package espacoaberto.backend.controllers;

import espacoaberto.backend.abstrato.Usuario;
import espacoaberto.backend.dto.CurtidaDTO;
import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.entidades.Curtida;
import espacoaberto.backend.repository.AnuncianteRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ClienteRepository;
import espacoaberto.backend.repository.CurtidaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curtidas")
public class CurtidaController {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private AnuncianteRepository anuncianteRepository;
    @Autowired
    private CurtidaRepository curtidaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
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
        Optional<Anunciante> opAnunciante = anuncianteRepository.findById(curtidaDTO.getIdUsuario());
        // Caso exista, atribuir nessa váriavel para uso futuro
        Cliente cliente = new Cliente();
        Anunciante anunciante = new Anunciante();
        // Se der true, assumimos que ele não é anunciante, mas pode ser cliente
        if(opAnunciante.isEmpty()){
            Optional<Cliente> opCliente = clienteRepository.findById(curtidaDTO.getIdUsuario());
            // Se der true, assumimos que ele não é cliente também, logo não existe
            if (opCliente.isEmpty()){
                return ResponseEntity.status(404).build();
            }
            // Se chegar até aqui, significa que ele é cliente

            curtida.setUsuario(opCliente.get());

            // Criando o usuário que curtiu
             cliente = opCliente.get();
             anunciante = null;
        }else{
            // E se chegar até aqui, ele é anunciante
            curtida.setUsuario(opAnunciante.get());

            // Criando o usuário que curtiu
            anunciante = opAnunciante.get();
            cliente = null;
        }


        // Descobrindo se o usuário é cliente ou anunciante
        List<Curtida> anunciosCurtidosPorUsuario = new ArrayList<>();
        if (anunciante == null){
            anunciosCurtidosPorUsuario = curtidaRepository.findByUsuario(cliente);
        }else{
             anunciosCurtidosPorUsuario = curtidaRepository.findByUsuario(anunciante);
        }

        // Verificando se o usuário já curtiu o anuncio
        for (int i = 0; i < anunciosCurtidosPorUsuario.size(); i++) {
            if(anunciosCurtidosPorUsuario.get(i).getAnuncio().getIdAnuncio() == curtidaDTO.getIdAnuncio()){
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
            curtidaDTO.setIdAnuncio(curtidas.get(i).getAnuncio().getIdAnuncio());
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

        // Descobrindo se é anunciante ou usuário
        Optional<Anunciante> opAnunciante = anuncianteRepository.findById(curtidaDTO.getIdUsuario());
        Optional<Cliente> opCliente = clienteRepository.findById(curtidaDTO.getIdUsuario());
        Cliente cli;
        Anunciante an;

        if (opAnunciante.isEmpty()) {
            // Se cair aqui, ele não é anunciante, mas pode ser cliente
            if (opCliente.isEmpty()) {
                // Se cair aqui, o usuário não existe
                return ResponseEntity.notFound().build();
            }  else {
                // Se cair aqui, ele é cliente
                cli = opCliente.get();
            }

        }  else {
            // Se cair aqui, ele é anunciante
            an = opAnunciante.get();
        }

        // Listar todas as curtidas para que seja possível encontrar uma curtida que tenha o usuario e anuncio que temos
        List<Curtida> curtidasCadastradas = curtidaRepository.findAll();

        for (Curtida curtidaCadastrada : curtidasCadastradas) {
            if (curtidaCadastrada.getUsuario().getId() == curtidaDTO.getIdUsuario() && curtidaCadastrada.getAnuncio().getIdAnuncio() == curtidaDTO.getIdAnuncio()) {
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
