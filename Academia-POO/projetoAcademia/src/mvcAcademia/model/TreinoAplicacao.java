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
public class TreinoAplicacao {

    private static long serial = 1;
    private long id;
    Pessoa pessoa;
    Academia academia;
    Treino treino;
    Exercicio exercicio;
    ExercicioAplicacao exAplicacao;
    DivisaoTreino divTreino;
    DivisaoTreinoMusculo divTreinoMusc;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public  TreinoAplicacao() {
        id = TreinoAplicacao.serial++;
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
    
    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public ExercicioAplicacao getExAplicacao() {
        return exAplicacao;
    }

    public void setExAplicacao(ExercicioAplicacao exAplicacao) {
        this.exAplicacao = exAplicacao;
    }

    public DivisaoTreino getDivTreino() {
        return divTreino;
    }

    public void setDivTreino(DivisaoTreino divTreino) {
        this.divTreino = divTreino;
    }

    public DivisaoTreinoMusculo getDivTreinoMusc() {
        return divTreinoMusc;
    }

    public void setDivTreinoMusc(DivisaoTreinoMusculo divTreinoMusc) {
        this.divTreinoMusc = divTreinoMusc;
    }

    public Academia getAcademia() {
        return academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
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
        return "====================FICHA DE TREINO=====================\n" 
                + "ID DA FICHA: " + id
                + "\nDATA DE CRIACAO DA FICHA: " + dataCriacao
                +  academia 
                +  pessoa
                +  treino
                +  exercicio 
                +  exAplicacao 
                +  divTreino
                +  divTreinoMusc;
                
    }

    
}
