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

public class Exercicio{
    private static long serial = 1;
    private long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;


    public Exercicio (){
        id = Exercicio.serial++;
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

    @Override
     public String toString() {
         return "==================INFORMACOES DO EXERCICIO================= " 
                 + "\nID: " + id 
                 + "\nNOME: " + nome 
                 + "\nDESCRICAO: " + descricao 
                 + "\nDATA DE CRIACAO: " + dataCriacao 
                 + "\nDATA DE MODIFICACAO: " + dataModificacao;
     }

      @Override
     public int hashCode() {
         int hash = 7;
         hash = 31 * hash + Objects.hashCode(this.nome);
         hash = 31 * hash + Objects.hashCode(this.descricao);
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
         final Exercicio other = (Exercicio) obj;
         if (!Objects.equals(this.nome, other.nome)) {
             return false;
         }
         return Objects.equals(this.descricao, other.descricao);
     }  

}