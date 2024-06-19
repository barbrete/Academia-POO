package mvcAcademia.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author barbrete e kitotsui
 */
public class AvaliacaoFisicaDAO {

    AvaliacaoFisica[] vetAvFis = new AvaliacaoFisica[10];
    

    
    
    public double calculaIMC(double peso, double altura) {
        if (altura > 3) {
            altura /= 100;
        }
        double imc = peso / (altura * altura);
        
        return imc;
    }
    
    public AvaliacaoFisica buscaAvaliacaoPorPessoaId(long pessoaId) {
        for (AvaliacaoFisica avFis : vetAvFis) {
            if (avFis != null && avFis.getPessoa() != null && avFis.getPessoa().getId() == pessoaId) {
                return avFis;
            }
        }
        return null;
    }
    
    public boolean remover(long id) {
    for (int i = 0; i < vetAvFis.length; i++) {
        if (vetAvFis[i] != null && vetAvFis[i].getId() == id) {
            vetAvFis[i] = null;
            return true;
        }
    }
    return false;
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
     
     public void mostrarAvaliacaoFisica() {
        boolean temAvaliacao = false;
        for (AvaliacaoFisica av : vetAvFis) {
            if (av != null) {
                System.out.println(av);
                temAvaliacao = true;
            }
        }
        if (!temAvaliacao) {
            System.out.println("não existe usuario cadastrado");
        }
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
