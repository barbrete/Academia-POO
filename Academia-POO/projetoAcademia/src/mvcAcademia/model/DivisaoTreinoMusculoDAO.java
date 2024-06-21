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
 * @author barbrete e kitotsui
 */
public class DivisaoTreinoMusculoDAO {

    public boolean adiciona(DivisaoTreinoMusculo divisaoMusculo) {
        String sql = "INSERT INTO divisaotreinomusculo (nome, descricao, datacriacao, datamodificacao, iddivisaotreino) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, divisaoMusculo.getNome());
            stmt.setString(2, divisaoMusculo.getDescricao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(divisaoMusculo.getDataCriacao()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(divisaoMusculo.getDataModificacao()));
            stmt.setLong(5, divisaoMusculo.getDivisaoTreino().getId());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR DIVISÃO DE TREINO MUSCULO: " + e.getMessage());
        }
    }

    public DivisaoTreinoMusculo buscaPorId(long id) {
        String sql = "SELECT * FROM divisaotreinomusculo WHERE iddivisaotreinomusculo = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();
                    long idDivisaoTreino = rs.getLong("iddivisaotreino");

                    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
                    DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                    DivisaoTreinoMusculo divisaoMusculo = new DivisaoTreinoMusculo();
                    divisaoMusculo.setId(id);
                    divisaoMusculo.setNome(nome);
                    divisaoMusculo.setDescricao(descricao);
                    divisaoMusculo.setDataCriacao(dataCriacao);
                    divisaoMusculo.setDataModificacao(dataModificacao);
                    divisaoMusculo.setDivisaoTreino(divisaoTreino);

                    return divisaoMusculo;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR DIVISÃO DE TREINO MUSCULO POR ID: " + e.getMessage());
        }

        return null;
    }

    public DivisaoTreinoMusculo alterar(DivisaoTreinoMusculo divisaoMusculo) {
        String sql = "UPDATE divisaotreinomusculo SET nome = ?, descricao = ?, datamodificacao = ?, iddivisaotreino = ? WHERE iddivisaotreinomusculo = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, divisaoMusculo.getNome());
            stmt.setString(2, divisaoMusculo.getDescricao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(divisaoMusculo.getDataModificacao()));
            stmt.setLong(4, divisaoMusculo.getDivisaoTreino().getId());
            stmt.setLong(5, divisaoMusculo.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                return divisaoMusculo;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR DIVISÃO DE TREINO MUSCULO: " + e.getMessage());
        }

        return null;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM divisaotreinomusculo WHERE iddivisaotreinomusculo = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER DIVISÃO DE TREINO MUSCULO: " + e.getMessage());
        }
    }

    public List<DivisaoTreinoMusculo> lista() {
        String sql = "SELECT * FROM divisaotreinomusculo";
        List<DivisaoTreinoMusculo> divisoesMusculo = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("iddivisaotreinomusculo");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();
                long idDivisaoTreino = rs.getLong("iddivisaotreino");

                DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
                DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                DivisaoTreinoMusculo divisaoMusculo = new DivisaoTreinoMusculo();
                divisaoMusculo.setId(id);
                divisaoMusculo.setNome(nome);
                divisaoMusculo.setDescricao(descricao);
                divisaoMusculo.setDataCriacao(dataCriacao);
                divisaoMusculo.setDataModificacao(dataModificacao);
                divisaoMusculo.setDivisaoTreino(divisaoTreino);

                divisoesMusculo.add(divisaoMusculo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR DIVISÕES DE TREINO MUSCULO: " + e.getMessage());
        }

        return divisoesMusculo;
    }
    
}
