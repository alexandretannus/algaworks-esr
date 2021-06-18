package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CozinhaControllerIT extends AbstractTestControllerWithDelete {
    
    private static final int COZINHA_ID_INEXISTENTE = 100;

    private long quantidadeCozinhasCadastradas;
    private String jsonIncorretoAtualizacao;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private CozinhaRepository repository;

    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        cleaner.clearTables();
        prepararDados();        
        quantidadeCozinhasCadastradas = repository.count();

        super.pathParam = "cozinhaId";
        super.param = "{cozinhaId}";

        super.userMessage = COZINHA_INEXISTENTE_MESSAGE;

        super.jsonCorretoCadastro = ResourceUtil
            .getContentFromResource("/json/correto/cozinha/cozinha.json");
        
        jsonIncorretoAtualizacao = ResourceUtil
            .getContentFromResource("/json/correto/cozinha/cozinhaUpdateIncorreto.json");        
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
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        given()
            .pathParam(pathParam, 2L)
            .accept(ContentType.JSON)
        .when()
            .get(param)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("Indiana"));        
    }

    @Test
    public void deveRetornarStatus404_QuandoTentarAtualizarCozinhaInexistente() {
        given()
            .pathParam(pathParam, COZINHA_ID_INEXISTENTE)
            .body(jsonCorretoCadastro)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoTentarAtualizarCozinhaComDadosIncorretos() {
        given()
            .pathParam(pathParam, 2)
            .body(jsonIncorretoAtualizacao)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
