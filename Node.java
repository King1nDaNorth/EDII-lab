public class Node {
    DadosEstacao estacao;    
    int height;
    Node left;
    Node right;

    public Node(DadosEstacao estacao) {
        this.estacao = estacao;   
        this.height = 1;
        left = null;
        right = null;
    }
}
