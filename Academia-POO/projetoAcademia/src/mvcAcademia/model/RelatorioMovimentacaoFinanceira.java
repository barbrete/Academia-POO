/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.util.List;

/**
 *
 * @author Rogério
 */
public class RelatorioMovimentacaoFinanceira {

    private MovimentacaoFinanceiraDAO movimentacaoDAO;

    public RelatorioMovimentacaoFinanceira(MovimentacaoFinanceiraDAO movimentacaoDAO) {
        this.movimentacaoDAO = movimentacaoDAO;
    }

    public void exibirRelatorio(int mes, int ano) {
        List<MovimentacaoFinanceira> movimentacoes = movimentacaoDAO.mostrarTodasNoMesEAno(mes, ano);

        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação financeira encontrada no período especificado.");
            return;
        }

        System.out.println("=== RELATÓRIO DE MOVIMENTAÇÃO FINANCEIRA ===");
        System.out.println("Mês: " + mes + ", Ano: " + ano);
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            System.out.println("ID: " + movimentacao.getId());
            System.out.println("Tipo: " + movimentacao.getTipo());
            System.out.println("Descrição: " + movimentacao.getDescricao());
            System.out.println("Valor: " + movimentacao.getValor());
            System.out.println("Data de Criação: " + movimentacao.getDataCriacao());
            System.out.println("-------------------------------------");
        }
    }

}
