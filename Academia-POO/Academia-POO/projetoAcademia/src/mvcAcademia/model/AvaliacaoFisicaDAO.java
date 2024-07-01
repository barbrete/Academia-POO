package mvcAcademia.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoFisicaDAO {

    public boolean adiciona(AvaliacaoFisica avaliacaoFisica) {
        String sql = "INSERT INTO avaliacao_fisica (id_pessoa, id_ultimo_treino, peso, altura, imc, indice_satisfacao, data_criacao, data_modificacao) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, avaliacaoFisica.getPessoa().getId());
            stmt.setLong(2, avaliacaoFisica.getUltimoTreino().getId());
            stmt.setDouble(3, avaliacaoFisica.getPeso());
            stmt.setDouble(4, avaliacaoFisica.getAltura());
            stmt.setDouble(5, avaliacaoFisica.getImc());
            stmt.setInt(6, avaliacaoFisica.getIndiceSatisfacao());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(avaliacaoFisica.getDataCriacao()));
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(avaliacaoFisica.getDataModificacao()));
            
            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ADICIONAR AVALIAÇÃO FÍSICA: " + e.getMessage());
        }
    }
    
    public List<AvaliacaoFisica> lista() {
        String sql = "SELECT * FROM avaliacao_fisica";
        List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

        try (Connection connection = new ConexaoAcademia().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("idavaliacaofisica");
                long idPessoa = rs.getLong("id_pessoa");
                double peso = rs.getDouble("peso");
                double altura = rs.getDouble("altura");
                double imc = rs.getDouble("imc");
                int indiceSatisfacao = rs.getInt("indice_satisfacao");
                LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

                Pessoa pessoa = new PessoaDAO().buscaPorId(idPessoa);
                Treino treino = new TreinoDAO().buscaPorId(idPessoa);

                AvaliacaoFisica avaliacao = new AvaliacaoFisica();
                avaliacao.setId(id);
                avaliacao.setPessoa(pessoa);
                avaliacao.setUltimoTreino(treino);
                avaliacao.setPeso(peso);
                avaliacao.setAltura(altura);
                avaliacao.setImc(imc);
                avaliacao.setIndiceSatisfacao(indiceSatisfacao);
                avaliacao.setDataCriacao(dataCriacao);
                avaliacao.setDataModificacao(dataModificacao);

                avaliacoes.add(avaliacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO LISTAR AVALIAÇÕES FÍSICAS: " + e.getMessage());
        }
        return avaliacoes;
    }

    public AvaliacaoFisica buscaPorId(long id) {
        String sql = "SELECT * FROM avaliacaofisica WHERE idavaliacaofisica = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long pessoaId = rs.getLong("pessoaId");
                    long treinoID = rs.getLong("pessoaId");
                    double peso = rs.getDouble("peso");
                    double altura = rs.getDouble("altura");
                    double imc = rs.getDouble("imc");
                    int indiceSatisfacao = rs.getInt("indice_satisfacao");
                    LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();

                    Pessoa pessoa = new PessoaDAO().buscaPorId(pessoaId);
                    Treino treino = new TreinoDAO().buscaPorId(treinoID);

                    AvaliacaoFisica avaliacao = new AvaliacaoFisica();
                    avaliacao.setId(id);
                    avaliacao.setPessoa(pessoa);
                    avaliacao.setUltimoTreino(treino);
                    avaliacao.setPeso(peso);
                    avaliacao.setAltura(altura);
                    avaliacao.setImc(imc);
                    avaliacao.setIndiceSatisfacao(indiceSatisfacao);
                    avaliacao.setDataCriacao(dataCriacao);
                    avaliacao.setDataModificacao(dataModificacao);

                    return avaliacao;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR AVALIAÇÃO FÍSICA POR ID: " + e.getMessage());
        }
        return null;
    }

    public AvaliacaoFisica alterar(AvaliacaoFisica avaliacao) {
        String sql = "UPDATE avaliacao_fisica SET idAluno = ?, idTreino = ?, peso = ?, altura = ?, imc = ?, indiceDeSatisfacao = ?, dataModificacao = ? WHERE idavaliacaofisica = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, avaliacao.getPessoa().getId());
            stmt.setLong(2, avaliacao.getUltimoTreino().getId());
            stmt.setDouble(2, avaliacao.getPeso());
            stmt.setDouble(3, avaliacao.getAltura());
            stmt.setDouble(4, avaliacao.getImc());
            stmt.setInt(5, avaliacao.getIndiceSatisfacao());
            stmt.setTimestamp(6, Timestamp.valueOf(avaliacao.getDataModificacao()));
            stmt.setLong(7, avaliacao.getId());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO ALTERAR AVALIAÇÃO FÍSICA: " + e.getMessage());
        }
        return avaliacao;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM avaliacao_fisica WHERE idavaliacaofisica = ?";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasDeletadas = stmt.executeUpdate();
            return linhasDeletadas > 0;
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO REMOVER AVALIAÇÃO FÍSICA: " + e.getMessage());
        }
    }

    public AvaliacaoFisica buscarUltimaAvaliacaoDoAluno(long alunoId) {
        String sql = "SELECT * FROM avaliacao_fisica WHERE idAluno = ? ORDER BY datamodificacao DESC LIMIT 1";

        try (Connection connection = new ConexaoAcademia().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, alunoId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("idavaliacaofisica");
                    double peso = rs.getDouble("peso");
                    double altura = rs.getDouble("altura");
                    double imc = rs.getDouble("imc");
                    int indiceSatisfacao = rs.getInt("indice_satisfacao");
                    LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();
                    LocalDateTime dataModificacao = rs.getTimestamp("data_modificacao").toLocalDateTime();
                    long pessoaId = rs.getLong("pessoaId");
                    long treinoId = rs.getLong("treinoId");

                    Pessoa pessoa = new PessoaDAO().buscaPorId(pessoaId);
                    Treino treino = new TreinoDAO().buscaPorId(treinoId);

                    AvaliacaoFisica avaliacao = new AvaliacaoFisica();
                    avaliacao.setId(id);
                    avaliacao.setPeso(peso);
                    avaliacao.setAltura(altura);
                    avaliacao.setImc(imc);
                    avaliacao.setIndiceSatisfacao(indiceSatisfacao);
                    avaliacao.setDataCriacao(dataCriacao);
                    avaliacao.setDataModificacao(dataModificacao);
                    avaliacao.setPessoa(pessoa);
                    avaliacao.setUltimoTreino(treino);

                    return avaliacao;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("ERRO AO BUSCAR ÚLTIMA AVALIAÇÃO DO ALUNO: " + e.getMessage());
        }

        return null;
    }
}
