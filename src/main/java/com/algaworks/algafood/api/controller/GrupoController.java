package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.inputs.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("grupos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GrupoController {
    
    private CadastroGrupoService service;

    private GrupoModelAssembler assembler;

    private GrupoInputDisassembler disassembler;

    @GetMapping
    public List<GrupoModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoModel buscar(@PathVariable Long grupoId) {
        Grupo grupo = service.buscarOuFalhar(grupoId);

        return assembler.toModel(grupo);
    } 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = disassembler.toDomainObject(grupoInput);
        return assembler.toModel(service.salvar(grupo));
    }

    @PutMapping("{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = service.buscarOuFalhar(grupoId);

        disassembler.copyToDomainObject(grupoInput, grupoAtual);

        return assembler.toModel(service.salvar(grupoAtual));
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long grupoId) {
        service.remover(grupoId);
    }



}
