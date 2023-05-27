package espacoaberto.backend.controllers;

//import espacoaberto.backend.csv.ExportacaoCsv;

//import espacoaberto.backend.dto.AvaliacaoDTO;
import espacoaberto.backend.entidades.*;

//import espacoaberto.backend.exceptions.FotoNaoEncontradaException;
import espacoaberto.backend.repository.*;

//import espacoaberto.backend.service.ServiceBase64;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    //@Autowired
    // private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    // @Autowired
    // private AgendamentoRepository agendamentoRepository;
    @Autowired
    private AcomodacaoRepository acomodacaoRepository;


    @PostMapping()
    public ResponseEntity<Anuncio> cadastrar(@RequestBody Anuncio novoAnuncio) {
        novoAnuncio.setVisualizacoes(0);
        novoAnuncio.setCurtidas(0);
        return ResponseEntity.status(201).body(anuncioRepository.save(novoAnuncio));
    }

   /* @GetMapping()
    public ResponseEntity<List<Anuncio>> listar() {
        return ResponseEntity.ok().body(anuncioRepository.findAll());
    } */

    @GetMapping()
    public ResponseEntity<List<Anuncio>> consultarAnuncios(
            @RequestParam(required = false) Double precoMin,
            @RequestParam(required = false) Double precoMax,
            @RequestParam(required = false) String disponibilidade
    ) {
        List<Anuncio> anuncios;

        if (precoMin != null && precoMax != null && disponibilidade != null) {
            // Se vier os três parametros
            anuncios = anuncioRepository.findByDisponibilidadeAndPrecoBetween(disponibilidade, precoMin, precoMax);
        } else if (disponibilidade == null && precoMax != null && precoMin != null) {
            // Se vier apenas os preços
            anuncios = anuncioRepository.findByPrecoBetween(precoMin, precoMax);
        } else if (precoMin == null && disponibilidade == null && precoMax != null) {
            // Se vier apenas o preço maximo
            anuncios = anuncioRepository.findByPrecoLessThan(precoMin);
        } else if (precoMax == null && precoMin != null && disponibilidade == null) {
            // Se vier apenas o preço mínimo
            anuncios = anuncioRepository.findByPrecoGreaterThan(precoMin);
        } else if (precoMax == null && precoMin == null && disponibilidade != null) {
            // Se vier apenas disponibilidade
            anuncios = anuncioRepository.findByDisponibilidade(disponibilidade);
        } else {
            anuncios = anuncioRepository.findAll();
        }

        return anuncios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(anuncios);
    }

   /* @PatchMapping("aumentarCurtidas/{idAnuncio}")
    public ResponseEntity<Anuncio> aumentarCurtidas(@PathVariable Integer idAnuncio) {

        List<Anuncio> anuncios = anuncioRepository.findAll();

        for (int i = 0; i < anuncios.size(); i++) {
            if (anuncios.get(i).getId() == idAnuncio) {
                anuncios.get(i).setCurtidas(anuncios.get(i).getCurtidas() + 1);
                return ResponseEntity.status(200).body();
            }
        }

        return ResponseEntity.status(204).build();
    } */


    @GetMapping("/listarAnunciosPremium")
    public ResponseEntity<List<Anuncio>> listarAnunciosPremium() {
        List<Usuario> usuariosPremium = usuarioRepository.findByIsPremiumTrue();
        List<Usuario> usuariosPremiumFiltro = new ArrayList<>();

        if(usuariosPremium.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // pegando os seis primeiros anunciantes
        for (int i = 0; i < usuariosPremium.size(); i++) {
            if (usuariosPremium.get(i) != null) {
                usuariosPremiumFiltro.add(usuariosPremium.get(i));
            }
        }

        List<Anuncio> anunciosSelecionados = new ArrayList<>();

        for (Usuario a : usuariosPremiumFiltro) {
            List<Anuncio> anuncios = anuncioRepository.findByUsuario(a);

            if (!anuncios.isEmpty()) {
                anunciosSelecionados.add(anuncios.get(0));
            }
        }

        return ResponseEntity.status(200).body(anunciosSelecionados);

    }

    @PatchMapping("aumentarVisualizacoes/{id}")
    public ResponseEntity<Anuncio> aumentarVisualizacao(@PathVariable Integer id){
        Optional<Anuncio> OPanuncioEncontrado = anuncioRepository.findById(id);

        if (OPanuncioEncontrado.isPresent()){
            // Caso encontre o anuncio procurado
            Anuncio anuncioEncontrado = OPanuncioEncontrado.get();
            int visualizacoes = anuncioEncontrado.getVisualizacoes();
            visualizacoes = visualizacoes +  1;
            anuncioEncontrado.setVisualizacoes(visualizacoes);
            anuncioRepository.save(anuncioEncontrado);

            // Criando novo anuncio com mesmas caracteristicas e aumentando 1 de visualização

            return ResponseEntity.status(201).body(anuncioEncontrado);

        }else {
            return ResponseEntity.status(404).build();
        }
    }


   /* @PostMapping("enviarAvaliacao")
    public ResponseEntity<Avaliacao> enviarAvaliacao(@RequestBody AvaliacaoDTO avDTO){
        Avaliacao avaliacao = new Avaliacao();

        Anuncio an = anuncioRepository.findById(avDTO.getIdAnuncio()).get();

        // Verifica se o usuário é anunciante
        Optional<Anunciante> ant = anuncianteRepository.findById(avDTO.getIdUsuario());

        // É anunciante
        if(ant.isPresent()){
            Anunciante antEncontrado = ant.get();
            avaliacao.setUsuario(antEncontrado);

        }else{
            // É cliente
            Optional<Cliente> cli = clienteRepository.findById(avDTO.getIdUsuario());
            Cliente cliEncontrado = cli.get();
            avaliacao.setUsuario(cliEncontrado);
        }


        avaliacao.setAnuncio(an);
        avaliacao.setAvaliacao(avDTO.getAvaliacao());
        return ResponseEntity.status(200).body(avaliacaoRepository.save(avaliacao));
    }

    @GetMapping("avaliacoes")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoes(){
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();

        if (avaliacoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }else {
            // Rodando a lista de avaliações encontradas e adicionando a listas de DTO
            for (int i = 0; i < avaliacoes.size(); i++) {
                AvaliacaoDTO avDTO = new AvaliacaoDTO(avaliacoes.get(i).getAnuncio().getIdAnuncio(), avaliacoes.get(i).getUsuario().getId(), avaliacoes.get(i).getAvaliacao());
                avaliacoesDTO.add(avDTO);
            }
            return ResponseEntity.status(200).body(avaliacoesDTO);
        }
    }

    @GetMapping("avaliacoes/usuarios/{idBase64}")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesPorUsuario(@PathVariable String idBase64){
        String stid = ServiceBase64.descriptografaBase64(idBase64);

        if (stid == null){
            return ResponseEntity.status(404).build();
        }

        int id = Integer.parseInt(stid);

        // Verificando se o usuário existe
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();
            List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuario(usuario);
            //Verificando se a lista de avaliações está vazia
            if (avaliacoes.isEmpty()){
                return ResponseEntity.noContent().build();
            }else {
                // Convertendo avaliação para avaliacao DTO
                // Rodando a lista de avaliações encontradas e adicionando a listas de DTO
                for (int i = 0; i < avaliacoes.size(); i++) {
                    AvaliacaoDTO avDTO = new AvaliacaoDTO(avaliacoes.get(i).getAnuncio().getIdAnuncio(), avaliacoes.get(i).getUsuario().getId(), avaliacoes.get(i).getAvaliacao());
                    avaliacoesDTO.add(avDTO);
                }
                return ResponseEntity.status(200).body(avaliacoesDTO);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("avaliacoes/anuncio/{idBase64}")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacaoPorAnuncio(@PathVariable String idBase64){
        String stid = ServiceBase64.descriptografaBase64(idBase64);

        if (stid == null){
            return ResponseEntity.status(404).build();
        }

        int id = Integer.parseInt(stid);

        // Verificando se o anuncio existe
        Optional<Anuncio> optionalAnuncio = anuncioRepository.findById(id);

        if(optionalAnuncio.isPresent()){
            List<Avaliacao> avaliacoes = avaliacaoRepository.findByAnuncio(optionalAnuncio.get());
            // Verificando se contém anúncios
            if (avaliacoes.isEmpty()){
                return ResponseEntity.status(204).build();
            }else {
                List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();
                // Convertendo as avaliacoes do anuncio em avaliações DTO
                for (int i = 0; i < avaliacoes.size(); i++) {
                    avaliacaoDTOS.add(new AvaliacaoDTO(avaliacoes.get(i).getAnuncio().getIdAnuncio(), avaliacoes.get(i).getUsuario().getId(), avaliacoes.get(i).getAvaliacao()));

                }
                return ResponseEntity.status(200).body(avaliacaoDTOS);
            }
        }else{
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("avaliacoes/ordenarPorMaior")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesOrdenadoPorMaior(){
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        if (avaliacoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();
        for (int i = 0; i < avaliacoes.size(); i++) {
            avaliacoesDTO.add(new AvaliacaoDTO(avaliacoes.get(i).getAnuncio().getIdAnuncio(), avaliacoes.get(i).getUsuario().getId(), avaliacoes.get(i).getAvaliacao()));
        }

        // Ordenando as avaliações
        List<AvaliacaoDTO> sortedList = avaliacoesDTO.stream()
                .sorted(Comparator.comparingDouble(AvaliacaoDTO::getAvaliacao)
                        .reversed())
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(sortedList);

    }

    @GetMapping("avaliacoes/ordenarPorMenor")
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoesOrdenadoPorMenor(){
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        if (avaliacoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<>();
        for (int i = 0; i < avaliacoes.size(); i++) {
            avaliacoesDTO.add(new AvaliacaoDTO(avaliacoes.get(i).getAnuncio().getIdAnuncio(), avaliacoes.get(i).getUsuario().getId(), avaliacoes.get(i).getAvaliacao()));
        }

        // Ordenando as avaliações
        List<AvaliacaoDTO> sortedList = avaliacoesDTO.stream()
                .sorted(Comparator.comparingDouble(AvaliacaoDTO::getAvaliacao))
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(sortedList);
    }

    @GetMapping("/avaliacoes/mediaPorImovel/{idBase64}")
    public ResponseEntity<Double> retornaMediaPorImovel(@PathVariable String idBase64){
        int id = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
        Optional<Anuncio> OPanuncioEncontrado = anuncioRepository.findById(id);

        if (OPanuncioEncontrado.isPresent()){
            List<Avaliacao> avaliacoes = avaliacaoRepository.findByAnuncio(OPanuncioEncontrado.get());
            // Verificando se contém anúncios
            if (avaliacoes.isEmpty()){
                return ResponseEntity.status(204).build();
            }else{
                Double media = 0.0;
                for (int i = 0; i < avaliacoes.size(); i++) {
                    media += avaliacoes.get(i).getAvaliacao();
                }
                media = media / avaliacoes.size();
                return ResponseEntity.status(200).body(media);
            }
        }else{
            return ResponseEntity.status(404).build();
        }
    }
*/
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAnuncio(@PathVariable Integer id) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);

        if (anuncio.isEmpty()) {
            return ResponseEntity.status(404).build();
        }



        anuncioRepository.deleteById(id);
        imovelRepository.deleteById(anuncio.get().getImovel().getId());

        for (Acomodacao acomodacao : acomodacaoRepository.findAll()) {
            if (acomodacao.equals(anuncio.get().getImovel().getAcomodacoes())) {
                acomodacaoRepository.deleteById(acomodacao.getId());
            }
        }




        return ResponseEntity.status(200).build();
    }
}
