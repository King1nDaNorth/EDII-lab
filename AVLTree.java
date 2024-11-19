public class AVLTree extends BinarySearchTree {

    @Override
    public void insert(DadosEstacao dados) {
        root = insertRecursive(root, dados);
    }

    @Override
    protected Node insertRecursive(Node node, DadosEstacao dados) {
        if (node == null) {
            return new Node(dados);
        }
        String newKey = dados.getEstacao() + Integer.toString(dados.getAno());
        String currentKey = node.dados.getEstacao() + Integer.toString(node.dados.getAno());

        if (newKey.compareTo(currentKey) < 0) {
            node.left = insertRecursive(node.left, dados);
        } else if (newKey.compareTo(currentKey) > 0) {
            node.right = insertRecursive(node.right, dados);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    @Override
    public void delete(DadosEstacao dados) {
        root = deleteRecursive(root, dados);
    }

    @Override
    protected Node deleteRecursive(Node node, DadosEstacao dados) {
        if (node == null)
            return null;

        String deleteKey = dados.getEstacao() + Integer.toString(dados.getAno());
        String currentKey = node.dados.getEstacao() + Integer.toString(node.dados.getAno());


        if (deleteKey.compareTo(currentKey) < 0) {
            node.left = deleteRecursive(node.left, dados);
        } else if (deleteKey.compareTo(currentKey) > 0) {
            node.right = deleteRecursive(node.right, dados);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                node.dados = findMinValue(node.right);
                node.right = deleteRecursive(node.right, node.dados);
            }
        }

        if (node == null)
            return null;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node balance(Node node) {
        int balanceFactor = getBalance(node);

        if (balanceFactor > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balanceFactor < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Metodos de travessia

    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.dados + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.print(node.dados + " ");
        inOrderTraversal(node.right);
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.dados + " ");
    }

    // Metodos de analise dos dados

    public void totais() {
        System.out.println("Totais por estacao e por ano:");
        totais(root, null, new int[1]);
    }

    private void totais(Node node, String estacaoAtual, int[] totalGeral) {
        if (node == null) return;

        totais(node.left, estacaoAtual, totalGeral);

        DadosEstacao dados = node.dados;
        String estacao = dados.getEstacao();
        int ano = dados.getAno();

        if (estacaoAtual == null || !estacaoAtual.equals(estacao)) {
            if (estacaoAtual != null) {
                System.out.println("Total geral (4 anos): " + totalGeral[0]);
            }
            System.out.println("Estacao: " + estacao);
            totalGeral[0] = 0;
        }

        int totalAno = 0;
        for (int valor : dados.getDadosMensais()) {
            totalAno += valor;
        }

        totalGeral[0] += totalAno;

        System.out.println("  Ano " + ano + ": " + totalAno);

        totais(node.right, estacao, totalGeral);
    }

    public void medias() {
        System.out.println("Medias por estacao e por ano:");
        medias(root, null, new double[1]);
    }

    private void medias(Node node, String estacaoAtual, double[] somaTotalGeral) {
        if (node == null) {
            return;
        }

        medias(node.left, estacaoAtual, somaTotalGeral);

        DadosEstacao dados = node.dados;
        String estacao = dados.getEstacao();
        int ano = dados.getAno();

        if (estacaoAtual == null || !estacaoAtual.equals(estacao)) {
            if (estacaoAtual != null) {
                System.out.println("Media total (4 anos): " + (somaTotalGeral[0] / 4));
            }
            System.out.println("estacao: " + estacao);
            somaTotalGeral[0] = 0;
        }

        int somaAno = 0;
        for (int valor : dados.getDadosMensais()) {
            somaAno += valor;
        }
        double mediaAno = somaAno / 12.0;

        somaTotalGeral[0] += somaAno;

        System.out.println("  Ano " + ano + ": media " + mediaAno);

        medias(node.right, estacao, somaTotalGeral);
    }
}
