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
import mvcAcademia.model.AvaliacaoFisica;
import mvcAcademia.model.AvaliacaoFisicaDAO;
import mvcAcademia.model.DivisaoTreino;
import mvcAcademia.model.DivisaoTreinoDAO;
import mvcAcademia.model.DivisaoTreinoMusculo;
import mvcAcademia.model.DivisaoTreinoMusculoDAO;
import mvcAcademia.model.Exercicio;
import mvcAcademia.model.ExercicioAplicacao;
import mvcAcademia.model.ExercicioAplicacaoDAO;
import mvcAcademia.model.ExercicioDAO;
import mvcAcademia.model.MensalidadeVigente;
import mvcAcademia.model.MensalidadeVigenteDAO;
import mvcAcademia.model.Treino;
import mvcAcademia.model.TreinoDAO;
import mvcAcademia.model.Pessoa;
import mvcAcademia.model.PessoaDAO;
import mvcAcademia.model.UtilPessoa;
import mvcAcademia.view.AcademiaController;

public class MenuGeralAcademia {

    private AcademiaController gui = new AcademiaController();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private AcademiaDAO academiaDAO = new AcademiaDAO();
    private ExercicioDAO exercicioDAO = new ExercicioDAO();
    private DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
    private DivisaoTreinoMusculoDAO divisaoTreinoMusculoDAO = new DivisaoTreinoMusculoDAO();
    private TreinoDAO treinoDAO = new TreinoDAO();
    private ExercicioAplicacaoDAO eaDAO = new ExercicioAplicacaoDAO();
    private AvaliacaoFisicaDAO avFisDAO = new AvaliacaoFisicaDAO();
    private MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();

    private Scanner scanner = new Scanner(System.in);
    private boolean sair = false;
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
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
                    return;
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
                    return;
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void exibirMenuAdministrador() {
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

    private ExercicioAplicacao cadastraExercicioAplicacao() {
        ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao();
        System.out.println("CADASTRO DE NOVA APLICACAO DE EXERCICIO:\n");

        System.out.println("DESCRICAO DA APLICACAO DO EXERCICIO: ");
        exercicioAplicacao.setDescricao(scanner.nextLine());

        exercicioAplicacao.setDataCriacao(LocalDateTime.now());
        exercicioAplicacao.setDataModificacao(LocalDateTime.now());

        return exercicioAplicacao;
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

    private DivisaoTreino cadastraDivisaoTreino() {
        DivisaoTreino div = new DivisaoTreino();
        System.out.println("NOME: ");
        div.setNome(scanner.nextLine());
        System.out.println("DESCRICAO: ");
        div.setDescricao(scanner.nextLine());
        div.setDataCriacao(LocalDateTime.now());
        div.setDataModificacao(LocalDateTime.now());
        return div;
    }

    private DivisaoTreinoMusculo cadastrarDivisaoTreinoMusculo() {
        DivisaoTreinoMusculo divMusculo = new DivisaoTreinoMusculo();
        System.out.println("CADASTRO DE NOVA DIVISAO DE TREINO MUSCULO:\n");

        System.out.println("NOME DA DIVISAO DE TREINO MUSCULO: ");
        divMusculo.setNome(scanner.nextLine());

        System.out.println("DESCRICAO DA DIVISAO DE TREINO MUSCULO: ");
        divMusculo.setDescricao(scanner.nextLine());

        divMusculo.setDataCriacao(LocalDateTime.now());
        divMusculo.setDataModificacao(LocalDateTime.now());

        return divMusculo;
    }

    private Treino cadastraTreino() {
        Treino novoTreino = new Treino();
        gui.exibirMensagem("CADASTRO DE TREINO\n");

        System.out.println("NOME DO TREINO: ");
        novoTreino.setNome(scanner.nextLine());

        System.out.println("DESCRICAO DO TREINO: ");
        novoTreino.setObjetivo(scanner.nextLine());

        novoTreino.setDataCriacao(LocalDateTime.now());
        novoTreino.setDataModificacao(LocalDateTime.now());

        return novoTreino;
    }
    
    private MensalidadeVigente cadastraMensalidadeVigente() {
        MensalidadeVigente novaMensalidade = new MensalidadeVigente();
        gui.exibirMensagem("CADASTRO DE MENSALIDADE VIGENTE\n");
        System.out.println("DIGITE VALOR DA MENSALIDADE DA ACADEMIA");
        novaMensalidade.setValor(scanner.nextInt());
        scanner.nextLine();
        gui.exibirMensagem("DATA DE INICIO (dd/MM/yyyy):");
        String dataInicioStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
        novaMensalidade.setDataInicio(dataInicio);
        gui.exibirMensagem("DATA DE TERMINO (dd/MM/yyyy):");
        String dataTerminoStr = scanner.nextLine();
        DateTimeFormatter formattert = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataTermino = LocalDate.parse(dataTerminoStr, formattert);
        novaMensalidade.setDataTermino(dataTermino);
        novaMensalidade.setDataCriacao(UtilPessoa.getDiaAtual());
        novaMensalidade.setDataModificacao(UtilPessoa.getDiaAtual());

        return novaMensalidade;
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
                    genreciaExercicioAplicacao();
                case 5:
                    gerenciaDivisaoTreino();
                    break;
                case 6:
                    gerenciarDivisaoTreinoMusculo();
                    break;
                case 7:
                    gerenciaTreino();
                    break;
                case 8:
                    gerenciaAvaliacaoFisica();
                    break;
                case 9:
                    gerenciaMensalidadeVigente();
                    break;
                    case 10:
                    //gerenciaPagamentoMensalidade();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                    break;
                case 11:
                    //gerenciaPagamentoRecorrente();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                    break;
                case 12:
                    //gerenciaEntradaAluno();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                    break;
                case 13:
                    //gerenciaMovimentacaoFinaceira();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");                    
                    break;
                case 14:
                    //gerenciaRelatorioAlunoAdimplentes();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                    break;
                case 15:
                    //gerenciaRelatorio();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                    break;
                case 16:
                    //gerenciaFichaDeTreino Crud treino aplicacao?
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                case 17:
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
                        System.out.println("ACADEMIA CRIADA COM SUCESSO!");
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

    private void gerenciaDivisaoTreino() {
        int resp;

        while (true) {
            resp = gui.menuCrudDivisaoTreino();

            switch (resp) {
                case 1:
                    DivisaoTreino div = this.cadastraDivisaoTreino();
                    if (divisaoTreinoDAO.adiciona(div)) {
                        System.out.println("DIVISAO DE TREINO CRIADA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO NAO CRIADA.");
                    }
                    break;
                case 2:
                    divisaoTreinoDAO.mostrarTodasDivisoesTreino();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DA DIVISAO DE TREINO QUE DESEJA ALTERAR...: ");
                    String nomeDivisaoTreino = scanner.nextLine();
                    DivisaoTreino divisaoParaAlterar = divisaoTreinoDAO.buscaPorNome(nomeDivisaoTreino);
                    if (divisaoParaAlterar != null) {
                        gui.exibirMensagem("DIGITE O NOVO NOME DA DIVISAO DE TREINO...: ");
                        String novoNomeDivisaoTreino = scanner.nextLine();
                        divisaoParaAlterar.setNome(novoNomeDivisaoTreino);
                        gui.exibirMensagem("DIGITE A NOVA DESCRICAO DA DIVISAO DE TREINO...: ");
                        String novaDescricaoDivisaoTreino = scanner.nextLine();
                        divisaoParaAlterar.setDescricao(novaDescricaoDivisaoTreino);
                        divisaoParaAlterar.setDataModificacao(LocalDateTime.now());
                        gui.exibirMensagem("DIVISAO DE TREINO ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O NOME DA DIVISAO DE TREINO QUE DESEJA EXCLUIR...: ");
                    String nomeDivisaoTreinoExcluir = scanner.nextLine();
                    DivisaoTreino divisaoParaExcluir = divisaoTreinoDAO.buscaPorNome(nomeDivisaoTreinoExcluir);
                    if (divisaoParaExcluir != null) {
                        divisaoTreinoDAO.remover(nomeDivisaoTreinoExcluir);
                        gui.exibirMensagem("DIVISAO DE TREINO EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO NAO EXISTENTE NO BANCO DE DADOS.");
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

    private void gerenciarDivisaoTreinoMusculo() {
        int resp;

        while (true) {
            resp = gui.menuCrudDivisaoTreinoMusculo();

            switch (resp) {
                case 1:
                    DivisaoTreinoMusculo divMusculo = this.cadastrarDivisaoTreinoMusculo();
                    if (divisaoTreinoMusculoDAO.adiciona(divMusculo)) {
                        System.out.println("DIVISAO DE TREINO MUSCULO CRIADA COM SUCESSO!");
                    } else {
                        System.out.println("FALHA AO CRIAR DIVISAO DE TREINO MUSCULO.");
                    }
                    break;
                case 2:
                    divisaoTreinoMusculoDAO.mostrarTodasDivisoesMusculo();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DA DIVISAO DE TREINO MUSCULO QUE DESEJA ALTERAR...: ");
                    String nomeDiv = scanner.nextLine();
                    DivisaoTreinoMusculo divParaAlterar = divisaoTreinoMusculoDAO.buscarPorNome(nomeDiv);
                    if (divParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DA DIVISAO DE TREINO MUSCULO...: ");
                        String novoNomeDivisao = scanner.nextLine();
                        divParaAlterar.setNome(novoNomeDivisao);
                        System.out.println("DIGITE A NOVA DESCRICAO DA DIVISAO DE TREINO MUSCULO...: ");
                        String novaDescricaoDivisao = scanner.nextLine();
                        divParaAlterar.setDescricao(novaDescricaoDivisao);
                        divParaAlterar.setDataModificacao(LocalDateTime.now());
                        System.out.println("DIVISAO DE TREINO MUSCULO ALTERADA COM SUCESSO!");

                    } else {
                        System.out.println("DIVISAO DE TREINO MUSCULO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O NOME DA DIVISAO DE TREINO MUSCULO QUE DESEJA EXCLUIR...: ");
                    String nomeDivExcluir = scanner.nextLine();
                    if (divisaoTreinoMusculoDAO.remover(nomeDivExcluir)) {
                        System.out.println("DIVISAO DE TREINO MUSCULO EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO MUSCULO NÃO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    System.out.println("DIVISOES DE TREINO EXISTENTES:\n");
                    divisaoTreinoDAO.mostrarTodasDivisoesTreino();
                    System.out.println("\nQUAL DIVISAO DE TREINO DESEJA GERENCIAR? DIGITE O ID:\n");
                    long idDivisaoTreino = Long.parseLong(scanner.nextLine());
                    DivisaoTreino divTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                    if (divTreino != null) {
                        System.out.println("DIVISOES DE TREINO POR GRUPO MUSCULAR:\n");
                        divisaoTreinoMusculoDAO.mostrarTodasDivisoesMusculo();
                        System.out.println("QUAL DIVISAO DE TREINO POR GRUPO MUSCULAR GOSTARIA DE INSERIR?");
                        long idDivisaoTreinoMusculo = Long.parseLong(scanner.nextLine());
                        DivisaoTreinoMusculo divTreinoMusculo = divisaoTreinoMusculoDAO.buscaPorId(idDivisaoTreinoMusculo);

                        if (divTreinoMusculo != null) {
                            divTreinoMusculo.setDivisaoTreino(divTreino);
                            if (divisaoTreinoMusculoDAO.adiciona(divTreinoMusculo)) {
                                System.out.println("DIVISAO DE TREINO MUSCULO ASSOCIADA COM SUCESSO!");
                            } else {
                                System.out.println("FALHA AO ASSOCIAR DIVISAO DE TREINO MUSCULO.");
                            }
                        } else {
                            System.out.println("DIVISAO DE TREINO MUSCULO NÃO ENCONTRADA.");
                        }
                    } else {
                        System.out.println("DIVISAO DE TREINO NÃO ENCONTRADA.");
                    }
                    break;
                case 6:
                    return;
                default:
                    gui.exibirMensagem("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void gerenciaTreino() {
        int resp;

        while (true) {
            resp = gui.menuCrudTreino();

            switch (resp) {
                case 1:
                    Treino novoTreino = this.cadastraTreino();
                    if (treinoDAO.adiciona(novoTreino)) {
                        System.out.println("TREINO CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("ERRO AO CRIAR O TREINO.");
                    }
                    break;
                case 2:
                    treinoDAO.mostrarTodosTreinos();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DO TREINO QUE DESEJA ALTERAR: ");
                    String nomeTreino = scanner.nextLine();
                    Treino treinoParaAlterar = treinoDAO.buscaPorNome(nomeTreino);
                    if (treinoParaAlterar != null) {
                        gui.exibirMensagem("DIGITE O NOVO NOME DO TREINO: ");
                        String novoNomeTreino = scanner.nextLine();
                        treinoParaAlterar.setNome(novoNomeTreino);
                        gui.exibirMensagem("DIGITE A NOVA DESCRICAO DO TREINO: ");
                        String novaDescricaoTreino = scanner.nextLine();
                        treinoParaAlterar.setObjetivo(novaDescricaoTreino);
                        treinoParaAlterar.setDataModificacao(LocalDateTime.now());
                        gui.exibirMensagem("TREINO ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("TREINO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O NOME DO TREINO QUE DESEJA EXCLUIR: ");
                    String nomeTreinoExcluir = scanner.nextLine();
                    Treino treinoParaExcluir = treinoDAO.buscaPorNome(nomeTreinoExcluir);
                    if (treinoParaExcluir != null) {
                        treinoDAO.remover(nomeTreinoExcluir);
                        System.out.println("TREINO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("TREINO NAO EXISTENTE NO BANCO DE DADOS.");
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

    private void genreciaExercicioAplicacao() {
        int resp;

        while (true) {
            resp = gui.menuCrudExercicioAplicacao();

            switch (resp) {
                case 1:
                    ExercicioAplicacao ea = this.cadastraExercicioAplicacao();
                    if (eaDAO.adiciona(ea)) {
                        System.out.println("APLICACAO CRIADA COM SUCESSO!");
                    } else {
                        System.out.println("APLICACAO NAO CRIADA.");
                    }
                    break;
                case 2:
                    eaDAO.mostrarTodasAplicacoes();
                    break;
                case 3:
                    System.out.println("DIGITE O NOME DA APLICACAO QUE DESEJA ALTERAR...: ");
                    String descAplicacao = scanner.nextLine();
                    ExercicioAplicacao descAplicacaoAlterada = eaDAO.buscaPorDescricao(descAplicacao);
                    if (descAplicacaoAlterada != null) {
                        gui.exibirMensagem("DIGITE A NOVA DESCRICAO DA APLICACAO...: ");
                        String novoNomeAplicacao = scanner.nextLine();
                        descAplicacaoAlterada.setDescricao(novoNomeAplicacao);
                        gui.exibirMensagem("DIGITE A NOVA DESCRICAO DA APLICACAO...: ");
                        String novaDescricaoExercicio = scanner.nextLine();
                        descAplicacaoAlterada.setDescricao(novaDescricaoExercicio);
                        descAplicacaoAlterada.setDataModificacao(LocalDateTime.now());
                        gui.exibirMensagem("APLICACAO ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("APLICACAO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;

                case 4:
                    System.out.println("DIGITE A DESCRICAO DA APLICACAO QUE DESEJA EXCLUIR...: ");
                    String descAplicacaoExcluir = scanner.nextLine();
                    ExercicioAplicacao apParaExcluir = eaDAO.buscaPorDescricao(descAplicacaoExcluir);
                    if (apParaExcluir != null) {
                        eaDAO.remover(descAplicacaoExcluir);
                        gui.exibirMensagem("APLICACAO EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("APLICACAO NAO EXISTENTE NO BANCO DE DADOS.");
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
    
    private void gerenciaAvaliacaoFisica() {
        AvaliacaoFisica af = new AvaliacaoFisica();
        System.out.println("AVALIACAO FISICA");
        System.out.println("A AVALIACAO FISICA CUSTA R$20,00, VOCE ESTA CIENTE (S/N)?");
        String resp = scanner.nextLine();
        
        if ("S".equals(resp)) {
            System.out.println("DIGITE SEU ID: ");
            long idPessoa = Long.parseLong(scanner.nextLine());
            Pessoa p = pessoaDAO.buscaPessoaPorId(idPessoa);
            Treino t = treinoDAO.buscaTreinoPorId(idPessoa);
            
            if (p != null) {
                pessoaDAO.mostraPessoa(idPessoa);
                scanner.nextLine();
                treinoDAO.mostraTreino(idPessoa);
                scanner.nextLine();
                System.out.println("DIGITE SEU PESO: ");
                double peso = scanner.nextDouble();
                System.out.println("DIGITE SUA ALTURA: ");
                double altura = scanner.nextDouble();
                double imc = avFisDAO.calculaIMC(peso, altura);
                scanner.nextLine();
                avFisDAO.interpretaIMC(imc);
                scanner.nextLine();
                System.out.println("QUAL O SEU INDICE DE SATISFACAO COM O RESULTADO? ");
                af.setIndiceSatisfacao(scanner.nextInt());
                scanner.nextLine();
                
                //CHAMAR METODO PARA PAGAR O DINHEIRO DA AVALIACAO
                System.out.println("OBRIGADO POR COMPRAR A AVALICAO FISICA! RETIRADO O VALOR DE R$20,00 DA SUA CONTA");
                scanner.nextLine();
                
            } 
            else {
                System.out.println("PESSOA NAO ENCONTRADA");
            }
        }
        else
            return;
}

    private void gerenciaMensalidadeVigente(){
        int resp;

        while (true) {
            resp = gui.menuCrudMensalidadeVigente();

            switch (resp) {
                case 1:
                    MensalidadeVigente mv = this.cadastraMensalidadeVigente();
                    
                    if (mvDAO.adiciona(mv)) {
                        System.out.println("MENSALIDADE VIGENTE CRIADA COM SUCESSO!");
                    } else {
                        System.out.println("MENSALIDADE VIGENTE NAO CRIADA.");
                    }
                    break;
                case 2:
                    mvDAO.mostrarTodasMensalidades();
                    break;
                case 3:
                    System.out.println("DIGITE O ID DA MENSALIDADE VIGENTE QUE DESEJA ALTERAR...: ");
                    long id = scanner.nextLong();
                    MensalidadeVigente mvParaAlterar = mvDAO.buscaMensalidadePorId(id);
                    if (mvParaAlterar != null) {
                        gui.exibirMensagem("DIGITE O NOVO VALOR DA MENSALIDADE...: ");
                        double novoValorMensalidade = scanner.nextDouble();
                        mvParaAlterar.setValor(novoValorMensalidade);
                        scanner.nextLine();
                        gui.exibirMensagem("DIGITE A NOVA DATA DE INICIO DA MENSALIDADE (dd/MM/yyyy)...: ");
                        String novaDataInicioStr = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataInicio = LocalDate.parse(novaDataInicioStr, formatter);
                        mvParaAlterar.setDataInicio(dataInicio);
                        gui.exibirMensagem("DIGITE A NOVA DATA DE DATA DE TERMINO DA MENSALIDADE(dd/MM/yyyy)...: ");
                        String novaDataTerminoStr = scanner.nextLine();
                        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataTermino = LocalDate.parse(novaDataTerminoStr, formatter);
                        mvParaAlterar.setDataTermino(dataTermino);
                        scanner.nextLine();
                        gui.exibirMensagem("MENSALIDADE ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("MENSALIDADE NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O ID DA MENSALIDADE VIGENTE QUE DESEJA EXCLUIR...: ");
                    long ide = scanner.nextLong();
                    MensalidadeVigente mvParaExcluir = mvDAO.buscaMensalidadePorId(ide);
                    if (mvParaExcluir != null) {
                        mvDAO.remover(ide);
                        gui.exibirMensagem("MENSALIDADE VIGENTE EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("MENSALIDADE VIGENTE NAO EXISTENTE NO BANCO DE DADOS.");
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
        MenuGeralAcademia menu = new MenuGeralAcademia();
        menu.iniciar();
    }
}
