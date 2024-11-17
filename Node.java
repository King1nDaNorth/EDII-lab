public class Node {
    Estacao estacao;    
    int height;
    Node left;
    Node right;

    public Node(Estacao estacao) {
        this.estacao = estacao;   
        this.height = 1;
        left = null;
        right = null;
    }
}
