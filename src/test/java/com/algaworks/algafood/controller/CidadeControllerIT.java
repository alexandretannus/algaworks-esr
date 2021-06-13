package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

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
public class CidadeControllerIT {

    private long quantidadeCidadesCadastradas;
    private String jsonCorretoCidadeBH;
    private String jsonIncorretoCidade;


    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner cleaner;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;
    
    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/cidades";

        cleaner.clearTables();
        prepararDados();

        jsonCorretoCidadeBH = ResourceUtil
            .getContentFromResource("/json/correto/cidade/cidade.json");

        jsonIncorretoCidade = ResourceUtil
            .getContentFromResource("/json/incorreto/cidade/cidadeIncorreta.json");

    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCidades() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
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
            .body("userMessage", containsString("Não existe cidade cadastrada com o código"));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCidadeCorretamente() {
        given()
            .body(jsonCorretoCidadeBH)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    
    @Test
    public void deveRetornarStatus200_QuandoAtualizarCidadeCorretamente() {
        given()
            .pathParam("cidadeId", 2L)
            .body(jsonCorretoCidadeBH)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .put("{cidadeId}")
        .then()
            .statusCode(HttpStatus.OK.value());
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
            .body("detail", containsString("Não existe estado cadastrado com o código"))
            .body("title", equalTo("Violação de regra de negócio"))
            .body("userMessage", containsString("Não existe estado cadastrado com o código"));
        
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
            .body("detail", containsString("Não existe estado cadastrado com o código"))
            .body("title", equalTo("Violação de regra de negócio"))
            .body("userMessage", containsString("Não existe estado cadastrado com o código"));
        
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

        Cidade anapolis = new Cidade();
        anapolis.setEstado(goias);
        anapolis.setNome("Anápolis");
        cidadeRepository.save(anapolis);

        Cozinha brasileira = new Cozinha();
        brasileira.setNome("Brasileira");
        cozinhaRepository.save(brasileira);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua ABC");
        endereco.setCidade(anapolis);

        Restaurante restaurante = new Restaurante();
        restaurante.setCozinha(brasileira);
        restaurante.setNome("Restaurante Brasil");
        restaurante.setTaxaFrete(BigDecimal.valueOf(5.10));
        restaurante.setEndereco(endereco);
        restauranteRepository.save(restaurante);
        
        quantidadeCidadesCadastradas = cidadeRepository.count();

    }

}
