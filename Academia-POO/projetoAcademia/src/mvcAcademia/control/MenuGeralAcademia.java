/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.control;

import java.sql.SQLException;
/**
 *
 * @author barbrete e kitotsui
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mvcAcademia.model.Academia;
import mvcAcademia.model.AcademiaDAO;
import mvcAcademia.model.AlunoPagamentoMensalidade;
import mvcAcademia.model.AlunoPagamentoMensalidadeDAO;
import mvcAcademia.model.AvaliacaoFisica;
import mvcAcademia.model.AvaliacaoFisicaDAO;
import mvcAcademia.model.Calendario;
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
import mvcAcademia.model.RelatorioAlunoAdimplentes;
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
    private RelatorioMovimentacaoFinanceira relatorioMov = new RelatorioMovimentacaoFinanceira(movimentacaoFinanceiraDAO);
    private RelatorioAlunoAdimplentes relatorioAluno = new RelatorioAlunoAdimplentes(apmDAO);

    private Scanner scanner = new Scanner(System.in);
    private boolean sair = false;
    private long pessoaLogadaId;

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void iniciar() {
        int opcaoUsuario = 0;
        boolean resp = false;

        while (!sair) {

            try {
                opcaoUsuario = gui.menuBemVindo();
                resp = true;
            } catch (NumberFormatException e) {
                System.out.println("ENTRADA INVALIDA.");
            }

            switch (opcaoUsuario) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    Pessoa pu = this.cadastrarLogin();
                    long idPessoa = pessoaDAO.adiciona(pu);

                    if (idPessoa != -1) {
                        System.out.println("USUARIO ADICIONADO COM SUCESSO");

                        System.out.println("DESEJA REGISTRAR UM PAGAMENTO DE MENSALIDADE?");
                        System.out.println("1 - SIM");
                        System.out.println("2 - NAO");
                        int opcaoPagamento = Integer.parseInt(scanner.nextLine());

                        if (opcaoPagamento == 1) {
                            this.registrarPagamentoMensalidadePorId(idPessoa);
                        }
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
        AlunoPagamentoMensalidade apm = apmDAO.buscarUltimoPagamentoDoAluno(aluno.getId());
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

    private boolean pagamentoRecorrenteEstaEmDia(Pessoa aluno) {
        PagamentoRecorrente pagamentoRecorrente = pagamentoRecorrenteDAO.buscarUltimoPagamentoDoAluno(aluno.getId());
        if (pagamentoRecorrente == null) {
            return false;
        }
        LocalDate dataVencimento = pagamentoRecorrente.getDataVencimento();
        return LocalDate.now().isBefore(dataVencimento.plusDays(2));
    }

    private void registrarPagamentoMensalidade(Pessoa p) {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();
        LocalDate dataAtual = LocalDate.now();

        List<MensalidadeVigente> mensalidadesVigentes = mvDAO.lista();
        MensalidadeVigente mensalidadeVigenteAtual = null;
        for (MensalidadeVigente mv : mensalidadesVigentes) {
            if ((dataAtual.isAfter(mv.getDataInicio()) || dataAtual.isEqual(mv.getDataInicio()))
                    && (dataAtual.isBefore(mv.getDataTermino()) || dataAtual.isEqual(mv.getDataTermino()))) {
                mensalidadeVigenteAtual = mv;
                break;
            }
        }

        if (mensalidadeVigenteAtual == null) {
            System.out.println("NENHUMA MENSALIDADE VIGENTE ENCONTRADA PARA A DATA ATUAL.");
            return;
        }

        System.out.println("MENSALIDADE VIGENTE ATUAL: " + mensalidadeVigenteAtual.getValor());
        apm.setMvAlunoPagamento(mensalidadeVigenteAtual);
        apm.setDataVencimento(mensalidadeVigenteAtual.getDataTermino());
        apm.setDataPagamento(dataAtual);
        apm.setValorPago(mensalidadeVigenteAtual.getValor());

        System.out.println("MODALIDADE: \n0 - DINHEIRO\n2 - PIX\n3 - DEBITO AUTOMATICO\n4 - PAGAMENTO RECORRENTE");
        int modalidade = Integer.parseInt(scanner.nextLine());

        apm.setAluno(p);
        apm.setModalidade(modalidade);
        apm.setDataCriacao(LocalDateTime.now());
        apm.setDataModificacao(LocalDateTime.now());

        if (modalidade == 4) {
            PagamentoRecorrente novoPagamentoRecorrente = cadastraPagamentoRecorrente();
            pagamentoRecorrenteDAO.adiciona(novoPagamentoRecorrente);
        } else {
            if (apmDAO.adiciona(apm)) {
                System.out.println("PAGAMENTO DA MENSALIDADE REGISTRADO COM SUCESSO!");
            } else {
                System.out.println("FALHA AO REGISTRAR O PAGAMENTO DA MENSALIDADE.");
            }
        }
    }

    private void registrarPagamentoMensalidadePorId(long idPessoa) {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();
        LocalDate dataAtual = LocalDate.now();
        Pessoa p = pessoaDAO.buscaPorId(idPessoa);
        if (p == null) {
            System.out.println("PESSOA NÃO ENCONTRADA.");
            return;
        }

        List<MensalidadeVigente> mensalidadesVigentes = mvDAO.lista();
        MensalidadeVigente mensalidadeVigenteAtual = null;
        for (MensalidadeVigente mv : mensalidadesVigentes) {
            if ((dataAtual.isAfter(mv.getDataInicio()) || dataAtual.isEqual(mv.getDataInicio()))
                    && (dataAtual.isBefore(mv.getDataTermino()) || dataAtual.isEqual(mv.getDataTermino()))) {
                mensalidadeVigenteAtual = mv;
                break;
            }
        }

        if (mensalidadeVigenteAtual == null) {
            System.out.println("NENHUMA MENSALIDADE VIGENTE ENCONTRADA PARA A DATA ATUAL.");
            return;
        }

        System.out.println("MENSALIDADE VIGENTE ATUAL: " + mensalidadeVigenteAtual.getValor());
        apm.setMvAlunoPagamento(mensalidadeVigenteAtual);
        apm.setDataVencimento(mensalidadeVigenteAtual.getDataTermino());
        apm.setDataPagamento(dataAtual);
        apm.setValorPago(mensalidadeVigenteAtual.getValor());

        System.out.println("MODALIDADE: \n0 - DINHEIRO\n2 - PIX\n3 - DEBITO AUTOMATICO\n4 - PAGAMENTO RECORRENTE");
        int modalidade = Integer.parseInt(scanner.nextLine());

        apm.setAluno(p);
        apm.setModalidade(modalidade);
        apm.setDataCriacao(LocalDateTime.now());
        apm.setDataModificacao(LocalDateTime.now());

        if (modalidade == 4) {
            PagamentoRecorrente novoPagamentoRecorrente = cadastraPagamentoRecorrente();
            pagamentoRecorrenteDAO.adiciona(novoPagamentoRecorrente);
        } else {
            if (apmDAO.adiciona(apm)) {
                System.out.println("PAGAMENTO DA MENSALIDADE REGISTRADO COM SUCESSO!");
            } else {
                System.out.println("FALHA AO REGISTRAR O PAGAMENTO DA MENSALIDADE.");
            }
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

            if (pessoaLogada.getTipoUsuario().equals("Aluno")) {
                if (mensalidadeEstaEmDia(pessoaLogada) || pagamentoRecorrenteEstaEmDia(pessoaLogada)) {
                    System.out.println("╔═════════════════════════════╗");
                    System.out.println("║    MENSALIDADE EM DIA!  ║");
                    System.out.println("║                         ║");
                    System.out.println("║     ACESSO LIBERADO!    ║");
                    System.out.println("╚═════════════════════════════╝");

                    exibirMenuAluno();
                } else {
                    System.out.println("╔══════════════════════════════════════════╗");
                    System.out.println("║        MENSALIDADE ATRASADA!       ║");
                    System.out.println("║                                    ║");
                    System.out.println("║PROCURE A RECEPÇÃO PARA REGULARIZAR.║");
                    System.out.println("╚══════════════════════════════════════════╝");

                    System.out.println("");

                }
            } else {
                switch (pessoaLogada.getTipoUsuario()) {
                    case "Administrador":
                        exibirMenuAdministrador();
                        break;
                    case "Professor":
                        exibirMenuProfessor();
                        break;
                    default:
                        System.out.println("USUARIO NAO RECONHECIDO.");
                        break;
                }
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
                    TreinoAplicacao fichaTreino = treinoAplicacaoDAO.buscaPorId(pessoaLogadaId);
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
                    AvaliacaoFisica avaliacaoFisica = avFisDAO.buscaPorId(pessoaLogadaId);
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
                    gerenciarDivisaoTreino();
                    break;
                case 3:
                    gerenciarTreinoAplicacao();
                    break;
                case 4:
                    gerenciaAvaliacaoFisica();
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

    private void mostrarTodasAcademias() {
        List<Academia> academias = academiaDAO.lista();
        if (!academias.isEmpty()) {
            for (Academia academia : academias) {
                System.out.println(academia);
            }
        } else {
            System.out.println("NAO EXISTEM ACADEMIAS CADASTRADAS.");
        }
    }

    private void mostrarTodasPessoas() {
        List<Pessoa> pessoas = pessoaDAO.lista();
        if (!pessoas.isEmpty()) {
            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa);
            }
        } else {
            System.out.println("NAO EXISTEM PESSOAS CADASTRADAS.");
        }
    }

    private void mostrarTodosExercicios() {
        List<Exercicio> exercicios = exercicioDAO.lista();
        if (!exercicios.isEmpty()) {
            for (Exercicio exercicio : exercicios) {
                System.out.println(exercicio);
            }
        } else {
            System.out.println("NAO EXISTEM EXERCICIOS CADASTRADOS.");
        }
    }

    private void mostrarTodosExerciciosAplicacao() {
        List<ExercicioAplicacao> exerciciosAplicacao = eaDAO.lista();

        if (!exerciciosAplicacao.isEmpty()) {
            for (ExercicioAplicacao ea : exerciciosAplicacao) {
                System.out.println(ea);
            }
        } else {
            System.out.println("NAO EXISTEM APLICACOES DE EXERCICIO CADASTRADAS.");
        }
    }

    private void mostrarTodasDivisoesTreino() {
        List<DivisaoTreino> divisoes = divisaoTreinoDAO.lista();

        if (divisoes.isEmpty()) {
            System.out.println("NAO EXISTEM DIVISOES DE TREINO CADASTRADAS.");
        } else {
            for (DivisaoTreino dt : divisoes) {
                System.out.println(dt);
            }
        }
    }

    public void mostrarTodasDivisoesTreinoMusculo() {
        List<DivisaoTreinoMusculo> divisoesMusculo = divisaoTreinoMusculoDAO.lista();

        if (divisoesMusculo.isEmpty()) {
            System.out.println("NÃO EXISTEM DIVISÕES DE TREINO MUSCULO CADASTRADAS.");
        } else {
            for (DivisaoTreinoMusculo dtm : divisoesMusculo) {
                System.out.println(dtm);
            }
        }
    }

    public void mostrarTodosTreinos() {
        List<Treino> treinos = treinoDAO.lista();

        if (treinos.isEmpty()) {
            System.out.println("NÃO EXISTEM TREINOS CADASTRADOS.");
        } else {
            for (Treino treino : treinos) {
                System.out.println(treino);
            }
        }
    }

    private void mostrarTreinoAplicacao() {
        List<TreinoAplicacao> treinoAplicacoes = treinoAplicacaoDAO.listarTodos();

        if (treinoAplicacoes.isEmpty()) {
            System.out.println("NAO EXISTEM TREINOS APLICACAO CADASTRADOS.");
        } else {
            for (TreinoAplicacao ta : treinoAplicacoes) {
                System.out.println(ta);
            }
        }
    }

    private void mostrarMensalidadesVigentes() {
        List<MensalidadeVigente> mensalidades = mvDAO.lista();
        if (mensalidades.isEmpty()) {
            System.out.println("NENHUMA MENSALIDADE VIGENTE CADASTRADA.");
        } else {
            for (MensalidadeVigente mv : mensalidades) {
                System.out.println(mv);
            }
        }
    }

    private void mostrarPagamentosMensalidade() {
        List<AlunoPagamentoMensalidade> pagamentos = apmDAO.lista();
        if (pagamentos.isEmpty()) {
            System.out.println("NENHUM PAGAMENTO REGISTRADO.");
        } else {
            for (AlunoPagamentoMensalidade pagamento : pagamentos) {
                System.out.println(pagamento);
            }
        }

    }

    private void mostrarPagamentoRecorrente() {
        List<PagamentoRecorrente> pagamentoRecorrente = pagamentoRecorrenteDAO.lista();
        if (pagamentoRecorrente.isEmpty()) {
            System.out.println("NENHUM PAGAMENTO RECORRENTE REGISTRADO.");
        } else {
            for (PagamentoRecorrente pagamento : pagamentoRecorrente) {
                System.out.println(pagamento);
            }
        }

    }

    private void mostrarMovimentacaoFinanceira() {
        List<MovimentacaoFinanceira> movimentacaoFinanceiras = movimentacaoFinanceiraDAO.lista();

        if (movimentacaoFinanceiras.isEmpty()) {
            System.out.println("NAO EXISTEM MOVIMENTACOES FINANCEIRAS CADASTRADAS.");
        } else {
            for (MovimentacaoFinanceira mv : movimentacaoFinanceiras) {
                System.out.println(mv);
            }
        }
    }

    private void mostrarAvaliacaoFisica() {
        List<AvaliacaoFisica> avaliacoes = avFisDAO.lista();
        if (!avaliacoes.isEmpty()) {
            for (AvaliacaoFisica avaliacao : avaliacoes) {
                System.out.println(avaliacao);
            }
        } else {
            System.out.println("NAO EXISTEM AVALIACOES FISICAS CADASTRADAS.");
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
        System.out.println("\nCADASTRO DE LOGIN:");

        System.out.println("NOME: ");
        pu.setNome(scanner.nextLine());
        System.out.println("LOGIN: ");
        pu.setLogin(scanner.nextLine());
        System.out.println("SENHA: ");
        pu.setSenha(scanner.nextLine());
        System.out.println("SEXO [M/F]:");
        pu.setSexo(scanner.nextLine());
        System.out.println("DATA DE NASCIMENTO (dd/MM/yyyy):");
        String dataNascimentoStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        pu.setNascimento(dataNascimento);
        pu.setTipoUsuario("Aluno");
        pu.setDataCriacao(UtilPessoa.getDiaAtual());
        pu.setDataModificacao(UtilPessoa.getDiaAtual());

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

        this.mostrarTodasDivisoesTreino();
        System.out.println("ESCOLHA O ID DA DIVISAO DE TREINO QUE DESEJA ASSOCIAR: ");
        long idDivisaoTreino = -1;
        boolean validacao = false;

        while (!validacao) {
            try {
                idDivisaoTreino = Long.parseLong(scanner.nextLine());
                validacao = true;
            } catch (NumberFormatException e) {
                System.out.println("ID INVÁLIDO. POR FAVOR, DIGITE UM NÚMERO VÁLIDO:");
            }
        }

        DivisaoTreino divTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);
        divMusculo.setDivisaoTreino(divTreino);

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.println("DIGITE A DATA DE INICIO DO PERIODO DO TREINO (dd/MM/yyyy): ");
                LocalDate dataInicio = LocalDate.parse(scanner.nextLine(), formatter);
                novoTreino.setDataInicio(dataInicio);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }
        dataValida = false;
        while (!dataValida) {
            try {
                System.out.println("DIGITE A DATA DE TERMINO DO PERIODO DO TREINO (dd/MM/yyyy): ");
                LocalDate dataTermino = LocalDate.parse(scanner.nextLine(), formatter);
                novoTreino.setDataTermino(dataTermino);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }

        novoTreino.setDataCriacao(LocalDateTime.now());
        novoTreino.setDataModificacao(LocalDateTime.now());

        return novoTreino;
    }

    private TreinoAplicacao cadastrarTreinoAplicacao() {
        TreinoAplicacao treinoAplicacao = new TreinoAplicacao();
        System.out.println("CADASTRO DE NOVO TREINO APLICACAO:\n");

        this.mostrarTodasPessoas();
        System.out.println("DIGITE O ID DA PESSOA: ");
        long pessoaId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setPessoa(new PessoaDAO().buscaPorId(pessoaId));

        this.mostrarTodasAcademias();
        System.out.println("DIGITE O ID DA ACADEMIA: ");
        long academiaId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setAcademia(new AcademiaDAO().buscaPorId(academiaId));

        this.mostrarTodosExercicios();
        System.out.println("DIGITE O ID DO EXERCICIO: ");
        long exercicioId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setExercicio(new ExercicioDAO().buscaPorId(exercicioId));

        this.mostrarTodosExerciciosAplicacao();
        System.out.println("DIGITE O ID DA APLICACAO DE EXERCICIO: ");
        long exAplicacaoId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setExAplicacao(new ExercicioAplicacaoDAO().buscaPorId(exAplicacaoId));

        this.mostrarTodasDivisoesTreino();
        System.out.println("DIGITE O ID DA DIVISAO DE TREINO: ");
        long divTreinoId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setDivTreino(new DivisaoTreinoDAO().buscaPorId(divTreinoId));

        this.mostrarTodasDivisoesTreinoMusculo();
        System.out.println("DIGITE O ID DA DIVISAO DE TREINO MUSCULO: ");
        long divTreinoMuscId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setDivTreinoMusc(new DivisaoTreinoMusculoDAO().buscaPorId(divTreinoMuscId));

        this.mostrarTodosTreinos();
        System.out.println("DIGITE O ID DO TREINO: ");
        long treinoId = Long.parseLong(scanner.nextLine());
        treinoAplicacao.setTreino(new TreinoDAO().buscaPorId(treinoId));

        treinoAplicacao.setDataCriacao(LocalDateTime.now());
        treinoAplicacao.setDataModificacao(LocalDateTime.now());

        return treinoAplicacao;
    }

    private MensalidadeVigente cadastraMensalidadeVigente() {
        System.out.println("DIGITE O VALOR DA MENSALIDADE...: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("DIGITE A DATA DE INICIO DA MENSALIDADE (dd/MM/yyyy)...: ");
        String dataInicioStr = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
        System.out.println("DIGITE A DATA DE TERMINO DA MENSALIDADE (dd/MM/yyyy)...: ");
        String dataTerminoStr = scanner.nextLine();
        LocalDate dataTermino = LocalDate.parse(dataTerminoStr, formatter);

        MensalidadeVigente mv = new MensalidadeVigente();
        mv.setValor(valor);
        mv.setDataInicio(dataInicio);
        mv.setDataTermino(dataTermino);
        mv.setDataCriacao(LocalDateTime.now());
        mv.setDataModificacao(LocalDateTime.now());

        return mv;
    }

    private AlunoPagamentoMensalidade cadastraPagamentoMensalidade() {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();
        LocalDate dataAtual = LocalDate.now();

        List<MensalidadeVigente> mensalidadesVigentes = mvDAO.lista();
        MensalidadeVigente mensalidadeVigenteAtual = null;
        for (MensalidadeVigente mv : mensalidadesVigentes) {
            if ((dataAtual.isAfter(mv.getDataInicio()) || dataAtual.isEqual(mv.getDataInicio()))
                    && (dataAtual.isBefore(mv.getDataTermino()) || dataAtual.isEqual(mv.getDataTermino()))) {
                mensalidadeVigenteAtual = mv;
                break;
            }
        }

        if (mensalidadeVigenteAtual == null) {
            System.out.println("NENHUMA MENSALIDADE VIGENTE PARA A DATA ATUAL.");
            return null;
        }

        System.out.println("MENSALIDADE VIGENTE ATUAL: " + mensalidadeVigenteAtual.getValor());

        apm.setMvAlunoPagamento(mensalidadeVigenteAtual);
        apm.setDataVencimento(mensalidadeVigenteAtual.getDataTermino());
        apm.setDataPagamento(dataAtual);
        apm.setValorPago(mensalidadeVigenteAtual.getValor());

        this.mostrarTodasPessoas();
        System.out.println("\nDIGITE O ID DO ALUNO...: ");
        long idAluno = Long.parseLong(scanner.nextLine());

        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa aluno = pessoaDAO.buscaPorId(idAluno);
        if (aluno == null) {
            System.out.println("ALUNO NAO ENCONTRADO.");
            return null;
        }

        System.out.println("\nMODALIDADE: \n1 - DINHEIRO\n2 - PIX\n3 - DEBITO AUTOMATICO\n4 - PAGAMENTO RECORRENTE");
        int modalidade = Integer.parseInt(scanner.nextLine());

        apm.setAluno(aluno);
        apm.setModalidade(modalidade);
        apm.setDataCriacao(LocalDateTime.now());
        apm.setDataModificacao(LocalDateTime.now());

        if (modalidade == 4) {
            PagamentoRecorrente pr = this.cadastraPagamentoRecorrente();
            if (pr != null) {
                pagamentoRecorrenteDAO.adiciona(pr);
            } else {
                System.out.println("FALHA AO CRIAR PAGAMENTO RECORRENTE.");
                return null;
            }
        }

        return apm;
    }

    private PagamentoRecorrente cadastraPagamentoRecorrente() {
        PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente();
        Calendario calendario = new Calendario();
        LocalDate dataAtual = calendario.getDataAtual();

        System.out.println("CADASTRO DE PAGAMENTO RECORRENTE");

        this.mostrarTodasPessoas();
        System.out.println("DIGITE O ID DA PESSOA: ");
        long idPessoa = Long.parseLong(scanner.nextLine());
        Pessoa pessoa = pessoaDAO.buscaPorId(idPessoa);
        if (pessoa != null) {
            pagamentoRecorrente.setPessoa(pessoa);
        } else {
            System.out.println("PESSOA NÃO ENCONTRADA.");
            return null;
        }

        System.out.println("DIGITE O VALOR DO PAGAMENTO: ");
        double valor = Double.parseDouble(scanner.nextLine());
        pagamentoRecorrente.setValor(valor);

        pagamentoRecorrente.setDataInicio(dataAtual);

        System.out.println("DIGITE O NÚMERO DE MESES AUTORIZADOS: ");
        int numeroMesesAutorizados = Integer.parseInt(scanner.nextLine());
        LocalDate dataVencimento = dataAtual.plusMonths(numeroMesesAutorizados);
        pagamentoRecorrente.setDataVencimento(dataVencimento);
        pagamentoRecorrente.setNumeroMesesAutorizados(numeroMesesAutorizados);

        System.out.println("DIGITE O CARTÃO DE CRÉDITO: ");
        String cartaoCredito = scanner.nextLine();
        pagamentoRecorrente.setCartaoCredito(cartaoCredito);

        pagamentoRecorrente.setData(LocalDate.now());
        pagamentoRecorrente.setDataCriacao(LocalDateTime.now());
        pagamentoRecorrente.setDataModificacao(LocalDateTime.now());

        System.out.println("PAGAMENTO RECORRENTE CADASTRADO COM SUCESSO!");

        return pagamentoRecorrente;
    }

    private AvaliacaoFisica cadastraAvaliacaoFisica() {
        AvaliacaoFisica av = new AvaliacaoFisica();

        this.mostrarTodasPessoas();
        System.out.println("\nDIGITE O ID DA PESSOA: ");
        long idPessoa = Long.parseLong(scanner.nextLine());
        Pessoa pessoa = pessoaDAO.buscaPorId(idPessoa);
        Treino treino = treinoDAO.buscaPorId(idPessoa);
        if (pessoa != null) {
            av.setPessoa(pessoa);
            System.out.println("DIGITE SEU PESO: ");
            double peso = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("DIGITE SUA ALTURA: ");
            double altura = scanner.nextDouble();
            scanner.nextLine();
            double calcula = av.calculaIMC(peso, altura);
            double imc = av.interpretaIMC(calcula);
            System.out.println("DIGITE SEU INDICE DE SATISFACAO: ");
            int indice = scanner.nextInt();
            scanner.nextLine();

            av.setPessoa(pessoa);
            av.setUltimoTreino(treino);
            av.setImc(imc);
            av.setPeso(peso);
            av.setAltura(altura);
            av.setIndiceSatisfacao(indice);
            av.setDataCriacao(LocalDateTime.now());
            av.setDataModificacao(LocalDateTime.now());
        } else {
            System.out.println("PESSOA NÃO ENCONTRADA.");
            return null;
        }
        return av;
    }

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
        int resp = 0;

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
                    gerenciarDivisaoTreino();
                    break;
                case 6:
                    gerenciarDivisaoTreinoMusculo();
                    break;
                case 7:
                    gerenciaTreino();
                    break;
                case 8:
                    gerenciarTreinoAplicacao();
                    break;
                case 9:
                    gerenciaAvaliacaoFisica();
                    break;
                case 10:
                    gerenciaMensalidadeVigente();
                    break;
                case 11:
                    gerenciaAlunoPagamentoMensalidade();
                    break;
                case 12:
                    gerenciaPagamentoRecorrente();
                    break;
                case 13:
                    gerenciaMovimentacaoFinanceira();
                    break;
                case 14:
                    gerenciaRelatorioAlunoAdimplentes();
                    break;
                case 15:
                    gerenciaRelatorioMovimentacaoFinanceira();
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
                    System.out.println("ACADEMIA CRIADA COM SUCESSO!");
                    if (academiaDAO.adiciona(acad)) {
                        System.out.println("ACADEMIA CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("ACADEMIA NAO CRIADA.");
                    }
                    break;
                case 2:
                    this.mostrarTodasAcademias();
                    break;
                case 3:
                    this.mostrarTodasAcademias();
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
                    this.mostrarTodasAcademias();
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

                    System.out.println("DESEJA REGISTRAR UM PAGAMENTO DE MENSALIDADE?");
                    System.out.println("1 - SIM");
                    System.out.println("2 - NAO");
                    int opcaoPagamento = Integer.parseInt(scanner.nextLine());

                    if (opcaoPagamento == 1) {
                        this.registrarPagamentoMensalidade(p);
                    }
                    break;
                case 2:
                    this.mostrarTodasPessoas();
                    break;
                case 3:
                    this.mostrarTodasPessoas();
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
                    this.mostrarTodasPessoas();
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
                    this.mostrarTodosExercicios();
                    break;
                case 3:
                    this.mostrarTodosExercicios();
                    System.out.println("DIGITE O ID DO EXERCICIO QUE DESEJA ALTERAR...: ");
                    Long idExercicio = Long.parseLong(scanner.nextLine());

                    Exercicio exParaAlterar = exercicioDAO.buscaPorId(idExercicio);
                    if (exParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DO EXERCICIO...: ");
                        String novoNomeExercicio = scanner.nextLine();
                        exParaAlterar.setNome(novoNomeExercicio);
                        System.out.println("DIGITE A NOVA DESCRICAO DO EXERCICIO...: ");
                        String novaDescricaoExercicio = scanner.nextLine();
                        exParaAlterar.setDescricao(novaDescricaoExercicio);
                        exParaAlterar.setDataModificacao(LocalDateTime.now());

                        exercicioDAO.alterar(exParaAlterar);

                        System.out.println("EXERCICIO ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("EXERCICIO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarTodosExercicios();
                    System.out.println("DIGITE O ID DO EXERCICIO QUE DESEJA EXCLUIR...: ");
                    Long idExercicioExcluir = Long.parseLong(scanner.nextLine());
                    Exercicio exParaExcluir = exercicioDAO.buscaPorId(idExercicioExcluir);
                    if (exParaExcluir != null) {
                        exercicioDAO.remover(idExercicioExcluir);
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
                    this.mostrarTodosExerciciosAplicacao();
                    break;
                case 3:
                    this.mostrarTodosExerciciosAplicacao();
                    System.out.println("DIGITE A DESCRICAO DA APLICACAO QUE DESEJA ALTERAR...: ");
                    Long idExAp = Long.parseLong(scanner.nextLine());
                    ExercicioAplicacao descAplicacaoAlterada = eaDAO.buscaPorId(idExAp);
                    if (descAplicacaoAlterada != null) {
                        System.out.println("DIGITE A NOVA DESCRICAO DA APLICACAO...: ");
                        String novaDescricaoAplicacao = scanner.nextLine();
                        descAplicacaoAlterada.setDescricao(novaDescricaoAplicacao);
                        descAplicacaoAlterada.setDataModificacao(LocalDateTime.now());

                        eaDAO.alterar(descAplicacaoAlterada);

                        System.out.println("APLICACAO ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("APLICACAO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarTodosExerciciosAplicacao();
                    System.out.println("DIGITE O ID DA APLICACAO QUE DESEJA EXCLUIR...: ");
                    Long idExApExcluir = Long.parseLong(scanner.nextLine());
                    ExercicioAplicacao apParaExcluir = eaDAO.buscaPorId(idExApExcluir);
                    if (apParaExcluir != null) {
                        eaDAO.remover(idExApExcluir);
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

    private void gerenciarDivisaoTreino() {
        int resp;

        while (true) {
            resp = gui.menuCrudDivisaoTreino();

            switch (resp) {
                case 1:
                    DivisaoTreino dt = this.cadastraDivisaoTreino();
                    if (divisaoTreinoDAO.adiciona(dt)) {
                        System.out.println("DIVISAO DE TREINO CRIADA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO NAO CRIADA.");
                    }
                    break;
                case 2:
                    this.mostrarTodasDivisoesTreino();
                    break;
                case 3:
                    this.mostrarTodasDivisoesTreino();
                    System.out.println("DIGITE O ID DA DIVISAO DE TREINO QUE DESEJA ALTERAR...: ");
                    Long idDivisao = Long.parseLong(scanner.nextLine());
                    DivisaoTreino divTreino = divisaoTreinoDAO.buscaPorId(idDivisao);
                    if (divTreino != null) {
                        System.out.println("DIGITE O NOVO NOME DA DIVISAO DE TREINO...: ");
                        String novoNomeDivisao = scanner.nextLine();
                        divTreino.setNome(novoNomeDivisao);
                        System.out.println("DIGITE A NOVA DESCRICAO DA DIVISAO DE TREINO...: ");
                        String novaDescricaoDivisao = scanner.nextLine();
                        divTreino.setDescricao(novaDescricaoDivisao);
                        divTreino.setDataModificacao(LocalDateTime.now());

                        divisaoTreinoDAO.alterar(divTreino);

                        System.out.println("DIVISAO DE TREINO ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarTodasDivisoesTreino();
                    System.out.println("DIGITE O ID DA DIVISAO DE TREINO QUE DESEJA EXCLUIR...: ");
                    Long idDivisaoExcluir = Long.parseLong(scanner.nextLine());
                    DivisaoTreino divParaExcluir = divisaoTreinoDAO.buscaPorId(idDivisaoExcluir);
                    if (divParaExcluir != null) {
                        divisaoTreinoDAO.remover(idDivisaoExcluir);
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
                    this.mostrarTodasDivisoesTreinoMusculo();
                    break;
                case 3:
                    this.mostrarTodasDivisoesTreinoMusculo();
                    System.out.println("DIGITE O ID DA DIVISAO DE TREINO MUSCULO QUE DESEJA ALTERAR...: ");
                    long idDiv = Long.parseLong(scanner.nextLine());
                    DivisaoTreinoMusculo divParaAlterar = divisaoTreinoMusculoDAO.buscaPorId(idDiv);
                    if (divParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DA DIVISAO DE TREINO MUSCULO...: ");
                        String novoNomeDivisao = scanner.nextLine();
                        divParaAlterar.setNome(novoNomeDivisao);
                        System.out.println("DIGITE A NOVA DESCRICAO DA DIVISAO DE TREINO MUSCULO...: ");
                        String novaDescricaoDivisao = scanner.nextLine();
                        divParaAlterar.setDescricao(novaDescricaoDivisao);
                        divParaAlterar.setDataModificacao(LocalDateTime.now());

                        if (divisaoTreinoMusculoDAO.alterar(divParaAlterar) != null) {
                            System.out.println("DIVISAO DE TREINO MUSCULO ALTERADA COM SUCESSO!");
                        } else {
                            System.out.println("FALHA AO ALTERAR DIVISAO DE TREINO MUSCULO.");
                        }
                    } else {
                        System.out.println("DIVISAO DE TREINO MUSCULO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarTodasDivisoesTreinoMusculo();
                    System.out.println("DIGITE O ID DA DIVISAO DE TREINO MUSCULO QUE DESEJA EXCLUIR...: ");
                    long idDivTreinoEx = Long.parseLong(scanner.nextLine());
                    if (divisaoTreinoMusculoDAO.remover(idDivTreinoEx)) {
                        System.out.println("DIVISAO DE TREINO MUSCULO EXCLUIDA COM SUCESSO!");
                    } else {
                        System.out.println("DIVISAO DE TREINO MUSCULO NÃO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    System.out.println("DIVISOES DE TREINO EXISTENTES:\n");
                    this.mostrarTodasDivisoesTreinoMusculo();
                    System.out.println("\nA QUAL DIVISAO DE TREINO DESEJA ASSOCIAR? DIGITE O ID:\n");
                    long idDivisaoTreino = Long.parseLong(scanner.nextLine());
                    DivisaoTreino divTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                    if (divTreino != null) {
                        System.out.println("DIVISOES DE TREINO MUSCULO EXISTENTES:\n");
                        this.mostrarTodasDivisoesTreinoMusculo();
                        System.out.println("QUAL DIVISAO DE TREINO MUSCULO GOSTARIA DE ASSSOCIAR?");
                        long idDivisaoTreinoMusculo = Long.parseLong(scanner.nextLine());
                        DivisaoTreinoMusculo divTreinoMusculo = divisaoTreinoMusculoDAO
                                .buscaPorId(idDivisaoTreinoMusculo);

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
                    this.mostrarTodosTreinos();
                    break;
                case 3:
                    this.mostrarTodosTreinos();
                    System.out.println("DIGITE O ID DO TREINO QUE DESEJA ALTERAR...: ");
                    long idTreino = Long.parseLong(scanner.nextLine());
                    Treino treinoParaAlterar = treinoDAO.buscaPorId(idTreino);
                    if (treinoParaAlterar != null) {
                        System.out.println("DIGITE O NOVO NOME DO TREINO...: ");
                        String novoNomeTreino = scanner.nextLine();
                        treinoParaAlterar.setNome(novoNomeTreino);
                        System.out.println("DIGITE A NOVA DESCRICAO DO TREINO...: ");
                        String novaDescricaoTreino = scanner.nextLine();
                        treinoParaAlterar.setObjetivo(novaDescricaoTreino);
                        treinoParaAlterar.setDataModificacao(LocalDateTime.now());

                        // Mostrar divisões de treino existentes e permitir associação
                        System.out.println("DIVISOES DE TREINO EXISTENTES:\n");
                        this.mostrarTodasDivisoesTreino();
                        System.out.println("QUAL DIVISAO DE TREINO GOSTARIA DE ASSOCIAR?");
                        long idDivisaoTreino = Long.parseLong(scanner.nextLine());
                        DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                        if (divisaoTreino != null) {
                            treinoParaAlterar.setDivTreino(divisaoTreino);
                        } else {
                            System.out.println("DIVISAO DE TREINO NÃO ENCONTRADA.");
                        }

                        if (treinoDAO.alterar(treinoParaAlterar) != null) {
                            System.out.println("TREINO ALTERADO COM SUCESSO!");
                        } else {
                            System.out.println("ERRO AO ALTERAR O TREINO.");
                        }
                    } else {
                        System.out.println("TREINO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarTodosTreinos();
                    System.out.println("DIGITE O ID DO TREINO QUE DESEJA EXCLUIR...: ");
                    long idTreinoExcluir = Long.parseLong(scanner.nextLine());
                    if (treinoDAO.remover(idTreinoExcluir)) {
                        System.out.println("TREINO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("TREINO NÃO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 5:
                    System.out.println("TREINOS EXISTENTES:\n");
                    this.mostrarTodosTreinos();
                    System.out.println("\nA QUAL TREINO DESEJA ASSOCIAR UMA DIVISÃO DE TREINO? DIGITE O ID:\n");
                    long idTreinoParaAssociar = Long.parseLong(scanner.nextLine());
                    Treino treinoParaAssociar = treinoDAO.buscaPorId(idTreinoParaAssociar);

                    if (treinoParaAssociar != null) {
                        System.out.println("DIVISOES DE TREINO EXISTENTES:\n");
                        this.mostrarTodasDivisoesTreino();
                        System.out.println("QUAL DIVISAO DE TREINO GOSTARIA DE ASSOCIAR?");
                        long idDivisaoTreino = Long.parseLong(scanner.nextLine());
                        DivisaoTreino divisaoTreino = divisaoTreinoDAO.buscaPorId(idDivisaoTreino);

                        if (divisaoTreino != null) {
                            treinoParaAssociar.setDivTreino(divisaoTreino);
                            if (treinoDAO.alterar(treinoParaAssociar) != null) {
                                System.out.println("DIVISAO DE TREINO ASSOCIADA COM SUCESSO!");
                            } else {
                                System.out.println("FALHA AO ASSOCIAR DIVISAO DE TREINO.");
                            }
                        } else {
                            System.out.println("DIVISAO DE TREINO NÃO ENCONTRADA.");
                        }
                    } else {
                        System.out.println("TREINO NÃO ENCONTRADO.");
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

    private void gerenciarTreinoAplicacao() {
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
                    this.mostrarTreinoAplicacao();
                    break;
                case 3:
                    System.out.println("\nDIGITE O ID DO TREINO APLICACAO QUE DESEJA ALTERAR...: ");
                    long idAplicacao = Long.parseLong(scanner.nextLine());
                    TreinoAplicacao aplicacaoParaAlterar = treinoAplicacaoDAO.buscaPorId(idAplicacao);

                    if (aplicacaoParaAlterar != null) {
                        System.out.println("ALTERAR FICHA DE TREINO: \n");

                        this.mostrarTodasPessoas();
                        System.out.println("DIGITE O ID DA PESSOA: ");
                        long pessoaId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar.setPessoa(new PessoaDAO().buscaPorId(pessoaId));

                        this.mostrarTodasAcademias();
                        System.out.println("DIGITE O ID DA ACADEMIA: ");
                        long academiaId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar.setAcademia(new AcademiaDAO().buscaPorId(academiaId));

                        this.mostrarTodosExercicios();
                        System.out.println("DIGITE O ID DO EXERCICIO: ");
                        long exercicioId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar.setExercicio(new ExercicioDAO().buscaPorId(exercicioId));

                        this.mostrarTodosExerciciosAplicacao();
                        System.out.println("DIGITE O ID DA APLICACAO DE EXERCICIO: ");
                        long exAplicacaoId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar.setExAplicacao(new ExercicioAplicacaoDAO().buscaPorId(exAplicacaoId));

                        this.mostrarTodasDivisoesTreino();
                        System.out.println("DIGITE O ID DA DIVISAO DE TREINO: ");
                        long divTreinoId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar.setDivTreino(new DivisaoTreinoDAO().buscaPorId(divTreinoId));

                        this.mostrarTodasDivisoesTreinoMusculo();
                        System.out.println("DIGITE O ID DA DIVISAO DE TREINO MUSCULO: ");
                        long divTreinoMuscId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar
                                .setDivTreinoMusc(new DivisaoTreinoMusculoDAO().buscaPorId(divTreinoMuscId));

                        this.mostrarTodosTreinos();
                        System.out.println("DIGITE O ID DO TREINO: ");
                        long treinoId = Long.parseLong(scanner.nextLine());
                        aplicacaoParaAlterar.setTreino(new TreinoDAO().buscaPorId(treinoId));

                        aplicacaoParaAlterar.setDataModificacao(LocalDateTime.now());

                        treinoAplicacaoDAO.alterar(aplicacaoParaAlterar);

                        System.out.println("TREINO APLICACAO ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("TREINO APLICACAO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarTreinoAplicacao();
                    System.out.println("\nDIGITE O ID DO TREINO APLICACAO QUE DESEJA EXCLUIR...: ");
                    long idAplicacaoExcluir = Long.parseLong(scanner.nextLine());
                    if (treinoAplicacaoDAO.remover(idAplicacaoExcluir)) {
                        System.out.println("TREINO APLICACAO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("TREINO APLICACAO NÃO EXISTENTE NO BANCO DE DADOS.");
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

    private void gerenciaAvaliacaoFisica() {
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
                    this.mostrarAvaliacaoFisica();
                    break;
                case 3:
                    this.mostrarAvaliacaoFisica();
                    System.out.println("DIGITE O ID DA AVALIACAO FISICA QUE DESEJA ALTERAR: ");
                    long idAvaliacao = Long.parseLong(scanner.nextLine());
                    AvaliacaoFisica avaliacaoParaAlterar = avFisDAO.buscaPorId(idAvaliacao);
                    if (avaliacaoParaAlterar != null) {
                        System.out.println("DIGITE O NOVO PESO: ");
                        double novoPeso = scanner.nextDouble();
                        System.out.println("DIGITE A NOVA ALTURA: ");
                        double novaAltura = scanner.nextDouble();
                        double novoImc = avaliacaoParaAlterar.calculaIMC(novoPeso, novaAltura);
                        scanner.nextLine();
                        avaliacaoParaAlterar.interpretaIMC(novoImc);
                        System.out.println("QUAL O NOVO INDICE DE SATISFACAO? ");
                        int novoIndiceSatisfacao = scanner.nextInt();
                        scanner.nextLine();
                        avaliacaoParaAlterar.setPeso(novoPeso);
                        avaliacaoParaAlterar.setAltura(novaAltura);
                        avaliacaoParaAlterar.setImc(novoImc);
                        avaliacaoParaAlterar.setIndiceSatisfacao(novoIndiceSatisfacao);
                        avaliacaoParaAlterar.setDataModificacao(LocalDateTime.now());
                        System.out.println("AVALIACAO FISICA ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("AVALIACAO FISICA NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarAvaliacaoFisica();
                    System.out.println("DIGITE O ID DA AVALIACAO FISICA QUE DESEJA EXCLUIR: ");
                    long idAvaliacaoExcluir = Long.parseLong(scanner.nextLine());
                    AvaliacaoFisica avaliacaoParaExcluir = avFisDAO.buscaPorId(idAvaliacaoExcluir);
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
                    Pessoa pessoaParaAssociar = pessoaDAO.buscaPorId(idPessoa);

                    if (pessoaParaAssociar != null) {
                        System.out.println("AVALIACOES FISICAS EXISTENTES:\n");
                        this.mostrarAvaliacaoFisica();
                        System.out.println("\nQUAL AVALIACAO FISICA DESEJA ASSOCIAR? DIGITE O ID:\n");
                        long idAvaliacaoAssociar = Long.parseLong(scanner.nextLine());
                        AvaliacaoFisica avaliacaoAssociar = avFisDAO.buscaPorId(idAvaliacaoAssociar);

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
    }

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
                    this.mostrarMensalidadesVigentes();
                    break;
                case 3:
                    this.mostrarMensalidadesVigentes();
                    System.out.println("\nDIGITE O ID DA MENSALIDADE VIGENTE QUE DESEJA ALTERAR...: ");
                    long idMensalidade = Long.parseLong(scanner.nextLine());

                    MensalidadeVigente mensalidadeParaAlterar = mvDAO.buscaPorId(idMensalidade);
                    if (mensalidadeParaAlterar != null) {
                        System.out.println("DIGITE O NOVO VALOR DA MENSALIDADE: ");
                        double novoValorMensalidade = Double.parseDouble(scanner.nextLine());
                        System.out.println("DIGITE A NOVA DATA DE INICIO DA MENSALIDADE (dd/MM/yyyy): ");
                        String novaDataInicioStr = scanner.nextLine();
                        LocalDate dataInicio = LocalDate.parse(novaDataInicioStr,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        System.out.println("DIGITE A NOVA DATA DE TERMINO DA MENSALIDADE (dd/MM/yyyy): ");
                        String novaDataTerminoStr = scanner.nextLine();
                        LocalDate dataTermino = LocalDate.parse(novaDataTerminoStr,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        mensalidadeParaAlterar.setValor(novoValorMensalidade);
                        mensalidadeParaAlterar.setDataInicio(dataInicio);
                        mensalidadeParaAlterar.setDataTermino(dataTermino);
                        mensalidadeParaAlterar.setDataModificacao(LocalDateTime.now());

                        mvDAO.alterar(mensalidadeParaAlterar);

                        System.out.println("MENSALIDADE VIGENTE ALTERADA COM SUCESSO!");
                    } else {
                        System.out.println("MENSALIDADE VIGENTE NAO ENCONTRADA NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarMensalidadesVigentes();
                    System.out.println("\nDIGITE O ID DA MENSALIDADE VIGENTE QUE DESEJA EXCLUIR...: ");
                    long idMensalidadeExcluir = Long.parseLong(scanner.nextLine());
                    if (mvDAO.remover(idMensalidadeExcluir)) {
                        System.out.println("MENSALIDADE VIGENTE EXCLUIDA COM SUCESSO!");
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

    private void gerenciaPagamentoRecorrente() {
        int resp;

        while (true) {
            resp = gui.menuCrudPagamentoRecorrente();

            switch (resp) {
                case 1:
                    PagamentoRecorrente novoPagamento = this.cadastraPagamentoRecorrente();
                    if (novoPagamento != null && pagamentoRecorrenteDAO.adiciona(novoPagamento)) {
                        System.out.println("PAGAMENTO RECORRENTE CRIADO COM SUCESSO!");
                    } else {
                        System.out.println("ERRO AO CRIAR O PAGAMENTO RECORRENTE.");
                    }
                    break;
                case 2:
                    this.mostrarPagamentoRecorrente();
                    break;
                case 3:
                    this.mostrarPagamentoRecorrente();
                    System.out.println("\nDIGITE O ID DO PAGAMENTO RECORRENTE QUE DESEJA ALTERAR...: ");
                    long idPagamento = Long.parseLong(scanner.nextLine());

                    PagamentoRecorrente pagamentoParaAlterar = pagamentoRecorrenteDAO.buscaPorId(idPagamento);
                    if (pagamentoParaAlterar != null) {
                        System.out.println("DIGITE O NOVO VALOR DO PAGAMENTO: ");
                        double novoValor = Double.parseDouble(scanner.nextLine());
                        System.out.println("DIGITE A NOVA DATA DE INICIO DO PAGAMENTO (dd/MM/yyyy): ");
                        String novaDataInicioStr = scanner.nextLine();
                        LocalDate novaDataInicio = LocalDate.parse(novaDataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        System.out.println("DIGITE A NOVA DATA DE TERMINO DO PAGAMENTO (dd/MM/yyyy): ");
                        String novaDataTerminoStr = scanner.nextLine();
                        LocalDate novaDataVencimento = LocalDate.parse(novaDataTerminoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        pagamentoParaAlterar.setValor(novoValor);
                        pagamentoParaAlterar.setDataInicio(novaDataInicio);
                        pagamentoParaAlterar.setDataVencimento(novaDataVencimento);
                        pagamentoParaAlterar.setDataModificacao(LocalDateTime.now());

                        pagamentoRecorrenteDAO.alterar(pagamentoParaAlterar);

                        System.out.println("PAGAMENTO RECORRENTE ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("PAGAMENTO RECORRENTE NAO ENCONTRADO NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarPagamentoRecorrente();
                    System.out.println("\nDIGITE O ID DO PAGAMENTO RECORRENTE QUE DESEJA EXCLUIR...: ");
                    long idPagamentoExcluir = Long.parseLong(scanner.nextLine());
                    if (pagamentoRecorrenteDAO.remover(idPagamentoExcluir)) {
                        System.out.println("PAGAMENTO RECORRENTE EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("PAGAMENTO RECORRENTE NAO EXISTENTE NO BANCO DE DADOS.");
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

    private void gerenciaAlunoPagamentoMensalidade() {
        int resp;

        while (true) {
            resp = gui.menuCrudAlunoPagamentoMensalidade();

            switch (resp) {
                case 1:
                    AlunoPagamentoMensalidade apm = this.cadastraPagamentoMensalidade();
                    if (apm != null) {
                        if (apm.getModalidade() == 4) { // Pagamento Recorrente
                            PagamentoRecorrente pr = this.cadastraPagamentoRecorrente();
                            if (pr != null) {
                                pagamentoRecorrenteDAO.adiciona(pr);
                            } else {
                                System.out.println("FALHA AO CRIAR PAGAMENTO RECORRENTE.");
                            }
                        }
                        if (apmDAO.adiciona(apm)) {
                            System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO CRIADO COM SUCESSO!");
                        } else {
                            System.out.println("FALHA AO CRIAR PAGAMENTO DA MENSALIDADE DO ALUNO.");
                        }
                    }
                    break;
                case 2:
                    this.mostrarPagamentosMensalidade();
                    break;
                case 3:
                    this.mostrarPagamentosMensalidade();
                    System.out.println("DIGITE O ID DO PAGAMENTO DA MENSALIDADE QUE DESEJA ALTERAR...: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    AlunoPagamentoMensalidade apmParaAlterar = apmDAO.buscaPorId(id);
                    if (apmParaAlterar != null) {
                        System.out.println("DIGITE O NOVO VALOR QUE IRA PAGAR...: ");
                        double novoValorAPM = scanner.nextDouble();
                        apmParaAlterar.setValorPago(novoValorAPM);
                        scanner.nextLine();

                        System.out.println(
                                "MODALIDADE: \n1 - DINHEIRO\n2 - PIX\n3 - DEBITO AUTOMATICO\n4 - PAGAMENTO RECORRENTE");
                        int novaModalidade = scanner.nextInt();
                        apmParaAlterar.setModalidade(novaModalidade);
                        scanner.nextLine();

                        if (novaModalidade == 4) {
                            PagamentoRecorrente pr = this.cadastraPagamentoRecorrente();
                            if (pr != null) {
                                pagamentoRecorrenteDAO.adiciona(pr);
                            } else {
                                System.out.println("FALHA AO CRIAR PAGAMENTO RECORRENTE.");
                            }
                        }

                        System.out.println("DIGITE A NOVA DATA DE PAGAMENTO (dd/MM/yyyy)...: ");
                        String novaDataPagamentoStr = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataPagamento = LocalDate.parse(novaDataPagamentoStr, formatter);
                        apmParaAlterar.setDataPagamento(dataPagamento);

                        apmParaAlterar.setDataModificacao(UtilPessoa.getDiaAtual());

                        apmDAO.alterar(apmParaAlterar);
                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO ALTERADO COM SUCESSO!");
                    } else {
                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO NAO EXISTENTE NO BANCO DE DADOS.");
                    }
                    break;
                case 4:
                    this.mostrarPagamentosMensalidade();
                    System.out.println("DIGITE O ID DO PAGAMENTO DA MENSALIDADE QUE DESEJA EXCLUIR...: ");
                    long ide = scanner.nextLong();
                    scanner.nextLine();

                    AlunoPagamentoMensalidade apmParaExcluir = apmDAO.buscaPorId(ide);
                    if (apmParaExcluir != null) {
                        apmDAO.remover(ide);
                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO EXCLUIDO COM SUCESSO!");
                    } else {
                        System.out.println("PAGAMENTO DA MENSALIDADE DO ALUNO NAO EXISTENTE NO BANCO DE DADOS.");
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

    private void gerenciaMovimentacaoFinanceira() {
        int opcao;

        while (true) {
            opcao = gui.menuCrudMovimentacaoFinanceira();

            switch (opcao) {
                case 1:
                    MovimentacaoFinanceira novaMovimentacao = this.cadastraMovimentacaoFinanceira();
                    if (movimentacaoFinanceiraDAO.adiciona(novaMovimentacao)) {
                        System.out.println("MOVIMENTACAO FINANCEIRA CADASTRADA COM SUCESSO");
                    } else {
                        System.out.println("ERRO AO CADASTRAR MOVIMENTACAO FINANCEIRA");
                    }
                    break;
                case 2:
                    this.mostrarMovimentacaoFinanceira();
                    break;
                case 3:
                    System.out.println("Digite o ID da movimentação financeira que deseja alterar: ");
                    long idMovimentacao = Long.parseLong(scanner.nextLine());
                    MovimentacaoFinanceira movimentacaoParaAlterar = movimentacaoFinanceiraDAO
                            .buscaPorId(idMovimentacao);
                    if (movimentacaoParaAlterar != null) {
                        System.out.println("Digite o novo valor: ");
                        double novoValor = Double.parseDouble(scanner.nextLine());
                        System.out.println("Digite a nova descrição: ");
                        String novaDescricao = scanner.nextLine();
                        movimentacaoParaAlterar.setValor(novoValor);
                        movimentacaoParaAlterar.setDescricao(novaDescricao);
                        movimentacaoParaAlterar.setDataModificacao(LocalDateTime.now());
                        movimentacaoFinanceiraDAO.alterar(movimentacaoParaAlterar);

                        System.out.println("Movimentação financeira alterada com sucesso!");

                    } else {
                        System.out.println("Movimentação financeira não encontrada.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o ID da movimentação financeira que deseja excluir: ");
                    long idMovimentacaoExcluir = Long.parseLong(scanner.nextLine());
                    MovimentacaoFinanceira movimentacaoParaExcluir = movimentacaoFinanceiraDAO
                            .buscaPorId(idMovimentacaoExcluir);
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

    private void gerenciaRelatorioAlunoAdimplentes() {
        System.out.println("DIGITE O MES (1 A 12): ");
        int mes = 0;
        if (scanner.hasNextInt()) {
            mes = scanner.nextInt();
            if (mes < 1 || mes > 12) {
                System.out.println("Mês inválido. Digite um número de 1 a 12.");
                return;
            }
        } else {
            System.out.println("ENTRADA INVALIDA PARA O MES.");
            return;
        }

        System.out.println("DIGITE O ANO: ");
        int ano = 0;
        if (scanner.hasNextInt()) {
            ano = scanner.nextInt();
            if (ano < 0) {
                System.out.println("Ano inválido. Digite um número positivo.");
                return;
            }
        } else {
            System.out.println("ENTRADA INVALIDA PARA O ANO.");
            return;
        }

        scanner.nextLine();

        AlunoPagamentoMensalidadeDAO apmDAO = new AlunoPagamentoMensalidadeDAO();

        RelatorioAlunoAdimplentes relatorioAluno = new RelatorioAlunoAdimplentes(apmDAO);

        relatorioAluno.exibirRelatorio(mes, ano);
    }

    private void gerenciaRelatorioMovimentacaoFinanceira() {
        System.out.println("DIGITE O MES (1 A 12): ");
        int mes = 0;
        if (scanner.hasNextInt()) {
            mes = scanner.nextInt();
        } else {
            System.out.println("ENTRADA INVALIDA PARA O MES.");
            return;
        }

        System.out.println("DIGITE O ANO: ");
        int ano = 0;
        if (scanner.hasNextInt()) {
            ano = scanner.nextInt();
        } else {
            System.out.println("ENTRADA INVALIDA PARA O ANO.");
            return;
        }
        relatorioMov.exibirRelatorio(mes, ano);
    }

    public static void main(String[] args) {
        MenuGeralAcademia menu = new MenuGeralAcademia();
        menu.iniciar();
    }
}
