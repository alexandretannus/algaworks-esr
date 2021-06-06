package com.algaworks.algafood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.validation.ConstraintViolationException;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroEstadoIT {

    @Autowired
    private CadastroEstadoService estadoService;

    @Test
    public void deveEncontrarEstado_QuandoestadoExistir() {
        Estado estado = estadoService.buscarOuFalhar(1L);

        assertEquals(1L, estado.getId());
        assertEquals("Minas Gerais", estado.getNome());
    }

    @Test
    public void deveFalhar_QuandoBuscarEstadoInexistente() {
        
        var erroEsperado = Assertions.assertThrows(
            EstadoNaoEncontradoException.class, 
            () -> {
                estadoService.buscarOuFalhar(50L);
        });

        assertNotNull(erroEsperado);
    }

    @Test
    public void deveAtribuirId_QuandoCadastrarComDadosCorretos() {
        Estado estado = new Estado();
        estado.setNome("Pará");

        estado = estadoService.salvar(estado);

        assertNotNull(estado);
        assertNotNull(estado.getId());
    }

    @Test
    public void deveFalhar_QuandoCadastrarEstadoComNomeEmBranco() {
        Estado estado = new Estado();
        estado.setNome("  ");

        var erroEsperado = Assertions.assertThrows(
            ConstraintViolationException.class, 
            () -> {
                estadoService.salvar(estado);
            });
        
        assertNotNull(erroEsperado);
    }
    
    @Test
    public void deveFalhar_QuandoCadastrarEstadoComNomeNulo() {
        Estado estado = new Estado();
        estado.setNome(null);

        var erroEsperado = Assertions.assertThrows(
            ConstraintViolationException.class, 
            () -> {
                estadoService.salvar(estado);
            });
        
        assertNotNull(erroEsperado);
    }

    @Test
    public void deveFalhar_QuandoTentarExcluirEstadoInexistente() {
        var erroEsperado = Assertions.assertThrows(
            EstadoNaoEncontradoException.class, 
            () -> {
                estadoService.remover(50L);
            });
        
        assertNotNull(erroEsperado);
    }
    
    @Test
    public void deveFalhar_QuandoTentarExcluirEstadoEmUso() {
        var erroEsperado = Assertions.assertThrows(
            EntidadeEmUsoException.class, 
            () -> {
                estadoService.remover(2L);
            });
        
        assertNotNull(erroEsperado);
    }

    @Test
    public void deveExcluirEstadoComSucesso() {
        assertNotNull(estadoService.buscarOuFalhar(4L).getId());
        estadoService.remover(4L);

        var erroEsperado = Assertions.assertThrows(
            EstadoNaoEncontradoException.class, 
            () -> {
                estadoService.buscarOuFalhar(4L);
            });
        
        assertNotNull(erroEsperado);
    }
}
