package interfaz;

import dominio.*;
import java.util.*;

public class Interfaz {

    private Scanner teclado;
    private Sistema sistema;
    private Tablero tablero;

    public Interfaz() {
        teclado = new Scanner(System.in);
        sistema = new Sistema();
        tablero = new Tablero();
    }

    public void mostrarMenu() {
        System.out.println("Elegir una de las siguientes opciones");
        System.out.println("A) Registrar tester");
        System.out.println("B) Elegir matriz de juego");
        System.out.println("C) Registrar testeo");
        System.out.println("D) consulta testers");
        System.out.println("E) estadisticas");
        System.out.println("F) salir");
        System.out.println("ingrese opcion:");
    }

    public void inicio() {
        String opcion = "";
        
        while (!opcion.equalsIgnoreCase("F")) {

            mostrarMenu();
            opcion = teclado.nextLine();

            if (opcion.equalsIgnoreCase("A")) {
                opcionA();

            } else if (opcion.equalsIgnoreCase("B")) {
                opcionB();

            } else if (opcion.equalsIgnoreCase("C")) {
                opcionC();

            } else if (opcion.equalsIgnoreCase("D")) {
                opcionD();

            } else if (opcion.equalsIgnoreCase("E")) {
                opcionE();

            } else if (opcion.equalsIgnoreCase("F")) {
                System.out.println("Fin del programa.");

            } else {
                System.out.println("Opcion invalida.");
            }
        }
    }
    
    public void opcionB(){
         
    System.out.println("Matriz por default");
    System.out.println(tablero.prepararTablero());
        
    System.out.println("Desea cambiar la matriz? (S/N)");
    String respuesta = teclado.nextLine();
        
    if (respuesta.equalsIgnoreCase("S")) {

        System.out.println("Desea cargar matriz default o particular? (D/P)");
        String tipo = teclado.nextLine();

        if (tipo.equalsIgnoreCase("D")) {

            tablero.cargarDefault();

        } 
        else if (tipo.equalsIgnoreCase("P")) {

            String[] filas = new String[8];

            System.out.println("Ingrese las 8 filas de la matriz:");

            for (int i = 0; i < filas.length; i++) {

                boolean filaValida = false;

                while (!filaValida) {

                    System.out.print("Fila " + i + ": ");
                    filas[i] = teclado.nextLine().toUpperCase();

                    if (filas[i].length() == 10) {

                        filaValida = true;

                        for (int j = 0; j < 10; j++) {

                            char c = filas[i].charAt(j);

                            if (c != 'N' && c != 'B' && c != 'V') {
                                filaValida = false;
                            }
                        }
                    }

                    if (!filaValida) {
                        System.out.println("Solo se pueden ingresar 10 caracteres (N, B o V)");
                    }
                }
            }

            tablero.cargarMatriz(filas);

        } 
        else {
            System.out.println("Opcion invalida. No se modifico la matriz.");
        }

        System.out.println("Matriz actualizada:");
        System.out.println(tablero.prepararTablero());

    } 
    else if (respuesta.equalsIgnoreCase("N")) {
        System.out.println("No se modifico la matriz.");
    } 
    else {
        System.out.println("Respuesta invalida.");
    }

    }

    public void opcionA() {
        // registrar tester
    }
    
    public void opcionC() {
        // registrar testeo
    }

    public void opcionD() {
        // consulta testers
    }

    public void opcionE() {
        // estadisticas
    }
}