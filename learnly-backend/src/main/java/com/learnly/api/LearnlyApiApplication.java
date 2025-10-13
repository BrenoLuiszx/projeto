package com.learnly.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnlyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnlyApiApplication.class, args);
		System.out.println("🚀 Learnly API Server rodando na porta 8080");
		System.out.println("📊 Cursos: http://localhost:8080/api/cursos");
		System.out.println("👤 Usuários: http://localhost:8080/api/usuarios");
		System.out.println("📚 Desenvolvido para Learnly Platform");
	}
}