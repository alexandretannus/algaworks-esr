package com.algaworks.algafood;

import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        return new AtivacaoClienteService(notificador);
    }
}
