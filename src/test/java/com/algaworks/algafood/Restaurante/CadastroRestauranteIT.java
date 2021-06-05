package com.algaworks.algafood.Restaurante;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroRestauranteIT {
    
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Test
    public void deveAtribuirId_QuandoCadastrarComDadosCorretos() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante Tailandês");
        restaurante.setTaxaFrete(new BigDecimal(2.5));

        restaurante.setCozinha(cozinhaRepository.findById(1L).get());

        restaurante = cadastroRestauranteService.salvar(restaurante);

        assertThat(restaurante).isNotNull();
        assertThat(restaurante.getId()).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCadastrarRestauranteComNomeEmBranco(){        
        //cenário
        Cozinha cozinha = cozinhaRepository.findById(1L).get();

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("  ");
        restaurante.setTaxaFrete(new BigDecimal(2.5));
        restaurante.setCozinha(cozinha);

        verificarConstraintViolationException(restaurante);
    }
    
    @Test
    public void deveFalhar_QuandoCadastrarRestauranteComNomeNulo(){        
        //cenário
        Cozinha cozinha = cozinhaRepository.findById(1L).get();

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(null);
        restaurante.setTaxaFrete(new BigDecimal(2.5));
        restaurante.setCozinha(cozinha);

        verificarConstraintViolationException(restaurante);
    }

    
    @Test
    public void deveFalhar_QuandoCadastrarRestauranteComTaxaFreteEmBranco(){        
        //cenário
        Cozinha cozinha = cozinhaRepository.findById(1L).get();

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Teste");
        restaurante.setTaxaFrete(null);
        restaurante.setCozinha(cozinha);

        verificarConstraintViolationException(restaurante);
    }

    private void verificarConstraintViolationException(Restaurante restaurante) {
        ConstraintViolationException erroEsperado = Assertions.assertThrows(
            ConstraintViolationException.class,
            () -> {
                cadastroRestauranteService.salvar(restaurante);
            });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoBuscarRestauranteInexistente() {
        EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(
            EntidadeNaoEncontradaException.class,
            () -> {
                cadastroRestauranteService.buscarOuFalhar(1000L);
            });

        assertThat(erroEsperado).isNotNull();
    }
    
    @Test
    public void deveEncontrarRestaurante_QuandoRestauranteExistir() {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(1L);

        assertEquals(1L, restaurante.getId());
        assertEquals("Thai Gourmet", restaurante.getNome());

    }
}
