/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.util.List;

/**
 *
 * @author barbrete
 */
public class RelatorioMovimentacaoFinanceira {

    private MovimentacaoFinanceiraDAO movimentacaoDAO;

    public RelatorioMovimentacaoFinanceira(MovimentacaoFinanceiraDAO movimentacaoDAO) {
        this.movimentacaoDAO = movimentacaoDAO;
    }

    public void exibirRelatorio(int mes, int ano) {
        
        MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO = new MovimentacaoFinanceiraDAO();
        List<MovimentacaoFinanceira> movimentacoes = movimentacaoFinanceiraDAO.mostrarTodasNoMesEAno(mes, ano);
    
        System.out.println("=== RELATORIO DE MOVIMENTACAO FINANCEIRA ===");
        System.out.println("MES: " + mes + ", ANO: " + ano);
        System.out.println("--------------------------------------------");
        for (MovimentacaoFinanceira movimentacao : movimentacoes) {
            System.out.println(movimentacao);
        }
        
    }
    

}
