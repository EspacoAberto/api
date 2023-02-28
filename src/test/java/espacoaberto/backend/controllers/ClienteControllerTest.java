package espacoaberto.backend.controllers;

import espacoaberto.backend.entidades.Cliente;
import espacoaberto.backend.repository.ClienteRepository;
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
public class ClienteControllerTest {

    @Autowired
    private ClienteController controller;
    
    @MockBean
    private ClienteRepository repository;

    @Test
    @DisplayName("Retorna lista de clientes e retorna o status 200")
    void retornaComSucesso(){

        when(repository.findAll()).thenReturn(List.of(
                new Cliente(),
                new Cliente(),
                new Cliente()
        ));

        ResponseEntity<List<Cliente>> listaClientes = controller.listar();

        assertEquals(200, listaClientes.getStatusCodeValue());
        assertTrue(listaClientes.getBody().size() > 0);
    }

    @Test
    @DisplayName("Não retorna lista de Clientes e retorna o status 204")
    void retornaComFalha(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Cliente>> listaClientes = controller.listar();

        assertEquals(204, listaClientes.getStatusCodeValue());
        assertNull(listaClientes.getBody());
    }

    @Test
    @DisplayName("Retorna 201 quando cadastro com sucesso")
    void postRetornoSucesso(){
        /*when(repository.findAll()).thenReturn(List.of(
                new Cliente()
        ));*/

        ResponseEntity<Cliente> retorno = controller.cadastrar(new Cliente());

        assertEquals(201, retorno.getStatusCodeValue());
    }
}
