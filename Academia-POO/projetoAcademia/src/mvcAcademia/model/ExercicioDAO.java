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
 * @author barbrete e kitotsui
 */
public class ExercicioDAO {

    public boolean adiciona(Exercicio exercicio) {
        String sql = "INSERT INTO exercicio (nome, descricao, datacriacao, datamodificacao ) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getDescricao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(exercicio.getDataCriacao()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(exercicio.getDataModificacao()));

            stmt.execute();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR EXERCICIO: " + e.getMessage());
        }
    }

    public Exercicio buscaPorId(long id) {
        String sql = "SELECT * FROM exercicio WHERE idexercicio = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    Exercicio exercicio = new Exercicio();
                    exercicio.setId(id);
                    exercicio.setNome(nome);
                    exercicio.setDescricao(descricao);
                    exercicio.setDataCriacao(dataCriacao);
                    exercicio.setDataModificacao(dataModificacao);

                    return exercicio;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR EXERCICIO POR ID: " + e.getMessage());
        }
        return null;
    }

    public Exercicio alterar(Exercicio exercicio) {
        String sql = "update exercicio set nome = ?, descricao = ?, datamodificacao = ? where idexercicio = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getDescricao());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(exercicio.getDataModificacao()));
            stmt.setLong(4, exercicio.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR EXERCICIO: " + e.getMessage());
        }
        return exercicio;
    }
    
    
    public boolean remover(long id) {
        String sql = "DELETE FROM exercicio WHERE idexercicio = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER EXERCICIO: " + e.getMessage());
        }
        return false;
    }
    
    public List<Exercicio> lista() {
        String sql = "SELECT * FROM exercicio";
        List<Exercicio> exercicios = new ArrayList<>(); 

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("idexercicio");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");  
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                Exercicio e = new Exercicio();
                e.setId(id);
                e.setNome(nome);
                e.setDescricao(descricao);
                e.setDataCriacao(dataCriacao);
                e.setDataModificacao(dataModificacao);

                exercicios.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO MOSTRAR TODAS AS PESSOAS: " + e.getMessage());
        }
        return exercicios; 
    }

}
