public class NoTabuleiro {
    private NoTabuleiro paiEsq;
    private NoTabuleiro paiDir;
    private NoTabuleiro paiCen;
    private NoTabuleiro filEsq;
    private NoTabuleiro filDir;
    private NoTabuleiro filCen;
    private int valor;
    private int linha;
    private int coluna;

    public NoTabuleiro getPaiEsq() {
        return paiEsq;
    }

    public void setPaiEsq(NoTabuleiro paiEsq) {
        this.paiEsq = paiEsq;
    }

    public NoTabuleiro getPaiDir() {
        return paiDir;
    }

    public void setPaiDir(NoTabuleiro paiDir) {
        this.paiDir = paiDir;
    }

    public NoTabuleiro getPaiCen() {
        return paiCen;
    }

    public void setPaiCen(NoTabuleiro paiCen) {
        this.paiCen = paiCen;
    }

    public NoTabuleiro getFilEsq() {
        return filEsq;
    }

    public void setFilEsq(NoTabuleiro filEsq) {
        this.filEsq = filEsq;
    }

    public NoTabuleiro getFilDir() {
        return filDir;
    }

    public void setFilDir(NoTabuleiro filDir) {
        this.filDir = filDir;
    }

    public NoTabuleiro getFilCen() {
        return filCen;
    }

    public void setFilCen(NoTabuleiro filCen) {
        this.filCen = filCen;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}
