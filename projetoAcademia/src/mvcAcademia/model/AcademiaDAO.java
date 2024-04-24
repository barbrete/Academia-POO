/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */

public class AcademiaDAO {

    private Academia[] academias = new Academia[5];

    public AcademiaDAO() {
        Academia academia1 = new Academia();
        academia1.setNome("BRASIL FITNESS");
        academia1.setEndereco("RUA BRASILIA");
        academia1.setDataCriacao(LocalDateTime.now());
        academia1.setDataModificacao(LocalDateTime.now());
        adiciona(academia1);

        Academia academia2 = new Academia();
        academia2.setNome("GYM MASTER");
        academia2.setEndereco("RUA FERNANDO COSTA");
        academia2.setDataCriacao(LocalDateTime.now());
        academia2.setDataModificacao(LocalDateTime.now());
        adiciona(academia2);

        Academia academia3 = new Academia();
        academia3.setNome("ACADEMIA EQUILIBRIO");
        academia3.setEndereco("AVENIDA MARANHAO");
        academia3.setDataCriacao(LocalDateTime.now());
        academia3.setDataModificacao(LocalDateTime.now());
        adiciona(academia3);
    }

    public boolean adiciona(Academia academia) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            academias[proximaPosicaoLivre] = academia;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodasAcademias() {
        boolean temAcademia = false;
        for (Academia academia : academias) {
            if (academia != null) {
                System.out.println(academia);
                temAcademia = true;
            }
        }
        if (!temAcademia) {
            System.out.println("NAO EXISTEM ACADEMIAS CADASTRADA.");
        }
    }

    public Academia buscaPorNome(String nome) {
        for (Academia academia : academias) {
            if (academia != null && academia.getNome().equals(nome)) {
                return academia;
            }
        }
        return null;
    }

    public boolean remover(String nome) {
        for (int i = 0; i < academias.length; i++) {
            if (academias[i] != null && academias[i].getNome().equals(nome)) {
                academias[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < academias.length; i++) {
            if (academias[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
