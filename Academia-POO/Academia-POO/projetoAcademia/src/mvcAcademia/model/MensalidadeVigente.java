/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */
public class MensalidadeVigente {
    static long serial;
    private long id;
    private double valor;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MensalidadeVigente(){
        id = MensalidadeVigente.serial++;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
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

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
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
        return "\n==================INFORMACOES DA MENSALIDADE VIGENTE=================" +
                "\nID: " + id +
                "\nVALOR: " + valor +
                "\nDATA INICIO: " + dataInicio + 
                "\nDATA TERMINO: " + dataTermino +
                "\nDATA DE CRIACAO: " + dataCriacao +
                "\nDATA DE MODIFICACAO: " + dataModificacao;
    }

    public String toStringAluno() {
        return "\n\n-------- INFORMACOES DA MENSALIDADE VIGENTE --------" +
                "\nID: " + id +
                "\nVALOR: " + valor +
                "\nDATA INICIO: " + dataInicio + 
                "\nDATA TERMINO: " + dataTermino +
                "\nDATA DE CRIACAO: " + dataCriacao +
                "\nDATA DE MODIFICACAO: " + dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MensalidadeVigente other = (MensalidadeVigente) obj;
        return this.id == other.id;
    }
    
    
    
}
