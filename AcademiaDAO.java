/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto01; 

import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */
public class AcademiaDAO {
   
    
   private Academia academia = new Academia();

     public AcademiaDAO() {
        this.academia = new Academia();
        this.academia.setNome("BRASIL FITNESS");
        this.academia.setEndereco("RUA BRASILIA");
        this.academia.setDataCriacao(LocalDateTime.now());
        this.academia.setDataModificacao(LocalDateTime.now());
    }
    
    public Academia getAcademia() {
        return academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

    public void criarAcademia(String nome, String endereco) {
        this.academia = new Academia();
        this.academia.setNome(nome);
        this.academia.setEndereco(endereco);
        this.academia.setDataCriacao(LocalDateTime.now());
        this.academia.setDataModificacao(LocalDateTime.now());
    }

    public void mostrarAcademia() {
        System.out.println(this.academia);
    }

    public void excluirAcademia() {
        this.academia = null;
    }

    public void alterarNomeAcademia(String novoNome) {
        this.academia.setNome(novoNome);
        this.academia.setDataModificacao(LocalDateTime.now());
    }
}
