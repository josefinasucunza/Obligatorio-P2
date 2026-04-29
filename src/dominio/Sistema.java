package dominio;

import interfaz.*;
import java.util.*;

public class Sistema {

    private Tablero tablero;
    private ArrayList<Tester> testers;

    public Sistema() {
        this.tablero = new Tablero();
        this.testers = new ArrayList<>();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Interfaz interfaz = new Interfaz(sistema);
        interfaz.inicio();
    }

    public String prepararTablero() {
        return tablero.prepararTablero();
    }

    public void cargarDefault() {
        tablero.cargarDefault();
    }

    public void cargarMatriz(String[] filas) {
        tablero.cargarMatriz(filas);
    }

    public boolean existeTester(String nombre) {

        boolean existe = false;

        Iterator<Tester> it = testers.iterator();

        while (it.hasNext()) {
            Tester t = it.next();

            if (t.getNombre().equalsIgnoreCase(nombre)) {
                existe = true;
            }
        }

        return existe;
    }

    public void agregarTester(String nombre, int edad, int experiencia) {

        Tester t = new Tester(nombre, edad, experiencia);
        testers.add(t);
    }

    public ArrayList<Tester> getTestersOrdenados() {

        ArrayList<Tester> copia = new ArrayList<>(testers);

        Collections.sort(copia, new Comparator<Tester>() {
            public int compare(Tester t1, Tester t2) {
                return t1.getNombre().compareToIgnoreCase(t2.getNombre());
            }
        });

        return copia;
    }
}