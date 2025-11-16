package br.com.issuelite.model;

import java.sql.Timestamp;

public class Issue {
    private int id;
    private String titulo;
    private String descricao;
    private Timestamp dataCriacao;
    private String tipo;
    private String status;
    private String projeto;
    private int idUsuarioReportou;

    public Issue() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getProjeto() { return projeto; }
    public void setProjeto(String projeto) { this.projeto = projeto; }

    public int getIdUsuarioReportou() { return idUsuarioReportou; }
    public void setIdUsuarioReportou(int idUsuarioReportou) { this.idUsuarioReportou = idUsuarioReportou; }
}