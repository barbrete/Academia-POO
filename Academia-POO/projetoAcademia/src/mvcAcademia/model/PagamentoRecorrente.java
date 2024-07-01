package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PagamentoRecorrente {

    private static long serial;
    private long id;
    private Pessoa pessoa;
    private LocalDate data;
    private String cartaoCredito;
    private double valor;
    private LocalDate dataInicio;
    private LocalDate dataVencimento;
    private int numeroMesesAutorizados;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public PagamentoRecorrente() {
        this.id = PagamentoRecorrente.serial++;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public PagamentoRecorrente(Pessoa pessoa, LocalDate data, String cartaoCredito, double valor, LocalDate dataInicio, LocalDate dataVencimento, int numeroMesesAutorizados, LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = PagamentoRecorrente.serial++;
        this.pessoa = pessoa;
        this.data = data;
        this.cartaoCredito = cartaoCredito;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.dataVencimento = dataVencimento;
        this.numeroMesesAutorizados = numeroMesesAutorizados;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getNumeroMesesAutorizados() {
        return numeroMesesAutorizados;
    }

    public void setNumeroMesesAutorizados(int numeroMesesAutorizados) {
        this.numeroMesesAutorizados = numeroMesesAutorizados;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return "================== INFORMACOES DE PAGAMENTO RECORRENTE ================="
                + "\nID: " + id
                + "\nPESSOA: " + pessoa
                + "\nDATA: " + data
                + "\nCARTÃO DE CRÉDITO: " + cartaoCredito
                + "\nVALOR: " + valor
                + "\nDATA DE INÍCIO: " + dataInicio
                + "\nDATA DE VENCIMENTO: " + dataVencimento
                + "\nNÚMERO DE MESES AUTORIZADOS: " + numeroMesesAutorizados
                + "\nDATA DE CRIAÇÃO: " + dataCriacao
                + "\nDATA DE MODIFICAÇÃO: " + dataModificacao
                + "\n===================================================================";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pessoa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PagamentoRecorrente that = (PagamentoRecorrente) o;
        return id == that.id && Objects.equals(pessoa, that.pessoa);
    }
}
