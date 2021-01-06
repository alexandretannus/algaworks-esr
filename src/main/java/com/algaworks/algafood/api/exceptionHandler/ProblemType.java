package com.algaworks.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada");

    private final String title;
    private final String uri;

    private ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br/" + path;
    }

}
