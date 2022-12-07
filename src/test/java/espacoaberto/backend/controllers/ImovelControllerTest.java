package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Imovel;
import espacoaberto.backend.repository.ImovelRepository;
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
public class ImovelControllerTest {
    @Autowired
    private ImovelController controller;
    @MockBean
    private ImovelRepository repository;

    /*@Test
    @DisplayName("Retorna lista de Imovels e retorna o status 200")
    void retornaComSucesso(){

        when(repository.findAll()).thenReturn(List.of(
                new Imovel(),
                new Imovel(),
                new Imovel()
        ));

        ResponseEntity<List<Imovel>> listaImovels = controller.listar();

        assertEquals(200, listaImovels.getStatusCodeValue());
        assertTrue(listaImovels.getBody().size() > 0);
    }

    @Test
    @DisplayName("Não retorna lista de Imovels e retorna o status 204")
    void retornaComFalha(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Imovel>> listaImovels = controller.listar();

        assertEquals(204, listaImovels.getStatusCodeValue());
        assertNull(listaImovels.getBody());
    }*/

    @Test
    @DisplayName("Retorna 201 quando cadastro com sucesso")
    void postRetornoSucesso(){
        /*when(repository.findAll()).thenReturn(List.of(
                new Imovel()
        ));*/

        ResponseEntity<Imovel> retorno = controller.cadastrar(new Imovel());

        assertEquals(201, retorno.getStatusCodeValue());
    }
}
