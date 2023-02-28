package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Anunciante;
import espacoaberto.backend.repository.AnuncianteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnuncianteControllerTest {

@Autowired
private AnuncianteController controller;

@MockBean
private AnuncianteRepository repository;

    @Test
    @DisplayName("Retorna lista de anunciantes e retorna o status 200")
    void retornaComSucesso(){

        when(repository.findAll()).thenReturn(List.of(
                new Anunciante(),
                new Anunciante(),
                new Anunciante()
        ));

        ResponseEntity<List<Anunciante>> listaAnunciantes = controller.listarAnunciantes();

        assertEquals(200, listaAnunciantes.getStatusCodeValue());
        assertTrue(listaAnunciantes.getBody().size() > 0);
    }

    @Test
    @DisplayName("Não retorna lista de anunciantes e retorna o status 204")
    void retornaComFalha(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Anunciante>> listaAnunciantes = controller.listarAnunciantes();

        assertEquals(204, listaAnunciantes.getStatusCodeValue());
        assertNull(listaAnunciantes.getBody());
    }



    @Test
    @DisplayName("Retorna 201 quando cadastro com sucesso")
    void postRetornoSucesso(){
       /* when(repository.findAll()).thenReturn(List.of(
                new Anunciante()
        )); */

        ResponseEntity<Anunciante> retorno = controller.cadastrarAnunciante(new Anunciante());

        assertEquals(201, retorno.getStatusCodeValue());
    }
}
