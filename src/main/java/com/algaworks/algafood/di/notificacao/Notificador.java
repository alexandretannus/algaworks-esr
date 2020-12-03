package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;

public interface Notificador {
    public void notificar(Cliente cliente, String mensagem);
}
