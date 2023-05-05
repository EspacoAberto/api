package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Acomodacao;
import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.AcomodacaoRepository;
import espacoaberto.backend.repository.AnuncioRepository;
import espacoaberto.backend.repository.ImovelRepository;
import espacoaberto.backend.service.ServiceBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/acomodacoes")
public class AcomodacaoController {
    @Autowired
    private AcomodacaoRepository acomodacaoRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

    @PostMapping("/cadastrar/{idBase64}")
    public ResponseEntity<Acomodacao> cadastrarAcomodacao(@PathVariable String idBase64, @RequestBody Acomodacao acomodacao){
        Integer idDecodificado;

        try{
            idDecodificado = Integer.parseInt(ServiceBase64.descriptografaBase64(idBase64));
            Optional<Imovel> im = imovelRepository.findById(idDecodificado);

            if(im.isEmpty()){
                return ResponseEntity.status(404).build();
            }

            Acomodacao a = new Acomodacao(im.get(), acomodacao.getDescricao());


            return ResponseEntity.status(200).body( this.acomodacaoRepository.save(a));

        }catch (Exception e) {
            System.out.println("Não foi possível converter o ID de base 64");
        }


        return ResponseEntity.status(404).build();

    }

    @GetMapping
    public ResponseEntity<List<Acomodacao>> listarAcomodacoes() {
        List<Acomodacao> acomodacoes = acomodacaoRepository.findAll();

        if (acomodacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(acomodacoes);
    }

    @DeleteMapping("/{idAnuncioBase64}/{idAcomodacaoBase64}")
    public ResponseEntity<Acomodacao> removerAcomodacao(@PathVariable String idAnuncioBase64, @PathVariable String idAcomodacaoBase64) {
        Integer idAnuncio = Integer.parseInt(ServiceBase64.descriptografaBase64(idAnuncioBase64));

        Optional<Anuncio> opAnuncio = anuncioRepository.findById(idAnuncio);

        if (opAnuncio.isPresent()) {
            Anuncio anuncioEncontrado = opAnuncio.get();
            List<Acomodacao> acomodacoesAnuncio = anuncioEncontrado.getImovel().getAcomodacoes();

            if (acomodacoesAnuncio.isEmpty()) {
                // Não tem acomodações
                return ResponseEntity.notFound().build();
            } else {
                Integer idAcomodacao = Integer.parseInt(ServiceBase64.descriptografaBase64(idAcomodacaoBase64));
                boolean encontrou = false;
                for (Acomodacao acomodacao : acomodacoesAnuncio) {
                    if (acomodacao.getId() == idAcomodacao) {
                        encontrou = true;
                    }
                }
                // Verificar se o ID passado no parametro equivale a algum ID daquele anuncio
                if(encontrou) {
                    Acomodacao acomodacaoExcluida = acomodacaoRepository.findById(idAcomodacao).get();

                    acomodacaoRepository.deleteById(idAcomodacao);

                    // Atualizando o imóvel
                    Imovel im = anuncioEncontrado.getImovel();

                    im.getAcomodacoes().remove(acomodacaoExcluida);
                    imovelRepository.save(im);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }



}
