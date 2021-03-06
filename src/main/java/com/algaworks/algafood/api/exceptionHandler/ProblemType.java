package com.algaworks.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"), DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos"),
    ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem Incompreensível", "/mensagem-incompreensivel"),
    PARAMETRO_INVALIDO("O parâmetro informado é inválido", "/parametro-invalido"),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    ERRO_DE_SISTEMA("Erro de Sistema", "/erro-sistema");

    private final String title;
    private final String uri;

    private ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }

}
