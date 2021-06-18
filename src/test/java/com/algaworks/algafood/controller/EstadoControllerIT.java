package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
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
public class EstadoControllerIT {
    
    private long quantidadeEstadosCadastrados;
    private String jsonCorretoEstado;
    private String jsonCorretoEstadoUpdate;
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
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @BeforeEach
    public void setup() {
        
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/estados";

        cleaner.clearTables();
        prepararDados();

        jsonCorretoEstado = ResourceUtil
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
    public void deveRetornarStatus200_QuandoConsultarEstados() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

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
            .pathParam("estadoId", 1L)
            .contentType(ContentType.JSON)
        .when()
            .get("{estadoId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("Goiás"));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarEstadoInexistente() {
        given() 
            .pathParam("estadoId", 200L)
            .contentType(ContentType.JSON)
        .when()
            .get("{estadoId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("userMessage", containsString("Não existe estado cadastrado com o código"));
    }

    // Cadastro de estados
    @Test
    public void deveRetornarStatus201_QuandoCadastrarEstadoCorretamente() {
        given()
            .body(jsonCorretoEstado)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    
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
            .pathParam("estadoId", 2L)
        .when()
            .put("{estadoId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", containsString("Mensagem Incompreensível"))
            .body("detail", containsString("A propriedade 'sigla' é inválida"));
    }

    // Atualização de estados
    @Test
    public void deveRetornarStatus200_QuandoAtualizarEstadoCorretamente() {
        given()
            .body(jsonCorretoEstado)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("estadoId", 2L)
        .when()
            .put("{estadoId}")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComNomeNulo() {
        given()
            .body(jsonIncorretoEstadoNomeNulo)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("estadoId", 2L)
        .when()
            .put("{estadoId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
       
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComNomeEmBranco() {
        given()
            .body(jsonIncorretoEstadoNomeBranco)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("estadoId", 2L)
        .when()
            .put("{estadoId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }   
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComNomeApenasComEspaços() {
        given()
            .body(jsonIncorretoEstadoNomeEspaco)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("estadoId", 2L)
        .when()
            .put("{estadoId}")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoAtualizarEstadoComPropriedadeInexistente() {
        given()
            .body(jsonIncorretoEstadoPropriedadeInexistente)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", containsString("Mensagem Incompreensível"))
            .body("detail", containsString("A propriedade 'sigla' é inválida"));
    }
    // Exclusão de estado
    @Test
    public void deveRetornarStatus204_QuandoExcluirEstado() {
        given()
            .pathParam("estadoId", 2L)
            .contentType(ContentType.JSON)
        .when()
            .delete("{estadoId}")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
    
    @Test
    public void deveRetornarStatus404_QuandoExcluirEstadoInexistente() {
        given()
            .pathParam("estadoId", 20L)
            .contentType(ContentType.JSON)
        .when()
            .delete("{estadoId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
    
    @Test
    public void deveRetornarStatus409_QuandoExcluirEstadoEmUso() {
        given()
            .pathParam("estadoId", 1L)
            .contentType(ContentType.JSON)
        .when()
            .delete("{estadoId}")
        .then()
            .statusCode(HttpStatus.CONFLICT.value());
    }

    private void prepararDados() {
        Estado goias = new Estado();
        goias.setNome("Goiás");
        estadoRepository.save(goias);

        Estado minas = new Estado();
        minas.setNome("Minas Gerais");
        estadoRepository.save(minas);        

        Cidade goiania = new Cidade();
        goiania.setEstado(goias);
        goiania.setNome("Goiânia");
        cidadeRepository.save(goiania);
        
        quantidadeEstadosCadastrados = estadoRepository.count();

    }
}
