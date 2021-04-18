package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.inputs.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

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
@RequestMapping("estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService service;

    @Autowired
    private EstadoModelAssembler estadoAssembler;
    
    @Autowired
    private EstadoInputDisassembler estadoDisassembler;

    @GetMapping
    public List<EstadoModel> listar() {
        return estadoAssembler.toCollectionModel(estadoRepository.findAll());
    }

    @GetMapping("{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoModel buscar(@PathVariable("estadoId") Long estadoId) {
        return estadoAssembler.toModel(service.buscarOuFalhar(estadoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoDisassembler.toDomainObject(estadoInput);
        return estadoAssembler.toModel(service.salvar(estado));
    }

    @PutMapping("{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = service.buscarOuFalhar(estadoId);
        
        estadoDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        return estadoAssembler.toModel(service.salvar(estadoAtual));
    }

    @DeleteMapping("{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long estadoId) {
        service.remover(estadoId);
    }
}
