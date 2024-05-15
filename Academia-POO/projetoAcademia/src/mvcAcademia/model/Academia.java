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
 public class Academia {
 
     private static long serial;
     private long id;
     private String nome;
     private String endereco;
     private LocalDateTime dataCriacao;
     private LocalDateTime dataModificacao;
 
     public Academia()
     {
         id = Academia.serial++;
     }
     public long getId()
     {
         return id;
     }
 
     public String getNome() {
         return nome;
     }
 
     public void setNome(String nome) {
         this.nome = nome;
     }
 
     public String getEndereco() {
         return endereco;
     }
 
     public void setEndereco(String endereco) {
         this.endereco = endereco;
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
         return "==================INFORMACOES DA ACADEMIA================= " 
                 + "\nID: " + id 
                 + "\nNOME: " + nome 
                 + "\nENDERECO: " + endereco 
                 + "\nDATA DE CRIACAO: " + dataCriacao 
                 + "\nDATA DE MODIFICACAO: " + dataModificacao;
     }
 
     @Override
     public int hashCode() {
         int hash = 3;
         hash = 23 * hash + Objects.hashCode(this.nome);
         hash = 23 * hash + Objects.hashCode(this.endereco);
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
         final Academia other = (Academia) obj;
         if (!Objects.equals(this.nome, other.nome)) {
             return false;
         }
         return Objects.equals(this.endereco, other.endereco);
     }        
 }
