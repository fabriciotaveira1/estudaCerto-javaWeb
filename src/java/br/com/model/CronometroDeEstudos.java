package br.com.model;

import java.sql.Time;
import java.util.Date;

public class CronometroDeEstudos {

    private int id;
    private int usuarioId;
    private String atividade;
    private Time tempoGasto;
    private java.sql.Date data;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Time getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(Time tempoGasto) {
        this.tempoGasto = tempoGasto;
    }

    public java.sql.Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }
}
