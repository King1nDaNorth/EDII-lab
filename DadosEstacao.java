public class DadosEstacao {
    private String estacao;
    private String linha;
    private int ano;
    private int[] dadosMensais;

    public DadosEstacao(String estacao, String linha, int ano, int[] dadosMensais) {
        this.estacao = estacao;
        this.linha = linha;
        this.ano = ano;
        this.dadosMensais = dadosMensais;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int[] getDadosMensais() {
        return dadosMensais;
    }

    public void setDadosMensais(int[] dadosMensais) {
        this.dadosMensais = dadosMensais;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estação: ").append(estacao).append(", Ano: ").append(ano)
                .append(", Dados Mensais: ");
        for (int dado : dadosMensais) {
            sb.append(dado).append(" ");
        }
        sb.append(", Linha: ").append(linha);
        return sb.toString();
    }
}
