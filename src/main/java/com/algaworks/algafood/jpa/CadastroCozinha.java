package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.algaworks.algafood.domain.model.Cozinha;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
        return query.getResultList();
    }

    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
}
