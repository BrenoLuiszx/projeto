package com.learnly.controller;

import com.learnly.model.entity.Curso;
import com.learnly.model.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodosCursos() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Curso> salvarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Curso>> buscarPorCategoria(@PathVariable String categoria) {
        List<Curso> cursos = cursoService.findAll().stream()
            .filter(c -> c.getCategoria().equals(categoria))
            .toList();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarPorTitulo(@RequestParam String titulo) {
        List<Curso> cursos = cursoService.findAll().stream()
            .filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase()) ||
                        c.getDescricao().toLowerCase().contains(titulo.toLowerCase()) ||
                        c.getInstrutor().toLowerCase().contains(titulo.toLowerCase()))
            .toList();
        return ResponseEntity.ok(cursos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.update(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarCurso(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Curso deletado com sucesso"));
    }
}