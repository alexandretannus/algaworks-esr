package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.inputs.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
    
    @Autowired
    private FormaPagamentoRepository repository;

    @Autowired
    private CadastroFormaPagamentoService service;

    @Autowired
    private FormaPagamentoAssembler assembler;

    @Autowired
    private FormaPagamentoInputDisassembler disassembler;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = service.buscarOuFalhar(formaPagamentoId);

        return assembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = disassembler.toDomainObject(formaPagamentoInput);
        return assembler.toModel(service.salvar(formaPagamento));
    }

    @PutMapping("{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput, @PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamentoAtual = service.buscarOuFalhar(formaPagamentoId);
        
        disassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        return assembler.toModel(service.salvar(formaPagamentoAtual));
    }
    
    @DeleteMapping("{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long formaPagamentoId) {
        service.remover(formaPagamentoId);
    }
}
