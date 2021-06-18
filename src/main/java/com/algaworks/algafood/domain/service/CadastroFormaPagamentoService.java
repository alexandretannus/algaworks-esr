package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroFormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser excluída, pois está em uso";

    @Autowired
    private FormaPagamentoRepository repository;

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
        return repository.findById(formaPagamentoId)
                    .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }

    public void remover(Long formaPagamentoId) {
        try {
            repository.deleteById(formaPagamentoId);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
        }
    }
}
