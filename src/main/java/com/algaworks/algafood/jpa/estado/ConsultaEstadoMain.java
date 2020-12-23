package com.algaworks.algafood.jpa.estado;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaEstadoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

        List<Estado> estados = estadoRepository.listar();

        for (Estado estado : estados) {
            System.out.println(estado.getNome());
        }
    }

}
