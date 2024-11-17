// Precisa modificar para refletir mudancas nos Nodes, que agora incluem os objetos da estacao
// Principalmente na parte de comparacao, como na linha 18. talvez precise comparar
// "estacao" (nome) + "ano", para nao dar erro, porque teremos 4 de cada estacao

public class BinarySearchTree {
    protected Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(DadosEstacao dados) {
        root = insertRecursive(root, dados);
    }

    protected Node insertRecursive(Node node, DadosEstacao dados) {
        if (node == null) {
            return new Node(dados);
        }
        if (dados < node.dados) {
            node.left = insertRecursive(node.left, dados);
        } else if (dados > node.dados) {
            node.right = insertRecursive(node.right, dados);
        }
        return node;
    }

    public boolean search(DadosEstacao dados) {
        return searchRecursive(root, dados);
    }

    private boolean searchRecursive(Node node, DadosEstacao dados) {
        if (node == null) {
            return false;
        }
        if (dados == node.dados) {
            return true;
        }
        return dados < node.dados
            ? searchRecursive(node.left, dados)
            : searchRecursive(node.right, dados);
    }

    public void delete(DadosEstacao dados) {
        root = deleteRecursive(root, dados);
    }

    protected Node deleteRecursive(Node node, DadosEstacao dados) {
        if (node == null) {
            return null;
        }

        if (dados < node.dados) {
            node.left = deleteRecursive(node.left, dados);
        } else if (dados > node.dados) {
            node.right = deleteRecursive(node.right, dados);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            node.dados = findMinValue(node.right);
            node.right = deleteRecursive(node.right, node.dados);
        }
        return node;
    }

    protected int findMinValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.dados;
    }

    public void inOrderTraversal() {
        inOrderTraversalRecursive(root);
    }

    private void inOrderTraversalRecursive(Node node) {
        if (node != null) {
            inOrderTraversalRecursive(node.left);
            System.out.print(node.dados + " ");
            inOrderTraversalRecursive(node.right);
        }
    }

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
}
