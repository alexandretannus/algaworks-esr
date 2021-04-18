package com.algaworks.algafood.api.model.inputs;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInput {
    
    @NotNull
    private Long id;
}
