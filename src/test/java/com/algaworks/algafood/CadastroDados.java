package com.algaworks.algafood;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroDados {

    private static Estado goias;
    private static Estado minas;
    private static Cidade goiania;
    private static Cidade anapolis;
    private static Cozinha cozinhaTailandesa;
    private static Cozinha cozinhaBrasileira;
    private static Cozinha cozinhaIndiana;
    private static Cozinha cozinhaChinesa;

    private static Restaurante restauranteChines;
    private static Restaurante restauranteBrasil;
    private static Restaurante restauranteBrasil2;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;
    
    public void cadastrarEstados() {

        goias = Estado.builder().nome("Goiás").build();
        minas = Estado.builder().nome("Minas Gerais").build();     
        
        estadoRepository.save(goias);   
        estadoRepository.save(minas);
    }

    public void cadastrarCidades() {
        goiania = Cidade.builder().nome("Goiânia").estado(goias).build();
        anapolis = Cidade.builder().nome("Anápolis").estado(goias).build();

        cidadeRepository.save(goiania);
        cidadeRepository.save(anapolis);
    }

    public void cadastrarCozinhas() {
        cozinhaTailandesa = Cozinha.builder().nome("Tailandesa").build();        
        cozinhaIndiana = Cozinha.builder().nome("Indiana").build();
        cozinhaChinesa = Cozinha.builder().nome("Chinesa").build();
        cozinhaBrasileira = Cozinha.builder().nome("Brasileira").build();

        cozinhaRepository.save(cozinhaBrasileira);
        cozinhaRepository.save(cozinhaIndiana);
        cozinhaRepository.save(cozinhaTailandesa);
        cozinhaRepository.save(cozinhaChinesa);
    }

    public void cadastrarRestaurantes() {

        Endereco endereco1 = criarEndereco("Rua Brasil", "154", "750000", "Centro", anapolis);
        Endereco endereco2 = criarEndereco("Rua China", "845", "750000", "Centro", anapolis);
        Endereco endereco3 = criarEndereco("Rua Brasil", "222", "750000", "Centro", anapolis);
        restauranteBrasil = Restaurante.builder()
                                .nome("Feijoada")
                                .taxaFrete(BigDecimal.ZERO)
                                .cozinha(cozinhaBrasileira)
                                .endereco(endereco1)
                                .build();

        restauranteChines = Restaurante.builder()
                                .nome("China Box")
                                .taxaFrete(BigDecimal.ONE)
                                .cozinha(cozinhaChinesa)
                                .endereco(endereco2)
                                .build();

        restauranteBrasil2 = Restaurante.builder()
                                .nome("Restaurante Brasil")
                                .taxaFrete(BigDecimal.valueOf(5.10))
                                .endereco(endereco3)
                                .cozinha(cozinhaBrasileira)
                                .build();

        restauranteRepository.save(restauranteBrasil);
        restauranteRepository.save(restauranteChines);
        restauranteRepository.save(restauranteBrasil2);
    }

    private Endereco criarEndereco(String logradouro, String numero, String cep, String bairro, Cidade cidade) {
        return Endereco.builder()
                    .bairro(bairro)
                    .cidade(cidade)
                    .logradouro(logradouro)
                    .cep(cep)
                    .numero(numero)
                    .build();


    }



}
