package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("por-nome")
    public List<Cozinha> porNome(String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("exists")
    public boolean existsPorNome(String nome) {
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("{cozinhaId}")
    public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
        return service.buscarOuFalhar(id);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cozinha));
    }

    @PutMapping("{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = service.buscarOuFalhar(cozinhaId);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return service.salvar(cozinhaAtual);

    }

    @DeleteMapping("{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cozinhaId) {
        service.remover(cozinhaId);
    }

}
