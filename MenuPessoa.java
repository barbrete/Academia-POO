/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto01; 

import java.util.Scanner;

/**
 *
 * @author barbrete e kitotsui
 */

public class MenuPessoa {

    Scanner scanner = new Scanner(System.in);
    PessoaDAO PessoaDAO = new PessoaDAO();
    boolean PessoaCriada = false;

    public MenuPessoa() { //segundo menu

        int opcaoUsuario = 6;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.menuLogar();

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("Login: ");
                    String login = scanner.nextLine();
                    System.out.println("Senha: ");
                    String senha = scanner.nextLine();
                    Pessoa logado = PessoaDAO.buscaPessoaLogin(login, senha);
                    
                    if (logado != null)
                    {
                        System.out.println("USUARIO LOGADO COM SUCESSO!");
                        UtilPessoa.setPessoaLogado(logado);
                        System.out.println("Usuario logado: " + UtilPessoa.getPessoaLogado().toString());
                    }
                    else{
                        System.out.println("Login invalido, tente novamente");
                    }
                    
                    //PessoaCriada = true;
                    break;
                case 2:
                     if (PessoaCriada) {
                        mostrarPessoa();
                    } else {
                        System.out.println("NENHUM USUARIO CRIADO AINDA.");
                    }
                    break;
                case 3:
                    if (PessoaCriada) {
                        excluirPessoa();
                        PessoaCriada = false;
                    } else {
                        System.out.println("NENHUM USUARIO CRIADO AINDA.");
                    }
                    break;
                case 4:
                    if (PessoaCriada) {
                        alterarNomeAcademia();
                    } else {
                        System.out.println("NENHUM USUARIO CRIADO AINDA.");
                    }
                    break;
                case 5:
                    System.out.println("FINALIZANDO O PROGRAMA...OBRIGADO POR UTILIZAR.");
                    break;
                default:
                    System.out.println("escolha uma opcao valida");
                    break;
            }
        }
        System.out.println("ATE A PROXIMA!");
    }

    public static void MenuPessoa(String[] args) {
        new MenuPessoa();
    }

    private int menu (){
        StringBuilder builder = new StringBuilder("");

        builder.append("\n1 - Inserir USUARIO");
        builder.append("\n2 - MOSTRAR USUARIO");
        builder.append("\n3 - EXCLUIR USUARIO");
        builder.append("\n4 - ALTERAR USUARIO");
        builder.append("\n5 - SAIR DO PROGRAMA\n");
        builder.append("\nQUAL A SUA OPCAO? \nR: ");
        
        System.out.println(builder.toString());
        
        return Integer.parseInt(scanner.nextLine());
    }
    
    private void loopMenu() { //terceiro menu
        
        int opcao = 0;
        
        while (opcao != 5)//PARAMO AQUI
        {
            opcao = this.menu();
            switch(opcao){
                case 1:
                    break;

            }
               
        }

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    private int menuLogar() { //primeiro menu

        StringBuilder builder = new StringBuilder("");

        builder.append("SEJA BEM VINDO AO MEU PROGRAMA\n\n");
        builder.append("\n0 - Logar");
        builder.append("\n1 - Cadastrar ");
        builder.append("\nQual sua opcao ? R: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }


    private void mostrarPessoa() {
        PessoaDAO.mostrarPessoa();
    }

    private void excluirPessoa() {
        PessoaDAO.excluirPessoa();
         PessoaCriada = false;
        System.out.println("USUARIO EXCLUIDO COM SUCESSO!");
    }

    private void alterarNomeAcademia() {
        System.out.println("DIGITE O NOVO NOME DE USUARIO: ");
        String novoNome = scanner.nextLine();
        PessoaDAO.alterarNomePessoa(novoNome);
        System.out.println("NOME DA ACADEMIA ALTERADO COM SUCESSO!");
    }

}