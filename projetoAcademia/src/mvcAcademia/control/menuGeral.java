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
    Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 3) {
            opcaoUsuario = gui.menuBemVindo();

            switch (opcaoUsuario) {
                case 1:
                    gui.exibirMensagem("LOGIN: ");
                    String login = scanner.nextLine();
                    gui.exibirMensagem("SENHA ");
                    String senha = scanner.nextLine();
                    Pessoa pessoaLogada = pessoaDAO.buscaPessoaLogin(login, senha);

                    if (pessoaLogada != null) {
                        gui.exibirMensagem("USUARIO LOGADO COM SUCESSO!");
                        UtilPessoa.setPessoaLogado(pessoaLogada);
                        gui.exibirMensagem("USUARIO LOGADO: " + UtilPessoa.getPessoaLogado().toString());

                        int resp;

                        resp = gui.exibirMenuCruds();
                        receberOpcaoMenu(resp);

                    } else {
                        gui.exibirMensagem("LOGIN INVALIDO, TENTE NOVAMENTE.");
                    }
                    break;
                case 2:
                    Pessoa p = this.cadastraPessoa();
                    if (pessoaDAO.adiciona(p)) {

                        gui.exibirMensagem("USUARIO ADICIONADO COM SUCESSO");
                    } else {
                        System.out.println("USUARIO NAO ADICIONADO.");
                    }
                    break;
                case 3:
                    gui.exibirMensagem("FINALIZANDO O PROGRAMA... OBRIGADO POR UTILIZAR.");
                    break;
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPÇÃO VÁLIDA.");
                    break;
            }
        }
    }

    private Pessoa cadastraPessoa() {

        Pessoa p = new Pessoa();
        System.out.println("NOME: ");
        p.setNome(scanner.nextLine());
        System.out.println("LOGIN: ");
        p.setLogin(scanner.nextLine());
        System.out.println("SENHA: ");
        p.setSenha(scanner.nextLine());
        return p;

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

    public static void main(String[] args) {
        menuGeral menu = new menuGeral();
        menu.iniciar();
    }

    public void receberOpcaoMenu(int opc) {
        int resp;
        String nome, endereco;

        while (opc != 3) {

            switch (opc) {
                case 1:
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
                            gui.exibirMenuCruds();

                        default:
                            gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    }
                    break;
                case 2:
                    //opc = gui.menuCrudPessoa();
                    break;
                case 3:
                    iniciar();
                    break;
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPÇÃO VÁLIDA.");
                    break;
            }
        }
    }
}
