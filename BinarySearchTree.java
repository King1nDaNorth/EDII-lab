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
        String newKey = dados.getEstacao() + Integer.toString(dados.getAno());
        String currentKey = node.dados.getEstacao() + Integer.toString(node.dados.getAno());

        if (newKey.compareTo(currentKey) < 0) {
            node.left = insertRecursive(node.left, dados);
        } else if (newKey.compareTo(currentKey) > 0) {
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

        String searchKey = dados.getEstacao() + Integer.toString(dados.getAno());
        String currentKey = node.dados.getEstacao() + Integer.toString(node.dados.getAno());

        if (searchKey.equals(currentKey)) {
            return true;
        }

        return searchKey.compareTo(currentKey) < 0
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

        String deleteKey = dados.getEstacao() + Integer.toString(dados.getAno());
        String currentKey = node.dados.getEstacao() + Integer.toString(node.dados.getAno());


        if (deleteKey.compareTo(currentKey) < 0) {
            node.left = deleteRecursive(node.left, dados);
        } else if (deleteKey.compareTo(currentKey) > 0) {
            node.right = deleteRecursive(node.right, dados);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            node.dados = findMinValue(node.right);
            node.right = deleteRecursive(node.right, node.dados);
        }
        return node;
    }

    protected DadosEstacao findMinValue(Node node) {
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
