package mvcAcademia.model;

import java.time.LocalDateTime;

/**
 *
 * @author barbrete e kitotsui
 */
public class DivisaoTreinoMusculo {

    private static long serial = 1;
    private long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private DivisaoTreino divisaoTreino;  
    

    public DivisaoTreinoMusculo() {
        this.id = serial++;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

      public DivisaoTreino getDivisaoTreino() {  
        return divisaoTreino;
    }

    public void setDivisaoTreino(DivisaoTreino divisaoTreino) {  
        this.divisaoTreino = divisaoTreino;
    }

    @Override
    public String toString() {
        return   "\n" + divisaoTreino 
                + "\n================== DIVISAO DE TREINO MUSCULO ASSOCIADA: ================= "
                + " \nID: " + id
                + " \nNOME: " + nome
                + " \nDESCRICAO: " + descricao;
    }
}
