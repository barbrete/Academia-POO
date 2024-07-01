/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barbrete e kitotsui
 */
public class TreinoDAO {
        
    public boolean adiciona(Treino treino) {
        String sql = "INSERT INTO treino (nome, objetivo, datainicio, datatermino, datacriacao, datamodificacao) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, treino.getNome());
            stmt.setString(2, treino.getObjetivo());
            stmt.setDate(3, java.sql.Date.valueOf(treino.getDataInicio()));
            stmt.setDate(4, java.sql.Date.valueOf(treino.getDataTermino()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(treino.getDataCriacao()));
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(treino.getDataModificacao()));
            //stmt.setLong(7, treino.getDivTreino().getId());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR TREINO: " + e.getMessage());
        }
    }

    public Treino buscaPorId(long id) {
        String sql = "SELECT * FROM treino WHERE idtreino = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String objetivo = rs.getString("objetivo");
                    LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                    LocalDate dataTermino = rs.getDate("datatermino").toLocalDate();
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();
                    long idDivisaoTreino = rs.getLong("iddivisaotreino");

                    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
                    DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                    Treino treino = new Treino();
                    treino.setId(id);
                    treino.setNome(nome);
                    treino.setObjetivo(objetivo);
                    treino.setDataInicio(dataInicio);
                    treino.setDataTermino(dataTermino);
                    treino.setDataCriacao(dataCriacao);
                    treino.setDataModificacao(dataModificacao);
                    treino.setDivTreino(divisaoTreino);

                    return treino;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR TREINO POR ID: " + e.getMessage());
        }

        return null;
    }

    public Treino alterar(Treino treino) {
        String sql = "UPDATE treino SET nome = ?, objetivo = ?, datainicio = ?, datatermino = ?, datamodificacao = ?, iddivisaotreino = ? WHERE idtreino = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, treino.getNome());
            stmt.setString(2, treino.getObjetivo());
            stmt.setDate(3, java.sql.Date.valueOf(treino.getDataInicio()));
            stmt.setDate(4, java.sql.Date.valueOf(treino.getDataTermino()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(treino.getDataModificacao()));
            stmt.setLong(6, treino.getDivTreino().getId());
            stmt.setLong(7, treino.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                return treino;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR TREINO: " + e.getMessage());
        }

        return null;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM treino WHERE idtreino = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER TREINO: " + e.getMessage());
        }
    }

    public List<Treino> lista() {
        String sql = "SELECT * FROM treino";
        List<Treino> treinos = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idtreino");
                String nome = rs.getString("nome");
                String objetivo = rs.getString("objetivo");
                LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("datatermino").toLocalDate();
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();
                long idDivisaoTreino = rs.getLong("iddivisaotreino");

                DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
                DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                Treino treino = new Treino();
                treino.setId(id);
                treino.setNome(nome);
                treino.setObjetivo(objetivo);
                treino.setDataInicio(dataInicio);
                treino.setDataTermino(dataTermino);
                treino.setDataCriacao(dataCriacao);
                treino.setDataModificacao(dataModificacao);
                treino.setDivTreino(divisaoTreino);

                treinos.add(treino);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR TREINOS: " + e.getMessage());
        }

        return treinos;
    }
   
}
