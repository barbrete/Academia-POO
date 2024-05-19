/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author barbrete e kitotsui
 */
 public class ExercicioAplicacao {
    private static long serial;
    private long id;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public ExercicioAplicacao() {
        id = ExercicioAplicacao.serial++;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
         return "==================INFORMACOES DA APLICACAO DO EXERCICIO================= " 
                 + "\nID: " + id 
                 + "\nDESCRICAO: " + descricao 
                 + "\nDATA DE CRIACAO: " + dataCriacao 
                 + "\nDATA DE MODIFICACAO: " + dataModificacao;
     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.descricao);
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
        final ExercicioAplicacao other = (ExercicioAplicacao) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.descricao, other.descricao);
    }
    
    
    
}
