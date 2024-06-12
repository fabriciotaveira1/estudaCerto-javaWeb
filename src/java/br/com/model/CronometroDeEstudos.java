/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.model;

/**
 *
 * @author Marina
 */
import java.sql.Time;
import java.util.Date;

public class CronometroDeEstudos {
    private int id;
    private int usuarioId;
    private String atividade;
    private Time tempoGasto;
    private Date data;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
