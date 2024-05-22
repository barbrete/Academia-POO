package mvcAcademia.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author barbrete e kitotsui
 */
public class AvaliacaoFisicaDAO {

    AvaliacaoFisica[] vetAvFis = new AvaliacaoFisica[10];
    

    public AvaliacaoFisicaDAO() {
        
        Treino treino1 = new Treino();
        treino1.setNome("Treino A");
        treino1.setObjetivo("Hipertrofia");
       

        Treino treino2 = new Treino();
        treino2.setNome("Treino B");
        treino2.setObjetivo("Emagrecimento");
        

        Treino treino3 = new Treino();
        treino3.setNome("Treino C");
        treino3.setObjetivo("Condicionamento Fisico");
         
        AvaliacaoFisica avaliacao1 = new AvaliacaoFisica();
        avaliacao1.setPessoa(new PessoaDAO().buscaPessoaPorId(1));
        avaliacao1.setUltimoTreino(treino1);
        avaliacao1.setPeso(70);
        avaliacao1.setAltura(1.75);
        avaliacao1.setImc(calculaIMC(avaliacao1.getPeso(), avaliacao1.getAltura()));
        avaliacao1.setIndiceSatisfacao(8);
        avaliacao1.setDataCriacao(new Date());
        avaliacao1.setDataModificacao(new Date());
        vetAvFis[0] = avaliacao1;

        AvaliacaoFisica avaliacao2 = new AvaliacaoFisica();
        avaliacao2.setPessoa(new PessoaDAO().buscaPessoaPorId(2));
        avaliacao2.setUltimoTreino(treino2);
        avaliacao2.setPeso(60);
        avaliacao2.setAltura(1.65);
        avaliacao2.setImc(calculaIMC(avaliacao2.getPeso(), avaliacao2.getAltura()));
        avaliacao2.setIndiceSatisfacao(7);
        avaliacao2.setDataCriacao(new Date());
        avaliacao2.setDataModificacao(new Date());
        vetAvFis[1] = avaliacao2;

        AvaliacaoFisica avaliacao3 = new AvaliacaoFisica();
        avaliacao3.setPessoa(new PessoaDAO().buscaPessoaPorId(3));
        avaliacao3.setUltimoTreino(treino3);
        avaliacao3.setPeso(80);
        avaliacao3.setAltura(1.85);
        avaliacao3.setImc(calculaIMC(avaliacao3.getPeso(), avaliacao3.getAltura()));
        avaliacao3.setIndiceSatisfacao(9);
        avaliacao3.setDataCriacao(new Date());
        avaliacao3.setDataModificacao(new Date());
        vetAvFis[2] = avaliacao3;


    }
    
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
