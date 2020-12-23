package com.algaworks.algafood.jpa.restaurante;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante = new Restaurante();
        restaurante.setId(2L);

        restauranteRepository.remover(restaurante);
    }

}
