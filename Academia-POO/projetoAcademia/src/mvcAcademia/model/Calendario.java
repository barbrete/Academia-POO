/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Rogério
 */
public class Calendario {

    private LocalDate dataAtual;

    public Calendario() {
        this.dataAtual = LocalDate.now();
    }

    public LocalDate getDataAtual() {
        return dataAtual;
    }

    public void avancarData(int dias) {
        this.dataAtual = this.dataAtual.plusDays(dias);
         verificarVencimentos();
    }
    
    

     public void verificarVencimentos() {
        AlunoPagamentoMensalidadeDAO alunoPagamentoMensalidadeDAO = new AlunoPagamentoMensalidadeDAO();
        MensalidadeVigenteDAO mensalidadeVigenteDAO = new MensalidadeVigenteDAO();
        PagamentoRecorrenteDAO pagamentoRecorrenteDAO = new PagamentoRecorrenteDAO();

        List<PagamentoRecorrente> pagamentos = pagamentoRecorrenteDAO.lista();

        for (PagamentoRecorrente pr : pagamentos) {
            if (pr != null && dataAtual.equals(pr.getDataVencimento())) {
                MensalidadeVigente mensalidadeVigente = mensalidadeVigenteDAO.buscaPorId(pr.getPessoa().getId());
                if (mensalidadeVigente == null) {
                    mensalidadeVigente = new MensalidadeVigente(pr.getPessoa(), pr.getValor(), dataAtual, dataAtual.plusMonths(1), LocalDateTime.now(), LocalDateTime.now());
                    mensalidadeVigenteDAO.adiciona(mensalidadeVigente);
                } else {
                    mensalidadeVigente.setDataTermino(dataAtual.plusMonths(1));
                    mensalidadeVigenteDAO.alterar(mensalidadeVigente);
                }
                alunoPagamentoMensalidadeDAO.adiciona(new AlunoPagamentoMensalidade(pr.getPessoa(), dataAtual, pr.getValor(), LocalDateTime.now(), LocalDateTime.now()));
            }
        }
    }

    private void registrarPagamentoMensalidade(Pessoa aluno, double valor) {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();
        apm.setAluno(aluno);
        apm.setValorPago(valor);
        apm.setDataPagamento(dataAtual);
        apm.setModalidade(1);
        apm.setDataCriacao(LocalDateTime.now());
        apm.setDataModificacao(LocalDateTime.now());

        AlunoPagamentoMensalidadeDAO alunoPagamentoMensalidadeDAO = new AlunoPagamentoMensalidadeDAO();
        alunoPagamentoMensalidadeDAO.adiciona(apm);

        System.out.println("Pagamento registrado para: " + aluno.getNome());
    }

}
