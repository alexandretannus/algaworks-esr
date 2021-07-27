package com.algaworks.algafood.util.cadastro;

import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroGrupos {    

    @Autowired
    private GrupoRepository grupoRepository;
    
    private static Grupo gerente;
    private static Grupo vendedor;
    private static Grupo cliente;

    public void cadastrarGrupos() {
        vendedor = Grupo.builder().nome("Vendedor").build();
        gerente = Grupo.builder().nome("Gerente").build();
        cliente = Grupo.builder().nome("Cliente").build();

        grupoRepository.save(vendedor);
        grupoRepository.save(gerente);
        grupoRepository.save(cliente);
    }
    
}
