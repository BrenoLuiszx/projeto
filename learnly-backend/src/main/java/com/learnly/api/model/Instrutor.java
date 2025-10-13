package com.learnly.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Instrutores")
public class Instrutor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(length = 500)
    private String bio;
    
    @Column(length = 500)
    private String foto;
    
    private Boolean ativo = true;

    // Construtores
    public Instrutor() {}

    public Instrutor(String nome, String bio) {
        this.nome = nome;
        this.bio = bio;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}