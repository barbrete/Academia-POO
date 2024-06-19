/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author Rogério
 */
public class TreinoAplicacaoDAO {

    private TreinoAplicacao[] vetorTreinoAplicacoes = new TreinoAplicacao[10];
    PessoaDAO pessoaDAO = new PessoaDAO();

    

    public boolean adiciona(TreinoAplicacao treinoAplicacao) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vetorTreinoAplicacoes[proximaPosicaoLivre] = treinoAplicacao;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodosTreinosAplicacoes() {
        boolean temTreino = false;
        for (TreinoAplicacao treino : vetorTreinoAplicacoes) {
            if (treino != null) {
                System.out.println(treino);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("NENHUMA APLICACAO DE TREINO CADASTRADA.");
        }
    }

    public TreinoAplicacao buscarPorId(long id) {
        for (TreinoAplicacao treino : vetorTreinoAplicacoes) {
            if (treino != null && treino.getId() == id) {
                return treino;
            }
        }
        return null;
    }
    
    public TreinoAplicacao buscarPorPessoaId(long pessoaId) {
    for (TreinoAplicacao treino : vetorTreinoAplicacoes) {
        if (treino != null && treino.getPessoa() != null && treino.getPessoa().getId() == pessoaId) {
            return treino;
        }
    }
    return null;
}

    public boolean remover(long id) {
        for (int i = 0; i < vetorTreinoAplicacoes.length; i++) {
            if (vetorTreinoAplicacoes[i] != null && vetorTreinoAplicacoes[i].getId() == id) {
                vetorTreinoAplicacoes[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < vetorTreinoAplicacoes.length; i++) {
            if (vetorTreinoAplicacoes[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
