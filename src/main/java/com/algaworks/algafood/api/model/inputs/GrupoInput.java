package com.algaworks.algafood.api.model.inputs;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {
    
    @NotBlank
    private String nome;
}
