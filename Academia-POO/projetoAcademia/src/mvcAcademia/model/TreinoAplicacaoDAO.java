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

    public TreinoAplicacaoDAO() {
        TreinoAplicacao treino1 = new TreinoAplicacao();
        treino1.setNome("Ficha generica 1");
        treino1.setDescricao("Ficha generica para voce");
        treino1.setDataCriacao(LocalDateTime.now().minusDays(10));
        treino1.setDataModificacao(LocalDateTime.now().minusDays(5));
        treino1.setPessoa(pessoaDAO.buscaPessoaPorId(1));

        TreinoAplicacao treino2 = new TreinoAplicacao();
        treino2.setNome("Ficha generica 2");
        treino2.setDescricao("Ficha generica para voce");
        treino2.setDataCriacao(LocalDateTime.now().minusDays(8));
        treino2.setDataModificacao(LocalDateTime.now().minusDays(3));
        treino2.setPessoa(pessoaDAO.buscaPessoaPorId(2));

        TreinoAplicacao treino3 = new TreinoAplicacao();
        treino3.setNome("Ficha generica 3");
        treino3.setDescricao("Ficha generica para voce");
        treino3.setDataCriacao(LocalDateTime.now().minusDays(15));
        treino3.setDataModificacao(LocalDateTime.now().minusDays(7));
        treino2.setPessoa(pessoaDAO.buscaPessoaPorId(2));

        vetorTreinoAplicacoes[0] = treino1;
        vetorTreinoAplicacoes[1] = treino2;
        vetorTreinoAplicacoes[2] = treino3;
    }

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
