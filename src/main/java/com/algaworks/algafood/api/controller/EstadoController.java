package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

import org.springframework.beans.BeanUtils;
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

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public Estado buscar(@PathVariable("estadoId") Long estadoId) {
        return service.buscarOuFalhar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return service.salvar(estado);
    }

    @PutMapping("{estadoId}")
    @ResponseStatus(HttpStatus.OK)
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Estado estadoAtual = service.buscarOuFalhar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return service.salvar(estadoAtual);
    }

    @DeleteMapping("{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long estadoId) {
        service.remover(estadoId);
    }
}
