package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    public ApplicationEventPublisher publisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        publisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }

}
