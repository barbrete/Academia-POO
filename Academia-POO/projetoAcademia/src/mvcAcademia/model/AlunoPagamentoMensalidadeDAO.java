/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author barbrete e kitotsui
 */
public class AlunoPagamentoMensalidadeDAO {

    AlunoPagamentoMensalidade[] vAlunoPagMensalidade = new AlunoPagamentoMensalidade[5];
    PessoaDAO pessoaDAO = new PessoaDAO();
    MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();

    

    public AlunoPagamentoMensalidade buscarUltimoPagamentoPorAluno(long alunoId) {
        AlunoPagamentoMensalidade ultimoPagamento = null;
        for (AlunoPagamentoMensalidade apm : vAlunoPagMensalidade) {
            if (apm != null && apm.getAluno().getId() == alunoId) {
                if (ultimoPagamento == null || apm.getDataVencimento().isAfter(ultimoPagamento.getDataVencimento())) {
                    ultimoPagamento = apm;
                }
            }
        }
        return ultimoPagamento;
    }

    public AlunoPagamentoMensalidade buscaAlunoPagamentoMensalidadePorId(long id) {
        for (AlunoPagamentoMensalidade apm : vAlunoPagMensalidade) {
            if (apm != null && apm.getId() == id) {
                return apm;
            }
        }
        return null;
    }

    public void mostraAlunoPagamentoMensalidade(long id) {//mostra pagamento especifico
        AlunoPagamentoMensalidade apm = buscaAlunoPagamentoMensalidadePorId(id);
        if (apm != null) {
            System.out.println(apm);
        } else {
            System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO NAO ENCONTRADO.");
        }
    }

    public boolean adiciona(AlunoPagamentoMensalidade apm) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vAlunoPagMensalidade[proximaPosicaoLivre] = apm;
            return true;
        } else {
            return false;
        }

    }

    public boolean ehVazio() {
        for (AlunoPagamentoMensalidade apm : vAlunoPagMensalidade) {
            if (apm != null) {
                return false;
            }
        }
        return true;

    }

    public void mostrarTodos() {
        boolean temAlunoPagamentoMensalidade = false;
        for (AlunoPagamentoMensalidade apm : vAlunoPagMensalidade) {
            if (apm != null) {
                System.out.println(apm);
                temAlunoPagamentoMensalidade = true;
            }
        }
        if (!temAlunoPagamentoMensalidade) {
            System.out.println("não existe usuario cadastrado");
        }
    }

    public boolean alterarValor(long id, double novoValor) {
        AlunoPagamentoMensalidade valor = buscaAlunoPagamentoMensalidadePorId(id);
        if (valor != null) {
            valor.setValorPago(novoValor);
            return true;
        }
        return false;
    }

    public boolean remover(long id) {
        for (int i = 0; i < vAlunoPagMensalidade.length; i++) {
            if (vAlunoPagMensalidade[i] != null && vAlunoPagMensalidade[i].getId() == id) {
                for (int j = i; j < vAlunoPagMensalidade.length - 1; j++) {
                    vAlunoPagMensalidade[j] = vAlunoPagMensalidade[j + 1];
                }
                vAlunoPagMensalidade[vAlunoPagMensalidade.length - 1] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < vAlunoPagMensalidade.length; i++) {
            if (vAlunoPagMensalidade[i] == null) {
                return i;
            }

        }
        return -1;

    }

}
