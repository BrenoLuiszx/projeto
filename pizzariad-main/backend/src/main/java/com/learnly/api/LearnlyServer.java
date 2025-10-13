package com.learnly.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnlyServer {

    public static void main(String[] args) {
        SpringApplication.run(LearnlyServer.class, args);
        System.out.println("🚀 Learnly API Server rodando na porta 8080");
        System.out.println("📊 Produtos: http://localhost:8080/api/v1/produto");
        System.out.println("👤 Usuários: http://localhost:8080/api/v1/usuario");
        System.out.println("📚 Desenvolvido para Pizzariad Platform");
    }
}