package com.learnly.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(length = 100, nullable = false)
    private String titulo;
    @Column(length = 500, nullable = true)
    private String descricao;
    @Column(length = 255, nullable = true)
    private String url;
    @Column(length = 50, nullable = true)
    private String categoria;
    @Column(length = 100, nullable = true)
    private String instrutor;
    private int duracao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getInstrutor() { return instrutor; }
    public void setInstrutor(String instrutor) { this.instrutor = instrutor; }
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
}