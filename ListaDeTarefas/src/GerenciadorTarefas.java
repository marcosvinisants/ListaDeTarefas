import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorTarefas {
    private final String FILE_PATH = "tarefas.txt";
    private List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        this.tarefas = new ArrayList<>();
        this.carregarTarefasDoArquivo();
    }

    public void adicionarTarefa(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            System.out.println("Erro: A descrição da tarefa não pode estar vazia.");
            return;
        }

        Tarefa novaTarefa = new Tarefa(descricao.trim());
        this.tarefas.add(novaTarefa);
        this.salvarTarefasNoArquivo();
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarTarefas() {
        if (this.tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("\n--- SUAS TAREFAS ---");
        for (int i = 0; i < this.tarefas.size(); i++) {
            System.out.println((i + 1) + ". " + this.tarefas.get(i));
        }
    }

    public void concluirTarefa(int indice) {
        if (this.indiceValidado(indice)) {
            Tarefa tarefaSelecionada = this.tarefas.get(indice);

            if (tarefaSelecionada.isConcluida()) {
                System.out.println("Aviso: Esta tarefa já está marcada como concluída!");
                return;
            }

            tarefaSelecionada.setConcluida(true);
            this.salvarTarefasNoArquivo();
            System.out.println("Tarefa marcada como concluída com sucesso!");
        }
    }

    public void removerTarefa(int indice) {
        if (this.indiceValidado(indice)) {
            this.tarefas.remove(indice);
            this.salvarTarefasNoArquivo();
            System.out.println("Tarefa removida com sucesso!");
        }
    }

    private boolean indiceValidado(int indice) {
        if (this.tarefas.isEmpty()) {
            System.out.println("Erro: Não existem tarefas cadastradas.");
            return false;
        }
        if (indice < 0 || indice >= this.tarefas.size()) {
            System.out.println("Erro: Opção inválida! Escolha de 1 a " + this.tarefas.size() + ".");
            return false;
        }
        return true;
    }

    private void salvarTarefasNoArquivo() {
        try {
            FileWriter escritor = new FileWriter(FILE_PATH);

            for (Tarefa tarefa : this.tarefas) {
                escritor.write(tarefa.paraLinhaArquivo() + "\n");
            }

            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro crítico ao gravar no arquivo tarefas.txt.");
        }
    }

    private void carregarTarefasDoArquivo() {
        try {
            File arquivo = new File(FILE_PATH);

            if (!arquivo.exists()) {
                return;
            }

            Scanner leitorArquivo = new Scanner(arquivo);

            while (leitorArquivo.hasNextLine()) {
                String dynamicLine = leitorArquivo.nextLine();

                if (dynamicLine.trim().isEmpty()) {
                    continue;
                }

                String[] partes = dynamicLine.split(";");

                if (partes.length == 2) {
                    String descricao = partes[0].trim();
                    boolean concluida = Boolean.parseBoolean(partes[1].trim());
                    this.tarefas.add(new Tarefa(descricao, concluida));
                }
            }

            leitorArquivo.close();
        } catch (IOException e) {
            System.out.println("Erro crítico ao ler o arquivo tarefas.txt.");
        }
    }
}