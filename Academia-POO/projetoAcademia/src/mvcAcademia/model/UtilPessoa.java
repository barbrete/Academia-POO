/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

/**
 *
 * @author barbrete e kitotsui
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;


public class UtilPessoa {
    private static Pessoa pessoaLogado = null;
    
    private static LocalDateTime diaAtual = LocalDateTime.now();
    private static LocalDate diaAtualL = LocalDate.now();

    public static Pessoa getPessoaLogado() {
        return pessoaLogado;
    }

    public static void setPessoaLogado(Pessoa pessoaLogado) {
        UtilPessoa.pessoaLogado = pessoaLogado;
    }

    public static LocalDateTime getDiaAtual() {
        return diaAtual;
    }
    
    public static LocalDate getDiaAtualL() {
        return diaAtualL;
    }
 
    public static int getDiaDoMes() {
        return diaAtual.getDayOfMonth();
    }

    public static void incrementaDias(int dias) {
        diaAtual.plusDays(dias);
    }
    
    public static void incrementaMes(int numeroMeses) {
        diaAtual.plusMonths(numeroMeses);
    }
    
    public static LocalDate incrementaMes(LocalDate dataCriacao, int numeroMeses) {
        return dataCriacao.plusMonths(numeroMeses);
    }
    
}