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
public class DivisaoTreinoMusculoDAO {

    private DivisaoTreinoMusculo[] vetorDivisoesMusculo = new DivisaoTreinoMusculo[10];


    public DivisaoTreinoMusculoDAO() {
        DivisaoTreinoMusculo divA = new DivisaoTreinoMusculo();
        divA.setNome("A");
        divA.setDescricao("PEITO, OMBRO, TRICEPS");
        divA.setDataCriacao(LocalDateTime.now());
        divA.setDataModificacao(LocalDateTime.now());
        adiciona(divA);

        DivisaoTreinoMusculo divB = new DivisaoTreinoMusculo();
        divB.setNome("B");
        divB.setDescricao("COSTAS, BICEPS, ABDOMEN");
        divB.setDataCriacao(LocalDateTime.now());
        divB.setDataModificacao(LocalDateTime.now());
        adiciona(divB);

        DivisaoTreinoMusculo divC = new DivisaoTreinoMusculo();
        divC.setNome("C");
        divC.setDescricao("PERNAS, PANTURRILHAS, ABDOMEN");
        divC.setDataCriacao(LocalDateTime.now());
        divC.setDataModificacao(LocalDateTime.now());
        adiciona(divC);

        DivisaoTreinoMusculo divD = new DivisaoTreinoMusculo();
        divD.setNome("D");
        divD.setDescricao("PEITO, OMBRO, TRICEPS");
        divD.setDataCriacao(LocalDateTime.now());
        divD.setDataModificacao(LocalDateTime.now());
        adiciona(divD);

        DivisaoTreinoMusculo divE = new DivisaoTreinoMusculo();
        divE.setNome("E");
        divE.setDescricao("COSTAS, BICEPS, ABDOMEN");
        divE.setDataCriacao(LocalDateTime.now());
        divE.setDataModificacao(LocalDateTime.now());
        adiciona(divE);
    }

    public boolean adiciona(DivisaoTreinoMusculo divisao) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vetorDivisoesMusculo[proximaPosicaoLivre] = divisao;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodasDivisoesMusculo() {
        boolean temDivisao = false;
        for (DivisaoTreinoMusculo divisao : vetorDivisoesMusculo) {
            if (divisao != null) {
                System.out.println(divisao);
                temDivisao = true;
            }
        }
        if (!temDivisao) {
            System.out.println("NENHUMA DIVISAO DE TREINO MUSCULAR CADASTRADA.");
        }
    }

    public DivisaoTreinoMusculo buscarPorNome(String nome) {
        for (DivisaoTreinoMusculo divisao : vetorDivisoesMusculo) {
            if (divisao != null && divisao.getNome().equals(nome)) {
                return divisao;
            }
        }
        return null;
    }
    
     public DivisaoTreinoMusculo buscaPorId(long id) {
        for (DivisaoTreinoMusculo divisao : vetorDivisoesMusculo) {
            if (divisao != null && divisao.getId() == id) {
                return divisao;
            }
        }
        return null;
    }

    public boolean remover(String nome) {
        for (int i = 0; i < vetorDivisoesMusculo.length; i++) {
            if (vetorDivisoesMusculo[i] != null && vetorDivisoesMusculo[i].getNome().equals(nome)) {
                vetorDivisoesMusculo[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < vetorDivisoesMusculo.length; i++) {
            if (vetorDivisoesMusculo[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
