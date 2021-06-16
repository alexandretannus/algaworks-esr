package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtil;

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

    private long quantidadeRestaurantesCadastrados;
    private String jsonCorretoRestaurante;
    private String jsonCorretoRestauranteUpdate;
    private String jsonIncorretoRestauranteCozinhaInexistente;

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

        jsonCorretoRestaurante = ResourceUtil
            .getContentFromResource("/json/correto/restaurante/restaurante.json");

        jsonCorretoRestauranteUpdate = ResourceUtil
                .getContentFromResource("/json/correto/restaurante/restauranteUpdate.json");

        jsonIncorretoRestauranteCozinhaInexistente = ResourceUtil
            .getContentFromResource("/json/incorreto/restaurante/restauranteIncorretoCozinhaInexistente.json");
                
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

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
        given()
            .pathParam("restauranteId", 2L)
            .accept(ContentType.JSON)
        .when()
            .get("{restauranteId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("China Box"));       
    }

    @Test
    public void deveConterDoisRestaurantes_QuandoConsultarRestaurantes() {        
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize((int)quantidadeRestaurantesCadastrados))
            .body("nome", hasItems("Feijoada - Frete Grátis", "China Box"));
    }

    @Test 
    public void deveRetornarStatus404_QuandoConsultarRestauranteInxistente() {
        given()
            .pathParam("restauranteId", 20L)
            .accept(ContentType.JSON)
        .when()
            .get("{restauranteId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(jsonCorretoRestaurante)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoRestauranteCozinhaInexistente)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString("Não existe cozinha cadastrada com o código"))
        .body("title", equalTo("Violação de regra de negócio"))
        .body("userMessage", containsString("Não existe cozinha cadastrada com o código"));;
    }

    @Test
    public void deveRetornarStatus200_QuandoAtualizarRestaurante() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("restauranteId", 1L)
            .body(jsonCorretoRestauranteUpdate)
        .when()
            .put("{restauranteId}")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarRestauranteComDadosIncorretos() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("restauranteId", 1L)
            .body(jsonIncorretoRestauranteCozinhaInexistente)
        .when()
            .put("{restauranteId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // Ativar e inativar restaurante

    @Test
    public void deveRetornarStatus204EMudarStatus_QuandoAtivarRestaurante() {
        Long codigo = 1L;

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("restauranteId", codigo)
        .when()
            .put("{restauranteId}/ativo")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        Restaurante restaurante = restauranteRepository.findById(codigo).get();
        assertTrue(restaurante.getAtivo());
    }

    @Test
    public void deveRetornarStatus204EMudarStatus_QuandoInativarRestaurante() {
        Long codigo = 1L;

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("restauranteId", codigo)
        .when()
            .delete("{restauranteId}/ativo")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        Restaurante restaurante = restauranteRepository.findById(codigo).get();
        assertFalse(restaurante.getAtivo());
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

        quantidadeRestaurantesCadastrados = restauranteRepository.count();
    }
}

