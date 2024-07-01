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
public class RelatorioAlunoAdimplentes {

    private AlunoPagamentoMensalidadeDAO apmDAO;

    public RelatorioAlunoAdimplentes(AlunoPagamentoMensalidadeDAO apmDAO) {
        this.apmDAO = apmDAO;
    }

    public void exibirRelatorio(int mes, int ano) {
        
        AlunoPagamentoMensalidadeDAO apmDAO = new AlunoPagamentoMensalidadeDAO();
        List<AlunoPagamentoMensalidade> pagamentomensalidades = apmDAO.mostrarAdimplentesNoMesEAno(mes, ano);
    
        System.out.println("=== RELATORIO DE ALUNOS ADIMPLENTES ===");
        System.out.println("MES: " + mes + ", ANO: " + ano);
        System.out.println("--------------------------------------------");
        for (AlunoPagamentoMensalidade aluno : pagamentomensalidades) {
            System.out.println(aluno);
        }
        
    }
    

}
