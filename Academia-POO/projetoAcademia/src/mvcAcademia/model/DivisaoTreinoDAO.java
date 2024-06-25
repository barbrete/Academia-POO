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
public class DivisaoTreinoDAO {

    public boolean adiciona(DivisaoTreino divisao) {
        String sql = "INSERT INTO divisaotreino (nome, descricao, datacriacao, datamodificacao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, divisao.getNome());
            stmt.setString(2, divisao.getDescricao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(divisao.getDataCriacao()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(divisao.getDataModificacao()));

            int ColunaInserida = stmt.executeUpdate();

            return ColunaInserida > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR DIVISÃO DE TREINO: " + e.getMessage());
        }
    }

    public DivisaoTreino buscaPorId(long id) {
        String sql = "SELECT * FROM divisaotreino WHERE iddivisaotreino = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    DivisaoTreino divisao = new DivisaoTreino();
                    divisao.setId(id);
                    divisao.setNome(nome);
                    divisao.setDescricao(descricao);
                    divisao.setDataCriacao(dataCriacao);
                    divisao.setDataModificacao(dataModificacao);

                    return divisao;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR DIVISÃO DE TREINO POR ID: " + e.getMessage());
        }

        return null;
    }

    public DivisaoTreino alterar(DivisaoTreino divisao) {
        String sql = "UPDATE divisaotreino SET nome = ?, descricao = ?, datamodificacao = ? WHERE iddivisaotreino = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, divisao.getNome());
            stmt.setString(2, divisao.getDescricao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(divisao.getDataModificacao()));
            stmt.setLong(4, divisao.getId());

            int linhaAtt = stmt.executeUpdate();

            if (linhaAtt > 0) {
                return divisao;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR DIVISÃO DE TREINO: " + e.getMessage());
        }

        return null;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM divisaotreino WHERE iddivisaotreino = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhaDeletada = stmt.executeUpdate();

            return linhaDeletada > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER DIVISÃO DE TREINO: " + e.getMessage());
        }
    }

    public List<DivisaoTreino> lista() {
        String sql = "SELECT * FROM divisaotreino";
        List<DivisaoTreino> divisoes = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("iddivisaotreino");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                DivisaoTreino divisao = new DivisaoTreino();
                divisao.setId(id);
                divisao.setNome(nome);
                divisao.setDescricao(descricao);
                divisao.setDataCriacao(dataCriacao);
                divisao.setDataModificacao(dataModificacao);

                divisoes.add(divisao);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR DIVISÕES DE TREINO: " + e.getMessage());
        }

        return divisoes;
    }
}
