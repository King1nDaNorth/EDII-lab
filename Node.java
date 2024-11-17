public class Node {
    String estacao;
    String linha;
    
    int ano;
    int jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez;
    
    int height;
    Node left;
    Node right;

    public Node(String estacao, String linha, int ano, int jan, int fev, int mar, int abr, int mai, int jun, int jul, int ago, int set, int out, int nov, int dez) {
        
        this.estacao = estacao;
        this.linha = linha;
        this.ano = ano;
        this.jan = jan;
        this.fev = fev;
        this.mar = mar;
        this.abr = abr;
        this.mai = mai;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.set = set;
        this.out = out;
        this.nov = nov;
        this.dez = dez;
        
        this.height = 1;
        left = null;
        right = null;
    }
}
