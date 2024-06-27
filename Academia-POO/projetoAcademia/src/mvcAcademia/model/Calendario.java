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
    }

    public void verificarVencimentos() {
        AlunoPagamentoMensalidadeDAO alunoPagamentoMensalidadeDAO = new AlunoPagamentoMensalidadeDAO();
        MensalidadeVigenteDAO mensalidadeVigenteDAO = new MensalidadeVigenteDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();

        List<AlunoPagamentoMensalidade> pagamentos = alunoPagamentoMensalidadeDAO.lista();

        for (AlunoPagamentoMensalidade apm : pagamentos) {
            if (apm != null && dataAtual.equals(apm.getDataVencimento())) {
                MensalidadeVigente mensalidadeVigente = mensalidadeVigenteDAO.buscaPorId(apm.getMvAlunoPagamento().getId());
                if (mensalidadeVigente != null) {
                    registrarPagamentoMensalidade(apm.getAluno(), mensalidadeVigente.getValor());
                }
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
