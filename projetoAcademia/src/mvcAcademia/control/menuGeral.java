/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.control;

/**
 *
 * @author barbrete e kitotsui
 */
import java.time.LocalDateTime;
import java.util.Scanner;

import mvcAcademia.model.Academia;
import mvcAcademia.model.AcademiaDAO;
import mvcAcademia.model.Pessoa;
import mvcAcademia.model.PessoaDAO;
import mvcAcademia.model.UtilPessoa;
import mvcAcademia.view.GUI;

public class menuGeral {

    private GUI gui = new GUI();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private AcademiaDAO academiaDAO = new AcademiaDAO();
    private Scanner scanner = new Scanner(System.in);
    private boolean sair = false;

    public void iniciar() {
        int opcaoUsuario;

        while (!sair) {
            opcaoUsuario = gui.menuBemVindo();

            switch (opcaoUsuario) {
                case 1: 
                    fazerLogin();
                    break;
                case 2: 
                    Pessoa pu = this.cadastrarLogin();
                    if (pessoaDAO.adiciona(pu)) {

                        gui.exibirMensagem("USUARIO ADICIONADO COM SUCESSO");
                    } else {
                        System.out.println("USUARIO NAO ADICIONADO.");
                    }
                    break;
                case 3: 
                    gui.exibirMensagem("FINALIZANDO O PROGRAMA... OBRIGADO POR UTILIZAR.");
                    sair = true;
                    break;
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void fazerLogin() {
        gui.exibirMensagem("LOGIN:");
        String login = scanner.nextLine();
        gui.exibirMensagem("SENHA:");
        String senha = scanner.nextLine();
        Pessoa pessoaLogada = pessoaDAO.buscaPessoaLogin(login, senha);

        if (pessoaLogada != null) {
            gui.exibirMensagem("USUARIO LOGADO COM SUCESSO!");
            exibirMenuCRUD();
        } else {
            gui.exibirMensagem("LOGIN INVALIDO, TENTE NOVAMENTE.");
        }
    }

    private Academia cadastraAcademia() {
        Academia acad = new Academia();
        System.out.println("NOME: ");
        acad.setNome(scanner.nextLine());
        System.out.println("ENDERECO: ");
        acad.setEndereco(scanner.nextLine());
        acad.setDataCriacao(LocalDateTime.now());
        acad.setDataModificacao(LocalDateTime.now());
        return acad;
    }

    private Pessoa cadastrarLogin() {
        Pessoa pu = new Pessoa();
        gui.exibirMensagem("CADASTRO DE LOGIN:");
        System.out.println("NOME: ");
        pu.setNome(scanner.nextLine());
        System.out.println("LOGIN: ");
        pu.setLogin(scanner.nextLine());
        System.out.println("SENHA: ");
        pu.setSenha(scanner.nextLine());
        return pu;

    }

    private Pessoa cadastraPessoa() {
        Pessoa p = new Pessoa();
        gui.exibirMensagem("CADASTRO DE PESSOA:");
        gui.exibirMensagem("NOME:");
        p.setNome(scanner.nextLine());
        gui.exibirMensagem("SEXO [M/F]:");
        p.setSexo(scanner.nextLine());
        gui.exibirMensagem("DATA DE NASCIMENTO:");
        p.setNascimento(scanner.nextInt());
        // Adicione validação e conversão para data de nascimento
        // p.setNascimento(scanner.nextLine());
        // Implemente o restante do cadastro
        //implementar o tipo de usuario
        p.setDataCriacao(LocalDateTime.now());
        p.setDataModificacao(LocalDateTime.now());
        return p;

    }

    private void exibirMenuCRUD() {
        int resp;

        while (true) {
            resp = gui.exibirMenuCruds();

            switch (resp) {
                case 1: 
                    gerenciarAcademias();
                    break;
                case 2: 
                    gerenciarPessoas();
                    break;
                case 3: 
                    return; 
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void gerenciarAcademias() {
        int resp;

        while (true) {
            resp = gui.menuCrudAcademia();

            switch (resp) {
                case 1:
                    Academia acad = this.cadastraAcademia();
                    if (academiaDAO.adiciona(acad)) {

                        gui.exibirMensagem("ACADEMIA CRIADA COM SUCESSO!");
                    } else {
                        System.out.println("ACADEMIA NAO CRIADA.");
                    }
                    break;
                case 2:
                    academiaDAO.mostrarTodasAcademias();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DA ACADEMIA QUE DESEJA ALTERAR...: ");
                    String nomeAcademia = scanner.nextLine();
                    Academia academiaParaAlterar = academiaDAO.buscaPorNome(nomeAcademia);
                    if (academiaParaAlterar != null) {
                        gui.exibirMensagem("DIGITE O NOVO NOME DA ACADEMIA...: ");
                        String novoNomeAcademia = scanner.nextLine();
                        academiaParaAlterar.setNome(novoNomeAcademia);
                        gui.exibirMensagem("DIGITE O NOVO ENDERECO DA ACADEMIA...: ");
                        String novoEnderecoAcademia = scanner.nextLine();
                        academiaParaAlterar.setEndereco(novoEnderecoAcademia);
                        academiaParaAlterar.setDataModificacao(LocalDateTime.now());
                        gui.exibirMensagem("ACADEMIA ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("ACADEMIA NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O NOME DA ACADEMIA QUE DESEJA EXCLUIR...: ");
                    String nomeAcademiaExcluir = scanner.nextLine();
                    Academia academiaParaExcluir = academiaDAO.buscaPorNome(nomeAcademiaExcluir);
                    if (academiaParaExcluir != null) {
                        academiaDAO.remover(nomeAcademiaExcluir);
                        gui.exibirMensagem("ACADEMIA EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("ACADEMIA NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5: 
                    return; 
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void gerenciarPessoas() {
        int resp;

        while (true) {
            resp = gui.menuCrudPessoa();

            switch (resp) {
                case 1:
                    Pessoa p = cadastraPessoa();

                    if (pessoaDAO.adiciona(p)) {
                        gui.exibirMensagem("USUARIO ADICIONADO COM SUCESSO!");
                    } else {
                        System.out.println("NAO FOI POSSIVEL ADICIONAR USUARIO.");
                    }
                    break;
                case 2:
                    pessoaDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DO USUARIO QUE DESEJA ALTERAR...: ");
                    String nomePessoa = scanner.nextLine();
                    Pessoa pessoaParaAlterar = pessoaDAO.buscaPorNome(nomePessoa);
                    if (pessoaParaAlterar != null) {
                        gui.exibirMensagem("DIGITE O NOVO NOME DE USUÁRIO...: ");
                        String novoNomeUsuario = scanner.nextLine();
                        pessoaParaAlterar.setNome(novoNomeUsuario);
                        gui.exibirMensagem("DIGITE O NOVO SEXO DE USUÁRIO [M/F]...: ");
                        String novoSexoUsuario = scanner.nextLine();
                        pessoaParaAlterar.setSexo(novoSexoUsuario);
                        gui.exibirMensagem("DIGITE A NOVA DATA DE NASCIMENTO...: ");
                        int novaDataNascimento = scanner.nextInt();
                        pessoaParaAlterar.setNascimento(novaDataNascimento);
                        //gui.exibirMensagem("DIGITE O NOVO TIPO DE USUÁRIO...: ");
                        //String novoTipoUsuario = scanner.nextLine();
                        //pessoaParaAlterar.setTipoUsuario(novoTipoUsuario);
                        gui.exibirMensagem("DIGITE O NOVO LOGIN DE USUARIO...: ");
                        String novoLoginUsuario = scanner.nextLine();
                        pessoaParaAlterar.setLogin(novoLoginUsuario);
                        gui.exibirMensagem("DIGITE A NOVA SENHA DO USUARIO...: ");
                        String novaSenhaUsuario = scanner.nextLine();
                        pessoaParaAlterar.setSenha(novaSenhaUsuario);

                        pessoaParaAlterar.setDataModificacao(LocalDateTime.now());
                        gui.exibirMensagem("USUARIO ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("USUARIO NAO EXISTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O NOME DO USUARIO QUE DESEJA EXCLUIR...: ");
                    String nomeUsuarioExcluir = scanner.nextLine();
                    Pessoa pessoaParaExcluir = pessoaDAO.buscaPorNome(nomeUsuarioExcluir);
                    if (pessoaParaExcluir != null) {
                        pessoaDAO.remover(nomeUsuarioExcluir);
                        gui.exibirMensagem("USUARIO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("USUARIO NAO EXISTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5: 
                    return;
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        menuGeral menu = new menuGeral();
        menu.iniciar();
    }
}
