package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMap = new ModelMapper();

        /* modelMap.createTypeMap(Restaurante.class, RestauranteModel.class)
            .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
         */
        return modelMap;
    }
}
