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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barbrete e kitotsui
 */
public class AcademiaDAO {

    public Academia adiciona(Academia academia) {
        String sql = "insert into academia" + "(nome, endereco, datacriacao, datamodificacao)"
                + "values (?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(academia.getDataCriacao()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(academia.getDataModificacao()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return academia;
    }

    public List<Academia> lista(Academia academia) {
        String sql = "select * from academia";
        List<Academia> academias = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idacademia");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                Timestamp timestampCriacao = rs.getTimestamp("datacriacao");
                LocalDateTime dataCriacao = timestampCriacao.toLocalDateTime();
                Timestamp timestampModificacao = rs.getTimestamp("datamodificacao");
                LocalDateTime dataModificacao = timestampModificacao.toLocalDateTime();

                Academia acad = new Academia();
                acad.setId(id);
                acad.setNome(nome);
                acad.setEndereco(endereco);
                acad.setDataCriacao(dataCriacao);
                acad.setDataModificacao(dataModificacao);

                academias.add(acad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return academias;
    }

}
