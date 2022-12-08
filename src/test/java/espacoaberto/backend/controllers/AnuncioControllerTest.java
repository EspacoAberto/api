package espacoaberto.backend.controllers;


import espacoaberto.backend.entidades.Anuncio;
import espacoaberto.backend.repository.AnuncioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnuncioControllerTest {
    @Autowired
    private AnuncioController controller;

    @MockBean
    private AnuncioRepository repository;

   /* @Test
    @DisplayName("Retorna lista de anuncios e retorna o status 200")
    void retornaComSucesso(){

        when(repository.findAll()).thenReturn(List.of(
                new Anuncio(),
                new Anuncio(),
                new Anuncio()
        ));

        ResponseEntity<List<Anuncio>> listaAnuncios = controller.listar();

        assertEquals(200, listaAnuncios.getStatusCodeValue());
        assertTrue(listaAnuncios.getBody().size() > 0);
    }

    @Test
    @DisplayName("Não retorna lista de anuncios e retorna o status 204")
    void retornaComFalha(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Anuncio>> listaanuncios = controller.listar();

        assertEquals(204, listaanuncios.getStatusCodeValue());
        assertNull(listaanuncios.getBody());
    }*/

    @Test
    @DisplayName("Retorna 201 quando cadastro com sucesso")
    void postRetornoSucesso(){
        /*when(repository.findAll()).thenReturn(List.of(
                new anuncio()
        ));*/

        ResponseEntity<Anuncio> retorno = controller.cadastrar(new Anuncio());

        assertEquals(201, retorno.getStatusCodeValue());
    }

}
