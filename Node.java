public class Node {
    DadosEstacao dados;    
    int height;
    Node left;
    Node right;

    public Node(DadosEstacao dados) {
        this.dados = dados;   
        this.height = 1;
        left = null;
        right = null;
    }
}
