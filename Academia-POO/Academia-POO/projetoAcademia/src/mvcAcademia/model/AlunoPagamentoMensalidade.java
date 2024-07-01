/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author barbrete e kitotsui
 */
public class AlunoPagamentoMensalidade {
    private static long serial;
    private long id;   
    private MensalidadeVigente mvAlunoPagamento;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valorPago;
    private Pessoa aluno;
    private int modalidade; 
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    public AlunoPagamentoMensalidade(){
        id = AlunoPagamentoMensalidade.serial++;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    public long getId() {
        return id;
    }

    public MensalidadeVigente getMvAlunoPagamento() {
        return mvAlunoPagamento;
    }

    public void setMvAlunoPagamento(MensalidadeVigente mvAlunoPagamento) {
        this.mvAlunoPagamento = mvAlunoPagamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Pessoa getAluno() {
        return aluno;
    }

    public void setAluno(Pessoa aluno) {
        this.aluno = aluno;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
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
        return "\n======= FICHA DE PAGAMENTO DA MENSALIDADE =======" +
                "\nID: " + id
                + "\nDATA DE VENCIMENTO: " + dataVencimento 
                + "\nDATA DE PAGAMENTO: " + dataPagamento 
                + "\nMODALIDADE: " + modalidade 
                + "\nDATA DE CRIACAO: " + dataCriacao 
                + "\nDATA DE MODIFICACAO: " + dataModificacao
                + "\nVALOR PAGO: " + valorPago 
                + mvAlunoPagamento.toStringAluno()
                + aluno.toStringAluno();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.aluno);
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
        final AlunoPagamentoMensalidade other = (AlunoPagamentoMensalidade) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.aluno, other.aluno);
    }
 
    
    
}
