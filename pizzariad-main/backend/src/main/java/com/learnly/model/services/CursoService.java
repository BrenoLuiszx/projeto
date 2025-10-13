package com.learnly.model.services;

import com.learnly.model.entity.Curso;
import com.learnly.model.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com o id: " + id));
    }

    public Curso update(Long id, Curso curso) {
        Curso cursoExistente = findById(id);
        cursoExistente.setTitulo(curso.getTitulo());
        cursoExistente.setDescricao(curso.getDescricao());
        cursoExistente.setUrl(curso.getUrl());
        cursoExistente.setCategoria(curso.getCategoria());
        cursoExistente.setInstrutor(curso.getInstrutor());
        cursoExistente.setDuracao(curso.getDuracao());
        return cursoRepository.save(cursoExistente);
    }

    public void delete(Long id) {
        Curso cursoExistente = findById(id);
        cursoRepository.delete(cursoExistente);
    }
}