package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.inputs.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

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
@RequestMapping(value = "cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService service;

    @Autowired
    private CozinhaModelAssembler cozinhaAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaDisassembler;

    @GetMapping
    public List<CozinhaModel> listar() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();
        return cozinhaAssembler.toCollectionModel(cozinhas);
    }

    @GetMapping("{cozinhaId}")
    public CozinhaModel buscar(@PathVariable("cozinhaId") Long id) {
        return cozinhaAssembler.toModel(service.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        
        Cozinha cozinhaSalva = service.salvar(cozinhaDisassembler.toDomainObject(cozinhaInput));
        
        return cozinhaAssembler.toModel(cozinhaSalva);
    }

    @PutMapping("{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = service.buscarOuFalhar(cozinhaId);

        cozinhaDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        
        return cozinhaAssembler.toModel(service.salvar(cozinhaAtual));
    }

    @DeleteMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cozinhaId) {
        service.remover(cozinhaId);
    }

}
