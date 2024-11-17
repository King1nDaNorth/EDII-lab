import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int[] lerValoresDoCSV(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                String estacao = dados[0];
                int ano = Integer.parseInt(dados[1]);
                int[] dadosMensais = Arrays.stream(Arrays.copyOfRange(dados, 2, 14))
                                           .mapToInt(Integer::parseInt)
                                           .toArray();
                // Modificar para situacoes de multiplas linhas. Talvez fazer array tambem?
                int linhaArvore = Integer.parseInt(dados[14]);

                DadosEstacao dadosEstacao = new DadosEstacao(estacao, ano, dadosMensais, linhaArvore);

                avlTree.insert(dadosEstacao)
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro na formatação dos números: " + e.getMessage());
        }
        return new int[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree tree = null;

        System.out.println("Escolha o tipo de árvore:");
        System.out.println("1. Árvore BST");
        System.out.println("2. Árvore AVL");
        System.out.println("3. Carregar dados de um arquivo CSV para BST");
        System.out.println("4. Carregar dados de um arquivo CSV para AVL");
        System.out.print("Digite a opção desejada: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                tree = new BinarySearchTree();
                break;
            case 2:
                tree = new AVLTree();
                break;
            case 3:
            case 4:
                String caminhoCSV = "src/main/java/dados.csv";
                int[] valores = lerValoresDoCSV(caminhoCSV);
                if (valores.length == 0) {
                    System.out.println("Nenhum valor foi carregado. Saindo...");
                    scanner.close();
                    return;
                }
                if (choice == 3) {
                    tree = new BinarySearchTree();
                } else {
                    tree = new AVLTree();
                }
                for (int valor : valores) {
                    tree.insert(valor);
                }
                System.out.println("Dados carregados com sucesso.");
                break;
            default:
                System.out.println("Opção inválida.");
                scanner.close();
                return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nOpções:");
            System.out.println("1. Inserir");
            System.out.println("2. Buscar");
            System.out.println("3. Deletar");
            System.out.println("4. Exibir em pré-ordem");
            System.out.println("5. Exibir em ordem");
            System.out.println("6. Exibir em pós-ordem");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
                continue;
            }
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Digite o valor para inserir: ");
                    if (scanner.hasNextInt()) {
                        int valueToInsert = scanner.nextInt();
                        tree.insert(valueToInsert);
                        System.out.println("Valor inserido.");
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.next();
                    }
                    break;
                case 2:
                    System.out.print("Digite o valor para buscar: ");
                    if (scanner.hasNextInt()) {
                        int valueToSearch = scanner.nextInt();
                        boolean encontrado = tree.search(valueToSearch);
                        System.out.println("Encontrado: " + encontrado);
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.next();
                    }
                    break;
                case 3:
                    System.out.print("Digite o valor para deletar: ");
                    if (scanner.hasNextInt()) {
                        int valueToDelete = scanner.nextInt();
                        tree.delete(valueToDelete);
                        System.out.println("Valor deletado (se existia).");
                    } else {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                        scanner.next();
                    }
                    break;
                case 4:
                    System.out.println("Árvore em pré-ordem:");
                    tree.preOrderTraversal();
                    break;
                case 5:
                    System.out.println("Árvore em ordem:");
                    tree.inOrderTraversal();
                    break;
                case 6:
                    System.out.println("Árvore em pós-ordem:");
                    tree.postOrderTraversal();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
