package com.algaworks.algafood.domain.exception.naoencontrado;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this(String.format("%s %d", GRUPO_INEXISTENTE_MESSAGE, grupoId));
    }
}