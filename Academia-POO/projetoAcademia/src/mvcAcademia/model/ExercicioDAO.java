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

public class ExercicioDAO {

    private Exercicio[] vetorEx = new Exercicio[15];

    public ExercicioDAO(){
        Exercicio ex1 = new Exercicio();

        ex1.setNome("Agachamento");
        ex1.setDescricao("Segure uma barra sobre os ombros, abaixe o corpo até que as coxas fiquem paralelas ao chão e então volte à posição inicial.");
        ex1.setDataCriacao(LocalDateTime.now());
        ex1.setDataModificacao(LocalDateTime.now());
        adiciona(ex1);

        Exercicio ex2 = new Exercicio();        
        ex2.setNome("Supino");
        ex2.setDescricao("Deite-se em um banco plano, segure a barra sobre o peito e empurre-a para cima até que os braços estejam estendidos, depois abaixe-a controladamente até o peito.");
        ex2.setDataCriacao(LocalDateTime.now());
        ex2.setDataModificacao(LocalDateTime.now());
        adiciona(ex2);

        Exercicio ex3 = new Exercicio();
        ex3.setNome("Leg Press");
        ex3.setDescricao("Sente-se na máquina, empurre os pesos com os pés até esticar completamente as pernas e depois retorne à posição inicial sem bloquear os joelhos. ");
        ex3.setDataCriacao(LocalDateTime.now());
        ex3.setDataModificacao(LocalDateTime.now());
        adiciona(ex3);
    
        Exercicio ex4 = new Exercicio();
        ex4.setNome("Puxada na máquina (Pull Down)");
        ex4.setDescricao("Sente-se na máquina, agarre a barra com as mãos afastadas na largura dos ombros e puxe-a em direção ao peito, mantendo os ombros para baixo e para trás.");
        ex4.setDataCriacao(LocalDateTime.now());
        ex4.setDataModificacao(LocalDateTime.now());
        adiciona(ex4);
        
        Exercicio ex5 = new Exercicio();
        ex5.setNome("Elevação lateral");
        ex5.setDescricao("Segure halteres com os braços estendidos ao lado do corpo, levante os halteres para os lados até a altura dos ombros e então abaixe-os controladamente. ");
        ex5.setDataCriacao(LocalDateTime.now());
        ex5.setDataModificacao(LocalDateTime.now());
        adiciona(ex5);
        
        Exercicio ex6 = new Exercicio();
        ex6.setNome("Flexão de braço");
        ex6.setDescricao("Fique em posição de prancha com as mãos afastadas na largura dos ombros, abaixe o corpo até que o peito quase toque o chão e depois empurre de volta à posição inicial");
        ex6.setDataCriacao(LocalDateTime.now());
        ex6.setDataModificacao(LocalDateTime.now());
        adiciona(ex6);
        
        Exercicio ex7 = new Exercicio();
        ex7.setNome("Desenvolvimento de ombros (Shoulder Press)");
        ex7.setDescricao("Sente-se em um banco com encosto vertical, segure halteres ou uma barra acima da cabeça e empurre-os para cima até que os braços estejam estendidos, depois abaixe-os controladamente até os ombros.");
        ex7.setDataCriacao(LocalDateTime.now());
        ex7.setDataModificacao(LocalDateTime.now());
        adiciona(ex7);
        
        Exercicio ex8 = new Exercicio();
        ex8.setNome("Curl de bíceps");
        ex8.setDescricao("egure halteres com os braços estendidos ao lado do corpo, dobre os cotovelos e levante os halteres em direção aos ombros, mantendo os cotovelos próximos ao corpo.");
        ex8.setDataCriacao(LocalDateTime.now());
        ex8.setDataModificacao(LocalDateTime.now());
        adiciona(ex8);
        
        Exercicio ex9 = new Exercicio();
        ex9.setNome("Extensão de pernas");
        ex9.setDescricao("Sente-se na máquina, estenda as pernas para empurrar a plataforma para cima e depois abaixe-a controladamente.");
        ex9.setDataCriacao(LocalDateTime.now());
        ex9.setDataModificacao(LocalDateTime.now());
        adiciona(ex9);
        
        Exercicio ex10 = new Exercicio();
        ex10.setNome("Tríceps na polia alta (Tricep Pushdown)");
        ex10.setDescricao("Fique em pé na frente da polia alta, segure a barra com as mãos afastadas na largura dos ombros e empurre-a para baixo até que os braços estejam estendidos, depois volte à posição inicial. ");
        ex10.setDataCriacao(LocalDateTime.now());
        ex10.setDataModificacao(LocalDateTime.now());
        adiciona(ex10);
        
        Exercicio ex11 = new Exercicio();
        ex11.setNome("Remada com barra");
        ex11.setDescricao("Segure uma barra com as palmas das mãos voltadas para baixo, mantenha as costas retas e puxe a barra em direção ao abdômen, mantendo os cotovelos próximos ao corpo.");
        ex11.setDataCriacao(LocalDateTime.now());
        ex11.setDataModificacao(LocalDateTime.now());
        adiciona(ex11);
        
        Exercicio ex12 = new Exercicio();
        ex12.setNome("Elevação de panturrilhas");
        ex12.setDescricao(" Fique em pé em uma plataforma elevada com os calcanhares pendurados, levante os calcanhares o máximo possível e depois abaixe-os controladamente.");
        ex12.setDataCriacao(LocalDateTime.now());
        ex12.setDataModificacao(LocalDateTime.now());
        adiciona(ex12);
        
        Exercicio ex13 = new Exercicio();
        ex13.setNome("Abdominais crunch");
        ex13.setDescricao("Deite-se de costas, dobre os joelhos, coloque as mãos atrás da cabeça e levante os ombros em direção aos joelhos, contraindo os músculos abdominais.");
        ex13.setDataCriacao(LocalDateTime.now());
        ex13.setDataModificacao(LocalDateTime.now());
        adiciona(ex13);
        
        Exercicio ex14 = new Exercicio();
        ex14.setNome("Rosca Scott");
        ex14.setDescricao("Sente-se em um banco Scott, segure uma barra com as palmas das mãos voltadas para cima e levante-a em direção aos ombros, mantendo os cotovelos apoiados no banco.");
        ex14.setDataCriacao(LocalDateTime.now());
        ex14.setDataModificacao(LocalDateTime.now());
        adiciona(ex14);
        
        Exercicio ex15 = new Exercicio();
        ex15.setNome("Agachamento frontal (Front Squat)");
        ex15.setDescricao("Variação do agachamento que enfatiza os quadríceps. Segure uma barra na frente dos ombros, mantenha o tronco ereto e agache-se até que as coxas fiquem paralelas ao chão, depois volte à posição inicial. ");
        ex15.setDataCriacao(LocalDateTime.now());
        ex15.setDataModificacao(LocalDateTime.now());
        adiciona(ex15);
    }

    public boolean adiciona(Exercicio exercicio) {
        int proximaPosicaoLivre = proximaPosicaoLivre();
        if (proximaPosicaoLivre != -1) {
            vetorEx[proximaPosicaoLivre] = exercicio;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarTodosExercicios() {
        boolean temExercicio = false;
        for (Exercicio exercicio : vetorEx) {
            if (exercicio != null) {
                System.out.println(exercicio);
                temExercicio = true;
            }
        }
        if (!temExercicio) {
            System.out.println("NAO EXISTEM EXERCICIOS CADASTRADO.");
        }
    }

    public Exercicio buscaPorNome(String nome) {
        for (Exercicio exercicio : vetorEx) {
            if (exercicio != null && exercicio.getNome().equals(nome)) {
                return exercicio;
            }
        }
        return null;
    }

    public boolean remover(String nome) {
        for (int i = 0; i < vetorEx.length; i++) {
            if (vetorEx[i] != null && vetorEx[i].getNome().equals(nome)) {
                vetorEx[i] = null;
                return true;
            }
        }
        return false;
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < vetorEx.length; i++) {
            if (vetorEx[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
