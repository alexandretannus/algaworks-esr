package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.inputs.RestauranteInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService service;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @GetMapping
    public List<RestauranteModel> listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return restauranteModelAssembler.toCollectionModel(restaurantes);
    }

    @GetMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteModel buscar(@PathVariable("restauranteId") Long restauranteId) {
        Restaurante restaurante = service.buscarOuFalhar(restauranteId);

        RestauranteModel restauranteModel = restauranteModelAssembler.toModel(restaurante);

        return restauranteModel;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = toDomainObject(restauranteInput);  
            Restaurante restauranteSalvo = service.salvar(restaurante);          
            return restauranteModelAssembler.toModel(restauranteSalvo);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput) {

        Restaurante restaurante = toDomainObject(restauranteInput);
        Restaurante restauranteAtual = service.buscarOuFalhar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
                "produtos");

        try {
            Restaurante restauranteSalvo = service.salvar(restauranteAtual);
            return restauranteModelAssembler.toModel(restauranteSalvo);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    private Restaurante toDomainObject(RestauranteInput restauranteInput) { 
        Restaurante restaurante = new Restaurante();

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
