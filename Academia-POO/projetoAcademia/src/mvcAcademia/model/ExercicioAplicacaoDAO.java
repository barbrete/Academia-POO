/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */
public class ExercicioAplicacaoDAO {
    
    private ExercicioAplicacao[] vetDescricao = new ExercicioAplicacao[16];
    
    public ExercicioAplicacaoDAO(){
        
        ExercicioAplicacao ea1 = new ExercicioAplicacao();
        ea1.setDescricao("4x12");
        ea1.setDataCriacao(LocalDateTime.now());
        ea1.setDataModificacao(LocalDateTime.now());
        adiciona(ea1);
        
        ExercicioAplicacao ea2 = new ExercicioAplicacao();
        ea2.setDescricao("4x15");
        ea2.setDataCriacao(LocalDateTime.now());
        ea2.setDataModificacao(LocalDateTime.now());
        adiciona(ea2);
        
        ExercicioAplicacao ea3 = new ExercicioAplicacao();
        ea3.setDescricao("4x8");
        ea3.setDataCriacao(LocalDateTime.now());
        ea3.setDataModificacao(LocalDateTime.now());
        adiciona(ea3);
        
        ExercicioAplicacao ea4 = new ExercicioAplicacao();
        ea4.setDescricao("5x10");
        ea4.setDataCriacao(LocalDateTime.now());
        ea4.setDataModificacao(LocalDateTime.now());
        adiciona(ea4);
        
        ExercicioAplicacao ea5 = new ExercicioAplicacao();
        ea5.setDescricao("3x15");
        ea5.setDataCriacao(LocalDateTime.now());
        ea5.setDataModificacao(LocalDateTime.now());
        adiciona(ea5);
        
        ExercicioAplicacao ea6 = new ExercicioAplicacao();
        ea6.setDescricao("3x12");
        ea6.setDataCriacao(LocalDateTime.now());
        ea6.setDataModificacao(LocalDateTime.now());
        adiciona(ea6);
        
        ExercicioAplicacao ea7 = new ExercicioAplicacao();
        ea7.setDescricao("4x10");
        ea7.setDataCriacao(LocalDateTime.now());
        ea7.setDataModificacao(LocalDateTime.now());
        adiciona(ea7);
        
        ExercicioAplicacao ea8 = new ExercicioAplicacao();
        ea8.setDescricao("3x20");
        ea8.setDataCriacao(LocalDateTime.now());
        ea8.setDataModificacao(LocalDateTime.now());
        adiciona(ea8);
        
        ExercicioAplicacao ea9 = new ExercicioAplicacao();
        ea9.setDescricao("5 séries de 1 minuto");
        ea9.setDataCriacao(LocalDateTime.now());
        ea9.setDataModificacao(LocalDateTime.now());
        adiciona(ea9);
        
        ExercicioAplicacao ea10 = new ExercicioAplicacao();
        ea10.setDescricao("3 séries de 1 minuto");
        ea10.setDataCriacao(LocalDateTime.now());
        ea10.setDataModificacao(LocalDateTime.now());
        adiciona(ea10);
    }
         public boolean adiciona(ExercicioAplicacao exAplic) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vetDescricao[proximaPosicaoLivre] = exAplic;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodasAplicacoes(){
        boolean temAplicacao = false;
        for (ExercicioAplicacao ea : vetDescricao) {
            if (ea != null) {
                System.out.println(ea);
                temAplicacao = true;
            }
        }
        if (!temAplicacao) {
            System.out.println("NAO EXISTEM APLICACOES DE EXERCICIO CADASTRADA.");
        }
    }

    public ExercicioAplicacao buscaPorDescricao(String descricao) {
        for (ExercicioAplicacao ea : vetDescricao) {
            if (ea != null && ea.getDescricao().equals(descricao)) {
                return ea;
            }
        }
        return null;
    }

    public boolean remover(String descricao) {
        for (int i = 0; i < vetDescricao.length; i++) {
            if (vetDescricao[i] != null && vetDescricao[i].getDescricao().equals(descricao)) {
                vetDescricao[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < vetDescricao.length; i++) {
            if (vetDescricao[i] == null) {
                return i;
            }
        }
        return -1;  
    }   
}
