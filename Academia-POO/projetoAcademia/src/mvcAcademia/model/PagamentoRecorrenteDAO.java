/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Rogério
 */
public class PagamentoRecorrenteDAO {

    private PagamentoRecorrente[] pagamentos = new PagamentoRecorrente[5];
    PessoaDAO pessoaDAO = new PessoaDAO();

    

    public PagamentoRecorrente buscaPagamentoPorId(long id) {
        for (PagamentoRecorrente pagamento : pagamentos) {
            if (pagamento != null && pagamento.getId() == id) {
                return pagamento;
            }
        }
        return null;
    }

    public void mostrarPagamento(long id) {
        PagamentoRecorrente pagamento = buscaPagamentoPorId(id);
        if (pagamento != null) {
            System.out.println(pagamento);
        } else {
            System.out.println("PAGAMENTO RECORRENTE NÃO ENCONTRADO.");
        }
    }

    public PagamentoRecorrente[] mostrarTodosPagamentos() {
        int count = 0;
        for (PagamentoRecorrente pag : pagamentos) {
            if (pag != null) {
                count++;
            }
        }

        PagamentoRecorrente[] listaPagamentos = new PagamentoRecorrente[count];
        int index = 0;
        for (PagamentoRecorrente pag : pagamentos) {
            if (pag != null) {
                listaPagamentos[index++] = pag;
            }
        }

        return listaPagamentos;
    }

    public boolean adiciona(PagamentoRecorrente pagamento) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            pagamentos[proximaPosicaoLivre] = pagamento;
            return true;
        } else {
            return false;
        }
    }

    public boolean remover(long id) {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] != null && pagamentos[i].getId() == id) {
                pagamentos[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < pagamentos.length; i++) {
            if (pagamentos[i] == null) {
                return i;
            }
        }
        return -1;
    }

}
