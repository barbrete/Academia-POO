/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 *
 * @author barbrete e kitotsui
 */

public class PessoaDAO {
    
    Pessoa[] pessoas = new Pessoa[5];

    
    public PessoaDAO() {
        LocalDate dataNascimento1 = LocalDate.of(1996, 9, 11);
        Pessoa p1 = new Pessoa();
        p1.setNome("Rogerio");
        p1.setLogin("Rojas");
        p1.setSenha("123");
        p1.setTipoUsuario("Administrador");
        p1.setSexo("M");
        p1.setNascimento(dataNascimento1);
        p1.setDataCriacao(UtilPessoa.getDiaAtual());
        p1.setDataModificacao(UtilPessoa.getDiaAtual());
        
        adiciona(p1);

        Pessoa p2 = new Pessoa();
        LocalDate dataNascimento2 = LocalDate.of(2000, 5, 13);
        p2.setNome("Barbara");
        p2.setLogin("babs");
        p2.setSenha("123");
        p2.setSexo("F");
        p2.setNascimento(dataNascimento2);
        p2.setTipoUsuario("Professor");
        p2.setDataCriacao(UtilPessoa.getDiaAtual());
        p2.setDataModificacao(UtilPessoa.getDiaAtual());
        adiciona(p2);

        Pessoa p3 = new Pessoa();
        LocalDate dataNascimento3 = LocalDate.of(1986, 8, 13);
        p3.setNome("Eduardo");
        p3.setLogin("dudu");
        p3.setSenha("123");
        p3.setSexo("M");
        p3.setNascimento(dataNascimento3);
        p3.setTipoUsuario("Aluno");
        p3.setDataCriacao(UtilPessoa.getDiaAtual());
        p3.setDataModificacao(UtilPessoa.getDiaAtual());
        adiciona(p3);
        
    }
    
    public Pessoa buscaPessoaPorId(long id) {
        for (Pessoa p : pessoas) {
            if (p != null && p.getId() == id) {
                return p;
            }
        }
        return null;
    }
      
   public void mostraPessoa(long id) {//mostra pessoa especifica
       Pessoa p = buscaPessoaPorId(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("PESSOA NAO ENCONTRADA.");
        }
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
