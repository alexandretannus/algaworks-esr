package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

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
public class CozinhaControllerIT {
    
    private static final int COZINHA_ID_INEXISTENTE = 100;

    private Cozinha cozinhaAmericana;
    private long quantidadeCozinhasCadastradas;
    private String jsonCorretoCozinhaChinesa;
    private String jsonCorretoAtualizacao;
    private String jsonIncorretoAtualizacao;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        cleaner.clearTables();
        prepararDados();

        jsonCorretoCozinhaChinesa = ResourceUtil
            .getContentFromResource("/json/correto/cozinha/cozinha.json");
        
        jsonCorretoAtualizacao = ResourceUtil
            .getContentFromResource("/json/correto/cozinha/cozinhaUpdate.json");
        
        jsonIncorretoAtualizacao = ResourceUtil
            .getContentFromResource("/json/correto/cozinha/cozinhaUpdateIncorreto.json");
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterDuasCozinhas_QuandoConsultarCozinhas() {        
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize((int)quantidadeCozinhasCadastradas))
            .body("nome", hasItems("Indiana", "Tailandesa"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        given()
            .body(jsonCorretoCozinhaChinesa)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        given()
            .pathParam("cozinhaId", 2L)
            .accept(ContentType.JSON)
        .when()
            .get("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("Indiana"));        
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInxistente() {
        given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .accept(ContentType.JSON)
        .when()
            .get("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());        
    }

    @Test
    public void deveRetornarStatus404_QuandoExcluirCozinhaInexistente() {
        given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .accept(ContentType.JSON)
        .when()
            .delete("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());            
    }

    @Test
    public void deveRetornarStatus204_QuandoExcluirCozinha() {
        given()
            .pathParam("cozinhaId", 2)
            .accept(ContentType.JSON)
        .when()
            .delete("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deveRetornarStatus409_QuandoExcluirCozinhaEmUso() {
        given()
            .pathParam("cozinhaId", 1)
            .accept(ContentType.JSON)
        .when()
            .delete("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.CONFLICT.value());            
    }

    @Test
    public void deveRetornarStatus200_QuandoAtualizarCozinha() {
        given()
            .pathParam("cozinhaId", 2)
            .body(jsonCorretoAtualizacao)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void deveRetornarStatus404_QuandoTentarAtualizarCozinhaInexistente() {
        given()
            .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
            .body(jsonCorretoAtualizacao)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoTentarAtualizarCozinhaComDadosIncorretos() {
        given()
            .pathParam("cozinhaId", 2)
            .body(jsonIncorretoAtualizacao)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put("{cozinhaId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    private void prepararDados() {
        cozinhaAmericana = new Cozinha();
        cozinhaAmericana.setNome("Tailandesa");
        repository.save(cozinhaAmericana);
        
        Cozinha cozinhaIndiana = new Cozinha();
        cozinhaIndiana.setNome("Indiana");
        repository.save(cozinhaIndiana);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Teste");
        restaurante.setTaxaFrete(BigDecimal.ONE);
        restaurante.setCozinha(cozinhaAmericana);
        restauranteRepository.save(restaurante);

        quantidadeCozinhasCadastradas = repository.count();
    }
}
