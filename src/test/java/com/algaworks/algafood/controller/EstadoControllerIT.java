package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class EstadoControllerIT extends AbstractTestControllerWithDelete {
    
    private long quantidadeEstadosCadastrados;
    private String jsonIncorretoEstadoNomeNulo;
    private String jsonIncorretoEstadoNomeBranco;
    private String jsonIncorretoEstadoNomeEspaco;
    private String jsonIncorretoEstadoPropriedadeInexistente;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private EstadoRepository estadoRepository;

    @BeforeEach
    public void setup() {
        
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/estados";

        cleaner.clearTables();
        prepararDados();
        quantidadeEstadosCadastrados = estadoRepository.count();
        
        super.pathParam = "estadoId";
        super.param = "{estadoId}";
        super.userMessage = ESTADO_INEXISTENTE_MESSAGE;

        super.jsonCorretoCadastro = ResourceUtil
            .getContentFromResource("/json/correto/estado/estado.json");

        jsonIncorretoEstadoNomeBranco = ResourceUtil
            .getContentFromResource("/json/incorreto/estado/estadoNomeBranco.json");

        jsonIncorretoEstadoNomeEspaco = ResourceUtil
            .getContentFromResource("/json/incorreto/estado/estadoNomeComEspaço.json");

        jsonIncorretoEstadoNomeNulo = ResourceUtil
            .getContentFromResource("/json/incorreto/estado/estadoNomeNulo.json");

        jsonIncorretoEstadoPropriedadeInexistente = ResourceUtil
            .getContentFromResource("/json/incorreto/estado/estadoPropriedadeInexistente.json");
    }

    // Consultas de listagem
    @Test
    public void deveConterDoisEstados_QuandoConsultarEstados() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()            
            .body("", hasSize((int)quantidadeEstadosCadastrados))
            .body("nome", hasItems("Goiás", "Minas Gerais"));
    }

    // Consultas de estado específico   
    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarEstadoExistente() {
        given()
            .pathParam(pathParam, 1L)
            .contentType(ContentType.JSON)
        .when()
            .get(param)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("Goiás"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarEstadoInexistente() {
        given() 
            .pathParam(pathParam, 200L)
            .contentType(ContentType.JSON)
        .when()
            .get(param)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("userMessage", containsString(ESTADO_INEXISTENTE_MESSAGE));
    }

    // Cadastro de estados  
    @Test
    public void deveRetornarStatus400_QuandoCadastrarEstadoComNomeNulo() {
        given()
            .body(jsonIncorretoEstadoNomeNulo)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
       
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarEstadoComNomeEmBranco() {
        given()
            .body(jsonIncorretoEstadoNomeBranco)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }   
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarEstadoComNomeApenasComEspaços() {
        given()
            .body(jsonIncorretoEstadoNomeEspaco)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoCadastrarEstadoComPropriedadeInexistente() {
        given()
            .body(jsonIncorretoEstadoPropriedadeInexistente)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, 2L)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", containsString("Mensagem Incompreensível"))
            .body("detail", containsString("A propriedade 'sigla' é inválida"));
    }

    // Atualização de estados    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComNomeNulo() {
        given()
            .body(jsonIncorretoEstadoNomeNulo)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, 2L)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
       
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComNomeEmBranco() {
        given()
            .body(jsonIncorretoEstadoNomeBranco)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, 2L)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }   
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComNomeApenasComEspaços() {
        given()
            .body(jsonIncorretoEstadoNomeEspaco)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, 2L)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComPropriedadeInexistente() {
        given()
            .body(jsonIncorretoEstadoPropriedadeInexistente)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, 1L)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", containsString("Mensagem Incompreensível"))
            .body("detail", containsString("A propriedade 'sigla' é inválida"));
    }

}
