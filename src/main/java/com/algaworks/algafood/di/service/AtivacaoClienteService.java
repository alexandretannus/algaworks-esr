package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoNotificador;

import org.springframework.beans.factory.annotation.Autowired;

// @Component
public class AtivacaoClienteService {

    @TipoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

    // @PostConstruct
    public void init() {
        System.out.println("INIT");
    }

    // @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro está ativo");

    }

}
