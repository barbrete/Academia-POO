/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rogério
 */
public class PagamentoRecorrenteDAO {

    public boolean adiciona(PagamentoRecorrente pagamentoRecorrente) {
        String sql = "insert into pagamento_recorrente (pessoa_id, data, cartao_credito, valor, data_inicio, numero_meses_autorizados, data_criacao, data_modificacao) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, pagamentoRecorrente.getPessoa().getId());
            stmt.setDate(2, java.sql.Date.valueOf(pagamentoRecorrente.getData()));
            stmt.setString(3, pagamentoRecorrente.getCartaoCredito());
            stmt.setDouble(4, pagamentoRecorrente.getValor());
            stmt.setDate(5, java.sql.Date.valueOf(pagamentoRecorrente.getDataInicio()));
            stmt.setInt(6, pagamentoRecorrente.getNumMesesAutorizados());
            stmt.setTimestamp(7, Timestamp.valueOf(pagamentoRecorrente.getDataCriacao()));
            stmt.setTimestamp(8, Timestamp.valueOf(pagamentoRecorrente.getDataModificacao()));

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR PAGAMENTO RECORRENTE: " + e.getMessage());
        }
    }

    public List<PagamentoRecorrente> lista() {
        String sql = "select * from pagamento_recorrente";
        List<PagamentoRecorrente> pagamentos = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                long pessoaId = rs.getLong("pessoa_id");
                LocalDate data = rs.getDate("data").toLocalDate();
                String cartaoCredito = rs.getString("cartao_credito");
                double valor = rs.getDouble("valor");
                LocalDate dataInicio = rs.getDate("data_inicio").toLocalDate();
                int numeroMesesAutorizados = rs.getInt("numero_meses_autorizados");
                LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

                PagamentoRecorrente pagamento = new PagamentoRecorrente();
                pagamento.setId(id);
                pagamento.setPessoa(buscaPessoaPorId(pessoaId)); // Método para buscar pessoa pelo ID
                pagamento.setData(data);
                pagamento.setCartaoCredito(cartaoCredito);
                pagamento.setValor(valor);
                pagamento.setDataInicio(dataInicio);
                pagamento.setNumMesesAutorizados(numeroMesesAutorizados);
                pagamento.setDataCriacao(dataCriacao);
                pagamento.setDataModificacao(dataModificacao);

                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pagamentos;
    }

    public PagamentoRecorrente buscaPorId(long id) {
        String sql = "select * from pagamento_recorrente where id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long pessoaId = rs.getLong("pessoa_id");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    String cartaoCredito = rs.getString("cartao_credito");
                    double valor = rs.getDouble("valor");
                    LocalDate dataInicio = rs.getDate("data_inicio").toLocalDate();
                    int numeroMesesAutorizados = rs.getInt("numero_meses_autorizados");
                    LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

                    PagamentoRecorrente pagamento = new PagamentoRecorrente();
                    pagamento.setId(id);
                    pagamento.setPessoa(buscaPessoaPorId(pessoaId)); // Método para buscar pessoa pelo ID
                    pagamento.setData(data);
                    pagamento.setCartaoCredito(cartaoCredito);
                    pagamento.setValor(valor);
                    pagamento.setDataInicio(dataInicio);
                    pagamento.setNumMesesAutorizados(numeroMesesAutorizados);
                    pagamento.setDataCriacao(dataCriacao);
                    pagamento.setDataModificacao(dataModificacao);

                    return pagamento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public PagamentoRecorrente alterar(PagamentoRecorrente pagamentoRecorrente) {
        String sql = "update pagamento_recorrente set pessoa_id = ?, data = ?, cartao_credito = ?, valor = ?, data_inicio = ?, numero_meses_autorizados = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, pagamentoRecorrente.getPessoa().getId());
            stmt.setDate(2, java.sql.Date.valueOf(pagamentoRecorrente.getData()));
            stmt.setString(3, pagamentoRecorrente.getCartaoCredito());
            stmt.setDouble(4, pagamentoRecorrente.getValor());
            stmt.setDate(5, java.sql.Date.valueOf(pagamentoRecorrente.getDataInicio()));
            stmt.setInt(6, pagamentoRecorrente.getNumMesesAutorizados());
            stmt.setTimestamp(7, Timestamp.valueOf(pagamentoRecorrente.getDataModificacao()));
            stmt.setLong(8, pagamentoRecorrente.getId());

            stmt.execute();
            return pagamentoRecorrente;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR PAGAMENTO RECORRENTE: " + e.getMessage());
        }
    }

    public boolean remover(long id) {
        String sql = "delete from pagamento_recorrente where id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasDeletadas = stmt.executeUpdate();
            return linhasDeletadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER PAGAMENTO RECORRENTE: " + e.getMessage());
        }
    }

    
    private Pessoa buscaPessoaPorId(long pessoaId) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.buscaPorId(pessoaId);
    }
}
