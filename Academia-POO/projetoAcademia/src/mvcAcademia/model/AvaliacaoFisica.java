package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */
public class AvaliacaoFisica {

    private static long serial = 1;
    private long id;
    private Pessoa pessoa;
    private Treino ultimoTreino;
    private double peso;
    private double altura;
    private double imc;
    private int indiceSatisfacao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public AvaliacaoFisica() {
        id = AvaliacaoFisica.serial++;
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

    public Treino getUltimoTreino() {
        return ultimoTreino;
    }

    public void setUltimoTreino(Treino ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public int getIndiceSatisfacao() {
        return indiceSatisfacao;
    }

    public void setIndiceSatisfacao(int indiceSatisfacao) {
        this.indiceSatisfacao = indiceSatisfacao;
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

    public double calculaIMC(double peso, double altura) {
        if (altura > 3) {
            altura /= 100;
        }
        double imc = peso / (altura * altura);

        return imc;
    }

    public double interpretaIMC(double imc) {
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
        return imc;
    }

    @Override
    public String toString() {
        return "\n=========INFORMACOES DA AVALIACAO FISICA========="
                + "\nID: " + id
                + "\nPESO: " + peso + " kg"
                + "\nALTURA: " + altura + " m"
                + "\nIMC: " + imc
                + "\nINDICE DE SATISFACAO: " + indiceSatisfacao
                + "\nDATA DE CRIACAO: " + dataCriacao
                + "\nDATA DE MODIFICACAO: " + dataModificacao
                + ultimoTreino.toStringAluno()
                + pessoa.toStringAluno();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final AvaliacaoFisica other = (AvaliacaoFisica) obj;
        return this.id == other.id;
    }

}
