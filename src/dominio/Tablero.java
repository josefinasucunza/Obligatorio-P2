package dominio;
/**
 *
 * @author Josefina Sucunza(258389) J.Bautista Díaz(272168) 
 */

public class Tablero {

    private String[][] logico;
    private String[][] visual;

    public Tablero() {
        cargarDefault();
    }

    public void cargarDefault() {
        this.logico = tableroDefaultLogico();
        this.visual = tableroVisualDeLogico(this.logico);
    }

    public void cargarMatriz(String[] filas) {
        this.logico = new String[8][10];

        for (int i = 0; i < filas.length; i++) {
            for (int j = 0; j < filas[i].length(); j++) {

                char c = Character.toUpperCase(filas[i].charAt(j));

                if (c == 'V') {
                    this.logico[i][j] = " ";
                } else {
                    this.logico[i][j] = String.valueOf(c);
                }
            }
        }

        this.visual = tableroVisualDeLogico(this.logico);
    }

    public String prepararTablero() {
        this.visual = tableroVisualDeLogico(this.logico);

        String resultado = "";

        for (int i = 0; i < this.visual.length; i++) {
            for (int j = 0; j < this.visual[0].length; j++) {
                resultado += this.visual[i][j];
            }
            resultado += "\n";
        }

        return resultado;
    }

    public static String[][] tableroDefaultLogico() {
        String[][] logicoDefault = new String[8][10];

        for (int i = 0; i < logicoDefault.length; i++) {
            for (int j = 0; j < logicoDefault[0].length; j++) {

                logicoDefault[i][j] = " ";

                if (i == 0) {
                    if (j == 2 || j == 3 || j == 6 || j == 7) {
                        logicoDefault[i][j] = "N";
                    }

                } else if (i == 1) {
                    logicoDefault[i][j] = "N";

                } else if (i == 2) {
                    if (j == 0 || j == 1 || j == 4 || j == 5 || j == 8 || j == 9) {
                        logicoDefault[i][j] = "N";
                    }

                } else if (i == 5) {
                    if (j == 0 || j == 1 || j == 4 || j == 5 || j == 8 || j == 9) {
                        logicoDefault[i][j] = "B";
                    }

                } else if (i == 6) {
                    logicoDefault[i][j] = "B";

                } else if (i == 7) {
                    if (j == 2 || j == 3 || j == 6 || j == 7) {
                        logicoDefault[i][j] = "B";
                    }
                }
            }
        }

        return logicoDefault;
    }

    public static String[][] tableroVisualDeLogico(String[][] mat) {
        String[][] visual = new String[17][41];

        for (int i = 0; i < visual.length; i++) {
            for (int j = 0; j < visual[0].length; j++) {

                if (i % 2 == 0) {
                    if (j % 4 == 0) {
                        visual[i][j] = "+";
                    } else {
                        visual[i][j] = "-";
                    }

                } else {
                    if (j % 4 == 0) {
                        visual[i][j] = "|";
                    } else {
                        visual[i][j] = " ";
                    }
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                int filaVisual = i * 2 + 1;
                int colVisual = j * 4 + 2;

                if (mat[i][j].equalsIgnoreCase("V")) {
                    visual[filaVisual][colVisual] = " ";
                } else {
                    visual[filaVisual][colVisual] = mat[i][j];
                }
            }
        }

        return visual;
    }

    public String[][] getLogico() {
        return this.logico;
    }

    public String[][] getVisual() {
        return this.visual;
    }
    
    public static int caso1(String[][] mat, char c) {
        int suma = 0;
        String aux = String.valueOf(c);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j].equalsIgnoreCase(aux)) {
                    suma++;
                }
            }
        }
        return suma;
    }

    public static boolean caso2(String[][] mat, char color, String sentido,
                                int fila, int columna, int pasos) {
        boolean movValido = true;
        String sColor = String.valueOf(color).toUpperCase();
        sentido = sentido.toUpperCase();

        if (!mat[fila][columna].equalsIgnoreCase(sColor)) {
            movValido = false;
        }

        // Validar que el sentido sea permitido para ese color
        if (movValido) {
            if (sColor.equals("N")) {
                if (!sentido.equals("N") && !sentido.equals("NE") && !sentido.equals("NO")
                        /*&& !sentido.equals("E") && !sentido.equals("O")*/) {
                    movValido = false;
                }
            } else { // "B"
                if (!sentido.equals("S") && !sentido.equals("SE") && !sentido.equals("SO")
                        /*&& !sentido.equals("E") && !sentido.equals("O")*/) {
                    movValido = false;
                }
            }
        }

        // Calcular el delta de fila y columna según el sentido
        int dFila = 0;
        int dCol = 0;

        if (movValido) {
            if (sentido.equals("N")) {
                dFila = -1;
                dCol = 0;
            } else if (sentido.equals("S")) {
                dFila = 1;
                dCol = 0;
            } else if (sentido.equals("E")) {
                dFila = 0;
                dCol = 1;
            } else if (sentido.equals("O")) {
                dFila = 0;
                dCol = -1;
            } else if (sentido.equals("NE")) {
                dFila = -1;
                dCol = 1;
            } else if (sentido.equals("NO")) {
                dFila = -1;
                dCol = -1;
            } else if (sentido.equals("SE")) {
                dFila = 1;
                dCol = 1;
            } else if (sentido.equals("SO")) {
                dFila = 1;
                dCol = -1;
            }
        }

        // Validar el camino intermedio (debe estar vacío)
        // y que no se salga del tablero
        int paso = 1;
        while (movValido && paso <= pasos) {
            int nFila = fila + dFila * paso;
            int nCol = columna + dCol * paso;

            // Verificar límites
            if (nFila < 0 || nFila >= mat.length || nCol < 0 || nCol >= mat[0].length) {
                movValido = false;
            }

            // Verificar camino: pasos intermedios deben estar vacíos
            // el último paso puede estar vacío o tener ficha del color opuesto
            if (movValido && paso < pasos) {
                if (!mat[nFila][nCol].equals(" ")) {
                    movValido = false;
                }
            }

            // Último paso: vacío o color contrario
            if (movValido && paso == pasos) {
                String colorOpuesto = sColor.equals("B") ? "N" : "B";
                if (!mat[nFila][nCol].equals(" ") && !mat[nFila][nCol].equalsIgnoreCase(colorOpuesto)) {
                    movValido = false;
                }
            }

            paso++;
        }

        // Si es válido, modificar la matriz
        if (movValido) {
            int nFila = fila + dFila * pasos;
            int nCol = columna + dCol * pasos;
            mat[nFila][nCol] = mat[fila][columna];
            mat[fila][columna] = " ";
        }

        return movValido;
    }

    public static boolean caso3(String[][] mat, char color, char forma,
            String sentido, int fila, int col, int tamanio, int pasos) {
        boolean movValido = true;
        String sColor = String.valueOf(color).toUpperCase();
        sentido = sentido.toUpperCase();
        String sForma = String.valueOf(forma).toUpperCase();

        // Grupo "H" solo se mueve verticalmente (N o S)
        // Grupo "V" solo se mueve horizontalmente (E u O)
        if (sForma.equals("H")) {
            if (!sentido.equals("N") && !sentido.equals("S")) {
                movValido = false;
            }
        } else if (sForma.equals("V")) {
            if (!sentido.equals("E") && !sentido.equals("O")) {
                movValido = false;
            }
        } else {
            movValido = false;
        }

        // Validar que el sentido sea permitido para ese color
        if (movValido) {
            if (sColor.equals("B")) {
                if (!sentido.equals("N") && !sentido.equals("E") && !sentido.equals("O")) {
                    movValido = false;
                }
            } else {
                if (!sentido.equals("S") && !sentido.equals("E") && !sentido.equals("O")) {
                    movValido = false;
                }
            }
        }

        // Calcular delta
        int dFila = 0;
        int dCol = 0;
        if (movValido) {
            if (sentido.equals("N")) {
                dFila = -1;
                dCol = 0;
            } else if (sentido.equals("S")) {
                dFila = 1;
                dCol = 0;
            } else if (sentido.equals("E")) {
                dFila = 0;
                dCol = 1;
            } else if (sentido.equals("O")) {
                dFila = 0;
                dCol = -1;
            }
        }

        // Verificar que todas las fichas del grupo existan y sean del color correcto
        int k = 0;
        while (movValido && k < tamanio) {
            int fActual = sForma.equals("H") ? fila : fila + k;
            int cActual = sForma.equals("H") ? col + k : col;

            if (fActual < 0 || fActual >= mat.length || cActual < 0 || cActual >= mat[0].length) {
                movValido = false;
            }
            if (movValido && !mat[fActual][cActual].equalsIgnoreCase(sColor)) {
                movValido = false;
            }
            k++;
        }

        // Verificar que el camino y destino de cada ficha esté libre
        k = 0;
        while (movValido && k < tamanio) {
            int fActual = sForma.equals("H") ? fila : fila + k;
            int cActual = sForma.equals("H") ? col + k : col;

            int paso = 1;
            while (movValido && paso <= pasos) {
                int nFila = fActual + dFila * paso;
                int nCol = cActual + dCol * paso;

                if (nFila < 0 || nFila >= mat.length || nCol < 0 || nCol >= mat[0].length) {
                    movValido = false;
                }
                if (movValido && !mat[nFila][nCol].equals(" ")) {
                    movValido = false;
                }
                paso++;
            }
            k++;
        }

        // Si es válido, mover todas las fichas
        if (movValido) {
            // Para no pisar datos, el orden de movimiento importa según el sentido
            // Si vamos al S o E, movemos de atrás hacia adelante
            // Si vamos al N u O, movemos de adelante hacia atrás
            int inicio = 0;
            int fin = tamanio - 1;
            int delta = 1;

            if (sentido.equals("S") || sentido.equals("E")) {
                inicio = tamanio - 1;
                fin = 0;
                delta = -1;
            }

            k = inicio;
            while (k != fin + delta * -1) {
                int fActual = sForma.equals("H") ? fila : fila + k;
                int cActual = sForma.equals("H") ? col + k : col;
                int nFila = fActual + dFila * pasos;
                int nCol = cActual + dCol * pasos;

                mat[nFila][nCol] = sColor;
                mat[fActual][cActual] = " ";
                k += delta;
            }
        }

        return movValido;
    }

    public static String caso4(String[][] mat) {
        // igual al prepararTablero que ya tienen en Tablero
        String[][] visual = Tablero.tableroVisualDeLogico(mat);
        String resultado = "";
        for (int i = 0; i < visual.length; i++) {
            for (int j = 0; j < visual[0].length; j++) {
                resultado += visual[i][j];
            }
            resultado += "\n";
        }
        return resultado;
    }

    public static boolean caso5(String[][] mat, char color) {
        String sColor = String.valueOf(color).toUpperCase();

        // Encontrar la primera ficha del color
        int filaInicio = -1;
        int colInicio = -1;
        int totalFichas = 0;

        int i = 0;
        while (i < mat.length) {
            int j = 0;
            while (j < mat[0].length) {
                if (mat[i][j].equalsIgnoreCase(sColor)) {
                    totalFichas++;
                    if (filaInicio == -1) {
                        filaInicio = i;
                        colInicio = j;
                    }
                }
                j++;
            }
            i++;
        }

        // Si no hay fichas del color, retorna false
        if (totalFichas == 0) {
            return false;
        }

        // BFS/flood fill desde la primera ficha para contar conectadas
        boolean[][] visitado = new boolean[mat.length][mat[0].length];
        int[] colaFilas = new int[mat.length * mat[0].length];
        int[] colaCols = new int[mat.length * mat[0].length];
        int frente = 0;
        int fondo = 0;

        colaFilas[fondo] = filaInicio;
        colaCols[fondo] = colInicio;
        fondo++;
        visitado[filaInicio][colInicio] = true;
        int conectadas = 0;

        int[] dFilas = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCols = {-1, 0, 1, -1, 1, -1, 0, 1};

        while (frente < fondo) {
            int fActual = colaFilas[frente];
            int cActual = colaCols[frente];
            frente++;
            conectadas++;

            int d = 0;
            while (d < 8) {
                int nFila = fActual + dFilas[d];
                int nCol = cActual + dCols[d];

                if (nFila >= 0 && nFila < mat.length && nCol >= 0 && nCol < mat[0].length) {
                    if (!visitado[nFila][nCol] && mat[nFila][nCol].equalsIgnoreCase(sColor)) {
                        visitado[nFila][nCol] = true;
                        colaFilas[fondo] = nFila;
                        colaCols[fondo] = nCol;
                        fondo++;
                    }
                }
                d++;
            }
        }

        return conectadas == totalFichas;
    }
}

