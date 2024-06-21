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
public class DivisaoTreino {

    private static long serial = 1;
    private long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public DivisaoTreino() {
        id = DivisaoTreino.serial++;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    
    public void atualizarDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "\n==================INFORMACOES DE DIVISAO DE TREINO ================= " 
                 + "\nID: " + id 
                 + "\nNOME: " + nome 
                 + "\nDESCRICAO: " + descricao 
                 + "\nDATA DE CRIACAO: " + dataCriacao 
                 + "\nDATA DE MODIFICACAO: " + dataModificacao;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.nome);
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
        final DivisaoTreino other = (DivisaoTreino) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }

}
