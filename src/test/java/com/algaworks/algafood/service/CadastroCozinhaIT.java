package com.algaworks.algafood.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void deveAtribuirId_QuandoCadastrarComDadosCorretos(){
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Chinesa");

        //ação
        cozinha = cadastroCozinha.salvar(cozinha);

        //validação
        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCadastrarCozinhaComNomeEmBranco(){        
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("  ");

        verificarConstraintViolationException(cozinha);

    }
    
    @Test
    public void deveFalhar_QuandoCadastrarCozinhaComNomeNulo(){        
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        verificarConstraintViolationException(cozinha);
    }
        
    @Test
    public void deveFalhar_QuandoCadastrarCozinhaComNomeVazio(){        
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("");

        verificarConstraintViolationException(cozinha);
    }

    @Test
    public void deveExcluirCozinhaComSucesso() {
        cadastroCozinha.remover(5L);

        var erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {            
            cadastroCozinha.buscarOuFalhar(5L); 
        });
        
        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso(){
        var erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
            cadastroCozinha.remover(2L);
        });
        
        assertThat(erroEsperado).isNotNull();
    }
    
    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente(){        
        var erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {            
            cadastroCozinha.remover(40L); 
        });
        
        assertThat(erroEsperado).isNotNull();
    }

    private void verificarConstraintViolationException(Cozinha cozinha) {
        ConstraintViolationException erroEsperado = Assertions.assertThrows(
            ConstraintViolationException.class,
            () -> {
                cadastroCozinha.salvar(cozinha);
            });

        assertThat(erroEsperado).isNotNull();
    }
}
