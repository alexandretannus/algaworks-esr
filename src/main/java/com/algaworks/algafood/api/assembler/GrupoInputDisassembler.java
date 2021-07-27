package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.inputs.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    
    public Grupo toDomainObject(GrupoInput dto) { 
        return modelMapper.map(dto, Grupo.class);
    }
    
    public void copyToDomainObject(GrupoInput dto, Grupo entity) {
        modelMapper.map(dto, entity);
    }
}
