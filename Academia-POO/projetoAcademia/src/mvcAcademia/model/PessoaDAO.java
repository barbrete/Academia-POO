/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author barbrete e kitotsui
 */
public class PessoaDAO {

    public boolean adiciona(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, sexo, datanascimento, login, senha, tipousuario, datacriacao, datamodificacao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setObject(3, pessoa.getNascimento());
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            stmt.setString(6, pessoa.getTipoUsuario());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(pessoa.getDataCriacao()));
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(pessoa.getDataModificacao()));

            stmt.execute();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR PESSOA: " + e.getMessage());
        }
    }

    public Pessoa buscaPorId(long id) {
        String sql = "SELECT * FROM pessoa WHERE idpessoa = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String sexo = rs.getString("sexo");
                    LocalDate nascimento = rs.getObject("datanascimento", LocalDate.class);
                    String login = rs.getString("login");
                    String senha = rs.getString("senha");
                    String tipoUsuario = rs.getString("tipousuario");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(id);
                    pessoa.setNome(nome);
                    pessoa.setSexo(sexo);
                    pessoa.setNascimento(nascimento);
                    pessoa.setLogin(login);
                    pessoa.setSenha(senha);
                    pessoa.setTipoUsuario(tipoUsuario);
                    pessoa.setDataCriacao(dataCriacao);
                    pessoa.setDataModificacao(dataModificacao);

                    return pessoa;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR PESSOA POR ID: " + e.getMessage());
        }
        return null;
    }

    public Pessoa buscaPorLogin(String login, String senha) {
        String sql = "SELECT * FROM pessoa WHERE login = ? AND senha = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Long id = rs.getLong("idpessoa");
                    String nome = rs.getString("nome");
                    String sexo = rs.getString("sexo");
                    LocalDate nascimento = rs.getObject("datanascimento", LocalDate.class);
                    String tipoUsuario = rs.getString("tipousuario");
                    LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                    Pessoa pessoa = new Pessoa();
                    pessoa.setId(id);
                    pessoa.setNome(nome);
                    pessoa.setSexo(sexo);
                    pessoa.setNascimento(nascimento);
                    pessoa.setLogin(login);
                    pessoa.setSenha(senha);
                    pessoa.setTipoUsuario(tipoUsuario);
                    pessoa.setDataCriacao(dataCriacao);
                    pessoa.setDataModificacao(dataModificacao);

                    return pessoa;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR PESSOA POR LOGIN: " + e.getMessage());
        }
        return null;
    }

    public Pessoa alterar(Pessoa pessoa) {
    String sql = "update pessoa set nome = ?, sexo = ?, datanascimento = ?, tipousuario = ?, login = ?, senha = ?, datamodificacao = ? where idpessoa = ?";

    try (Connection connection = new ConexaoAcademia().getConnection(); 
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getSexo());
        stmt.setDate(3, java.sql.Date.valueOf(pessoa.getNascimento()));
        stmt.setString(4, pessoa.getTipoUsuario());
        stmt.setString(5, pessoa.getLogin());
        stmt.setString(6, pessoa.getSenha());
        stmt.setTimestamp(7, java.sql.Timestamp.valueOf(pessoa.getDataModificacao()));
        stmt.setLong(8, pessoa.getId());

        stmt.execute();

    } catch (SQLException e) {
        throw new RuntimeException("ERRO AO ALTERAR PESSOA: " + e.getMessage());
    }
    return pessoa;
}


    public boolean remover(long id) {
        String sql = "DELETE FROM pessoa WHERE idpessoa = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER PESSOA: " + e.getMessage());
        }
        return false;
    }

    public List<Pessoa> lista() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>(); 

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("idpessoa");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                LocalDate nascimento = rs.getObject("datanascimento", LocalDate.class);
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                String tipoUsuario = rs.getString("tipousuario");
                LocalDateTime dataCriacao = rs.getTimestamp("datacriacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("datamodificacao").toLocalDateTime();

                Pessoa p = new Pessoa();
                p.setId(id);
                p.setNome(nome);
                p.setSexo(sexo);
                p.setNascimento(nascimento);
                p.setLogin(login);
                p.setSenha(senha);
                p.setTipoUsuario(tipoUsuario);
                p.setDataCriacao(dataCriacao);
                p.setDataModificacao(dataModificacao);

                pessoas.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO MOSTRAR TODAS AS PESSOAS: " + e.getMessage());
        }
        return pessoas; 
    }

}
