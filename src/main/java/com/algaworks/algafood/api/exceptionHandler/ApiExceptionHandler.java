package com.algaworks.algafood.api.exceptionHandler;

import java.time.LocalDateTime;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        return lancarExcecao(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e) {
        return lancarExcecao(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
        return lancarExcecao(HttpStatus.CONFLICT, e.getMessage());
    }

    // @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    // public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException() {
    // return lancarExcecao(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de mídia não
    // suportado");
    // }

    private ResponseEntity<?> lancarExcecao(HttpStatus status, String mensagem) {
        Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem(mensagem).build();
        return ResponseEntity.status(status).body(problema);
    }

}
