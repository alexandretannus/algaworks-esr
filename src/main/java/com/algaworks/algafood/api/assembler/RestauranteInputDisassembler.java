package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.inputs.RestauranteInput;
import com.algaworks.algafood.domain.model.Restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Restaurante toDomainObject(RestauranteInput restauranteInput) { 
        return modelMapper.map(restauranteInput, Restaurante.class);
    }
}
