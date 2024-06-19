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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mvcAcademia.model.Academia;
import mvcAcademia.model.AcademiaDAO;
import mvcAcademia.model.AlunoPagamentoMensalidade;
import mvcAcademia.model.AlunoPagamentoMensalidadeDAO;
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
import mvcAcademia.model.MovimentacaoFinanceira;
import mvcAcademia.model.MovimentacaoFinanceiraDAO;
import mvcAcademia.model.PagamentoRecorrente;
import mvcAcademia.model.PagamentoRecorrenteDAO;
import mvcAcademia.model.Treino;
import mvcAcademia.model.TreinoDAO;
import mvcAcademia.model.Pessoa;
import mvcAcademia.model.PessoaDAO;
import mvcAcademia.model.RelatorioMovimentacaoFinanceira;
import mvcAcademia.model.TreinoAplicacao;
import mvcAcademia.model.TreinoAplicacaoDAO;
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
    private TreinoAplicacaoDAO treinoAplicacaoDAO = new TreinoAplicacaoDAO();
    private ExercicioAplicacaoDAO eaDAO = new ExercicioAplicacaoDAO();
    private AvaliacaoFisicaDAO avFisDAO = new AvaliacaoFisicaDAO();
    private MensalidadeVigenteDAO mvDAO = new MensalidadeVigenteDAO();
    private PagamentoRecorrenteDAO pagamentoRecorrenteDAO = new PagamentoRecorrenteDAO();
    private AlunoPagamentoMensalidadeDAO apmDAO = new AlunoPagamentoMensalidadeDAO();
    private MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO = new MovimentacaoFinanceiraDAO();
    RelatorioMovimentacaoFinanceira relatorio = new RelatorioMovimentacaoFinanceira(movimentacaoFinanceiraDAO);

    private Scanner scanner = new Scanner(System.in);
    private boolean sair = false;
    private long pessoaLogadaId;

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

                        System.out.println("USUARIO ADICIONADO COM SUCESSO");
                    } else {
                        System.out.println("USUARIO NAO ADICIONADO.");
                    }
                    break;
                case 3:
                    System.out.println("FINALIZANDO O PROGRAMA... OBRIGADO POR UTILIZAR.");
                    sair = true;
                    break;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private boolean mensalidadeEstaEmDia(Pessoa aluno) {
        AlunoPagamentoMensalidade apm = apmDAO.buscarUltimoPagamentoPorAluno(aluno.getId());
        if (apm == null) {
            return false;
        }
        LocalDate dataVencimento = apm.getDataVencimento();
        LocalDate dataPagamento = apm.getDataPagamento();
        if (dataPagamento != null) {
            return !dataPagamento.isAfter(dataVencimento.plusDays(2));
        } else {
            return LocalDate.now().isBefore(dataVencimento.plusDays(2));
        }
    }

    private void fazerLogin() {
        System.out.println("LOGIN:");
        String login = scanner.nextLine();
        System.out.println("SENHA:");
        String senha = scanner.nextLine();
        Pessoa pessoaLogada = pessoaDAO.buscaPorLogin(login, senha);

        if (pessoaLogada != null) {
            System.out.println("USUARIO LOGADO COM SUCESSO!");
            pessoaLogadaId = pessoaLogada.getId();

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
                    System.out.println("USUARIO NAO RECONHECIDO.");
                    break;
            }
        } else {
            System.out.println("LOGIN INVALIDO, TENTE NOVAMENTE.");
        }
    }

    private void exibirMenuAluno() {
        int opcaoUsuario;

        while (true) {
            opcaoUsuario = gui.menuAluno();

            switch (opcaoUsuario) {
                case 1:
                    TreinoAplicacao fichaTreino = treinoAplicacaoDAO.buscarPorPessoaId(pessoaLogadaId);
                    if (fichaTreino != null) {
                        System.out.println(fichaTreino);
                    } else {
                        System.out.println("NENHUMA FICHA DE TREINO ASSOCIADA A ESSA PESSOA.");
                    }
                    break;
                case 2:
                    // IMPRIMIR FICHA DE TREINO?  
                    System.out.println("FICHA DE TREINO SENDO IMPRESSA............");
                    break;
                case 3:
                    AvaliacaoFisica avaliacaoFisica = avFisDAO.buscaAvaliacaoPorPessoaId(pessoaLogadaId);
                    if (avaliacaoFisica != null) {
                        System.out.println(avaliacaoFisica);
                    } else {
                        System.out.println("NENHUMA AVALIACAO FISICA ASSOCIADA A ESSA PESSOA.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                    gerenciarPessoas();
                    break;
                case 2:
                    gerenciaDivisaoTreino();
                    break;
                case 3:
                    gerenciaTreinoAplicacao();
                    break;
                case 4:
                    //gerenciaAvaliacaoFisica();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void exibirMenuAdministrador() {
        exibirMenuCRUD();
    }

    private void mostrarTodasAcademias(List<Academia> academias) {
        for (Academia academia : academias) {
            System.out.println(academia);
        }
    }

    private void mostrarTodasPessoas(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
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
        System.out.println("CADASTRO DE LOGIN:");
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

        System.out.println("CADASTRO DE PESSOA:");
        System.out.println("NOME:");
        p.setNome(scanner.nextLine());
        System.out.println("SEXO [M/F]:");
        p.setSexo(scanner.nextLine());

        System.out.println("DATA DE NASCIMENTO (dd/MM/yyyy):");
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
        System.out.println("CADASTRO DE TREINO\n");

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
        System.out.println("CADASTRO DE MENSALIDADE VIGENTE\n");
        System.out.println("DIGITE VALOR DA MENSALIDADE DA ACADEMIA");
        novaMensalidade.setValor(scanner.nextInt());
        scanner.nextLine();
        System.out.println("DATA DE INICIO (dd/MM/yyyy):");
        String dataInicioStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
        novaMensalidade.setDataInicio(dataInicio);
        System.out.println("DATA DE TERMINO (dd/MM/yyyy):");
        String dataTerminoStr = scanner.nextLine();
        DateTimeFormatter formattert = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataTermino = LocalDate.parse(dataTerminoStr, formattert);
        novaMensalidade.setDataTermino(dataTermino);
        novaMensalidade.setDataCriacao(UtilPessoa.getDiaAtual());
        novaMensalidade.setDataModificacao(UtilPessoa.getDiaAtual());

        return novaMensalidade;
    }

    /*private AlunoPagamentoMensalidade cadastraAlunoPagamentoMensalidade() {
        AlunoPagamentoMensalidade novoAPM = new AlunoPagamentoMensalidade();
        System.out.println("CADASTRO DE NOVO PAGAMENTO DE MENSALIDADE DO ALUNO:\n");
        System.out.println("DIGITE ID DO ALUNO: ");
        long idPessoa = Long.parseLong(scanner.nextLine());
        Pessoa p = pessoaDAO.buscaPorId(idPessoa);
        System.out.println("ESCOLHA O ID DA MENSALIDADE VIGENTE ASSOCIADA A ESSA PESSOA: ");
        MensalidadeVigente mv = mvDAO.mostraMensalidadeVigente(idPessoa);
        long idMensalidadeVigente = Long.parseLong(scanner.nextLine());

        LocalDateTime dataCriacao = pessoaDAO.getDataCriacaoPorId(idPessoa);
        LocalDate dataCriacaoLocalDate = dataCriacao.toLocalDate();
        UtilPessoa.incrementaMes(dataCriacaoLocalDate, 1);
        novoAPM.setDataVencimento(dataCriacaoLocalDate);

        System.out.println("VALOR PAGO: ");
        novoAPM.setValorPago(scanner.nextDouble());
        scanner.nextLine();
        System.out.println("MODALIDADE: \n1 - DINHEIRO\n2 - PIX\n3 - DEBITO AUTOMATICO\n4 - PAGAMENTO RECORRENTE");
        novoAPM.setModalidade(scanner.nextInt());
        scanner.nextLine();

        System.out.println("DIGITE A DATA DE PAGAMENTO: (dd/MM/yyyy)");//fazer metodo para verificar se a data de pagamento esta sendo paga depois da data de criacao da conta da pessoa
        String dataPagamentoStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataPagamento = LocalDate.parse(dataPagamentoStr, formatter);
        novoAPM.setDataPagamento(dataPagamento);

        novoAPM.setDataCriacao(UtilPessoa.getDiaAtual());
        novoAPM.setDataModificacao(UtilPessoa.getDiaAtual());

        return novoAPM;
    }*/
    private TreinoAplicacao cadastrarTreinoAplicacao() {
        TreinoAplicacao treinoAplicacao = new TreinoAplicacao();
        System.out.println("CADASTRO DE NOVO TREINO APLICACAO:\n");

        System.out.println("NOME DO TREINO APLICACAO: ");
        treinoAplicacao.setNome(scanner.nextLine());

        System.out.println("DESCRICAO DO TREINO APLICACAO: ");
        treinoAplicacao.setDescricao(scanner.nextLine());

        return treinoAplicacao;
    }

    /*private AvaliacaoFisica cadastraAvaliacaoFisica() {
        AvaliacaoFisica af = new AvaliacaoFisica();
        System.out.println("AVALIACAO FISICA");
        System.out.println("A AVALIACAO FISICA CUSTA R$20,00, VOCE ESTA CIENTE (S/N)?");
        String resp = scanner.nextLine();

        if ("S".equalsIgnoreCase(resp)) {
            System.out.println("DIGITE SEU ID: ");
            long idPessoa = Long.parseLong(scanner.nextLine());
            Pessoa p = pessoaDAO.buscaPessoaPorId(idPessoa);
            Treino t = treinoDAO.buscaTreinoPorId(idPessoa);

            if (p != null) {
                af.setPessoa(p);
                System.out.println("DIGITE SEU PESO: ");
                double peso = scanner.nextDouble();
                System.out.println("DIGITE SUA ALTURA: ");
                double altura = scanner.nextDouble();
                double imc = avFisDAO.calculaIMC(peso, altura);
                scanner.nextLine();
                avFisDAO.interpretaIMC(imc);
                System.out.println("QUAL O SEU INDICE DE SATISFACAO COM O RESULTADO? ");
                af.setIndiceSatisfacao(scanner.nextInt());
                scanner.nextLine();
                af.setPeso(peso);
                af.setAltura(altura);
                af.setImc(imc);
                af.setDataCriacao(new Date());
                af.setDataModificacao(new Date());
                System.out.println("OBRIGADO POR REALIZAR A AVALIACAO FISICA! SERA ADICIONADO O VALOR DE R$20,00 NA SUA MENSALIDADE!");
            } else {
                System.out.println("PESSOA NAO ENCONTRADA");
            }
        } else {
            System.out.println("AVALIACAO FISICA NAO FOI CRIADA.");
        }
        return af;
    }

    private PagamentoRecorrente cadastraPagamentoRecorrente() {
        PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente();
        System.out.println("CADASTRO DE PAGAMENTO RECORRENTE");

        System.out.println("DIGITE O ID DA PESSOA: ");
        long idPessoa = Long.parseLong(scanner.nextLine());
        Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);
        if (pessoa != null) {
            pagamentoRecorrente.setPessoa(pessoa);
        } else {
            System.out.println("PESSOA NÃO ENCONTRADA.");
            return null;
        }

        return pagamentoRecorrente;
    }*/
    private MovimentacaoFinanceira cadastraMovimentacaoFinanceira() {
        MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira();
        System.out.println("CADASTRO DE MOVIMENTACAO FINANCEIRA");
        System.out.println("DIGITE O VALOR: ");
        double valor = Double.parseDouble(scanner.nextLine());
        System.out.println("SELECIONE O TIPO DE MOVIMENTACAO (ENTRADA ou SAIDA): ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.println("DIGITE A DESCRICAO: ");
        String descricao = scanner.nextLine();

        movimentacao.setValor(valor);
        movimentacao.setTipo(tipo);
        movimentacao.setDescricao(descricao);
        movimentacao.setDataCriacao(LocalDateTime.now());
        movimentacao.setDataModificacao(LocalDateTime.now());

        return movimentacao;
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
                    break;
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
                    gerenciaTreinoAplicacao();
                    break;
                case 9:
                    //gerenciaAvaliacaoFisica();

                    break;
                case 10:
                    gerenciaMensalidadeVigente();

                    break;
                case 11:
                    //gerenciaAlunoPagamentoMensalidade();

                    break;
                case 12:
                    //gerenciaPagamentoRecorrente();
                    break;
                case 13:
                    gerenciaMovimentacaoFinanceira();
                    break;
                case 14:
                    //gerenciaRelatorioAlunoAdimplentes();
                    System.out.println("CRUD EM ESTADO DE DESENVOLVIMENTO");
                    break;
                case 15:
                    //gerenciaRelatorio();
                    break;
                case 16:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                    academiaDAO.adiciona(acad);
                    System.out.println("ACADEMIA CRIADA COM SUCESSO!");

                    break;
                case 2:
                    List<Academia> academias = academiaDAO.lista(null);
                    this.mostrarTodasAcademias(academias);
                    break;
                case 3:
                    List<Academia> academiasExistentes = academiaDAO.lista(null);
                    this.mostrarTodasAcademias(academiasExistentes);
                    System.out.println("\nDIGITE O ID ACADEMIA QUE DESEJA ALTERAR...: ");
                    Long idAcademia = Long.parseLong(scanner.nextLine());

                    Academia academiaParaAlterar = academiaDAO.buscaPorId(idAcademia);
                    if (academiaParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DA ACADEMIA: ");
                        String novoNome = scanner.nextLine();
                        System.out.println("DIGITE O NOVO ENDERECO DA ACADEMIA: ");
                        String novoEndereco = scanner.nextLine();

                        academiaParaAlterar.setNome(novoNome);
                        academiaParaAlterar.setEndereco(novoEndereco);
                        academiaParaAlterar.setDataModificacao(LocalDateTime.now());

                        academiaDAO.alterar(academiaParaAlterar);

                        System.out.println("ACADEMIA ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("ACADEMIA NAO ENCONTRADA NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    List<Academia> academiasExcluir = academiaDAO.lista(null);
                    this.mostrarTodasAcademias(academiasExcluir);
                    System.out.println("\nDIGITE O ID DA ACADEMIA QUE DESEJA EXCLUIR...: ");
                    Long idAcad = Long.parseLong(scanner.nextLine());
                    Academia academiaParaExcluir = academiaDAO.buscaPorId(idAcad);
                    if (academiaParaExcluir != null) {

                        academiaDAO.excluir(academiaParaExcluir);

                        System.out.println("ACADEMIA EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("ACADEMIA NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                        System.out.println("DIGITE O NOVO NOME DO EXERCICIO...: ");
                        String novoNomeExercicio = scanner.nextLine();
                        exParaAlterar.setNome(novoNomeExercicio);
                        System.out.println("DIGITE A NOVA DESCRICAO DO EXERCICIO...: ");
                        String novaDescricaoExercicio = scanner.nextLine();
                        exParaAlterar.setDescricao(novaDescricaoExercicio);
                        exParaAlterar.setDataModificacao(LocalDateTime.now());
                        System.out.println("EXERCICIO ALTERADA COM SUCESSO!");
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
                        System.out.println("EXERCICIO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("EXERCICIO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                    Pessoa p = this.cadastraPessoa();
                    pessoaDAO.adiciona(p);
                    System.out.println("USUARIO ADICIONADO COM SUCESSO!");

                    break;
                case 2:
                    List<Pessoa> pessoas = pessoaDAO.lista();
                    this.mostrarTodasPessoas(pessoas);
                    break;
                case 3:
                    List<Pessoa> pessoasAlterar = pessoaDAO.lista();
                    this.mostrarTodasPessoas(pessoasAlterar);
                    System.out.println("DIGITE O ID DO USUARIO QUE DESEJA ALTERAR...: ");
                    Long idPessoa = Long.parseLong(scanner.nextLine());
                    Pessoa pessoaParaAlterar = pessoaDAO.buscaPorId(idPessoa);
                    if (pessoaParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DE USUARIO...: ");
                        String novoNomeUsuario = scanner.nextLine();
                        pessoaParaAlterar.setNome(novoNomeUsuario);
                        System.out.println("DIGITE O NOVO SEXO DE USUARIO [M/F]...: ");
                        String novoSexoUsuario = scanner.nextLine();
                        pessoaParaAlterar.setSexo(novoSexoUsuario);
                        System.out.println("DIGITE A NOVA DATA DE NASCIMENTO (dd/MM/yyyy): ");
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
                        System.out.println("DIGITE O NOVO LOGIN DE USUARIO...: ");
                        String novoLoginUsuario = scanner.nextLine();
                        pessoaParaAlterar.setLogin(novoLoginUsuario);
                        System.out.println("DIGITE A NOVA SENHA DO USUARIO...: ");
                        String novaSenhaUsuario = scanner.nextLine();
                        pessoaParaAlterar.setSenha(novaSenhaUsuario);

                        pessoaParaAlterar.setDataModificacao(LocalDateTime.now());

                        pessoaDAO.alterar(pessoaParaAlterar);

                        System.out.println("USUARIO ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("USUARIO NAO EXISTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    List<Pessoa> pessoasExcluir = pessoaDAO.lista();
                    this.mostrarTodasPessoas(pessoasExcluir);
                    System.out.println("DIGITE O ID USUARIO QUE DESEJA EXCLUIR...: ");
                    Long idUsuarioExcluir = Long.parseLong(scanner.nextLine());
                    Pessoa pessoaParaExcluir = pessoaDAO.buscaPorId(idUsuarioExcluir);
                    if (pessoaParaExcluir != null) {
                        pessoaDAO.remover(idUsuarioExcluir);
                        System.out.println("USUARIO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("USUARIO NAO EXISTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                        System.out.println("DIGITE O NOVO NOME DA DIVISAO DE TREINO...: ");
                        String novoNomeDivisaoTreino = scanner.nextLine();
                        divisaoParaAlterar.setNome(novoNomeDivisaoTreino);
                        System.out.println("DIGITE A NOVA DESCRICAO DA DIVISAO DE TREINO...: ");
                        String novaDescricaoDivisaoTreino = scanner.nextLine();
                        divisaoParaAlterar.setDescricao(novaDescricaoDivisaoTreino);
                        divisaoParaAlterar.setDataModificacao(LocalDateTime.now());
                        System.out.println("DIVISAO DE TREINO ALTERADA COM SUCESSO!");
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
                        System.out.println("DIVISAO DE TREINO EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    private void gerenciaTreinoAplicacao() {
        int resp;

        while (true) {
            resp = gui.menuCrudTreinoAplicacao();

            switch (resp) {
                case 1:
                    TreinoAplicacao treinoAplicacao = this.cadastrarTreinoAplicacao();
                    if (treinoAplicacaoDAO.adiciona(treinoAplicacao)) {
                        System.out.println("TREINO APLICACAO CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("FALHA AO CRIAR TREINO APLICACAO.");
                    }
                    break;
                case 2:
                    treinoAplicacaoDAO.mostrarTodosTreinosAplicacoes();
                    break;
                case 3:
                    System.out.println("DIGITE O ID DO TREINO APLICACAO QUE DESEJA ALTERAR...: ");
                    long idAplicacao = Long.parseLong(scanner.nextLine());
                    TreinoAplicacao aplicacaoParaAlterar = treinoAplicacaoDAO.buscarPorId(idAplicacao);
                    if (aplicacaoParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DO TREINO APLICACAO...: ");
                        String novoNomeAplicacao = scanner.nextLine();
                        aplicacaoParaAlterar.setNome(novoNomeAplicacao);
                        System.out.println("DIGITE A NOVA DESCRICAO DO TREINO APLICACAO...: ");
                        String novaDescricaoAplicacao = scanner.nextLine();
                        aplicacaoParaAlterar.setDescricao(novaDescricaoAplicacao);
                        System.out.println("TREINO APLICACAO ALTERADO COM SUCESSO!");

                    } else {
                        System.out.println("TREINO APLICACAO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O ID DO TREINO APLICACAO QUE DESEJA EXCLUIR...: ");
                    long idAplicacaoExcluir = Long.parseLong(scanner.nextLine());
                    if (treinoAplicacaoDAO.remover(idAplicacaoExcluir)) {
                        System.out.println("TREINO APLICACAO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("TREINO APLICACAO NÃO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                /*case 5:
                    treinoAplicacaoDAO.mostrarTodosTreinosAplicacoes();
                    System.out.println("\nQUAL TREINO APLICACAO DESEJA GERENCIAR? DIGITE O ID:\n");
                    long idTreinoAplicacaoAssociar = Long.parseLong(scanner.nextLine());
                    TreinoAplicacao treinoAplicacaoAssociar = treinoAplicacaoDAO.buscarPorId(idTreinoAplicacaoAssociar);

                    if (treinoAplicacaoAssociar != null) {
                        System.out.println("ACADEMIAS EXISTENTES:\n");
                        academiaDAO.mostrarTodasAcademias();
                        System.out.println("QUAL ACADEMIA DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idAcademia = Long.parseLong(scanner.nextLine());
                        Academia academia = academiaDAO.buscaPorId(idAcademia);
                        if (academia != null) {
                            treinoAplicacaoAssociar.setAcademia(academia);
                        } else {
                            System.out.println("ACADEMIA NÃO ENCONTRADA.");
                            break;
                        }

                        // Associar Pessoa
                        System.out.println("PESSOAS EXISTENTES:\n");
                        pessoaDAO.mostrarTodos();
                        System.out.println("QUAL PESSOA DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idPessoa = Long.parseLong(scanner.nextLine());
                        Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);
                        if (pessoa != null) {
                            treinoAplicacaoAssociar.setPessoa(pessoa);
                        } else {
                            System.out.println("PESSOA NÃO ENCONTRADA.");
                            break;
                        }

                        System.out.println("TREINOS EXISTENTES:\n");
                        treinoDAO.mostrarTodosTreinos();
                        System.out.println("QUAL TREINO DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idTreino = Long.parseLong(scanner.nextLine());
                        Treino treino = treinoDAO.buscaTreinoPorId(idTreino);
                        if (treino != null) {
                            treinoAplicacaoAssociar.setTreino(treino);
                        } else {
                            System.out.println("TREINO NÃO ENCONTRADO.");
                            break;
                        }

                        System.out.println("EXERCICIOS EXISTENTES:\n");
                        exercicioDAO.mostrarTodosExercicios();
                        System.out.println("QUAL EXERCICIO DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idExercicio = Long.parseLong(scanner.nextLine());
                        Exercicio exercicio = exercicioDAO.buscaPorId(idExercicio);
                        if (exercicio != null) {
                            treinoAplicacaoAssociar.setExercicio(exercicio);
                        } else {
                            System.out.println("EXERCICIO NÃO ENCONTRADO.");
                            break;
                        }

                        System.out.println("DIVISOES DE TREINO EXISTENTES:\n");
                        divisaoTreinoDAO.mostrarTodasDivisoesTreino();
                        System.out.println("QUAL DIVISAO DE TREINO DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idDivisaoTreino = Long.parseLong(scanner.nextLine());
                        DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);
                        if (divisaoTreino != null) {
                            treinoAplicacaoAssociar.setDivTreino(divisaoTreino);
                        } else {
                            System.out.println("DIVISAO DE TREINO NÃO ENCONTRADA.");
                            break;
                        }

                        System.out.println("DIVISOES DE TREINO MUSCULO EXISTENTES:\n");
                        divisaoTreinoMusculoDAO.mostrarTodasDivisoesMusculo();
                        System.out.println("QUAL DIVISAO DE TREINO MUSCULO DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idDivisaoTreinoMusculo = Long.parseLong(scanner.nextLine());
                        DivisaoTreinoMusculo divisaoTreinoMusculo = divisaoTreinoMusculoDAO.buscaPorId(idDivisaoTreinoMusculo);

                        if (academia != null && pessoa != null && treino != null && exercicio != null && divisaoTreino != null && divisaoTreinoMusculo != null) {
                            treinoAplicacaoAssociar.setAcademia(academia);
                            treinoAplicacaoAssociar.setPessoa(pessoa);
                            treinoAplicacaoAssociar.setTreino(treino);
                            treinoAplicacaoAssociar.setExercicio(exercicio);
                            treinoAplicacaoAssociar.setDivTreino(divisaoTreino);
                            treinoAplicacaoAssociar.setDivTreinoMusc(divisaoTreinoMusculo);
                            System.out.println("ASSOCIACOES REALIZADAS COM SUCESSO!");
                        } else {
                            System.out.println("FALHA AO REALIZAR ASSOCIACOES. CERTIFIQUE-SE DE QUE TODOS OS DADOS EXISTEM.");
                        }
                    } else {
                        System.out.println("TREINO APLICACAO NÃO ENCONTRADO.");
                    }*/

                //break;
                case 6:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                        System.out.println("DIGITE O NOVO NOME DO TREINO: ");
                        String novoNomeTreino = scanner.nextLine();
                        treinoParaAlterar.setNome(novoNomeTreino);
                        System.out.println("DIGITE A NOVA DESCRICAO DO TREINO: ");
                        String novaDescricaoTreino = scanner.nextLine();
                        treinoParaAlterar.setObjetivo(novaDescricaoTreino);
                        treinoParaAlterar.setDataModificacao(LocalDateTime.now());
                        System.out.println("TREINO ALTERADO COM SUCESSO!");
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
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
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
                        System.out.println("DIGITE A NOVA DESCRICAO DA APLICACAO...: ");
                        String novoNomeAplicacao = scanner.nextLine();
                        descAplicacaoAlterada.setDescricao(novoNomeAplicacao);
                        System.out.println("DIGITE A NOVA DESCRICAO DA APLICACAO...: ");
                        String novaDescricaoExercicio = scanner.nextLine();
                        descAplicacaoAlterada.setDescricao(novaDescricaoExercicio);
                        descAplicacaoAlterada.setDataModificacao(LocalDateTime.now());
                        System.out.println("APLICACAO ALTERADA COM SUCESSO!");
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
                        System.out.println("APLICACAO EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("APLICACAO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    /*private void gerenciaAvaliacaoFisica() {
        int resp;

        while (true) {
            resp = gui.menuCrudAvaliacaoFisica();

            switch (resp) {
                case 1:
                    System.out.println("A AVALIACAO FISICA CUSTA R$20,00, VOCE ESTA CIENTE (S/N)?");
                    String respCriacao = scanner.nextLine();

                    if ("S".equalsIgnoreCase(respCriacao)) {

                        AvaliacaoFisica novaAvaliacao = this.cadastraAvaliacaoFisica();
                        if (avFisDAO.adiciona(novaAvaliacao)) {
                            System.out.println("AVALIACAO FISICA CRIADA COM SUCESSO!");
                        } else {
                            System.out.println("ERRO AO CRIAR A AVALIACAO FISICA.");
                        }
                    } else {
                        System.out.println("AVALIACAO FISICA NAO FOI CRIADA.");
                    }
                    break;
                case 2:
                    // Mostrar Todas as Avaliações Físicas
                    avFisDAO.mostrarAvaliacaoFisica();
                    break;
                case 3:
                    // Alterar Avaliação Física
                    System.out.println("DIGITE O ID DA AVALIACAO FISICA QUE DESEJA ALTERAR: ");
                    long idAvaliacao = Long.parseLong(scanner.nextLine());
                    AvaliacaoFisica avaliacaoParaAlterar = avFisDAO.buscaAvaliacaoID(idAvaliacao);
                    if (avaliacaoParaAlterar != null) {
                        System.out.println("DIGITE O NOVO PESO: ");
                        double novoPeso = scanner.nextDouble();
                        System.out.println("DIGITE A NOVA ALTURA: ");
                        double novaAltura = scanner.nextDouble();
                        double novoImc = avFisDAO.calculaIMC(novoPeso, novaAltura);
                        scanner.nextLine();
                        avFisDAO.interpretaIMC(novoImc);
                        System.out.println("QUAL O NOVO INDICE DE SATISFACAO? ");
                        int novoIndiceSatisfacao = scanner.nextInt();
                        scanner.nextLine();
                        avaliacaoParaAlterar.setPeso(novoPeso);
                        avaliacaoParaAlterar.setAltura(novaAltura);
                        avaliacaoParaAlterar.setImc(novoImc);
                        avaliacaoParaAlterar.setIndiceSatisfacao(novoIndiceSatisfacao);
                        avaliacaoParaAlterar.setDataModificacao(new Date());
                        System.out.println("AVALIACAO FISICA ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("AVALIACAO FISICA NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    // Excluir Avaliação Física
                    System.out.println("DIGITE O ID DA AVALIACAO FISICA QUE DESEJA EXCLUIR: ");
                    long idAvaliacaoExcluir = Long.parseLong(scanner.nextLine());
                    AvaliacaoFisica avaliacaoParaExcluir = avFisDAO.buscaAvaliacaoID(idAvaliacaoExcluir);
                    if (avaliacaoParaExcluir != null) {
                        avFisDAO.remover(avaliacaoParaExcluir.getId());
                        System.out.println("AVALIACAO FISICA EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("AVALIACAO FISICA NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    System.out.println("DIGITE O ID DA PESSOA PARA ASSOCIAR A AVALIACAO FISICA: ");
                    long idPessoa = Long.parseLong(scanner.nextLine());
                    Pessoa pessoaParaAssociar = pessoaDAO.buscaPessoaPorId(idPessoa);

                    if (pessoaParaAssociar != null) {
                        System.out.println("AVALIACOES FISICAS EXISTENTES:\n");
                        avFisDAO.mostrarAvaliacaoFisica();
                        System.out.println("\nQUAL AVALIACAO FISICA DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idAvaliacaoAssociar = Long.parseLong(scanner.nextLine());
                        AvaliacaoFisica avaliacaoAssociar = avFisDAO.buscaAvaliacaoID(idAvaliacaoAssociar);

                        if (avaliacaoAssociar != null) {
                            avaliacaoAssociar.setPessoa(pessoaParaAssociar);
                            System.out.println("AVALIACAO FISICA ASSOCIADA COM SUCESSO!");
                        } else {
                            System.out.println("AVALIACAO FISICA NAO ENCONTRADA.");
                        }
                    } else {
                        System.out.println("PESSOA NAO ENCONTRADA.");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }*/
    private void gerenciaMensalidadeVigente() {
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
                        System.out.println("DIGITE O NOVO VALOR DA MENSALIDADE...: ");
                        double novoValorMensalidade = scanner.nextDouble();
                        mvParaAlterar.setValor(novoValorMensalidade);
                        scanner.nextLine();
                        System.out.println("DIGITE A NOVA DATA DE INICIO DA MENSALIDADE (dd/MM/yyyy)...: ");
                        String novaDataInicioStr = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataInicio = LocalDate.parse(novaDataInicioStr, formatter);
                        mvParaAlterar.setDataInicio(dataInicio);
                        System.out.println("DIGITE A NOVA DATA DE DATA DE TERMINO DA MENSALIDADE(dd/MM/yyyy)...: ");
                        String novaDataTerminoStr = scanner.nextLine();
                        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataTermino = LocalDate.parse(novaDataTerminoStr, formatter);
                        mvParaAlterar.setDataTermino(dataTermino);
                        scanner.nextLine();
                        System.out.println("MENSALIDADE ALTERADA COM SUCESSO!");
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
                        System.out.println("MENSALIDADE VIGENTE EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("MENSALIDADE VIGENTE NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }

    /*private void gerenciaPagamentoRecorrente() {
        int resp;

        while (true) {
            resp = gui.menuCrudPagamentoRecorrente();

            switch (resp) {
                case 1:
                    PagamentoRecorrente novoPagamento = this.cadastraPagamentoRecorrente();
                    if (pagamentoRecorrenteDAO.adiciona(novoPagamento)) {
                        System.out.println("PAGAMENTO RECORRENTE CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("ERRO AO CRIAR O PAGAMENTO RECORRENTE.");
                    }
                    break;
                case 2:
                    PagamentoRecorrente[] pagamentos = pagamentoRecorrenteDAO.mostrarTodosPagamentos();
                    if (pagamentos.length > 0) {
                        System.out.println("PAGAMENTOS RECORRENTES:");
                        for (PagamentoRecorrente pagamento : pagamentos) {
                            System.out.println(pagamento);
                        }
                    } else {
                        System.out.println("NÃO HÁ PAGAMENTOS RECORRENTES REGISTRADOS.");
                    }
                    break;
                case 3:
                    System.out.println("DIGITE O ID DO PAGAMENTO RECORRENTE QUE DESEJA ALTERAR: ");
                    int idPagamento = Integer.parseInt(scanner.nextLine());
                    PagamentoRecorrente pagamentoParaAlterar = pagamentoRecorrenteDAO.buscaPagamentoPorId(idPagamento);
                    if (pagamentoParaAlterar != null) {
                        System.out.println("IMPLEMENTAR LÓGICA PARA ALTERAR O PAGAMENTO RECORRENTE.");
                    } else {
                        System.out.println("PAGAMENTO RECORRENTE NÃO ENCONTRADO.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O ID DO PAGAMENTO RECORRENTE QUE DESEJA EXCLUIR: ");
                    int idPagamentoExcluir = Integer.parseInt(scanner.nextLine());
                    if (pagamentoRecorrenteDAO.remover(idPagamentoExcluir)) {
                        System.out.println("PAGAMENTO RECORRENTE EXCLUÍDO COM SUCESSO!");
                    } else {
                        System.out.println("PAGAMENTO RECORRENTE NÃO ENCONTRADO.");
                    }
                    break;
                case 5:
                    System.out.println("DIGITE O ID DA PESSOA PARA ASSOCIAR AO PAGAMENTO RECORRENTE: ");
                    long idPessoa = Long.parseLong(scanner.nextLine());
                    Pessoa pessoaParaAssociar = pessoaDAO.buscaPessoaPorId(idPessoa);

                    if (pessoaParaAssociar != null) {
                        System.out.println("PAGAMENTOS RECORRENTES EXISTENTES:\n");
                        pagamentoRecorrenteDAO.mostrarTodosPagamentos();
                        System.out.println("\nQUAL PAGAMENTO RECORRENTE DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idPagamentoAssociar = Long.parseLong(scanner.nextLine());
                        PagamentoRecorrente pagamentoAssociar = pagamentoRecorrenteDAO.buscaPagamentoPorId(idPagamentoAssociar);

                        if (pagamentoAssociar != null) {
                            pagamentoAssociar.setPessoa(pessoaParaAssociar);
                            System.out.println("PAGAMENTO RECORRENTE ASSOCIADO COM SUCESSO!");
                        } else {
                            System.out.println("PAGAMENTO RECORRENTE NAO ENCONTRADO.");
                        }
                    } else {
                        System.out.println("PESSOA NAO ENCONTRADA.");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPÇÃO VÁLIDA.");
                    break;
            }
        }
    }*/
    private void gerenciaMovimentacaoFinanceira() {
        int opcao;

        while (true) {
            opcao = gui.menuCrudMovimentacaoFinanceira();

            switch (opcao) {
                case 1:
                    MovimentacaoFinanceira novaMovimentacao = this.cadastraMovimentacaoFinanceira();
                    if (movimentacaoFinanceiraDAO.adiciona(novaMovimentacao)) {
                        System.out.println("Movimentação financeira cadastrada com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar movimentação financeira.");
                    }
                    break;
                case 2:
                    movimentacaoFinanceiraDAO.mostrarTodas();
                    break;
                case 3:
                    System.out.println("Digite o ID da movimentação financeira que deseja alterar: ");
                    long idMovimentacao = Long.parseLong(scanner.nextLine());
                    MovimentacaoFinanceira movimentacaoParaAlterar = movimentacaoFinanceiraDAO.buscarMovimentacaoPorId(idMovimentacao);
                    if (movimentacaoParaAlterar != null) {
                        System.out.println("Digite o novo valor: ");
                        double novoValor = Double.parseDouble(scanner.nextLine());
                        System.out.println("Digite a nova descrição: ");
                        String novaDescricao = scanner.nextLine();
                        movimentacaoParaAlterar.setValor(novoValor);
                        movimentacaoParaAlterar.setDescricao(novaDescricao);
                        movimentacaoParaAlterar.setDataModificacao(LocalDateTime.now());
                        System.out.println("Movimentação financeira alterada com sucesso!");
                    } else {
                        System.out.println("Movimentação financeira não encontrada.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o ID da movimentação financeira que deseja excluir: ");
                    long idMovimentacaoExcluir = Long.parseLong(scanner.nextLine());
                    MovimentacaoFinanceira movimentacaoParaExcluir = movimentacaoFinanceiraDAO.buscarMovimentacaoPorId(idMovimentacaoExcluir);
                    if (movimentacaoParaExcluir != null) {
                        movimentacaoFinanceiraDAO.remover(idMovimentacaoExcluir);
                        System.out.println("Movimentação financeira excluída com sucesso!");
                    } else {
                        System.out.println("Movimentação financeira não encontrada.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Escolha uma opção válida.");
                    break;
            }
        }
    }

    /*private void gerenciaAlunoPagamentoMensalidade() {
        int resp;

        while (true) {
            resp = gui.menuCrudAlunoPagamentoMensalidade();

            switch (resp) {
                case 1:
                    AlunoPagamentoMensalidade apm = this.cadastraAlunoPagamentoMensalidade();
                    if (apmDAO.adiciona(apm)) {
                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("FALHA AO CRIAR PAGAMENTO DA MENSALIDADE DO ALUNO.");
                    }
                    break;
                case 2:
                    apmDAO.mostrarTodos();
                    break;
                case 3:
                    System.out.println("DIGITE O ID DO PAGAMENTO DA MENSALIDADE DO ALUNO QUE DESEJA ALTERAR...: ");
                    long id = scanner.nextLong();
                    AlunoPagamentoMensalidade apmParaAlterar = apmDAO.buscaAlunoPagamentoMensalidadePorId(id);
                    if (apmParaAlterar != null) {
                        System.out.println("DIGITE O NOVO VALOR QUE IRA PAGAR...: ");
                        double novoValorAPM = scanner.nextDouble();
                        apmParaAlterar.setValorPago(novoValorAPM);
                        scanner.nextLine();

                        System.out.println("MODALIDADE: \n0 - DINHEIRO\n2 - PIX\n3 - DEBITO AUTOMATICO\n4 - PAGAMENTO RECORRENTE");
                        int novaModalidade = scanner.nextInt();
                        apmParaAlterar.setModalidade(novaModalidade);

                        System.out.println("DIGITE A NOVA DATA DE PAGAMENTO (dd/MM/yyyy)...: ");
                        String novaDataPagamentoStr = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataPagamento = LocalDate.parse(novaDataPagamentoStr, formatter);
                        apmParaAlterar.setDataPagamento(dataPagamento);

                        apmParaAlterar.setDataCriacao(UtilPessoa.getDiaAtual());
                        apmParaAlterar.setDataModificacao(UtilPessoa.getDiaAtual());

                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    System.out.println("DIGITE O ID DO PAGAMENTO DA MENSALIDADE DO ALUNO QUE DESEJA EXCLUIR...: ");
                    long ide = scanner.nextLong();
                    AlunoPagamentoMensalidade apmParaExcluir = apmDAO.buscaAlunoPagamentoMensalidadePorId(ide);
                    if (apmParaExcluir != null) {
                        mvDAO.remover(ide);
                        System.out.println("MENSALIDADE VIGENTE EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("MENSALIDADE VIGENTE NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("ESCOLHA UMA OPCAO VALIDA.");
                    break;
            }
        }
    }*/
    private void gerenciaRelatorio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DIGITE O MES (1 A 12): ");
        int MES = 0;
        if (scanner.hasNextInt()) {
            MES = scanner.nextInt();
        } else {
            System.out.println("ENTRADA INVALIDA PARA O MES.");
            return;
        }

        System.out.println("DIGITE O ANO: ");
        int ANO = 0;
        if (scanner.hasNextInt()) {
            ANO = scanner.nextInt();
        } else {
            System.out.println("ENTRADA INVALIDA PARA O ANO.");
            return;
        }
        relatorio.exibirRelatorio(MES, ANO);

        scanner.close();
    }

    public static void main(String[] args) {
        MenuGeralAcademia menu = new MenuGeralAcademia();
        menu.iniciar();
    }
}
