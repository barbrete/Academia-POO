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

    public PagamentoRecorrenteDAO() {
        PagamentoRecorrente pagamento1 = new PagamentoRecorrente();
        pagamento1.setPessoa(pessoaDAO.buscaPessoaPorId(1));
        pagamento1.setData(LocalDateTime.of(2024, 5, 15, 0, 0));
        pagamento1.setCartaoCredito("1234 5678 9012 3456");
        pagamento1.setValor(150.00);
        pagamento1.setDataInicio(LocalDateTime.of(2024, 5, 1, 0, 0));
        pagamento1.setNumMesesAutorizados(6);
        pagamento1.setDataCriacao(LocalDateTime.now());
        pagamento1.setDataModificacao(LocalDateTime.now());
        adiciona(pagamento1);

        PagamentoRecorrente pagamento2 = new PagamentoRecorrente();
        pagamento1.setPessoa(pessoaDAO.buscaPessoaPorId(2));
        pagamento2.setData(LocalDateTime.of(2024, 6, 10, 0, 0));
        pagamento2.setCartaoCredito("9876 5432 1098 7654");
        pagamento2.setValor(120.00); 
        pagamento2.setDataInicio(LocalDateTime.of(2024, 6, 1, 0, 0));
        pagamento2.setNumMesesAutorizados(3);
        pagamento2.setDataCriacao(LocalDateTime.now());
        pagamento2.setDataModificacao(LocalDateTime.now());
        adiciona(pagamento2);

        PagamentoRecorrente pagamento3 = new PagamentoRecorrente();
        pagamento1.setPessoa(pessoaDAO.buscaPessoaPorId(3));;
        pagamento3.setData(LocalDateTime.of(2024, 7, 5, 0, 0));
        pagamento3.setCartaoCredito("2468 1357 8024 6790");
        pagamento3.setValor(200.00);
        pagamento3.setDataInicio(LocalDateTime.of(2024, 7, 1, 0, 0));
        pagamento3.setNumMesesAutorizados(12);
        pagamento3.setDataCriacao(LocalDateTime.now());
        pagamento3.setDataModificacao(LocalDateTime.now());
        adiciona(pagamento3);

    }

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
