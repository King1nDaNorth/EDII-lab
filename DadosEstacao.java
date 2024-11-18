public class DadosEstacao {
    private String estacao;
    private int ano;
    private int[] dadosMensais;
    private int linha;

    public DadosEstacao(String estacao, int ano, int[] dadosMensais, int linha) {
        this.estacao = estacao;
        this.ano = ano;
        this.dadosMensais = dadosMensais;
        this.linha = linha;
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

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
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
