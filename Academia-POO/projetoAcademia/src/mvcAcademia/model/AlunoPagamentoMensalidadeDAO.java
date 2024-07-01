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
 * @author barbrete e kitotsui
 */
public class AlunoPagamentoMensalidadeDAO {

    public boolean adiciona(AlunoPagamentoMensalidade apm) {
        String sql = "INSERT INTO alunopagamentomensalidade (id_mensalidadevigente, datavencimento, datapagamento, valorpago, id_pessoa, modalidadepagamento, datacriacao, datamodificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, apm.getMvAlunoPagamento().getId());
            stmt.setDate(2, java.sql.Date.valueOf(apm.getDataVencimento()));
            stmt.setDate(3, java.sql.Date.valueOf(apm.getDataPagamento()));
            stmt.setDouble(4, apm.getValorPago());
            stmt.setLong(5, apm.getAluno().getId());
            stmt.setInt(6, apm.getModalidade());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(apm.getDataCriacao()));
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(apm.getDataModificacao()));

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR ALUNO PAGAMENTO MENSALIDADE: " + e.getMessage());
        }
    }

    public List<AlunoPagamentoMensalidade> lista() {
        String sql = "SELECT * FROM alunopagamentomensalidade";
        List<AlunoPagamentoMensalidade> pagamentos = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idalunopagamentomensalidade");
                long mvAlunoPagamentoId = rs.getLong("id_mensalidadevigente");
                LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                LocalDate dataPagamento = rs.getDate("datapagamento").toLocalDate();
                double valorPago = rs.getDouble("valorpago");
                long alunoId = rs.getLong("id_pessoa");
                int modalidade = rs.getInt("modalidadepagamento");
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                // Buscar objetos relacionados
                MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();
                PessoaDAO pessoaDAO = new PessoaDAO();

                MensalidadeVigente mvAlunoPagamento = mvDAO.buscaPorId(mvAlunoPagamentoId);
                Pessoa aluno = pessoaDAO.buscaPorId(alunoId);

                AlunoPagamentoMensalidade pagamento = new AlunoPagamentoMensalidade();
                pagamento.setId(id);
                pagamento.setMvAlunoPagamento(mvAlunoPagamento);
                pagamento.setDataVencimento(dataVencimento);
                pagamento.setDataPagamento(dataPagamento);
                pagamento.setValorPago(valorPago);
                pagamento.setAluno(aluno);
                pagamento.setModalidade(modalidade);
                pagamento.setDataCriacao(dataCriacao);
                pagamento.setDataModificacao(dataModificacao);

                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR ALUNO PAGAMENTO MENSALIDADE: " + e.getMessage());
        }
        return pagamentos;
    }

    public AlunoPagamentoMensalidade buscaPorId(long id) {
        String sql = "SELECT * FROM alunopagamentomensalidade WHERE idalunopagamentomensalidade = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long mvAlunoPagamentoId = rs.getLong("id_mensalidadevigente");
                    LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                    LocalDate dataPagamento = rs.getDate("datapagamento").toLocalDate();
                    double valorPago = rs.getDouble("valorpago");
                    long alunoId = rs.getLong("id_pessoa");
                    int modalidade = rs.getInt("modalidadepagamento");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    // Buscar objetos relacionados
                    MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();
                    PessoaDAO pessoaDAO = new PessoaDAO();

                    MensalidadeVigente mvAlunoPagamento = mvDAO.buscaPorId(mvAlunoPagamentoId);
                    Pessoa aluno = pessoaDAO.buscaPorId(alunoId);

                    AlunoPagamentoMensalidade pagamento = new AlunoPagamentoMensalidade();
                    pagamento.setId(id);
                    pagamento.setMvAlunoPagamento(mvAlunoPagamento);
                    pagamento.setDataVencimento(dataVencimento);
                    pagamento.setDataPagamento(dataPagamento);
                    pagamento.setValorPago(valorPago);
                    pagamento.setAluno(aluno);
                    pagamento.setModalidade(modalidade);
                    pagamento.setDataCriacao(dataCriacao);
                    pagamento.setDataModificacao(dataModificacao);

                    return pagamento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR ALUNO PAGAMENTO MENSALIDADE POR ID: " + e.getMessage());
        }
        return null;
    }

    public AlunoPagamentoMensalidade alterar(AlunoPagamentoMensalidade apm) {
        String sql = "UPDATE alunopagamentomensalidade SET id_mensalidadevigente = ?, datavencimento = ?, datapagamento = ?, valorpago = ?, id_pessoa = ?, modalidadepagamento = ?, datamodificacao = ? WHERE idalunopagamentomensalidade = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, apm.getMvAlunoPagamento().getId());
            stmt.setDate(2, java.sql.Date.valueOf(apm.getDataVencimento()));
            stmt.setDate(3, java.sql.Date.valueOf(apm.getDataPagamento()));
            stmt.setDouble(4, apm.getValorPago());
            stmt.setLong(5, apm.getAluno().getId());
            stmt.setInt(6, apm.getModalidade());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(apm.getDataModificacao()));
            stmt.setLong(8, apm.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR ALUNO PAGAMENTO MENSALIDADE: " + e.getMessage());
        }
        return apm;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM alunopagamentomensalidade WHERE idalunopagamentomensalidade = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasDeletadas = stmt.executeUpdate();
            return linhasDeletadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER ALUNO PAGAMENTO MENSALIDADE: " + e.getMessage());
        }
    }

    public AlunoPagamentoMensalidade buscarUltimoPagamentoDoAluno(long alunoId) {
        String sql = "SELECT * FROM alunopagamentomensalidade WHERE id_pessoa = ? ORDER BY datavencimento DESC LIMIT 1";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, alunoId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("idalunopagamentomensalidade");
                    long mvAlunoPagamentoId = rs.getLong("id_mensalidadevigente");
                    LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                    LocalDate dataPagamento = rs.getDate("datapagamento") != null ? rs.getDate("datapagamento").toLocalDate() : null;
                    double valorPago = rs.getDouble("valorpago");
                    int modalidade = rs.getInt("modalidadepagamento");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();
                    PessoaDAO pessoaDAO = new PessoaDAO();

                    MensalidadeVigente mvAlunoPagamento = mvDAO.buscaPorId(mvAlunoPagamentoId);
                    Pessoa aluno = pessoaDAO.buscaPorId(alunoId);

                    AlunoPagamentoMensalidade pagamento = new AlunoPagamentoMensalidade();
                    pagamento.setId(id);
                    pagamento.setMvAlunoPagamento(mvAlunoPagamento);
                    pagamento.setDataVencimento(dataVencimento);
                    pagamento.setDataPagamento(dataPagamento);
                    pagamento.setValorPago(valorPago);
                    pagamento.setAluno(aluno);
                    pagamento.setModalidade(modalidade);
                    pagamento.setDataCriacao(dataCriacao);
                    pagamento.setDataModificacao(dataModificacao);

                    return pagamento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR ÚLTIMO PAGAMENTO DO ALUNO: " + e.getMessage());
        }

        return null;
    }

    public List<AlunoPagamentoMensalidade> mostrarAdimplentesNoMesEAno(int mes, int ano) {
        List<AlunoPagamentoMensalidade> adimplentes = new ArrayList<>();
        String sql = "SELECT * FROM alunopagamentomensalidade WHERE MONTH(datapagamento) = ? AND YEAR(datapagamento) = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, mes);
            stmt.setInt(2, ano);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("idalunopagamentomensalidade");
                    long mvAlunoPagamentoId = rs.getLong("id_mensalidadevigente");
                    LocalDate dataVencimento = rs.getDate("datavencimento").toLocalDate();
                    LocalDate dataPagamento = rs.getDate("datapagamento").toLocalDate();
                    double valorPago = rs.getDouble("valorpago");
                    long alunoId = rs.getLong("id_pessoa");
                    int modalidade = rs.getInt("modalidadepagamento");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    // Buscar objetos relacionados
                    MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();
                    PessoaDAO pessoaDAO = new PessoaDAO();

                    MensalidadeVigente mvAlunoPagamento = mvDAO.buscaPorId(mvAlunoPagamentoId);
                    Pessoa aluno = pessoaDAO.buscaPorId(alunoId);

                    AlunoPagamentoMensalidade pagamento = new AlunoPagamentoMensalidade();
                    pagamento.setId(id);
                    pagamento.setMvAlunoPagamento(mvAlunoPagamento);
                    pagamento.setDataVencimento(dataVencimento);
                    pagamento.setDataPagamento(dataPagamento);
                    pagamento.setValorPago(valorPago);
                    pagamento.setAluno(aluno);
                    pagamento.setModalidade(modalidade);
                    pagamento.setDataCriacao(dataCriacao);
                    pagamento.setDataModificacao(dataModificacao);

                    adimplentes.add(pagamento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR ALUNOS ADIMPLENTES: " + e.getMessage());
        }

        return adimplentes;
    }

}
