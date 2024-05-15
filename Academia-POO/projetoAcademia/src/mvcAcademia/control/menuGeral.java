/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.control;

/**
 *
 * @author barbrete e kitotsui
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import mvcAcademia.model.Academia;
import mvcAcademia.model.AcademiaDAO;
import mvcAcademia.model.Exercicio;
import mvcAcademia.model.ExercicioDAO;
import mvcAcademia.model.Pessoa;
import mvcAcademia.model.PessoaDAO;
import mvcAcademia.model.UtilPessoa;
import mvcAcademia.view.GUI;

public class menuGeral {

    private GUI gui = new GUI();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private AcademiaDAO academiaDAO = new AcademiaDAO();
    private ExercicioDAO exercicioDAO = new ExercicioDAO();
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

            switch (pessoaLogada.getTipoUsuario()) {
                case "Administrador":
                    exibirMenuAdministrador();
                    break;
                case "Professor":
                    exibirMenuProfessor();
                    break;
                case "Aluno":
                    exibirMenuAluno();
                    break;
                default:
                    gui.exibirMensagem("USUARIO NAO RECONHECIDO.");
                    break;
            }
        } else {
            gui.exibirMensagem("LOGIN INVALIDO, TENTE NOVAMENTE.");
        }
    }

    private void exibirMenuAluno() {
        int opcaoUsuario;

        while (true) {
            opcaoUsuario = gui.menuAluno();

            switch (opcaoUsuario) {
                case 1:
                    // Visualizar Ficha de Treino
                    // (implementação será adicionada)
                    break;
                case 2:
                    // Imprimir Ficha de Treino
                    // (implementação será adicionada)
                    break;
                case 3:
                    // Visualizar Avaliações Físicas
                    // (implementação será adicionada)
                    break;
                case 4:
                    return; // Voltar ao menu anterior
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void exibirMenuProfessor() {
        int opcaoUsuario;

        while (true) {
            opcaoUsuario = gui.menuProfessor();

            switch (opcaoUsuario) {
                case 1:
                    // CRUD Treino (implementação será adicionada)
                    break;
                case 2:
                    // Visualizar Ficha de Treino (para Alunos)
                    // (implementação será adicionada)
                    break;
                case 3:
                    // Imprimir Ficha de Treino (para Alunos)
                    // (implementação será adicionada)
                    break;
                case 4:
                    // Visualizar Avaliações Físicas (para Alunos)
                    // (implementação será adicionada)
                    break;
                case 5:
                    return; // Voltar ao menu anterior
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void exibirMenuAdministrador() {
        // Implementação do menu para Administrador (acesso a todas as CRUDs)
        exibirMenuCRUD();
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
        pu.setTipoUsuario("Aluno");
        return pu;

    }

    private Pessoa cadastraPessoa() {
        Pessoa p = new Pessoa();
        int opcaoTipoUsuario;

        gui.exibirMensagem("CADASTRO DE PESSOA:");
        gui.exibirMensagem("NOME:");
        p.setNome(scanner.nextLine());
        gui.exibirMensagem("SEXO [M/F]:");
        p.setSexo(scanner.nextLine());

        gui.exibirMensagem("DATA DE NASCIMENTO (dd/MM/yyyy):");
        String dataNascimentoStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        p.setNascimento(dataNascimento);

        // Obtenha a opção de tipo de usuário do menu
        opcaoTipoUsuario = gui.menuTipoUsuario();

        switch (opcaoTipoUsuario) {
            case 1:
                p.setTipoUsuario("Administrador");
                break;
            case 2:
                p.setTipoUsuario("Professor");
                break;
            case 3:
                p.setTipoUsuario("Aluno");
                break;
            default:
                p.setTipoUsuario("Aluno");
                break;
        }
        System.out.println("LOGIN: ");
        p.setLogin(scanner.nextLine());
        System.out.println("SENHA: ");
        p.setSenha(scanner.nextLine());
        p.setDataCriacao(UtilPessoa.getDiaAtual());
        p.setDataModificacao(UtilPessoa.getDiaAtual());

        return p;
    }
    
     private Exercicio cadastraExercicio() {
        Exercicio ex = new Exercicio();
        System.out.println("NOME: ");
        ex.setNome(scanner.nextLine());
        System.out.println("DESCRICAO: ");
        ex.setDescricao(scanner.nextLine());
        ex.setDataCriacao(LocalDateTime.now());
        ex.setDataModificacao(LocalDateTime.now());
        return ex;
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
                    gerenciarExercicios();
                    break;
                case 4: 
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
        int opcaoTipoUsuario;

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
                        gui.exibirMensagem("DIGITE O NOVO NOME DE USUARIO...: ");
                        String novoNomeUsuario = scanner.nextLine();
                        pessoaParaAlterar.setNome(novoNomeUsuario);
                        gui.exibirMensagem("DIGITE O NOVO SEXO DE USUARIO [M/F]...: ");
                        String novoSexoUsuario = scanner.nextLine();
                        pessoaParaAlterar.setSexo(novoSexoUsuario);
                        gui.exibirMensagem("DIGITE A NOVA DATA DE NASCIMENTO (dd/MM/yyyy): ");
                        String dataNascimentoStr = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
                        pessoaParaAlterar.setNascimento(dataNascimento);
                        opcaoTipoUsuario = gui.menuTipoUsuario();
                        switch (opcaoTipoUsuario) {
                            case 1:
                                pessoaParaAlterar.setTipoUsuario("Administrador");
                                break;
                            case 2:
                                pessoaParaAlterar.setTipoUsuario("Professor");
                                break;
                            case 3:
                                pessoaParaAlterar.setTipoUsuario("Aluno");
                                break;
                            default:
                                pessoaParaAlterar.setTipoUsuario("Aluno");
                                break;
                        }
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
    
    private void gerenciarExercicios() {
        int resp;

        while (true) {
            resp = gui.menuCrudExercicio();

            switch (resp) {
                case 1:
                    Exercicio ex = this.cadastraExercicio();
                    if (exercicioDAO.adiciona(ex)) {
                        System.out.println("EXERCICIO CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("EXERCICIO NAO CRIADO.");
                    }
                    break;
                case 2:
                    exercicioDAO.mostrarTodosExercicios();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DO EXERCICIO QUE DESEJA ALTERAR...: ");
                    String nomeEx = scanner.nextLine();
                    Exercicio exParaAlterar = exercicioDAO.buscaPorNome(nomeEx);
                    if (exParaAlterar != null) {
                        gui.exibirMensagem("DIGITE O NOVO NOME DO EXERCICIO...: ");
                        String novoNomeExercicio = scanner.nextLine();
                        exParaAlterar.setNome(novoNomeExercicio);
                        gui.exibirMensagem("DIGITE A NOVA DESCRICAO DO EXERCICIO...: ");
                        String novaDescricaoExercicio = scanner.nextLine();
                        exParaAlterar.setDescricao(novaDescricaoExercicio);
                        exParaAlterar.setDataModificacao(LocalDateTime.now());
                        gui.exibirMensagem("EXERCICIO ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("EXERCICIO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O NOME DO EXERCICIO QUE DESEJA EXCLUIR...: ");
                    String nomeExExcluir = scanner.nextLine();
                    Exercicio exParaExcluir = exercicioDAO.buscaPorNome(nomeExExcluir);
                    if (exParaExcluir != null) {
                        exercicioDAO.remover(nomeExExcluir);
                        gui.exibirMensagem("EXERCICIO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("EXERCICIO NAO EXISTENTE NO BANCO DE DADOS.");
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
