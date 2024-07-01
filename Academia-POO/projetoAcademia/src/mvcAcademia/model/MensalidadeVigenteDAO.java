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
 * @autor barbrete e kitotsui
 */
public class MensalidadeVigenteDAO {

    public boolean adiciona(MensalidadeVigente mensalidade) {
        String sql = "insert into mensalidadevigente (valor, datainicio, datatermino, datacriacao, datamodificacao) values (?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, mensalidade.getValor());
            stmt.setDate(2, java.sql.Date.valueOf(mensalidade.getDataInicio()));
            stmt.setDate(3, java.sql.Date.valueOf(mensalidade.getDataTermino()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(mensalidade.getDataCriacao()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(mensalidade.getDataModificacao()));

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR MENSALIDADE VIGENTE: " + e.getMessage());
        }
    }

    public List<MensalidadeVigente> lista() {
        String sql = "select * from mensalidadevigente";
        List<MensalidadeVigente> mensalidades = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idmensalidadevigente");
                double valor = rs.getDouble("valor");
                LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("datatermino").toLocalDate();
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                MensalidadeVigente mensalidade = new MensalidadeVigente();
                mensalidade.setId(id);
                mensalidade.setValor(valor);
                mensalidade.setDataInicio(dataInicio);
                mensalidade.setDataTermino(dataTermino);
                mensalidade.setDataCriacao(dataCriacao);
                mensalidade.setDataModificacao(dataModificacao);

                mensalidades.add(mensalidade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mensalidades;
    }

    public MensalidadeVigente buscaPorId(long id) {
        String sql = "select * from mensalidadevigente where idmensalidadevigente = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    double valor = rs.getDouble("valor");
                    LocalDate dataInicio = rs.getDate("datainicio").toLocalDate();
                    LocalDate dataTermino = rs.getDate("datatermino").toLocalDate();
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    MensalidadeVigente mensalidade = new MensalidadeVigente();
                    mensalidade.setId(id);
                    mensalidade.setValor(valor);
                    mensalidade.setDataInicio(dataInicio);
                    mensalidade.setDataTermino(dataTermino);
                    mensalidade.setDataCriacao(dataCriacao);
                    mensalidade.setDataModificacao(dataModificacao);

                    return mensalidade;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public MensalidadeVigente alterar(MensalidadeVigente mensalidade) {
        String sql = "update mensalidadevigente set valor = ?, datainicio = ?, datatermino = ?, datamodificacao = ? where idmensalidadevigente = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, mensalidade.getValor());
            stmt.setDate(2, java.sql.Date.valueOf(mensalidade.getDataInicio()));
            stmt.setDate(3, java.sql.Date.valueOf(mensalidade.getDataTermino()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(mensalidade.getDataModificacao()));
            stmt.setLong(5, mensalidade.getId());

            stmt.execute();
            

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR MENSALIDADE VIGENTE: " + e.getMessage());
        }
        return mensalidade;
    }

    public boolean remover (long id) {
        String sql = "delete from mensalidadevigente where idmensalidadevigente = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasDeletadas = stmt.executeUpdate();
            return linhasDeletadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER MENSALIDADE VIGENTE: " + e.getMessage());
        }
    }
    
    
}
