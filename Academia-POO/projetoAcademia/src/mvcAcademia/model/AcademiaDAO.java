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
public class AcademiaDAO {

    public Academia adiciona(Academia academia) {
        String sql = "insert into academia" + "(nome, endereco, datacriacao, datamodificacao)"
                + "values (?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, academia.getNome());
            stmt.setString(2, academia.getEndereco());
            stmt.setDate(3, java.sql.Date.valueOf(academia.getDataCriacao().toLocalDate()));
            stmt.setDate(4, java.sql.Date.valueOf(academia.getDataModificacao().toLocalDate()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return academia;
    }

    public List<Academia> lista(Academia academia) {
        String sql = "select *from academia";
        List<Academia> academias = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                long id = rs.getLong("idacademia");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                //String dataCriacao = rs.getString("datacriacao");
                //String dataModificacao = rs.getString("datamodificacao");

                Academia acad = new Academia();
                acad.setNome(nome);
                acad.setEndereco(endereco);
                //acad.setDataCriacao(dataCriacao);
                //acad.setDataModificacao(dataModificacao);

                academias.add(academia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return academias;
    }

   public void mostrarTodasAcademias() {
    List <Academia> academias = lista(null);
    for (Academia academia : academias) {
        System.out.println(academia);
    }
  }

}
