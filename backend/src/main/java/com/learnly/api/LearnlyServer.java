package com.learnly.api;

import java.io.*;
import java.net.*;
import java.util.*;

public class LearnlyServer {
    private static final String USUARIOS_FILE = "usuarios.txt";
    
    private static String[] cursos = {
        "{\"id\":1,\"titulo\":\"React Completo\",\"descricao\":\"Curso completo de React do básico ao avançado\",\"url\":\"https://www.youtube.com/watch?v=FXqX7oof0I0\",\"categoria\":\"Frontend\",\"instrutor\":\"Matheus Battisti\",\"duracao\":480}",
        "{\"id\":2,\"titulo\":\"Node.js para Iniciantes\",\"descricao\":\"Aprenda Node.js criando uma API REST\",\"url\":\"https://www.youtube.com/watch?v=LLqq6FemMNQ\",\"categoria\":\"Backend\",\"instrutor\":\"Rocketseat\",\"duracao\":360}",
        "{\"id\":3,\"titulo\":\"Python Fundamentos\",\"descricao\":\"Curso de Python para Data Science\",\"url\":\"https://www.youtube.com/watch?v=S9uPNppGsGo\",\"categoria\":\"Data Science\",\"instrutor\":\"Curso em Video\",\"duracao\":720}",
        "{\"id\":4,\"titulo\":\"JavaScript ES6+\",\"descricao\":\"Recursos modernos do JavaScript\",\"url\":\"https://www.youtube.com/watch?v=HN1UjzRSdBk\",\"categoria\":\"Frontend\",\"instrutor\":\"Cod3r\",\"duracao\":300}",
        "{\"id\":5,\"titulo\":\"Spring Boot API\",\"descricao\":\"Criando APIs REST com Spring Boot\",\"url\":\"https://www.youtube.com/watch?v=OHn1jLHGptw\",\"categoria\":\"Backend\",\"instrutor\":\"DevDojo\",\"duracao\":540}",
        "{\"id\":6,\"titulo\":\"MySQL Basico\",\"descricao\":\"Fundamentos de banco de dados MySQL\",\"url\":\"https://www.youtube.com/watch?v=Ofktsne-utM\",\"categoria\":\"Database\",\"instrutor\":\"Curso em Video\",\"duracao\":240}",
        "{\"id\":7,\"titulo\":\"Git e GitHub\",\"descricao\":\"Controle de versao com Git\",\"url\":\"https://www.youtube.com/watch?v=xEKo29OWILE\",\"categoria\":\"DevOps\",\"instrutor\":\"Curso em Video\",\"duracao\":180}",
        "{\"id\":8,\"titulo\":\"CSS Grid e Flexbox\",\"descricao\":\"Layout moderno com CSS\",\"url\":\"https://www.youtube.com/watch?v=x-4z_u8LcGc\",\"categoria\":\"Frontend\",\"instrutor\":\"Origamid\",\"duracao\":420}",
        "{\"id\":9,\"titulo\":\"Vue.js 3\",\"descricao\":\"Framework progressivo para interfaces\",\"url\":\"https://www.youtube.com/watch?v=wsAQQioPIJs\",\"categoria\":\"Frontend\",\"instrutor\":\"Cod3r\",\"duracao\":380}",
        "{\"id\":10,\"titulo\":\"Docker Essentials\",\"descricao\":\"Containerizacao de aplicacoes\",\"url\":\"https://www.youtube.com/watch?v=0xxHiOSJVe8\",\"categoria\":\"DevOps\",\"instrutor\":\"Fabricio Veronez\",\"duracao\":320}"
    };
    
    public static List<String> cursosLista = new ArrayList<>(Arrays.asList(cursos));
    public static List<String> usuariosLista = new ArrayList<>();
    public static int proximoId = 11;
    
    // Carregar usuários do arquivo
    static {
        carregarUsuarios();
    }
    
    private static void carregarUsuarios() {
        try {
            File file = new File(USUARIOS_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String linha;
                while ((linha = reader.readLine()) != null) {
                    if (!linha.trim().isEmpty()) {
                        usuariosLista.add(linha.trim());
                    }
                }
                reader.close();
                System.out.println("📂 Usuários carregados: " + usuariosLista.size());
            } else {
                usuariosLista.add("{\"id\":1,\"nome\":\"Breno\",\"email\":\"admin@teste.com\",\"senha\":\"123456\",\"foto\":\"https://i.pinimg.com/736x/72/21/bf/7221bf32061ed0edec3c4e737532b0c8.jpg\",\"role\":\"admin\"}");
                usuariosLista.add("{\"id\":2,\"nome\":\"User\",\"email\":\"user@teste.com\",\"senha\":\"123456\",\"foto\":\"https://api.dicebear.com/7.x/avataaars/svg?seed=User\",\"role\":\"user\"}");
                usuariosLista.add("{\"id\":3,\"nome\":\"João Silva\",\"email\":\"joao@email.com\",\"senha\":\"123456\",\"foto\":\"https://api.dicebear.com/7.x/avataaars/svg?seed=Joao\",\"role\":\"user\"}");
                usuariosLista.add("{\"id\":4,\"nome\":\"Maria Santos\",\"email\":\"maria@email.com\",\"senha\":\"123456\",\"foto\":\"https://api.dicebear.com/7.x/avataaars/svg?seed=Maria\",\"role\":\"user\"}");
                salvarUsuarios();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar usuários: " + e.getMessage());
        }
    }
    
    private static void salvarUsuarios() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(USUARIOS_FILE));
            for (String usuario : usuariosLista) {
                writer.println(usuario);
            }
            writer.close();
            System.out.println("💾 Usuários salvos");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("🚀 Learnly API Server rodando na porta 8080");
        System.out.println("📊 Cursos: http://localhost:8080/api/cursos");
        System.out.println("👤 Usuários: http://localhost:8080/api/usuarios");
        System.out.println("📚 Desenvolvido para Learnly Platform");
        
        while (true) {
            Socket client = server.accept();
            new RequestHandler(client).start();
        }
    }
    
    static class RequestHandler extends Thread {
        private Socket client;
        
        public RequestHandler(Socket client) {
            this.client = client;
        }
        
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream());
                
                String requestLine = in.readLine();
                if (requestLine == null) return;
                
                System.out.println("📥 " + requestLine);
                
                // Log especial para registro de usuário
                if (requestLine.contains("/api/usuarios/registrar")) {
                    System.out.println("🔥 REQUISIÇÃO DE REGISTRO DETECTADA!");
                }
                
                // Ler headers e body
                String line;
                int contentLength = 0;
                while ((line = in.readLine()) != null && !line.isEmpty()) {
                    if (line.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(line.substring(16).trim());
                    }
                }
                
                String body = "";
                if (contentLength > 0) {
                    char[] buffer = new char[contentLength];
                    in.read(buffer, 0, contentLength);
                    body = new String(buffer);
                }
                
                String[] parts = requestLine.split(" ");
                String method = parts[0];
                String path = parts[1];
                
                String response;
                try {
                    response = processRequest(method, path, body);
                } catch (Exception e) {
                    System.out.println("❌ Erro ao processar requisição: " + e.getMessage());
                    e.printStackTrace();
                    response = "{\"error\":\"Erro interno do servidor\"}";
                }
                
                // Enviar resposta
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: application/json; charset=UTF-8");
                out.println("Access-Control-Allow-Origin: *");
                out.println("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
                out.println("Access-Control-Allow-Headers: Content-Type");
                out.println("Content-Length: " + response.getBytes("UTF-8").length);
                out.println();
                out.println(response);
                out.flush();
                
                System.out.println("📤 Response sent");
                
            } catch (Exception e) {
                System.out.println("❌ Erro na conexão: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (client != null) client.close();
                } catch (Exception e) {
                    System.out.println("⚠️ Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        
        private String processRequest(String method, String path, String body) {
            // Handle OPTIONS requests for CORS
            if ("OPTIONS".equals(method)) {
                return "";
            }
            
            // Usuários
            if (path.equals("/api/usuarios") && "GET".equals(method)) {
                return getUsersInfo();
            } else if (path.equals("/api/usuarios/registrar") && "POST".equals(method)) {
                return registerUser(body);
            } else if (path.equals("/api/usuarios/login") && "POST".equals(method)) {
                return loginUser(body);
            }
            // Cursos
            else if (path.equals("/api/cursos") && "GET".equals(method)) {
                return "[" + String.join(",", cursosLista) + "]";
            } else if (path.equals("/api/cursos") && "POST".equals(method)) {
                return createCurso(body);
            } else if (path.startsWith("/api/cursos/") && "PUT".equals(method)) {
                String idStr = path.substring("/api/cursos/".length());
                return updateCurso(idStr, body);
            } else if (path.startsWith("/api/cursos/") && "DELETE".equals(method)) {
                String idStr = path.substring("/api/cursos/".length());
                return deleteCurso(idStr);
            } else if (path.startsWith("/api/cursos/categoria/") && "GET".equals(method)) {
                String categoria = path.substring("/api/cursos/categoria/".length());
                return buscarPorCategoria(categoria);
            } else if (path.startsWith("/api/cursos/buscar?") && "GET".equals(method)) {
                String query = path.substring("/api/cursos/buscar?".length());
                return buscarPorTitulo(query);
            }
            
            return "{\"error\":\"Endpoint não encontrado\"}";
        }
        
        private String getUsersInfo() {
            return "{\"message\":\"Learnly API funcionando\",\"total\":" + usuariosLista.size() + ",\"usuarios\":[" + String.join(",", usuariosLista) + "]}";
        }
        
        private String registerUser(String body) {
            System.out.println("👤 Registrando usuário - Body: " + body);
            
            String nome = extractFromJson(body, "nome");
            String email = extractFromJson(body, "email");
            String senha = extractFromJson(body, "senha");
            String foto = extractFromJson(body, "foto");
            String role = email.toLowerCase().contains("admin") ? "admin" : "user";
            
            System.out.println("📝 Dados extraídos - Nome: " + nome + ", Email: " + email);
            
            // Verificar email duplicado
            for (String usuario : usuariosLista) {
                if (usuario.contains("\"email\":\"" + email + "\"")) {
                    System.out.println("❌ Email já existe: " + email);
                    return "{\"error\":\"Email já cadastrado\"}";
                }
            }
            
            int id = usuariosLista.size() + 1;
            String novoUsuario = "{\"id\":" + id + ",\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"senha\":\"" + senha + "\",\"foto\":\"" + foto + "\",\"role\":\"" + role + "\"}";
            usuariosLista.add(novoUsuario);
            salvarUsuarios();
            
            System.out.println("✅ Usuário registrado! Total: " + usuariosLista.size());
            
            return "{\"id\":" + id + ",\"nome\":\"" + nome + "\",\"email\":\"" + email + "\",\"foto\":\"" + foto + "\",\"role\":\"" + role + "\"}";
        }
        
        private String loginUser(String body) {
            String email = extractFromJson(body, "email");
            String senha = extractFromJson(body, "senha");
            
            System.out.println("🔑 Tentativa de login - Email: " + email);
            
            for (String usuario : usuariosLista) {
                if (usuario.contains("\"email\":\"" + email + "\"") && usuario.contains("\"senha\":\"" + senha + "\"")) {
                    System.out.println("✅ Login bem-sucedido para: " + email);
                    return "{\"usuario\":" + usuario + "}";
                }
            }
            
            System.out.println("❌ Login falhou para: " + email);
            return "{\"error\":\"Email ou senha inválidos\"}";
        }
        
        private String extractFromJson(String json, String field) {
            String search = "\"" + field + "\":\"";
            int start = json.indexOf(search);
            if (start == -1) return "";
            
            start += search.length();
            int end = json.indexOf("\"", start);
            if (end == -1) return "";
            
            return json.substring(start, end);
        }
        
        private String createCurso(String body) {
            String titulo = extractFromJson(body, "titulo");
            String descricao = extractFromJson(body, "descricao");
            String url = extractFromJson(body, "url");
            String categoria = extractFromJson(body, "categoria");
            String instrutor = extractFromJson(body, "instrutor");
            String duracaoStr = extractFromJson(body, "duracao");
            
            int duracao = duracaoStr.isEmpty() ? 0 : Integer.parseInt(duracaoStr);
            
            String novoCurso = "{\"id\":" + proximoId + ",\"titulo\":\"" + titulo + "\",\"descricao\":\"" + descricao + "\",\"url\":\"" + url + "\",\"categoria\":\"" + categoria + "\",\"instrutor\":\"" + instrutor + "\",\"duracao\":" + duracao + "}";
            cursosLista.add(novoCurso);
            proximoId++;
            
            return novoCurso;
        }
        
        private String updateCurso(String idStr, String body) {
            try {
                int id = Integer.parseInt(idStr);
                System.out.println("🔄 Atualizando curso ID: " + id);
                
                String titulo = extractFromJson(body, "titulo");
                String descricao = extractFromJson(body, "descricao");
                String url = extractFromJson(body, "url");
                String categoria = extractFromJson(body, "categoria");
                String instrutor = extractFromJson(body, "instrutor");
                String duracaoStr = extractFromJson(body, "duracao");
                
                int duracao = 0;
                if (!duracaoStr.isEmpty()) {
                    duracao = Integer.parseInt(duracaoStr);
                }
                
                // Buscar curso por ID usando contains
                for (int i = 0; i < cursosLista.size(); i++) {
                    String curso = cursosLista.get(i);
                    if (curso.contains("\"id\":" + id + ",")) {
                        String cursoAtualizado = "{\"id\":" + id + ",\"titulo\":\"" + titulo + "\",\"descricao\":\"" + descricao + "\",\"url\":\"" + url + "\",\"categoria\":\"" + categoria + "\",\"instrutor\":\"" + instrutor + "\",\"duracao\":" + duracao + "}";
                        cursosLista.set(i, cursoAtualizado);
                        System.out.println("✅ Curso " + id + " atualizado!");
                        return cursoAtualizado;
                    }
                }
                
                return "{\"error\":\"Curso não encontrado\"}";
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
                return "{\"error\":\"Erro interno\"}";
            }
        }
        
        private String extractField(String user, String field) {
            return extractFromJson(user, field);
        }
        
        private String deleteCurso(String idStr) {
            try {
                int id = Integer.parseInt(idStr);
                
                for (int i = 0; i < cursosLista.size(); i++) {
                    String curso = cursosLista.get(i);
                    
                    if (curso.startsWith("{\"id\":" + id + ",")) {
                        cursosLista.remove(i);
                        System.out.println("🗑️ Curso " + id + " deletado!");
                        return "{\"message\":\"Curso deletado com sucesso\"}";
                    }
                }
                
                return "{\"error\":\"Curso não encontrado\"}";
            } catch (Exception e) {
                return "{\"error\":\"ID inválido\"}";
            }
        }
        
        private String buscarPorCategoria(String categoria) {
            List<String> cursosEncontrados = new ArrayList<>();
            
            // Decodificar a categoria da URL
            try {
                categoria = URLDecoder.decode(categoria, "UTF-8");
            } catch (Exception e) {
                // Manter categoria original se houver erro
            }
            
            for (String curso : cursosLista) {
                if (curso.contains("\"categoria\":\"" + categoria + "\"")) {
                    cursosEncontrados.add(curso);
                }
            }
            
            return "[" + String.join(",", cursosEncontrados) + "]";
        }
        
        private String buscarPorTitulo(String query) {
            // Extrair o parâmetro titulo da query string
            String titulo = "";
            if (query.startsWith("titulo=")) {
                titulo = query.substring(7); // Remove "titulo="
                try {
                    titulo = URLDecoder.decode(titulo, "UTF-8").toLowerCase();
                } catch (Exception e) {
                    titulo = titulo.toLowerCase();
                }
            }
            
            // Se não há termo de busca, retornar todos os cursos
            if (titulo.trim().isEmpty()) {
                return "[" + String.join(",", cursosLista) + "]";
            }
            
            List<String> cursosEncontrados = new ArrayList<>();
            
            for (String curso : cursosLista) {
                String tituloLower = extractFromJson(curso, "titulo").toLowerCase();
                String descricaoLower = extractFromJson(curso, "descricao").toLowerCase();
                String instrutorLower = extractFromJson(curso, "instrutor").toLowerCase();
                String categoriaLower = extractFromJson(curso, "categoria").toLowerCase();
                
                if (tituloLower.contains(titulo) || 
                    descricaoLower.contains(titulo) || 
                    instrutorLower.contains(titulo) ||
                    categoriaLower.contains(titulo)) {
                    cursosEncontrados.add(curso);
                }
            }
            
            return "[" + String.join(",", cursosEncontrados) + "]";
        }
    }
}