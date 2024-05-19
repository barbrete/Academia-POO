/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author barbrete e kitotsui
 */
public class Treino {

    private static long serial = 1;
    private long id;
    private String objetivo;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private DivisaoTreino divisaoTreino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Treino() {
        id = Treino.serial++;
    }

    public long getId() {
        return id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public DivisaoTreino getDivisaoTreino() {
        return divisaoTreino;
    }

    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {
        this.divisaoTreino = divisaoTreino;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    

    @Override
    public String toString() {
        return "==================INFORMACOES DO TREINO================== " 
                + "\nID: " + id 
                + "\nOBJETIVO: " + objetivo 
                + "\nDATA INICIO: " + dataInicio 
                + "\nDATA TERMINO: " + dataTermino 
                + "\nDIVISAO DE TREINO: " + (divisaoTreino != null ? divisaoTreino.getNome() : "N/A") 
                + "\nDATA DE CRIACAO: " + dataCriacao 
                + "\nDATA DE MODIFICACAO: " + dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.objetivo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Treino other = (Treino) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.objetivo, other.objetivo);
    }

    public void se(LocalDateTime now) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}