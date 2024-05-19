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
public class AcademiaController {

   private Scanner scanner;

    public AcademiaController() {
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
        System.out.println("3 - CRUD EXERCICIO");
        System.out.println("4 - CRUD EXERCICIO APLICACAO");
        System.out.println("5 - CRUD DIVISAO DE TREINO");
        System.out.println("6 - CRUD DIVISAO TREINO MUSCULO");
        System.out.println("7 - CRUD TREINO");
        System.out.println("8 - CRUD AVALIACAO FISICA");
        System.out.println("9 - CRUD MENSALIDADE VIGENTE");
        System.out.println("10 - GERAR PAGAMENTO MENSALIDADE");
        System.out.println("11 - CRUD PAGAMENTO RECORRENTE");
        System.out.println("12 - CRUD ENTRADA ALUNO");//a verificacao sera feita no login
        System.out.println("13 - CRUD MOVIMENTACAO FINANCEIRA");
        System.out.println("14 - RELATORIO DE ALUNOS ADIMPLENTES");
        System.out.println("15 - RELATORIO MOVIMENTACAO FINANCEIRA");
        System.out.println("16 - FICHA DE TREINO DO ALUNO");
        
        System.out.println("17 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuCrudAcademia() {
        System.out.println("\n=============== CRUD ACADEMIA ================");
        System.out.println("1 - CRIAR ACADEMIA");
        System.out.println("2 - MOSTRAR ACADEMIA");
        System.out.println("3 - ALTERAR ACADEMIA");
        System.out.println("4 - EXCLUIR ACADEMIA");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuCrudPessoa() {
        System.out.println("\n=============== CRUD PESSOA ================");
        System.out.println("1 - CRIAR PESSOA");
        System.out.println("2 - MOSTRAR PESSOA");
        System.out.println("3 - ATUALIZAR PESSOA");
        System.out.println("4 - EXCLUIR PESSOA");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuCrudExercicio() {
        System.out.println("\n=============== CRUD EXERCICIO ================");
        System.out.println("1 - CRIAR EXERCICIO");
        System.out.println("2 - MOSTRAR EXERCICIO");
        System.out.println("3 - ATUALIZAR EXERCICIO");
        System.out.println("4 - EXCLUIR EXERCICIO");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuCrudDivisaoTreino() {
        System.out.println("\n=============== CRUD DIVISAO DE TREINO ================");
        System.out.println("1 - CRIAR NOVA DIVISAO DE TREINO");
        System.out.println("2 - LISTAR TODAS AS DIVISOES DE TREINO");
        System.out.println("3 - ALTERAR UMA DIVISAO DE TREINO EXISTENTE");
        System.out.println("4 - EXCLUIR UMA DIVISAO DE TREINO");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuCrudDivisaoTreinoMusculo() {
        System.out.println("\n=============== CRUD DIVISAO TREINO MUSCULO ================");
        System.out.println("1 - CRIAR NOVA DIVISAO TREINO MUSCULO");
        System.out.println("2 - LISTAR TODAS AS DIVISOES TREINO MUSCULO");
        System.out.println("3 - ALTERAR UMA DIVISAO TREINO MUSCULO EXISTENTE");
        System.out.println("4 - EXCLUIR UMA DIVISAO TREINO MUSCULO");
        System.out.println("5 - CADASTRAR DIVISAO DE TREINO A UM GRUPO MUSCULAR");
        System.out.println("6 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuCrudTreino() {
        System.out.println("\n=============== CRUD TREINO ================");
        System.out.println("1 - CRIAR TREINO");
        System.out.println("2 - MOSTRAR TREINO");
        System.out.println("3 - ALTERAR TREINO");
        System.out.println("4 - EXCLUIR TREINO");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuCrudExercicioAplicacao() {
    System.out.println("\n=============== CRUD EXERCICIO APLICACAO ================");
    System.out.println("1 - ADICIONAR EXERCICIO APLICACAO");
    System.out.println("2 - MOSTRAR EXERCICIOS APLICACAO");
    System.out.println("3 - ALTERAR EXERCICIO APLICACAO");
    System.out.println("4 - REMOVER EXERCICIO APLICACAO");
    System.out.println("5 - VOLTAR AO MENU ANTERIOR");
    System.out.print("OPCAO...: ");

    return Integer.parseInt(scanner.nextLine());
}
    
    public int menuCrudMensalidadeVigente() {
        System.out.println("\n=============== CRUD MENSALIDADE VIGENTE ================");
        System.out.println("1 - CRIAR MENSALIDADE VIGENTE");
        System.out.println("2 - MOSTRAR MENSALIDADE VIGENTE");
        System.out.println("3 - ATUALIZAR MENSALIDADE VIGENTE");
        System.out.println("4 - EXCLUIR MENSALIDADE VIGENTE");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuTipoUsuario() {
        System.out.println("\nQUAL A CLASSIFICACAO DO TIPO DE USUARIO?");
        System.out.println("1 - ADMINISTRADOR(a)");
        System.out.println("2 - PROFESSOR(a)");
        System.out.println("3 - ALUNO(a)");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuAluno() {
        System.out.println("\n=============== MENU ALUNO ================");
        System.out.println("1 - VISUALIZAR FICHA DE TREINO");
        System.out.println("2 - IMPRIMIR FICHA DE TREINO");
        System.out.println("3 - VISUALIZAR AVALIACOES FISICAS");
        System.out.println("4 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO...: ");

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuProfessor() {
        System.out.println("\n=============== MENU PROFESSOR ================");
        System.out.println("1 - GERENCIAR TREINOS");
        System.out.println("2 - VISUALIZAR FICHA DE TREINO");
        System.out.println("3 - IMPRIMIR FICHA DE TREINO");
        System.out.println("4 - VISUALIZAR AVALIACOES FISICAS");
        System.out.println("5 - VOLTAR AO MENU ANTERIOR");
        System.out.print("OPCAO: ");

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
