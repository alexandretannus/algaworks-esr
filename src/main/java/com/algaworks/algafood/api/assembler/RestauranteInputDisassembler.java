package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.inputs.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {
    
    public Restaurante toDomainObject(RestauranteInput restauranteInput) { 
        Restaurante restaurante = new Restaurante();

        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
