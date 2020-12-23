package com.algaworks.algafood.jpa.restaurante;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        List<Restaurante> retaurantes = restauranteRepository.listar();

        for (Restaurante restaurante : retaurantes) {
            System.out.println(restaurante.getNome());
        }
    }

}
