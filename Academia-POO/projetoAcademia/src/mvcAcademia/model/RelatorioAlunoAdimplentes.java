/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.util.List;

/**
 *
 * @author barbrete e Kitotsui
 */
public class RelatorioAlunoAdimplentes {

    private AlunoPagamentoMensalidadeDAO apmDAO;

    public RelatorioAlunoAdimplentes(AlunoPagamentoMensalidadeDAO apmDAO) {
        this.apmDAO = apmDAO;
    }

    public void exibirRelatorio(int mes, int ano) {
        List<AlunoPagamentoMensalidade> pagamentos = apmDAO.mostrarAdimplentesNoMesEAno(mes, ano);

        if (pagamentos.isEmpty()) {
            System.out.println("Nenhuma movimentação financeira encontrada no período especificado.");
            return;
        }

        System.out.println("=== RELATÓRIO DE ALUNOS ADIMPLENTES ===");
        System.out.println("Mês: " + mes + ", Ano: " + ano);
        for (AlunoPagamentoMensalidade pagamento : pagamentos) {
            System.out.println("Aluno: " + pagamento.getAluno().getNome());
            System.out.println("Valor Pago: " + pagamento.getValorPago());
            System.out.println("Data do Pagamento: " + pagamento.getDataPagamento());
            System.out.println("-------------------------------------");
        }
    }
}
