package com.algaworks.algafood.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.util.ResourceUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class FormasPagamentoControllerIT extends AbstractTestControllerWithDelete  {
    
    private static final String DESCRICAO_OBRIGATORIA = "Descrição é obrigatório";
    
    private long quantidadeFormasPagamentoCadastradas;

    private String jsonIncorretoDescricaoNula;
    private String jsonIncorretoDescricaoBranco;
    private String jsonIncorretoDescricaoEspaco;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    
    @BeforeEach
    public void setup() {
        RestAssured.basePath = "/formas-pagamento";

        prepararDados();
        quantidadeFormasPagamentoCadastradas = formaPagamentoRepository.count();

        super.pathParam = "formaPagamentoId";
        super.param = "{formaPagamentoId}";
        super.resourcePath = "formaPagamento";

        super.jsonCorretoCadastro = ResourceUtil
                .getContentFromResource("/json/correto/formasPagamento/formaPagamento.json");
        
        jsonIncorretoDescricaoBranco = carregarJsonIncorreto("DescricaoBranco");
        jsonIncorretoDescricaoEspaco = carregarJsonIncorreto("DescricaoEspacos");
        jsonIncorretoDescricaoNula = carregarJsonIncorreto("DescricaoNula");
        
        super.userMessage = FORMA_PAGAMENTO_INEXISTENTE;
    }
    
    // Consultas
    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
        given()
            .pathParam(pathParam, 1L)
            .accept(ContentType.JSON)
        .when()
            .get(param)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("descricao", equalTo("Dinheiro"));       
    }

    @Test
    public void deveConterTresFormasPagamento_QuandoConsultar() {        
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .body("", hasSize((int)quantidadeFormasPagamentoCadastradas))
            .body("descricao", hasItems("Pix", "Dinheiro"));
    }

    // Cadastro    
    @Test
    public void deveRetornarStatus400_QuandoTentarCadastrarFormaPagamentoComDescricaoNula() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoDescricaoNula)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString(CAMPOS_INVALIDOS))
        .body("title", equalTo(DADOS_INVALIDOS))
        .body("objects.userMessage", hasItem(containsString(DESCRICAO_OBRIGATORIA)));
    }

    @Test
    public void deveRetornarStatus400_QuandoTentarCadastrarFormaPagamentoComDescricaoEmBranco() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoDescricaoBranco)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString(CAMPOS_INVALIDOS))
        .body("title", equalTo(DADOS_INVALIDOS))
        .body("objects.userMessage", hasItem(containsString(DESCRICAO_OBRIGATORIA)));
    }
    
    @Test
    public void deveRetornarStatus400_QuandoTentarCadastrarFormaPagamentoComDescricaoComEspacos() {
    given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(jsonIncorretoDescricaoEspaco)
    .when()
        .post()
    .then()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("detail", containsString(CAMPOS_INVALIDOS))
        .body("title", equalTo(DADOS_INVALIDOS))
        .body("objects.userMessage", hasItem(containsString(DESCRICAO_OBRIGATORIA)));
    }
}
