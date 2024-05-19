package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */
public class AvaliacaoFisicaDAO {

    AvaliacaoFisica[] vetAvFis = new AvaliacaoFisica[5];

    public AvaliacaoFisicaDAO() {


    }
    
    public double calculaIMC(double peso, double altura) {
        if (altura > 3) {// Convertendo a altura para metros
            altura /= 100;
        }
        double imc = peso / (altura * altura);
        
        return imc;
    }

    public boolean adiciona(AvaliacaoFisica avaliacao) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vetAvFis[proximaPosicaoLivre] = avaliacao;
            return true;
        } else {
            return false;
        }
    }

     public AvaliacaoFisica buscaAvaliacaoID(long id) {
         for (AvaliacaoFisica avFis : vetAvFis) {
            if (avFis != null && avFis.getId() == id) {
                return avFis;
            }
         
        }
        return null;
    }
     
     
    public void interpretaIMC (double imc){
        if (imc < 18.5) {
            System.out.printf("SEU IMC E: %.2f. VOCE ESTA ABAIXO DO PESO.%n", imc);
        } else if (imc >= 18.5 && imc < 25) {
            System.out.printf("SEU IMC E: %.2f. O SEU PESO ESTA NORMAL.%n", imc);
        } else if (imc >= 25 && imc < 30) {
            System.out.printf("SEU IMC E: %.2f. VOCE ESTA COM SOBREPESO.%n", imc);
        } else if (imc >= 30 && imc < 35) {
            System.out.printf("SEU IMC E: %.2f. VOCE ESTA COM OBESIDADE GRAU I.%n", imc);
        } else if (imc >= 35 && imc < 40) {
            System.out.printf("SEU IMC E: %.2f. VOCE ESTA COM OBESIDADE GRAU II.%n", imc);
        } else {
            System.out.printf("SEU IMC E: %.2f. VOCE ESTA COM OBESIDADE GRAU III (MORBIDA).%n", imc);
        }
    }
 
    private int proximaPosicaoLivre() {
        for (int i = 0; i < vetAvFis.length; i++) {
            if (vetAvFis[i] == null) {
                return i;
            }
        }
        return -1;
    }

}
