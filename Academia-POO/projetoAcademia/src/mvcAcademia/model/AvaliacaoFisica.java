package mvcAcademia.model;

import java.time.LocalDateTime;
import java.util.Date;

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
    private Date dataCriacao;
    private Date dataModificacao;

    public AvaliacaoFisica() {
        id = AvaliacaoFisica.serial++;
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

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

   @Override
public String toString() {
    return "\n==================INFORMACOES DA AVALIACAO FISICA================== " 
            + "\nID: " + id 
            + "\nULTIMO TREINO: " + ultimoTreino
            + "\nPESO: " + peso + " kg"
            + "\nALTURA: " + altura + " m"
            + "\nIMC: " + imc 
            + "\nINDICE DE SATISFACAO: " + indiceSatisfacao 
            + "\nDATA DE CRIACAO: " + dataCriacao 
            + "\nDATA DE MODIFICACAO: " + dataModificacao;
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
