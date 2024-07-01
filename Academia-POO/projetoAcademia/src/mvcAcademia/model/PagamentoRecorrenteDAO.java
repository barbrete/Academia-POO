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

    public boolean adiciona(PagamentoRecorrente pr) {
        String sql = "INSERT INTO pagamentorecorrente (id_pessoa, data, cartaocredito, valor, datainicio, datavencimento, numeroMesesAutorizados, datacriacao, datamodificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, pr.getPessoa().getId());
            stmt.setDate(2, java.sql.Date.valueOf(pr.getData()));
            stmt.setString(3, pr.getCartaoCredito());
            stmt.setDouble(4, pr.getValor());
            stmt.setDate(5, java.sql.Date.valueOf(pr.getDataInicio()));
            stmt.setDate(6, java.sql.Date.valueOf(pr.getDataVencimento()));
            stmt.setInt(7, pr.getNumeroMesesAutorizados());
            stmt.setTimestamp(8, Timestamp.valueOf(pr.getDataCriacao()));
            stmt.setTimestamp(9, Timestamp.valueOf(pr.getDataModificacao()));

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar pagamento recorrente: " + e.getMessage());
        }
    }

    public List<PagamentoRecorrente> lista() {
        String sql = "SELECT * FROM pagamentorecorrente";
        List<PagamentoRecorrente> pagamentos = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idpagamentorecorrente");
                long pessoaId = rs.getLong("id_pessoa");
                LocalDate data = rs.getDate("data").toLocalDate();
                String cartaoCredito = rs.getString("cartaocredito");
                double valor = rs.getDouble("valor");
                LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                int numeroMesesAutorizados = rs.getInt("numeroMesesAutorizados");
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                PessoaDAO pessoaDAO = new PessoaDAO();
                Pessoa pessoa = pessoaDAO.buscaPorId(pessoaId);

                PagamentoRecorrente pagamento = new PagamentoRecorrente();
                pagamento.setId(id);
                pagamento.setPessoa(pessoa);
                pagamento.setData(data);
                pagamento.setCartaoCredito(cartaoCredito);
                pagamento.setValor(valor);
                pagamento.setDataInicio(dataInicio);
                pagamento.setDataVencimento(dataVencimento);
                pagamento.setNumeroMesesAutorizados(numeroMesesAutorizados);
                pagamento.setDataCriacao(dataCriacao);
                pagamento.setDataModificacao(dataModificacao);

                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pagamentos recorrentes: " + e.getMessage());
        }
        return pagamentos;
    }

    public PagamentoRecorrente buscaPorId(long id) {
        String sql = "SELECT * FROM pagamentorecorrente WHERE idpagamentorecorrente = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long pessoaId = rs.getLong("id_pessoa");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    String cartaoCredito = rs.getString("cartaocredito");
                    double valor = rs.getDouble("valor");
                    LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                    LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                    int numeroMesesAutorizados = rs.getInt("numeroMesesAutorizados");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    PessoaDAO pessoaDAO = new PessoaDAO();
                    Pessoa pessoa = pessoaDAO.buscaPorId(pessoaId);

                    PagamentoRecorrente pagamento = new PagamentoRecorrente();
                    pagamento.setId(id);
                    pagamento.setPessoa(pessoa);
                    pagamento.setData(data);
                    pagamento.setCartaoCredito(cartaoCredito);
                    pagamento.setValor(valor);
                    pagamento.setDataInicio(dataInicio);
                    pagamento.setDataVencimento(dataVencimento);
                    pagamento.setNumeroMesesAutorizados(numeroMesesAutorizados);
                    pagamento.setDataCriacao(dataCriacao);
                    pagamento.setDataModificacao(dataModificacao);

                    return pagamento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamento recorrente: " + e.getMessage());
        }
        return null;
    }

    public boolean alterar(PagamentoRecorrente pr) {
        String sql = "UPDATE pagamentorecorrente SET id_pessoa = ?, data = ?, cartaocredito = ?, valor = ?, datainicio = ?, datavencimento = ?, numeroMesesAutorizados = ?, datacriacao = ?, datamodificacao = ? WHERE idpagamentorecorrente = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, pr.getPessoa().getId());
            stmt.setDate(2, java.sql.Date.valueOf(pr.getData()));
            stmt.setString(3, pr.getCartaoCredito());
            stmt.setDouble(4, pr.getValor());
            stmt.setDate(5, java.sql.Date.valueOf(pr.getDataInicio()));
            stmt.setDate(6, java.sql.Date.valueOf(pr.getDataVencimento()));
            stmt.setInt(7, pr.getNumeroMesesAutorizados());
            stmt.setTimestamp(8, Timestamp.valueOf(pr.getDataCriacao()));
            stmt.setTimestamp(9, Timestamp.valueOf(pr.getDataModificacao()));
            stmt.setLong(10, pr.getId());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar pagamento recorrente: " + e.getMessage());
        }
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM pagamentorecorrente WHERE idpagamentorecorrente = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover pagamento recorrente: " + e.getMessage());
        }
    }

    public PagamentoRecorrente buscarUltimoPagamentoDoAluno(long idAluno) {
        String sql = "SELECT * FROM pagamentorecorrente WHERE id_pessoa = ? ORDER BY datavencimento DESC LIMIT 1";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("idpagamentorecorrente");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    String cartaoCredito = rs.getString("cartaocredito");
                    double valor = rs.getDouble("valor");
                    LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                    LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                    int numeroMesesAutorizados = rs.getInt("numeroMesesAutorizados");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    PessoaDAO pessoaDAO = new PessoaDAO();
                    Pessoa pessoa = pessoaDAO.buscaPorId(idAluno);

                    PagamentoRecorrente pagamento = new PagamentoRecorrente();
                    pagamento.setId(id);
                    pagamento.setPessoa(pessoa);
                    pagamento.setData(data);
                    pagamento.setCartaoCredito(cartaoCredito);
                    pagamento.setValor(valor);
                    pagamento.setDataInicio(dataInicio);
                    pagamento.setDataVencimento(dataVencimento);
                    pagamento.setNumeroMesesAutorizados(numeroMesesAutorizados);
                    pagamento.setDataCriacao(dataCriacao);
                    pagamento.setDataModificacao(dataModificacao);

                    return pagamento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamento recorrente: " + e.getMessage());
        }
        return null;
    }
}
