/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;
import java.time.LocalDateTime;


/**
 *
 * @author barbrete e kitotsui
 */
public class PessoaDAO {
    
    Pessoa[] pessoas = new Pessoa[5];

    public PessoaDAO() {
        Pessoa p1 = new Pessoa();
        p1.setNome("Rogerio");
        p1.setLogin("Rojas");
        p1.setSenha("123");
        p1.setTipoUsuario(0);
        
        this.adiciona(p1);

        Pessoa p2 = new Pessoa();
        p2.setNome("Barbara");
        p2.setLogin("babara");
        p2.setSenha("654");
        p2.setTipoUsuario(1);
        adiciona(p2);

        Pessoa p3 = new Pessoa();
        p3.setNome("eduardo");
        p3.setLogin("dudu");
        p3.setSenha("corsa");
        p3.setTipoUsuario(2);
        adiciona(p3);
        
    }
    
    public Pessoa buscaPessoaLogin(String login, String senha) {
         for (Pessoa p : pessoas) {
            if (p!= null && p.getLogin().equals(login) &&
                    p.getSenha().equals(senha)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean adiciona(Pessoa p) {
        int proximaPosicaoLivre = this.proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            pessoas[proximaPosicaoLivre] = p;
            return true;
        } else {
            return false;
        }

    }

    public boolean ehVazio() {
        for (Pessoa p : pessoas) {
            if (p != null) {
                return false;
            }
        }
        return true;

    }

    public void mostrarTodos() {
        boolean temPessoa = false;
        for (Pessoa p : pessoas) {
            if (p != null) {
                System.out.println(p);
                temPessoa = true;
            }
        }
        if (!temPessoa) {
            System.out.println("não existe usuario cadastrado");
        }
    }

    public boolean alterarNome(String nome, String novoNome) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getNome().equals(nome)) {
                p.setNome(novoNome);
                return true;
            }
        }
        return false;

    }

    public Pessoa buscaPorNome(String nome) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;

    }

    public boolean remover(String nome) {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] != null && pessoas[i].getNome().equals(nome)) {
                pessoas[i] = null;
                return true;
            }
        }
        return false;

    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i] == null) {
                return i;
            }

        }
        return -1;
    
    }
}
