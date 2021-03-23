package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

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

    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_QuandoCadastrarCozinhaComNomeEmBranco(){        
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("  ");

        //ação
        cozinha = cadastroCozinha.salvar(cozinha);
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_QuandoCadastrarCozinhaComNomeNulo(){        
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        //ação
        cozinha = cadastroCozinha.salvar(cozinha);
    }
        
    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_QuandoCadastrarCozinhaComNomeVazio(){        
        //cenário
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("");

        //ação
        cozinha = cadastroCozinha.salvar(cozinha);
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_QuandoExcluirCozinhaEmUso(){
        cadastroCozinha.remover(2L);
    }
    
    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirCozinhaInexistente(){
        cadastroCozinha.remover(40L);
    }
}
