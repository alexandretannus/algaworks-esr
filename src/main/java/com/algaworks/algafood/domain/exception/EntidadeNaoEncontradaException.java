package com.algaworks.algafood.domain.exception;

import com.algaworks.algafood.domain.exception.interfaces.EntidadeInexistenteMessage;

public abstract class EntidadeNaoEncontradaException extends NegocioException implements EntidadeInexistenteMessage {

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
