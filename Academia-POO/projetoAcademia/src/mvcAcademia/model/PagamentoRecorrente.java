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
    private LocalDateTime data;
    private String cartaoCredito;
    private double valor;
    private LocalDateTime dataInicio;
    private int numMesesAutorizados;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public PagamentoRecorrente() {
        id = PagamentoRecorrente.serial++;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getNumMesesAutorizados() {
        return numMesesAutorizados;
    }

    public void setNumMesesAutorizados(int numMesesAutorizados) {
        this.numMesesAutorizados = numMesesAutorizados;
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
                + "Numero de Meses Autorizados: " + numMesesAutorizados + "\n"
                + "Data de Criacao: " + dataCriacao + "\n"
                + "Data de Modificacao: " + dataModificacao;
    }

}
