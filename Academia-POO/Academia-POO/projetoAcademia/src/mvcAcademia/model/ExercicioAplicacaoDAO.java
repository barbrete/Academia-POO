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
 * @autor barbrete e kitotsui
 */
public class ExercicioAplicacaoDAO {

    public boolean adiciona(ExercicioAplicacao exAplic) {
        String sql = "INSERT INTO exercicioaplicacao (descricao, datacriacao, datamodificacao) "
                + "VALUES (?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, exAplic.getDescricao());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(exAplic.getDataCriacao()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(exAplic.getDataModificacao()));

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR APLICACAO DE EXERCICIO: " + e.getMessage());
        }
    }

    public ExercicioAplicacao buscaPorId(long id) {
        String sql = "SELECT * FROM exercicioaplicacao WHERE idexercicioaplicacao = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String descricao = rs.getString("descricao");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    ExercicioAplicacao exAplic = new ExercicioAplicacao();
                    exAplic.setId(id);
                    exAplic.setDescricao(descricao);
                    exAplic.setDataCriacao(dataCriacao);
                    exAplic.setDataModificacao(dataModificacao);

                    return exAplic;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR ID DE APLICACAO DE EXERCICIO: " + e.getMessage());
        }
        return null;
    }

    public ExercicioAplicacao alterar(ExercicioAplicacao exAplic) {
        String sql = "UPDATE exercicioaplicacao SET descricao = ?, datamodificacao = ? WHERE idexercicioaplicacao = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, exAplic.getDescricao());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(exAplic.getDataModificacao()));
            stmt.setLong(3, exAplic.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                return exAplic;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR APLICACAO DE EXERCICIO: " + e.getMessage());
        }
        return null;
    }

    // Método para remover um ExercicioAplicacao do banco de dados pelo ID
    public boolean remover(long id) {
        String sql = "DELETE FROM exercicioaplicacao WHERE idexercicioaplicacao = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER APLICACAO DE EXERCICIO: " + e.getMessage());
        }
    }

    public List<ExercicioAplicacao> lista() {
        String sql = "SELECT * FROM exercicioaplicacao";
        List<ExercicioAplicacao> exercicioAplicacoes = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idexercicioaplicacao");
                String descricao = rs.getString("descricao");
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                ExercicioAplicacao exAplic = new ExercicioAplicacao();
                exAplic.setId(id);
                exAplic.setDescricao(descricao);
                exAplic.setDataCriacao(dataCriacao);
                exAplic.setDataModificacao(dataModificacao);

                exercicioAplicacoes.add(exAplic);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO MOSTRAR LISTA DE APLICAO DE EXERCICIOS: " + e.getMessage());
        }
        return exercicioAplicacoes;
    }
}
