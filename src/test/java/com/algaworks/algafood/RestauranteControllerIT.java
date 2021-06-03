package com.algaworks.algafood;


import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteControllerIT {
    
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        cleaner.clearTables();
        prepararDados();
        
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    private void prepararDados() {
        Cozinha chinesa = new Cozinha();
        chinesa.setNome("Chinesa");
        cozinhaRepository.save(chinesa);

        Cozinha brasileira = new Cozinha();
        brasileira.setNome("Brasileira");
        cozinhaRepository.save(brasileira);

        Restaurante restauranteBrasil = new Restaurante();
        restauranteBrasil.setCozinha(brasileira);
        restauranteBrasil.setNome("Feijoada - Frete Grátis");
        restauranteBrasil.setTaxaFrete(BigDecimal.ZERO);
        
        Restaurante restauranteChines = new Restaurante();
        restauranteChines.setCozinha(chinesa);
        restauranteChines.setNome("China Box");
        restauranteChines.setTaxaFrete(BigDecimal.ONE);

        restauranteRepository.save(restauranteBrasil);
        restauranteRepository.save(restauranteChines);
    }
}
