package com.learnly.controller;

import com.learnly.model.entity.Usuario;
import com.learnly.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(Map.of(
            "message", "Learnly API funcionando",
            "total", usuarios.size(),
            "usuarios", usuarios
        ));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            String role = usuario.getEmail().toLowerCase().contains("admin") ? "admin" : "user";
            usuario.setRole(role);
            Usuario novoUsuario = usuarioService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao registrar usuário"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUsuario(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String senha = loginData.get("senha");
        
        List<Usuario> usuarios = usuarioService.findAll();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return ResponseEntity.ok(Map.of("usuario", usuario));
            }
        }
        
        return ResponseEntity.status(401).body(Map.of("error", "Email ou senha inválidos"));
    }
}