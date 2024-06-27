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
        String sql = "INSERT INTO alunopagamentomensalidade (mv_aluno_pagamento_id, data_vencimento, data_pagamento, valor_pago, aluno_id, modalidade, data_criacao, data_modificacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                long id = rs.getLong("id_aluno_pagamento_mensalidade");
                long mvAlunoPagamentoId = rs.getLong("mv_aluno_pagamento_id");
                LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();
                LocalDate dataPagamento = rs.getDate("data_pagamento").toLocalDate();
                double valorPago = rs.getDouble("valor_pago");
                long alunoId = rs.getLong("aluno_id");
                int modalidade = rs.getInt("modalidade");
                LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

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
        String sql = "SELECT * FROM alunopagamentomensalidade WHERE id_aluno_pagamento_mensalidade = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long mvAlunoPagamentoId = rs.getLong("mv_aluno_pagamento_id");
                    LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();
                    LocalDate dataPagamento = rs.getDate("data_pagamento").toLocalDate();
                    double valorPago = rs.getDouble("valor_pago");
                    long alunoId = rs.getLong("aluno_id");
                    int modalidade = rs.getInt("modalidade");
                    LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

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
        String sql = "UPDATE alunopagamentomensalidade SET mv_aluno_pagamento_id = ?, data_vencimento = ?, data_pagamento = ?, valor_pago = ?, aluno_id = ?, modalidade = ?, data_modificacao = ? WHERE id_aluno_pagamento_mensalidade = ?";

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
        String sql = "DELETE FROM alunopagamentomensalidade WHERE id_aluno_pagamento_mensalidade = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasDeletadas = stmt.executeUpdate();
            return linhasDeletadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER ALUNO PAGAMENTO MENSALIDADE: " + e.getMessage());
        }
    }

    public AlunoPagamentoMensalidade buscarUltimoPagamentoDoAluno(long alunoId) {
        String sql = "SELECT * FROM alunopagamentomensalidade WHERE aluno_id = ? ORDER BY data_vencimento DESC LIMIT 1";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, alunoId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("id_aluno_pagamento_mensalidade");
                    long mvAlunoPagamentoId = rs.getLong("mv_aluno_pagamento_id");
                    LocalDate dataVencimento = rs.getDate("data_vencimento").toLocalDate();
                    LocalDate dataPagamento = rs.getDate("data_pagamento") != null ? rs.getDate("data_pagamento").toLocalDate() : null;
                    double valorPago = rs.getDouble("valor_pago");
                    int modalidade = rs.getInt("modalidade");
                    LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

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
            throw new RuntimeException("ERRO AO BUSCAR ÚLTIMO PAGAMENTO DO ALUNO: " + e.getMessage());
        }

        return null;
    }

}
