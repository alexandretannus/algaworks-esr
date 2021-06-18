package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.util.ResourceUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CidadeControllerIT extends AbstractTestController {

    private long quantidadeCidadesCadastradas;
    private String jsonIncorretoCidade;

    @Autowired
    private CidadeRepository cidadeRepository;
    
    @BeforeEach
    public void setup() {
        RestAssured.basePath = "/cidades";

        prepararDados();
        quantidadeCidadesCadastradas = cidadeRepository.count();

        super.pathParam = "cidadeId";
        super.param = "{cidadeId}";

        super.jsonCorretoCadastro = ResourceUtil
                .getContentFromResource("/json/correto/cidade/cidade.json");
                           
        jsonIncorretoCidade = ResourceUtil
                .getContentFromResource("/json/incorreto/cidade/cidadeIncorreta.json");

        super.userMessage = CIDADE_INEXISTENTE_MESSAGE;

    }

    @Test
    public void deveConterDuasCidades_QuandoConsultarCidades() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()            
            .body("", hasSize((int)quantidadeCidadesCadastradas))
            .body("nome", hasItems("Goiânia", "Anápolis"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCidadeInexistente() {
        given() 
            .pathParam("cidadeId", 200L)
            .contentType(ContentType.JSON)
        .when()
            .get("{cidadeId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("userMessage", containsString(CIDADE_INEXISTENTE_MESSAGE));
    }
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarCidadeComEstadoInexistente() {
        given()
            .body(jsonIncorretoCidade)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("detail", containsString(ESTADO_INEXISTENTE_MESSAGE))
            .body("title", equalTo(VIOLACAO_REGRA_NEGOCIO))
            .body("userMessage", containsString(ESTADO_INEXISTENTE_MESSAGE));
        
    }
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarCidadeComEstadoInexistente() {
        given()
            .pathParam("cidadeId", 1L)
            .body(jsonIncorretoCidade)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put("{cidadeId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("detail", containsString(ESTADO_INEXISTENTE_MESSAGE))
            .body("title", equalTo(VIOLACAO_REGRA_NEGOCIO))
            .body("userMessage", containsString(ESTADO_INEXISTENTE_MESSAGE));
        
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCidadeExistente() {
        given()
            .pathParam("cidadeId", 1L)
            .contentType(ContentType.JSON)
        .when()
            .get("{cidadeId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("Goiânia"));
    }

    @Test
    public void deveRetornarStatus204_QuandoExcluirCidade() {
        given()
            .pathParam("cidadeId", 1L)
            .contentType(ContentType.JSON)
        .when()
            .delete("{cidadeId}")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
    
    @Test
    public void deveRetornarStatus404_QuandoExcluirCidadeInexistente() {
        given()
            .pathParam("cidadeId", 20L)
            .contentType(ContentType.JSON)
        .when()
            .delete("{cidadeId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus409_QuandoExcluirCidadeEmUso() {
        given()
            .pathParam("cidadeId", 2L)
            .contentType(ContentType.JSON)
        .when()
            .delete("{cidadeId}")
        .then()
            .statusCode(HttpStatus.CONFLICT.value());
    }

}
