import java.util.Random;

public class Main {


    private static NoTabuleiro[][] iniciaTabuleiro(int tamanho){
        NoTabuleiro[][] tab = new NoTabuleiro[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                tab[i][j] = new NoTabuleiro();
                tab[i][j].setColuna(j);
                tab[i][j].setLinha(i);
            }
        }
        return tab;
    }

    private static void preencherMatriz(int tamanho, Random gerador, NoTabuleiro[][] tab){
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                tab[i][j].setValor(gerador.nextInt(15));
            }
        }
    }

    private static void printarMatriz(NoTabuleiro[][] tab){
        for (NoTabuleiro[] aTab : tab) {
            System.out.println();
            for (NoTabuleiro anATab : aTab) {
                System.out.print(anATab.getValor() + ", ");
            }
        }
    }

    private static void damasGuloso(NoTabuleiro[][] tab, NoTabuleiro[] res){
        NoTabuleiro ini = new NoTabuleiro();
        ini.setValor(-99999999);
        res[0] = ini;
        int j = 0;
        for(int i = 0; i < tab.length; i++){
            if(tab[0][i].getValor() > res[0].getValor()){
                res[0] = tab[0][i];
                j = i;
            }
        }
        encontraCaminhoGuloso(tab, res, j, 1);

    }

    private static void encontraCaminhoGuloso(NoTabuleiro[][] tab, NoTabuleiro[] res, int pos, int linha) {
        if (linha >= res.length) {
            return;
        } else {
            int mod = pos % res.length;
            if (mod == 9) {
                if (tab[linha][pos].getValor() > tab[linha][pos - 1].getValor()) {
                    res[linha] = tab[linha][pos];
                    int j = linha + 1;
                    encontraCaminhoGuloso(tab, res, pos, j);
                } else {
                    res[linha] = tab[linha][pos - 1];
                    int j = linha + 1;
                    encontraCaminhoGuloso(tab, res, pos - 1, j);
                }
            }
            else{
                if (mod == 0) {
                    if (tab[linha][pos].getValor() > tab[linha][pos + 1].getValor()) {
                        res[linha] = tab[linha][pos];
                        int j = linha + 1;
                        encontraCaminhoGuloso(tab, res, pos, j);
                    } else {
                        res[linha] = tab[linha][pos + 1];
                        int j = linha + 1;
                        encontraCaminhoGuloso(tab, res, pos + 1, j);
                    }
                } else{
                    if (tab[linha][pos].getValor() > tab[linha][pos - 1].getValor() && tab[linha][pos].getValor() > tab[linha][pos + 1].getValor()) {
                        res[linha] = tab[linha][pos];
                        int j = linha + 1;
                        encontraCaminhoGuloso(tab, res, pos, j);
                    } else {
                        if (tab[linha][pos + 1].getValor() > tab[linha][pos - 1].getValor() && tab[linha][pos + 1].getValor() > tab[linha][pos].getValor()) {
                            res[linha] = tab[linha][pos + 1];
                            int j = linha + 1;
                            encontraCaminhoGuloso(tab, res, pos + 1, j);
                        } else {
                            res[linha] = tab[linha][pos - 1];
                            int j = linha + 1;
                            encontraCaminhoGuloso(tab, res, pos - 1, j);
                        }
                    }
                }
            }
        }
    }

    private static int valorTotalAtual(NoTabuleiro[] res){
        if (res[1] == null){
            return -99999;
        }
        int total = 0;
        for (NoTabuleiro no: res) {
            total += no.getValor();
        }
        return total;
    }

    private static void damasDinamico(NoTabuleiro[][] tab, NoTabuleiro[] res){
        int j = 0;
        for (int i = 1; i < res.length; i++){
            if (tab[0][i].getValor() >= tab[0][j].getValor()){
                res[0] = tab[0][i];
                j = i;
            }
        }
        NoTabuleiro[] temporario = new NoTabuleiro[res.length];
        temporario[0] = res[0];
        encontrarCaminhoDinamico(tab, res, temporario, res[0].getValor(), 1, j);

    }

    private static void encontrarCaminhoDinamico(NoTabuleiro[][] tab, NoTabuleiro[] res, NoTabuleiro[] temp, int valorAtual, int linha, int pos){
        if(linha > res.length - 1){
            if (valorAtual > valorTotalAtual(res)){
                System.arraycopy(temp, 0, res, 0, res.length);
            }
        } else{
            int mod = pos % res.length;
            if (mod == res.length - 1){
                int j = linha;
                int novoAtual = valorAtual;
                int copia = novoAtual;
                for(int i = pos - 1; i < pos + 1; i++){
                    temp[j] = tab[j][i];
                    novoAtual += tab[j][i].getValor();
                    j++;
                    encontrarCaminhoDinamico(tab, res, temp, novoAtual, j, i);
                    j--;
                    novoAtual = copia;
                }
            } else {
                if (mod == 0){
                    int j = linha;
                    int novoAtual = valorAtual;
                    int copia = novoAtual;
                    for(int i = pos; i < pos + 2; i++){
                        temp[j] = tab[j][i];
                        novoAtual += tab[j][i].getValor();
                        j++;
                        encontrarCaminhoDinamico(tab, res, temp, novoAtual, j, i);
                        j--;
                        novoAtual = copia;
                    }
                } else{
                    int j = linha;
                    int novoAtual = valorAtual;
                    int copia = novoAtual;
                    for(int i = pos - 1; i <= pos + 1; i++){
                        temp[j] = tab[j][i];
                        novoAtual += tab[j][i].getValor();
                        j++;
                        encontrarCaminhoDinamico(tab, res, temp, novoAtual, j, i);
                        j--;
                        novoAtual = copia;
                    }
                }
            }
        }
    }





    public static void main(String[] args) {
        NoTabuleiro[][] tab = iniciaTabuleiro(10);
        Random gerador = new Random();
        preencherMatriz(10, gerador, tab);
        printarMatriz(tab);
        NoTabuleiro[] res = new NoTabuleiro[10];
        damasDinamico(tab, res);
        System.out.println("Resultado: ");
        for (NoTabuleiro re : res) {
            System.out.print(re.getValor() + ", ");
        }



    }
}
