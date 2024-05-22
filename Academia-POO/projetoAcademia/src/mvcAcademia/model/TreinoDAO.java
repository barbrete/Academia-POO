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
public class TreinoDAO {

    private Treino[] treinos = new Treino[10];
    
    public TreinoDAO(){
        LocalDate dtInicioT1 = LocalDate.of(2024, 05, 05);
        LocalDate dtTerminoT1 = LocalDate.of(2024, 06, 05);
        Treino t1 = new Treino();
        t1.setNome("Transformacao Fit");
        t1.setObjetivo("Emagrecimento");
        //t1.setDivTreino(divisaoTreinoDAO);
        t1.setDataInicio(dtInicioT1);
        t1.setDataTermino(dtTerminoT1);
        t1.setDataCriacao(LocalDateTime.now());
        t1.setDataModificacao(LocalDateTime.now());
        adiciona(t1);
        
        LocalDate dtInicioT2 = LocalDate.of(2024, 10, 05);
        LocalDate dtTerminoT2 = LocalDate.of(2024, 11, 05);
        Treino t2 = new Treino();
        t2.setNome("Massa Maximal");
        t2.setObjetivo("Hipertrofia Muscular");
        //t2.setDivisaoTreino(divisaoTreino);
        t2.setDataInicio(dtInicioT2);
        t2.setDataTermino(dtTerminoT2);
        t2.setDataCriacao(LocalDateTime.now());
        t2.setDataModificacao(LocalDateTime.now());
        adiciona(t2);
        
        LocalDate dtInicioT3 = LocalDate.of(2023, 12, 20);
        LocalDate dtTerminoT3 = LocalDate.of(2024, 01, 25);
        Treino t3 = new Treino();
        t3.setNome("Flex & Flow");
        t3.setObjetivo("Flexibilidade e Mobilidade");
        //t3.setDivisaoTreino(divisaoTreino);
        t3.setDataInicio(dtInicioT3);
        t3.setDataTermino(dtTerminoT3);
        t3.setDataCriacao(LocalDateTime.now());
        t3.setDataModificacao(LocalDateTime.now());
        adiciona(t3);
        
        LocalDate dtInicioT4 = LocalDate.of(2023, 10, 05);
        LocalDate dtTerminoT4 = LocalDate.of(2024, 01, 05);
        Treino t4 = new Treino();
        t4.setNome("Massa Maximal");
        t4.setObjetivo("Hipertrofia Muscular");
        //t4.setDivisaoTreino(divisaoTreino);
        t4.setDataInicio(dtInicioT4);
        t4.setDataTermino(dtTerminoT4);
        t4.setDataCriacao(LocalDateTime.now());
        t4.setDataModificacao(LocalDateTime.now());
        adiciona(t4);
        
        LocalDate dtInicioT5 = LocalDate.of(2024, 01, 01);
        LocalDate dtTerminoT5 = LocalDate.of(2024, 02, 05);
        Treino t5 = new Treino();
        t5.setNome("Massa Maximal");
        t5.setObjetivo("Hipertrofia Muscular");
        //t5.setDivisaoTreino(divisaoTreino);
        t5.setDataInicio(dtInicioT5);
        t5.setDataTermino(dtTerminoT5);
        t5.setDataCriacao(LocalDateTime.now());
        t5.setDataModificacao(LocalDateTime.now());
        adiciona(t5);
        
    }

    public Treino buscaTreinoPorId(long id) {
        for (Treino t : treinos) {
            if (t != null && t.getId() == id) {
                return t;
            }
        }
        return null;
    }
    
    public void mostraTreino(long id) {
       Treino t = buscaTreinoPorId(id);
        if (t != null) {
            System.out.println(t);
        } else {
            System.out.println("TREINO NAO ENCONTRADO.");
        }
    }

    public boolean adiciona(Treino treino) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            treinos[proximaPosicaoLivre] = treino;
            return true;
        } else {
            return false;
        }
    }
    
    public void mostrarTodosTreinos() {
        boolean temTreino = false;
        for (Treino treino : treinos) {
            if (treino != null) {
                System.out.println(treino);
                temTreino = true;
            }
        }
        if (!temTreino) {
            System.out.println("NAO EXISTEM TREINOS CADASTRADOS.");
        }
    }

    
      public Treino buscaPorNome(String nome) {
        for (Treino treino : treinos) {
            if (treino != null && treino.getNome().equals(nome)) {
                return treino;
            }
        }
        return null;
    }
      
        public Treino buscaPorId(long id) {
        for (Treino treino : treinos) {
            if (treino != null && treino.getId() == id) {
                return treino;
            }
        }
        return null;
    }

    public boolean remover(String objetivo) {
        for (int i = 0; i < treinos.length; i++) {
            if (treinos[i] != null && treinos[i].getObjetivo().equals(objetivo)) {
                treinos[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < treinos.length; i++) {
            if (treinos[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public Treino[] getTreinos() {
        return treinos;
    }
}
