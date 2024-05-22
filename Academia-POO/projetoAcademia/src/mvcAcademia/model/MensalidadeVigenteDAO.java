/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Acer
 */
public class MensalidadeVigenteDAO {
    
    private MensalidadeVigente[] vetMensalidade = new MensalidadeVigente[5];
    
    public MensalidadeVigenteDAO(){
        
        LocalDate dataInicio1 = LocalDate.of(2024, 5, 8);
        LocalDate dataTermino1 = LocalDate.of(2025, 6, 11);        
        MensalidadeVigente mv1 = new MensalidadeVigente();
        mv1.setValor(100.00);
        mv1.setDataInicio(dataInicio1);
        mv1.setDataTermino(dataTermino1);
        mv1.setDataCriacao(UtilPessoa.getDiaAtual());
        mv1.setDataModificacao(UtilPessoa.getDiaAtual());
        adiciona(mv1);
        
        LocalDate dataInicio2 = LocalDate.of(2023, 4, 10);
        LocalDate dataTermino2 = LocalDate.of(2026, 6, 11);        
        MensalidadeVigente mv2 = new MensalidadeVigente();
        mv2.setValor(120.00);
        mv2.setDataInicio(dataInicio2);
        mv2.setDataTermino(dataTermino2);
        mv2.setDataCriacao(UtilPessoa.getDiaAtual());
        mv2.setDataModificacao(UtilPessoa.getDiaAtual());
        adiciona(mv2);
        
        LocalDate dataInicio3 = LocalDate.of(2022, 1, 1);
        LocalDate dataTermino3 = LocalDate.of(2024, 12, 12);        
        MensalidadeVigente mv3 = new MensalidadeVigente();
        mv3.setValor(80.00);
        mv3.setDataInicio(dataInicio3);
        mv3.setDataTermino(dataTermino3);
        mv3.setDataCriacao(UtilPessoa.getDiaAtual());
        mv3.setDataModificacao(UtilPessoa.getDiaAtual());
        adiciona(mv3);

    }
    
    public MensalidadeVigente buscaMensalidadePorId(long id) {
        for (MensalidadeVigente mv : vetMensalidade) {
            if (mv != null && mv.getId() == id) {
                return mv;
            }
        }
        return null;
    }
      
   public void mostraMensalidade(long id) {//mostra mensalidade especifica
       MensalidadeVigente mv = buscaMensalidadePorId(id);
        if (mv != null) {
            System.out.println(mv);
        } else {
            System.out.println("MENSALIDADE NAO ENCONTRADA.");
        }
    }
   
    public MensalidadeVigente mostraMensalidadeVigente(long id) {
       MensalidadeVigente mv = buscaMensalidadePorId(id);
        if (mv != null) {
            return mv;
        } else {
            return null;
        }
    }
    
    
    public boolean adiciona(MensalidadeVigente mv) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vetMensalidade[proximaPosicaoLivre] = mv;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodasMensalidades(){
        boolean temMensalide = false;
        for (MensalidadeVigente mv : vetMensalidade) {
            if (mv != null) {
                System.out.println(mv);
                temMensalide = true;
            }
        }
        if (!temMensalide) {
            System.out.println("NAO EXISTEM APLICACOES DE EXERCICIO CADASTRADA.");
        }
    }

    public boolean remover(long id) {
        for (int i = 0; i < vetMensalidade.length; i++) {
            if (vetMensalidade[i] != null && vetMensalidade[i].getId() == id) {
                for (int j = i; j < vetMensalidade.length - 1; j++) {
                    vetMensalidade[j] = vetMensalidade[j + 1];
                }
                vetMensalidade[vetMensalidade.length - 1] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < vetMensalidade.length; i++) {
            if (vetMensalidade[i] == null) {
                return i;
            }
        }
        return -1;  
    }
}

