public class AnaliseDados {

    public void totais() {
        System.out.println("Totais por estacao e por ano:");
        totais(root, null, new int[1]);
    }

    private void totais(AVLNode node, String estacaoAtual, int[] totalGeral) {
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
    
    private void medias(AVLNode node, String estacaoAtual, double[] somaTotalGeral) {
        if (node == null) {
            return;
        }
    
        medias(node.left, estacaoAtual, somaTotalGeral);
    
        DadosEstacao data = node.data;
        String estacao = data.getEstacao();
        int ano = data.getAno();
    
        if (estacaoAtual == null || !estacaoAtual.equals(estacao)) {
            if (estacaoAtual != null) {
                System.out.println("Media total (4 anos): " + (somaTotalGeral[0] / 4));
            }
            System.out.println("estacao: " + estacao);
            somaTotalGeral[0] = 0;
        }
    
        int somaAno = 0;
        for (int valor : data.getDadosMensais()) {
            somaAno += valor;
        }
        double mediaAno = somaAno / 12.0;
    
        somaTotalGeral[0] += somaAno;
    
        System.out.println("  Ano " + ano + ": media " + mediaAno);
    
        medias(node.right, estacao, somaTotalGeral);
    }


}
