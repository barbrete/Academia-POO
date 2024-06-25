/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kitotsui e Barbrete
 */
public class TreinoAplicacaoDAO {

    public boolean adiciona(TreinoAplicacao treinoAplicacao) {
        String sql = "INSERT INTO TreinoAplicacao (pessoa_id, academia_id, treino_id, exercicio_id, exAplicacao_id, divTreino_id, divTreinoMusc_id, dataCriacao, dataModificacao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, treinoAplicacao.getPessoa().getId());
            stmt.setLong(2, treinoAplicacao.getAcademia().getId());
            stmt.setLong(3, treinoAplicacao.getTreino().getId());
            stmt.setLong(4, treinoAplicacao.getExercicio().getId());
            stmt.setLong(5, treinoAplicacao.getExAplicacao().getId());
            stmt.setLong(6, treinoAplicacao.getDivTreino().getId());
            stmt.setLong(7, treinoAplicacao.getDivTreinoMusc().getId());
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(treinoAplicacao.getDataCriacao()));
            stmt.setTimestamp(9, java.sql.Timestamp.valueOf(treinoAplicacao.getDataModificacao()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO CRIAR FICHA DE TREINO " + e.getMessage());
        }
    }

    public TreinoAplicacao buscaPorId(long id) {
        String sql = "SELECT * FROM TreinoAplicacao WHERE id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocalDateTime dataCriacao = rs.getTimestamp("dataCriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("dataModificacao").toLocalDateTime();
                    long pessoaId = rs.getLong("pessoa_id");
                    long academiaId = rs.getLong("academia_id");
                    long treinoId = rs.getLong("treino_id");
                    long exercicioId = rs.getLong("exercicio_id");
                    long exAplicacaoId = rs.getLong("exAplicacao_id");
                    long divTreinoId = rs.getLong("divTreino_id");
                    long divTreinoMuscId = rs.getLong("divTreinoMusc_id");

                    Pessoa pessoa = new PessoaDAO().buscaPorId(pessoaId);
                    Academia academia = new AcademiaDAO().buscaPorId(academiaId);
                    Treino treino = new TreinoDAO().buscaPorId(treinoId);
                    Exercicio exercicio = new ExercicioDAO().buscaPorId(exercicioId);
                    ExercicioAplicacao exAplicacao = new ExercicioAplicacaoDAO().buscaPorId(exAplicacaoId);
                    DivisaoTreino divTreino = new DivisaoTreinoDAO().buscaPorId(divTreinoId);
                    DivisaoTreinoMusculo divTreinoMusc = new DivisaoTreinoMusculoDAO().buscaPorId(divTreinoMuscId);

                    TreinoAplicacao treinoAplicacao = new TreinoAplicacao();
                    treinoAplicacao.setId(id);
                    treinoAplicacao.setDataCriacao(dataCriacao);
                    treinoAplicacao.setDataModificacao(dataModificacao);
                    treinoAplicacao.setPessoa(pessoa);
                    treinoAplicacao.setAcademia(academia);
                    treinoAplicacao.setTreino(treino);
                    treinoAplicacao.setExercicio(exercicio);
                    treinoAplicacao.setExAplicacao(exAplicacao);
                    treinoAplicacao.setDivTreino(divTreino);
                    treinoAplicacao.setDivTreinoMusc(divTreinoMusc);

                    return treinoAplicacao;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR FICHA DE TREINO POR ID: " + e.getMessage());
        }
        return null;
    }

    public List<TreinoAplicacao> listarTodos() {
        String sql = "SELECT * FROM TreinoAplicacao";
        List<TreinoAplicacao> treinoAplicacoes = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                LocalDateTime dataCriacao = rs.getTimestamp("dataCriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("dataModificacao").toLocalDateTime();
                long pessoaId = rs.getLong("pessoa_id");
                long academiaId = rs.getLong("academia_id");
                long treinoId = rs.getLong("treino_id");
                long exercicioId = rs.getLong("exercicio_id");
                long exAplicacaoId = rs.getLong("exAplicacao_id");
                long divTreinoId = rs.getLong("divTreino_id");
                long divTreinoMuscId = rs.getLong("divTreinoMusc_id");

                Pessoa pessoa = new PessoaDAO().buscaPorId(pessoaId);
                Academia academia = new AcademiaDAO().buscaPorId(academiaId);
                Treino treino = new TreinoDAO().buscaPorId(treinoId);
                Exercicio exercicio = new ExercicioDAO().buscaPorId(exercicioId);
                ExercicioAplicacao exAplicacao = new ExercicioAplicacaoDAO().buscaPorId(exAplicacaoId);
                DivisaoTreino divTreino = new DivisaoTreinoDAO().buscaPorId(divTreinoId);
                DivisaoTreinoMusculo divTreinoMusc = new DivisaoTreinoMusculoDAO().buscaPorId(divTreinoMuscId);

                TreinoAplicacao treinoAplicacao = new TreinoAplicacao();
                treinoAplicacao.setId(id);
                treinoAplicacao.setDataCriacao(dataCriacao);
                treinoAplicacao.setDataModificacao(dataModificacao);
                treinoAplicacao.setPessoa(pessoa);
                treinoAplicacao.setAcademia(academia);
                treinoAplicacao.setTreino(treino);
                treinoAplicacao.setExercicio(exercicio);
                treinoAplicacao.setExAplicacao(exAplicacao);
                treinoAplicacao.setDivTreino(divTreino);
                treinoAplicacao.setDivTreinoMusc(divTreinoMusc);

                treinoAplicacoes.add(treinoAplicacao);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR FICHAS DE TREINO: " + e.getMessage());
        }

        return treinoAplicacoes;
    }

    public TreinoAplicacao alterar(TreinoAplicacao treinoAplicacao) {
        String sql = "UPDATE treinoaplicacao SET pessoa_id = ?, academia_id = ?, treino_id = ?, exercicio_id = ?, exAplicacao_id = ?, divTreino_id = ?, divTreinoMusc_id = ?, dataModificacao = ? WHERE id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, treinoAplicacao.getPessoa().getId());
            stmt.setLong(2, treinoAplicacao.getAcademia().getId());
            stmt.setLong(3, treinoAplicacao.getTreino().getId());
            stmt.setLong(4, treinoAplicacao.getExercicio().getId());
            stmt.setLong(5, treinoAplicacao.getExAplicacao().getId());
            stmt.setLong(6, treinoAplicacao.getDivTreino().getId());
            stmt.setLong(7, treinoAplicacao.getDivTreinoMusc().getId());
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(treinoAplicacao.getDataModificacao()));
            stmt.setLong(9, treinoAplicacao.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                return treinoAplicacao;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR FICHA DE TREINO " + e.getMessage());
        }

        return null;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM TreinoAplicacao WHERE id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER FICHA DE TREINO " + e.getMessage());
        }
    }
}
