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
public class DivisaoTreinoDAO {

    private DivisaoTreino[] divisoesTreino = new DivisaoTreino[10];

    public DivisaoTreinoDAO() {
        DivisaoTreino divABC = new DivisaoTreino();
        divABC.setNome("ABC");
        divABC.setDescricao("ABC descansa 1x ABC descansa 2x");
        divABC.setDataCriacao(LocalDateTime.now());
        divABC.setDataModificacao(LocalDateTime.now());
        adiciona(divABC);

        DivisaoTreino divBCD = new DivisaoTreino();
        divBCD.setNome("BCD");
        divBCD.setDescricao("BCD descansa 1x BCD sem descanso");
        divBCD.setDataCriacao(LocalDateTime.now());
        divBCD.setDataModificacao(LocalDateTime.now());
        adiciona(divBCD);

        DivisaoTreino divCDE = new DivisaoTreino();
        divCDE.setNome("CDE");
        divCDE.setDescricao("CDE descansa 1x CDE descansa 1x CDE descansa 1x");
        divCDE.setDataCriacao(LocalDateTime.now());
        divCDE.setDataModificacao(LocalDateTime.now());
        adiciona(divCDE);
    }

    public boolean adiciona(DivisaoTreino divisaoTreino) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            divisoesTreino[proximaPosicaoLivre] = divisaoTreino;
            return true;
        } else {
            return false;
        }
    }
    
    public void mostrarTodasDivisoesTreino() {
        boolean temDivisaoTreino = false;
        for (DivisaoTreino divisaoTreino : divisoesTreino) {
            if (divisaoTreino != null) {
                System.out.println(divisaoTreino);
                temDivisaoTreino = true;
            }
        }
        if (!temDivisaoTreino) {
            System.out.println("NAO EXISTEM DIVISOES DE TREINO CADASTRADAS.");
        }
    }

    public DivisaoTreino buscaPorNome(String nome) {
        for (DivisaoTreino divisaoTreino : divisoesTreino) {
            if (divisaoTreino != null && divisaoTreino.getNome().equals(nome)) {
                return divisaoTreino;
            }
        }
        return null;
    }
    
      public DivisaoTreino buscaPorId(long id) {
        for (DivisaoTreino divisaoT : divisoesTreino) {
            if (divisaoT != null && divisaoT.getId() == id) {
                return divisaoT;
            }
        }
        return null;
    }

    public boolean remover(String nome) {
        for (int i = 0; i < divisoesTreino.length; i++) {
            if (divisoesTreino[i] != null && divisoesTreino[i].getNome().equals(nome)) {
                divisoesTreino[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < divisoesTreino.length; i++) {
            if (divisoesTreino[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
   
}
