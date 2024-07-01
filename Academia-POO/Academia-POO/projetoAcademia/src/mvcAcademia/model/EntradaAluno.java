package mvcAcademia.model;

import java.time.LocalDateTime;

public class EntradaAluno {
    private static long serial = 1;
    private long id;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private Pessoa pessoa;

    public EntradaAluno() {
        this.id = serial++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
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
        return   "\n" +  pessoa
                + "\n================== ENTRADA DO ALUNO ================= "
                + " \nID: " + id
                + " \nDATA E HORA: " + getDataEntrada();
                }
}
