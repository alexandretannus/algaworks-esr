package com.algaworks.algafood.domain.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.algafood.domain.model.Restaurante;

import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

    private static final long serialVersionUID = 1L;

    private String nome;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

}
