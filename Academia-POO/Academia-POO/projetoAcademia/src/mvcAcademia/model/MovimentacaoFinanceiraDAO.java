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
 * @author barbrete
 */

public class MovimentacaoFinanceiraDAO { 

    public boolean adiciona(MovimentacaoFinanceira movimentacaoFinanceira) {
        String sql = "insert into movimentacao_financeira (tipo, descricao, valor, data_criacao, data_modificacao) values (?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, movimentacaoFinanceira.getTipo());
            stmt.setString(2, movimentacaoFinanceira.getDescricao());
            stmt.setDouble(3, movimentacaoFinanceira.getValor());
            stmt.setTimestamp(4, Timestamp.valueOf(movimentacaoFinanceira.getDataCriacao()));
            stmt.setTimestamp(5, Timestamp.valueOf(movimentacaoFinanceira.getDataModificacao()));

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR A MOVIMENTACAO FINANCEIRA: " + e.getMessage());
        }
    }

    public List<MovimentacaoFinanceira> lista() {
        String sql = "select * from movimentacao_financeira";
        List<MovimentacaoFinanceira> movimentacaoFinanceiras = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String tipo = rs.getString("tipo");
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

                MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
                movimentacaoFinanceira.setId(id);
                movimentacaoFinanceira.setTipo(tipo);
                movimentacaoFinanceira.setDescricao(descricao);
                movimentacaoFinanceira.setValor(valor);
                movimentacaoFinanceira.setDataCriacao(dataCriacao);
                movimentacaoFinanceira.setDataModificacao(dataModificacao);

                movimentacaoFinanceiras.add(movimentacaoFinanceira);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movimentacaoFinanceiras;
    }

    public List<MovimentacaoFinanceira> mostrarTodasNoMesEAno(int mes, int ano) {
        String sql = "select * from movimentacao_financeira WHERE MONTH(data_criacao) = ? AND YEAR(data_criacao) = ?";
        List<MovimentacaoFinanceira> movimentacoes = new ArrayList<>();
        
        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira();
                    movimentacao.setId(rs.getLong("id"));
                    movimentacao.setTipo(rs.getString("tipo"));
                    movimentacao.setDescricao(rs.getString("descricao"));
                    movimentacao.setValor(rs.getDouble("valor"));
                    movimentacao.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                    movimentacao.setDataModificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());
                    
                    movimentacoes.add(movimentacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar movimentações financeiras: " + e.getMessage());
        }
        
        return movimentacoes;
    }


    public MovimentacaoFinanceira buscaPorId(long id) {
        String sql = "select * from movimentacao_financeira where id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getLong("id");
                    String tipo = rs.getString("tipo");
                    String descricao = rs.getString("descricao");
                    double valor = rs.getDouble("valor");
                    LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

                    MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
                    movimentacaoFinanceira.setId(id);
                    movimentacaoFinanceira.setTipo(tipo);
                    movimentacaoFinanceira.setDescricao(descricao);
                    movimentacaoFinanceira.setValor(valor);
                    movimentacaoFinanceira.setDataCriacao(dataCriacao);
                    movimentacaoFinanceira.setDataModificacao(dataModificacao);
                    
                    return movimentacaoFinanceira;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public MovimentacaoFinanceira alterar(MovimentacaoFinanceira movimentacaoFinanceira) {
        String sql = "update movimentacao_financeira set id = ?, tipo = ?, descricao = ?, valor = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, movimentacaoFinanceira.getTipo());
                stmt.setString(2, movimentacaoFinanceira.getDescricao());
                stmt.setDouble(3, movimentacaoFinanceira.getValor());
                stmt.setTimestamp(4, Timestamp.valueOf(movimentacaoFinanceira.getDataCriacao()));

            stmt.execute();
            return movimentacaoFinanceira;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR MOVIMENTACAO FINANCEIRA: " + e.getMessage());
        }
    }

    public boolean remover (long id) {
        String sql = "delete from movimentacao_financeira where idmovimentacao_financeira = ?";

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int linhasDeletadas = stmt.executeUpdate();
            return linhasDeletadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER MOVIMENTACAO FINANCEIRA: " + e.getMessage());
        }
    }
}
