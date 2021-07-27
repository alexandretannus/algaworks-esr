package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.naoencontrado.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser excluído, pois está em uso";

    private GrupoRepository grupoRepository;

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public void remover(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradoException(grupoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
        }
    }

    
}
