package dominio;

public class Testeo {
    private int numero;
    private int caso;
    private String matrizOriginal;
    private String matrizResultante;
    private String parametros;
    private String comentario;
    private String resultado;

    public Testeo(int numero, int caso, String matrizOriginal, String matrizResultante,
                  String parametros, String comentario, String resultado) {
        this.numero = numero;
        this.caso = caso;
        this.matrizOriginal = matrizOriginal;
        this.matrizResultante = matrizResultante;
        this.parametros = parametros;
        this.comentario = comentario;
        this.resultado = resultado;
    }

    public int getNumero() {
        return numero;
    }

    public String mostrarDatos() {
        return "Numero: " + numero +
                "\nCaso: " + caso +
                "\nMatriz original:\n" + matrizOriginal +
                "\nMatriz resultante:\n" + matrizResultante +
                "\nParametros: " + parametros +
                "\nComentario: " + comentario +
                "\nResultado: " + resultado;
    }

    @Override
    public String toString() {
        return "Testeo " + numero + " - Caso " + caso;
    }
}