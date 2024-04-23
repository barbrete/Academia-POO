/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.control;

/**
 *
 * @author barbrete e kitotsui
 */


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
    Scanner scanner = new Scanner(System.in);


    public void iniciar() {
        int opcaoUsuario = 0;

        while (opcaoUsuario != 3) {
            opcaoUsuario = gui.menuBemVindo();

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("LOGIN: ");
                    String login = scanner.nextLine();
                    System.out.println("SENHA ");
                    String senha = scanner.nextLine();
                    Pessoa pessoaLogada = pessoaDAO.buscaPessoaLogin(login, senha);

                    if (pessoaLogada != null) {
                        gui.exibirMensagem("USUARIO LOGADO COM SUCESSO!");
                        UtilPessoa.setPessoaLogado(pessoaLogada);
                        gui.exibirMensagem("PESSOA LOGADA: " + UtilPessoa.getPessoaLogado().toString());
                        
                        int resp; 

                        gui.exibirMenuCruds();
                        resp = gui.exibirMenuCruds();
                        receberOpcaoMenu(resp);
                       
                    
                    } else {
                        gui.exibirMensagem("LOGIN INVALIDO, TENTE NOVAMENTE.");
                    }
                    break;
                case 2: 
                    Pessoa p = this.cadastraPessoa();
                    if (pessoaDAO.adiciona(p)) {
                        
                        gui.exibirMensagem("PESSOA ADICIONADA COM SUCESSO");
                    } else {
                        System.out.println("PESSOA NAO ADICIONADA.");
                    }
                    break;
                case 3: // SAIR
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

    public static void main(String[] args) {
        menuGeral menu = new menuGeral();
        menu.iniciar();
    }

    public void receberOpcaoMenu(int opc){
        AcademiaDAO academiaDAO = new AcademiaDAO();
        int resp;
        String nome, endereco;

        while (opc != 3){
        
            switch (opc){
                case 1:
                    gui.menuCrudAcademia();
                    resp = gui.menuCrudAcademia();
                    System.out.println("Digite o nome da academia...: ");
                    nome = scanner.nextLine();
                    System.out.println("Digite o endereço da academia...: ");
                    endereco = scanner.nextLine();

                    switch (resp) {
                        case 1://criar academia
                            academiaDAO.criarAcademia(nome,endereco);
                            scanner.close();
                        break;

                        case 2: //mostrar academia
                            academiaDAO.mostrarAcademia();
                        break;

                        case 3: //alterar academia
                            academiaDAO.alterarNomeAcademia(nome);
                        break;
                    
                        case 4: //excluir academia
                            academiaDAO.excluirAcademia();
                        break;
                        default:
                            gui.exibirMenuCruds();
                        break;
                    }

                break;
                case 2:
                    //opc = gui.menuCrudPessoa();
                break;
                //case 3...4...5
                case 5:

                break;
    
            }
        }
    }
}

