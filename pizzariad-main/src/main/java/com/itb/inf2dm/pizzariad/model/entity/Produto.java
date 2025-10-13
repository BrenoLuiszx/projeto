package com.itb.inf2dm.pizzariad.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Produto")   // Caso o nome da classe for o mesmo da tabela, o @Table é opcional
public class Produto {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-Incremento Sequêncial 1 em 1
    private Long id;
    @Column(length = 100, nullable = false)  // length : qtde máx de caracteres, nullable: false (NOT NULL), true ( NULL)
    private String nome;
    @Column(length = 45, nullable = true)  // valor da propriedade "CINZA" significa que é padrão, logo, é opcional
    private String tipo;
    @Column(length = 250, nullable = true)
    private String descricao;
    @Column( nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double valorCompra;
    @Column( nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double valorVenda;
    @Column( nullable = true)
    private int quantidadeEstoque;
    private boolean codStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }
}
