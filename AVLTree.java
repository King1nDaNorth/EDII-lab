// Precisa modificar para refletir mudancas nos Nodes, que agora incluem os objetos da estacao
// Principalmente na parte de comparacao, como na linha 18. talvez precise comparar
// "estacao" (nome) + "ano", para nao dar erro, porque teremos 4 de cada estacao 


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

        if (dados < node.dados) {
            node.left = insertRecursive(node.left, dados);
        } else if (dados > node.dados) {
            node.right = insertRecursive(node.right, dados);
        } else {
            return node; // Duplicados não são permitidos
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

        if (dados < node.dados) {
            node.left = deleteRecursive(node.left, dados);
        } else if (dados > node.dados) {
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
}
