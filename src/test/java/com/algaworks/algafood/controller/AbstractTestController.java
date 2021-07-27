package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import com.algaworks.algafood.CadastroDados;
import com.algaworks.algafood.domain.exception.interfaces.EntidadeInexistenteMessage;
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
public abstract class AbstractTestController implements EntidadeInexistenteMessage {

    protected static final String VIOLACAO_REGRA_NEGOCIO = "Violação de regra de negócio";
    protected static final String MENSAGEM_INCOMPREENSIVEL = "Mensagem Incompreensível";
    protected static final String CAMPOS_INVALIDOS = "Um ou mais campos estão inválidos";
    protected static final String DADOS_INVALIDOS = "Dados inválidos";

    protected static final String TIPO_INVALIDO_DETAIL = "Corrija e informe um valor compatível com o tipo";
    
    protected String jsonCorretoCadastro;

    protected String pathParam;
    protected String param;

    protected String userMessage;
    protected String resourcePath;

    @LocalServerPort
    private int port;
    
    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private CadastroDados cadastroDados;

    
    @BeforeEach
    public void setupEach() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        
        cleaner.clearTables();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarRecursos() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }
 
    @Test
    public void deveRetornarStatus404_QuandoConsultarRecursoInexistente() {
        given() 
            .pathParam(pathParam, 200L)
            .contentType(ContentType.JSON)
        .when()
            .get(param)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .body("userMessage", containsString(userMessage));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRecursoCorretamente() {
        given()
            .body(jsonCorretoCadastro)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus200_QuandoAtualizarRecursoCorretamente() {
        given()
            .pathParam(pathParam, 2L)
            .body(jsonCorretoCadastro)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    public void prepararDados() {
        cadastroDados.cadastrarEstados();
        cadastroDados.cadastrarCidades();
        cadastroDados.cadastrarCozinhas();
        cadastroDados.cadastrarFormasPagamento();
        cadastroDados.cadastrarRestaurantes();
        cadastroDados.cadastrarGrupos();
    }


    public String carregarJsonIncorreto(String nomeErro) {
        String resourceName = String.format("/json/incorreto/%s/%sIncorreto%s.json", resourcePath, resourcePath, nomeErro);

        return ResourceUtil.getContentFromResource(resourceName);
    }
}
