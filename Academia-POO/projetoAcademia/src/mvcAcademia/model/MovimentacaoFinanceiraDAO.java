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
public class MovimentacaoFinanceiraDAO {

    private MovimentacaoFinanceira[] movimentacoes = new MovimentacaoFinanceira[10];
    private int tamanhoAtual = 0;
    
    

    public MovimentacaoFinanceiraDAO() {

        MovimentacaoFinanceira movimentacao1 = new MovimentacaoFinanceira();
        movimentacao1.setValor(1000.0);
        movimentacao1.setTipo("ENTRADA");
        movimentacao1.setDescricao("Recebimento mensalidade");
        movimentacao1.setDataCriacao(LocalDateTime.now());
        movimentacao1.setDataModificacao(LocalDateTime.now());
        adiciona(movimentacao1);

        MovimentacaoFinanceira movimentacao2 = new MovimentacaoFinanceira();

        movimentacao2.setValor(500.0);
        movimentacao2.setTipo("SAIDA");
        movimentacao2.setDescricao("Pagamento fornecedor");
        movimentacao2.setDataCriacao(LocalDateTime.now());
        movimentacao2.setDataModificacao(LocalDateTime.now());
        adiciona(movimentacao2);

        MovimentacaoFinanceira movimentacao3 = new MovimentacaoFinanceira();
        movimentacao3.setValor(300.0);
        movimentacao3.setTipo("ENTRADA");
        movimentacao3.setDescricao("Recebimento de venda de equipamento");
        movimentacao3.setDataCriacao(LocalDateTime.now());
        movimentacao3.setDataModificacao(LocalDateTime.now());
        adiciona(movimentacao3);

        MovimentacaoFinanceira movimentacao4 = new MovimentacaoFinanceira();
        movimentacao4.setValor(200.0);
        movimentacao4.setTipo("SAIDA");
        movimentacao4.setDescricao("Pagamento de conta de agua");
        movimentacao4.setDataCriacao(LocalDateTime.now());
        movimentacao4.setDataModificacao(LocalDateTime.now());
        adiciona(movimentacao4);

        MovimentacaoFinanceira movimentacao5 = new MovimentacaoFinanceira();
        movimentacao5.setValor(700.0);
        movimentacao5.setTipo("ENTRADA");
        movimentacao5.setDescricao("Recebimento de emprestimo");
        movimentacao5.setDataCriacao(LocalDateTime.now());
        movimentacao5.setDataModificacao(LocalDateTime.now());
        adiciona(movimentacao5);

    }

    public boolean adiciona(MovimentacaoFinanceira movimentacao) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            movimentacoes[proximaPosicaoLivre] = movimentacao;
            tamanhoAtual++; // Incrementa o tamanho atual
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodasNoMesEAno(int mes, int ano) {
        boolean encontrouMovimentacao = false;
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            if (movimentacao != null && movimentacao.getDataCriacao().getMonthValue() == mes
                    && movimentacao.getDataCriacao().getYear() == ano) {
                System.out.println(movimentacao);
                encontrouMovimentacao = true;
            }
        }
        if (!encontrouMovimentacao) {
            System.out.println("NENHUMA MOVIMENTACAO FINANCEIRA ENCONTRADA EM " + mes + " DE " + ano);
        }
    }

    public MovimentacaoFinanceira buscarMovimentacaoPorId(long id) {
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            if (movimentacao != null && movimentacao.getId() == id) {
                return movimentacao;
            }
        }
        return null;
    }

    public boolean remover(long id) {
        for (int i = 0; i < movimentacoes.length; i++) {
            if (movimentacoes[i] != null && movimentacoes[i].getId() == id) {
                movimentacoes[i] = null;
                return true;
            }
        }
        return false;
    }

    public void mostrarTodas() {
        if (tamanhoAtual == 0) {
            System.out.println("NAO HA MOVIMENTACOES FINANCEIRAS CADASTRADAS.");
        } else {
            for (int i = 0; i < tamanhoAtual; i++) {
                System.out.println(movimentacoes[i]);
            }
        }
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < movimentacoes.length; i++) {
            if (movimentacoes[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
