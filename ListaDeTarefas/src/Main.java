import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n===== LISTA DE TAREFAS =====");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Concluir Tarefa");
            System.out.println("4. Remover Tarefa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("Digite a descrição da tarefa: ");
                        String descricao = scanner.nextLine();
                        gerenciador.adicionarTarefa(descricao);
                        break;

                    case 2:
                        gerenciador.listarTarefas();
                        break;

                    case 3:
                        gerenciador.listarTarefas();
                        System.out.print("Digite o número da tarefa a concluir: ");
                        int numConcluir = Integer.parseInt(scanner.nextLine()) - 1;
                        gerenciador.concluirTarefa(numConcluir);
                        break;

                    case 4:
                        gerenciador.listarTarefas();
                        System.out.print("Digite o número da tarefa a remover: ");
                        int numRemover = Integer.parseInt(scanner.nextLine()) - 1;
                        gerenciador.removerTarefa(numRemover);
                        break;

                    case 5:
                        System.out.println("Encerrando o sistema. Até logo!");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, insira um número válido.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}