/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author Rogério
 */
public class MovimentacaoFinanceira {

    private static long serial = 1;
    private long id;
    private double valor;
    private String tipo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MovimentacaoFinanceira() {
        this.id = serial++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        return "\n==================INFORMACOES DA MOVIMENTACAO FINANCEIRA=================\n " 
                +"ID: " + id + "\n"
                + "Valor: " + valor + "\n"
                + "Tipo: " + tipo + "\n"
                + "Descricao: " + descricao + "\n"
                + "Data de Criacao: " + dataCriacao + "\n"
                + "Data de Modificacao: " + dataModificacao;
    }
}
