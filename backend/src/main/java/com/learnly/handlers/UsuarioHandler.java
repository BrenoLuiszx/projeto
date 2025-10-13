package com.learnly.handlers;

import java.util.List;

/**
 * Handler responsável por todas as operações relacionadas a usuários
 */
public class UsuarioHandler {
    
    /**
     * Processa informações dos usuários para debug
     */
    public static String getUsersInfo(List<String> usuariosLista) {
        StringBuilder emails = new StringBuilder("[");
        for (int i = 0; i < usuariosLista.size(); i++) {
            if (i > 0) emails.append(",");
            String email = extractField(usuariosLista.get(i), "email");
            String role = extractField(usuariosLista.get(i), "role");
            emails.append("{\"email\":\"").append(email).append("\",\"role\":\"").append(role).append("\"}");
        }
        emails.append("]");
        return "{\"message\":\"Learnly API funcionando\",\"total\":" + usuariosLista.size() + ",\"usuarios\":" + emails.toString() + "}";
    }
    
    /**
     * Registra um novo usuário
     */
    public static String registerUser(String body, List<String> usuariosLista) {
        String nome = extractFromJson(body, "nome");
        String email = extractFromJson(body, "email");
        String senha = extractFromJson(body, "senha");
        String foto = extractFromJson(body, "foto");
        String role = email.toLowerCase().contains("admin") ? "admin" : "user";
        
        // Verificar email duplicado
        for (String usuario : usuariosLista) {
            if (usuario.contains("\"email\":\"" + email + "\"")) {
                return "{\"error\":\"Email já cadastrado\"}";
            }
        }
        
        int id = usuariosLista.size() + 1;
        String novoUsuario = "{\"id\":" + id + ",\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"senha\":\"" + senha + "\",\"foto\":\"" + foto + "\",\"role\":\"" + role + "\"}";
        usuariosLista.add(novoUsuario);
        
        return "{\"id\":" + id + ",\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"foto\":\"" + foto + "\",\"role\":\"" + role + "\"}";
    }
    
    /**
     * Faz login do usuário
     */
    public static String loginUser(String body, List<String> usuariosLista) {
        String email = extractFromJson(body, "email");
        String senha = extractFromJson(body, "senha");
        
        for (String usuario : usuariosLista) {
            if (usuario.contains("\"email\":\"" + email + "\"") && usuario.contains("\"senha\":\"" + senha + "\"")) {
                String id = extractField(usuario, "id");
                String nome = extractField(usuario, "nome");
                String foto = extractField(usuario, "foto");
                String role = extractField(usuario, "role");
                
                return "{\"usuario\":{\"id\":" + id + ",\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"foto\":\"" + foto + "\",\"role\":\"" + role + "\"}}";
            }
        }
        
        return "{\"error\":\"Email ou senha inválidos\"}";
    }
    
    // Métodos utilitários
    private static String extractFromJson(String json, String field) {
        String search = "\"" + field + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return "";
        
        start += search.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return "";
        
        return json.substring(start, end);
    }
    
    private static String extractField(String user, String field) {
        if (field.equals("id")) {
            String search = "\"id\":";
            int start = user.indexOf(search) + search.length();
            int end = user.indexOf(",", start);
            return user.substring(start, end);
        }
        return extractFromJson(user, field);
    }
}