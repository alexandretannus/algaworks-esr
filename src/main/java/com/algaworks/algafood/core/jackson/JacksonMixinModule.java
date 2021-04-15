package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.model.mixin.RestauranteMixin;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;
    
    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
