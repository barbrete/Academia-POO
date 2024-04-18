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
public class Main {

    Scanner scanner = new Scanner(System.in);
    AcademiaDAO academiaDAO = new AcademiaDAO();
    boolean academiaCriada = false;

    public Main() {

        int opcaoUsuario = 6;

        while (opcaoUsuario != 5) {
            opcaoUsuario = this.recebeOpcaoUsuario();

            switch (opcaoUsuario) {
                case 1:
                    System.out.println("ACADEMIA CRIADA COM SUCESSO!");
                    academiaCriada = true;
                    break;
                case 2:
                     if (academiaCriada) {
                        mostrarAcademia();
                    } else {
                        System.out.println("NENHUMA ACADEMIA CRIADA AINDA.");
                    }
                    break;
                case 3:
                    if (academiaCriada) {
                        excluirAcademia();
                        academiaCriada = false;
                    } else {
                        System.out.println("NENHUMA ACADEMIA CRIADA AINDA.");
                    }
                    break;
                case 4:
                    if (academiaCriada) {
                        alterarNomeAcademia();
                    } else {
                        System.out.println("NENHUMA ACADEMIA CRIADA AINDA.");
                    }
                    break;
                case 5:
                    System.out.println("FINALIZANDO O PROGRAMA...OBRIGADO POR UTILIZAR.");
                    break;
                default:
                    System.out.println("Escolha uma opcao valida");
                    break;
            }
        }
        System.out.println("ATE A PROXIMA!");
    }

    public static void main(String[] args) {
        new Main();
    }

    private int recebeOpcaoUsuario() {

        StringBuilder builder = new StringBuilder("");

        builder.append("SEJA BEM VINDO AO MEU PROGRAMA\n\n");
        builder.append("\n1 - CRIAR ACADEMIA");
        builder.append("\n2 - MOSTRAR ACADEMIA");
        builder.append("\n3 - EXCLUIR ACADEMIA");
        builder.append("\n4 - ALTERAR ACADEMIA");
        builder.append("\n5 - SAIR DO PROGRAMA\n");
        builder.append("\nQUAL A SUA OPCAO? \nR: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

 

    private void mostrarAcademia() {
        academiaDAO.mostrarAcademia();
    }

    private void excluirAcademia() {
        academiaDAO.excluirAcademia();
         academiaCriada = false;
        System.out.println("ACADEMIA EXCLUIDA COM SUCESSO!");
    }

    private void alterarNomeAcademia() {
        System.out.println("DIGITE O NOVO NOME DA ACADEMIA: ");
        String novoNome = scanner.nextLine();
        academiaDAO.alterarNomeAcademia(novoNome);
        System.out.println("NOME DA ACADEMIA ALTERADO COM SUCESSO!");
    }

}
