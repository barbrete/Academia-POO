/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.view;

import java.util.Scanner;

/**
 *
 * @author barbrete e kitotsui
 */


public class GUI {

    private Scanner scanner;

    public GUI() {
        this.scanner = new Scanner(System.in);
    }

    public int menuBemVindo() {
        System.out.println("SEJA BEM VINDO!\n");
        System.out.println("1 - LOGIN");
        System.out.println("2 - CADASTRAR");
        System.out.println("3 - SAIR");

        System.out.print("QUAL EH A SUA ESCOLHA? ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int exibirMenuCruds() {
        System.out.println("\n=============== MENU GERAL ================");
        System.out.println("1 - CRUD ACADEMIA");
        System.out.println("2 - CRUD PESSOA");
       //System.out.println("3 - CRUD EXERCICIOS");
        //System.out.println("4 - CRUD TREINO");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuCrudAcademia(){
        System.out.println("Opção 1 - Criar academia");
        System.out.println("Opção 2 - Mostrar Academia");
        System.out.println("Opção 3 - Alterar Academia");
        System.out.println("Opção 4 - Apagar Academia");

        System.out.println("Opção...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int receberOpcaoMenuCruds() {
        System.out.print("\nESCOLHA UMA OPCAO: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

   

   

  



   
}
