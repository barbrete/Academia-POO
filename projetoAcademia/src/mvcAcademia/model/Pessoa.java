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
 
 public class Pessoa {
     static long serial;
     private long id;
     private String nome;
     private char sexo;
     private int nascimento;
     private String login;
     private String senha;
     private String tipoUsuario;
     private LocalDateTime dataCriacao;
     private LocalDateTime dataModificacao;
     
     public Pessoa()
     {
         id = Pessoa.serial++;
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
 
     public char getSexo() {
         return sexo;
     }
 
     public void setSexo(char sexo) {
         this.sexo = sexo;
     }
 
     public int getNascimento() {
         return nascimento;
     }
 
     public void setNascimento(int nascimento) {
         this.nascimento = nascimento;
     }
 
     public String getLogin() {
         return login;
     }
 
     public void setLogin(String login) {
         this.login = login;
     }
 
     public String getSenha() {
         return senha;
     }
 
     public void setSenha(String senha) {
         this.senha = senha;
     }
 
     public String getTipoUsuario() {
         return tipoUsuario;
     }
 
     public void setTipoUsuario(String tipoUsuario) {
         this.tipoUsuario = tipoUsuario;
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
         return "\nNOME:  " + this.nome + " COM ID: " + this.id;
     }
 
     @Override
     public int hashCode() {
         int hash = 5;
         hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
         hash = 67 * hash + Objects.hashCode(this.login);
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
         final Pessoa other = (Pessoa) obj;
         if (this.id != other.id) {
             return false;
         }
         return Objects.equals(this.login, other.login);
     }
 
 
     
 }
 