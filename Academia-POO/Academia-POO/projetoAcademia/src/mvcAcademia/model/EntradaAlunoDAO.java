package mvcAcademia.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntradaAlunoDAO {

/**
 *
 * @author barbrete e kitotsui
 */
    public boolean adiciona(EntradaAluno entradaAluno) {
        String sql = "INSERT INTO entradaluno (dataentrada, datacriacao, datamodificacao ) "
                + "VALUES (?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

           // stmt.setTimestamp(1, entradaAluno.getDataEntrada());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(entradaAluno.getDataCriacao()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(entradaAluno.getDataModificacao()));

            stmt.execute();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR ENTRADA ALUNO: " + e.getMessage());
        }
    }

    public EntradaAluno buscaPorId(long id) {
        String sql = "SELECT * FROM entradaluno WHERE identradaaluno = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocalDateTime dataEntrada = rs.getTimestamp("dataentrada").toLocalDateTime();
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    EntradaAluno ea = new EntradaAluno();
                    ea.setId(id);
                    ea.setDataEntrada(dataEntrada);
                    ea.setDataCriacao(dataCriacao);
                    ea.setDataModificacao(dataModificacao);

                    return ea;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR ENTRADA DE ALUNO POR ID: " + e.getMessage());
        }
        return null;
    }

    public EntradaAluno alterar(EntradaAluno entradaaluno) {
        String sql = "update entradaaluno set dataentrada = ?, datamodificacao = ? where identradaaluno = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(entradaaluno.getDataEntrada()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(entradaaluno.getDataModificacao()));
            stmt.setLong(4, entradaaluno.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR ENTRADA DE ALUNO: " + e.getMessage());
        }
        return entradaaluno;
    }
    
    
    public boolean remover(long id) {
        String sql = "DELETE FROM entradaaluno WHERE identradaaluno = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER ENTRADA DO ALUNO: " + e.getMessage());
        }
        return false;
    }
    
    public List<EntradaAluno> lista() {
        String sql = "SELECT * FROM entradaaluno";
        List<EntradaAluno> entradaalunos = new ArrayList<>(); 

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("identradaaluno");
                LocalDateTime dataEntrada = rs.getTimestamp("dataentrada").toLocalDateTime();
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                EntradaAluno ea = new EntradaAluno();
                ea.setId(id);
                ea.setDataEntrada(dataEntrada);
                ea.setDataCriacao(dataCriacao);
                ea.setDataModificacao(dataModificacao);

                entradaalunos.add(ea);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO MOSTRAR TODAS AS ENTRADAS DOS ALUNOS: " + e.getMessage());
        }
        return entradaalunos; 
    }

}
