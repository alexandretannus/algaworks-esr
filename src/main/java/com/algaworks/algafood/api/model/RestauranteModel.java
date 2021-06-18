package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {

    private Long id;
    private String nome;
  
    private BigDecimal precoFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;
    private EnderecoModel endereco;
    

    private BigDecimal taxaFrete;

    private CozinhaModel cozinha;

    private Boolean ativo;
}
