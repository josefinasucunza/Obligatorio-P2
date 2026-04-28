package dominio;

public class Tablero {

    private char[][] matriz;

    public static void main(String[] args) {
        Tablero t = new Tablero();
        System.out.println(t.prepararTablero());
    }

    public Tablero() {
        this.matriz = tableroDefault();
    }

    private char[][] tableroDefault() {
        char[][] m = new char[8][10];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 10; j++)
                m[i][j] = 'V';

        // negras
        m[0][2] = 'N'; m[0][3] = 'N'; m[0][6] = 'N'; m[0][7] = 'N';
        for (int j = 0; j < 10; j++) m[1][j] = 'N';
        m[2][0] = 'N'; m[2][1] = 'N'; m[2][4] = 'N'; m[2][5] = 'N'; m[2][8] = 'N'; m[2][9] = 'N';

        // blancas
        m[5][0] = 'B'; m[5][1] = 'B'; m[5][4] = 'B'; m[5][5] = 'B'; m[5][8] = 'B'; m[5][9] = 'B';
        for (int j = 0; j < 10; j++) m[6][j] = 'B';
        m[7][2] = 'B'; m[7][3] = 'B'; m[7][6] = 'B'; m[7][7] = 'B';

        return m;
    }

    public void cargarDefault() {
        this.matriz = tableroDefault();
    }

    public void cargarMatriz(String[] filas) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 10; j++)
                matriz[i][j] = Character.toUpperCase(filas[i].charAt(j));
    }

    public String prepararTablero() {
        String separador = "+---+---+---+---+---+---+---+---+---+---+";
        String resultado = "";

        for (int i = 0; i < 8; i++) {
            resultado += separador + "\n";
            resultado += "|";
            for (int j = 0; j < 10; j++) {
                if (matriz[i][j] == 'V') {
                    resultado += "   |";
                } else {
                    resultado += " " + matriz[i][j] + " |";
                }
            }
            resultado += "\n";
        }
        resultado += separador;

        return resultado;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(char[][] m) {
        this.matriz = m;
    }
}