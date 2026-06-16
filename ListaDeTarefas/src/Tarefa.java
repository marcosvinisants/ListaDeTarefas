public class Tarefa {
    private String descricao;
    private boolean concluida;

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
    }

    public Tarefa(String descricao, boolean concluida) {
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean isConcluida() {
        return this.concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String paraLinhaArquivo() {
        return this.descricao + ";" + this.concluida;
    }

    @Override
    public String toString() {
        String status = this.concluida ? "[X]" : "[ ]";
        return status + " " + this.descricao;
    }
}