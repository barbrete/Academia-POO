/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Rogério
 */
public class PagamentoRecorrente {

    private static long serial = 1;
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
    private boolean adimplente;

    public PagamentoRecorrente(long id, Pessoa pessoa, boolean adimplente) {
        this.id = id;
        this.pessoa = pessoa;
        this.adimplente = adimplente;
    }

    public boolean isAdimplente() {
        return adimplente;
    }

    public PagamentoRecorrente() {
        id = PagamentoRecorrente.serial++;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public long getId() {
        return id;
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
    
    public int getNumMesesAutorizados() {
        return numeroMesesAutorizados;
    }
        
    public void setNumMesesAutorizados(int numMesesAutorizados) {
        this.numeroMesesAutorizados = numMesesAutorizados;
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
        return "ID: " + id + "\n"
                + "Pessoa: " + pessoa + "\n"
                + "Data: " + data + "\n"
                + "Cartao de Credito: " + cartaoCredito + "\n"
                + "Valor: " + valor + "\n"
                + "Data de Inicio: " + dataInicio + "\n"
                + "Numero de Meses Autorizados: " + numeroMesesAutorizados + "\n"
                + "Data de Criacao: " + dataCriacao + "\n"
                + "Data de Modificacao: " + dataModificacao;
    }

}
