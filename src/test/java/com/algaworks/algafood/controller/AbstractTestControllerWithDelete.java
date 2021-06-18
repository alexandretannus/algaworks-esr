package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.http.ContentType;

public class AbstractTestControllerWithDelete extends AbstractTestController {
    
    @Test
    public void deveRetornarStatus204_QuandoExcluirRecurso() {
        given()
            .pathParam(pathParam, 2L)
            .accept(ContentType.JSON)
        .when()
            .delete(param)
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deveRetornarStatus404_QuandoExcluirRecursoInexistente() {
        given()
            .pathParam(pathParam, 200L)
            .accept(ContentType.JSON)
        .when()
            .delete(param)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());            
    }

    @Test
    public void deveRetornarStatus409_QuandoExcluirRecursoEmUso() {
        given()
            .pathParam(pathParam, 1L)
            .accept(ContentType.JSON)
        .when()
            .delete(param)
        .then()
            .statusCode(HttpStatus.CONFLICT.value());            
    }
}
