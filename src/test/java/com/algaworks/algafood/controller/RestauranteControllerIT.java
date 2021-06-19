package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.ResourceUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestauranteControllerIT extends AbstractTestController {   
    
    private static final String FRETE_OBRIGATORIO = "Taxa de frete do restaurante é obrigatório";
    private static final String FRETE_NAO_NEGATIVO = "Taxa de frete do restaurante deve ser um valor maior ou igual a zero";
    private static final String ENDERECO_OBRIGATORIO = "Endereço é obrigatório";

    private long quantidadeRestaurantesCadastrados;
    private Long codigoRestauranteExistente = 1L;
    private Long codigoRestauranteInexistente = 100L;
    
    private String jsonIncorretoRestauranteCozinhaInexistente;
    private String jsonIncorretoRestauranteDadoTipoInvalido;
    private String jsonIncorretoRestauranteFreteNegativo;
    private String jsonIncorretoRestauranteFreteNulo;
    private String jsonIncorretoRestauranteSemEndereco;


    @Autowired
    private RestauranteRepository restauranteRepository;

    @BeforeEach
    public void setup() {

        RestAssured.basePath = "/restaurantes";        

        prepararDados();
        quantidadeRestaurantesCadastrados = restauranteRepository.count();

        super.param = "{restauranteId}";
        super.pathParam = "restauranteId";
        super.userMessage = RESTAURANTE_INEXISTENTE_MESSAGE;
        super.resourcePath = "restaurante";

        super.jsonCorretoCadastro = ResourceUtil
            .getContentFromResource("/json/correto/restaurante/restaurante.json");

        jsonIncorretoRestauranteCozinhaInexistente = carregarJsonIncorreto("CozinhaInexistente");        
        jsonIncorretoRestauranteDadoTipoInvalido = carregarJsonIncorreto("DadoTipoInvalido");
        jsonIncorretoRestauranteFreteNegativo = carregarJsonIncorreto("FreteNegativo");        
        jsonIncorretoRestauranteFreteNulo = carregarJsonIncorreto("FreteNulo");
        jsonIncorretoRestauranteSemEndereco = carregarJsonIncorreto("SemEndereco");
    }

    // Consultas
    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
        given()
            .pathParam("restauranteId", codigoRestauranteExistente)
            .accept(ContentType.JSON)
        .when()
            .get("{restauranteId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nome", equalTo("Feijoada"));       
    }

    @Test
    public void deveConterDoisRestaurantes_QuandoConsultarRestaurantes() {        
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize((int)quantidadeRestaurantesCadastrados))
            .body("nome", hasItems("Feijoada", "China Box"));
    }

    // Cadastros
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
        .body("detail", containsString(COZINHA_INEXISTENTE_MESSAGE))
        .body("title", equalTo(VIOLACAO_REGRA_NEGOCIO))
        .body("userMessage", containsString(COZINHA_INEXISTENTE_MESSAGE));;
    }

    @Test
    public void deveRetornarStatus400_QuandoTentarCadastrarRestauranteComFreteNegativo() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoRestauranteFreteNegativo)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString(CAMPOS_INVALIDOS))
        .body("title", equalTo(DADOS_INVALIDOS))
        .body("objects.userMessage", hasItem(containsString(FRETE_NAO_NEGATIVO)));
    }

    @Test
    public void deveRetornarStatus400_QuandoTentarCadastrarRestauranteComFreteNulo() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoRestauranteFreteNulo)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString(CAMPOS_INVALIDOS))
        .body("title", equalTo(DADOS_INVALIDOS))
        .body("objects.userMessage", hasItem(containsString(FRETE_OBRIGATORIO)));
    }


    @Test
    public void deveRetornarStatus400_QuandoTentarCadastrarRestauranteSemEndereco() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoRestauranteSemEndereco)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString(CAMPOS_INVALIDOS))
        .body("title", equalTo(DADOS_INVALIDOS))
        .body("objects.userMessage", hasItem(containsString(ENDERECO_OBRIGATORIO)));
    }
    
    //Atualizações
    @Test
    public void deveRetornarStatus404_QuandoTentarAtualizarRestauranteInexistente() {

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, codigoRestauranteInexistente)
            .body(jsonCorretoCadastro)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoTentarAtualizarRestauranteComCozinhaInexistente() {

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, codigoRestauranteExistente)
            .body(jsonIncorretoRestauranteCozinhaInexistente)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarStatus400_QuandoTentarAtualizarRestauranteComDadosDeTipoInvalido() {        
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, codigoRestauranteExistente)
            .body(jsonIncorretoRestauranteDadoTipoInvalido)
        .when()
            .put(param)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", containsString(MENSAGEM_INCOMPREENSIVEL))
            .body("detail", containsString(TIPO_INVALIDO_DETAIL));

    }

    // Ativar e inativar restaurante

    @Test
    public void deveRetornarStatus204EMudarStatus_QuandoAtivarRestaurante() {
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, codigoRestauranteExistente)
        .when()
            .put(param + "/ativo")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        Restaurante restaurante = restauranteRepository.findById(codigoRestauranteExistente).get();
        assertTrue(restaurante.getAtivo());
    }

    @Test
    public void deveRetornarStatus204EMudarStatus_QuandoInativarRestaurante() {

        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam(pathParam, codigoRestauranteExistente)
        .when()
            .delete(param + "/ativo")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

        Restaurante restaurante = restauranteRepository.findById(codigoRestauranteExistente).get();
        assertFalse(restaurante.getAtivo());
    }



}

