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
                    int j = linha++;
                    encontraCaminhoGuloso(tab, res, pos, linha++);
                } else {
                    res[linha] = tab[linha][pos - 1];
                    int j = linha++;
                    encontraCaminhoGuloso(tab, res, pos - 1, linha++);
                }
            }
            else{
                if (mod == 0) {
                    if (tab[linha][pos].getValor() > tab[linha][pos + 1].getValor()) {
                        res[linha] = tab[linha][pos];
                        int j = linha++;
                        encontraCaminhoGuloso(tab, res, pos, linha++);
                    } else {
                        res[linha] = tab[linha][pos + 1];
                        int j = linha++;
                        encontraCaminhoGuloso(tab, res, pos + 1, linha++);
                    }
                } else{
                    if (tab[linha][pos].getValor() > tab[linha][pos - 1].getValor() && tab[linha][pos].getValor() > tab[linha][pos + 1].getValor()) {
                        res[linha] = tab[linha][pos];
                        int j = linha++;
                        encontraCaminhoGuloso(tab, res, pos, linha++);
                    } else {
                        if (tab[linha][pos + 1].getValor() > tab[linha][pos - 1].getValor() && tab[linha][pos + 1].getValor() > tab[linha][pos].getValor()) {
                            res[linha] = tab[linha][pos + 1];
                            int j = linha++;
                            encontraCaminhoGuloso(tab, res, pos + 1, linha++);
                        } else {
                            res[linha] = tab[linha][pos - 1];
                            int j = linha++;
                            encontraCaminhoGuloso(tab, res, pos - 1, linha++);
                        }
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
        System.out.println(res.length);
        damasGuloso(tab, res);
        System.out.println("Resultado: ");
        for (int i = 0; i < 10; i++){
            System.out.print(res[i].getValor() + ", ");
        }



    }
}
