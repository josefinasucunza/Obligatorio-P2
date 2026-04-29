package interfaz;

import dominio.*;
import java.util.*;

  

    public class Interfaz {

        private Scanner teclado;
        private Sistema sistema;

        public Interfaz(Sistema sistema) {
            this.teclado = new Scanner(System.in);
            this.sistema = sistema;
        }

        public void mostrarMenu() {
            System.out.println("Elegir una de las siguientes opciones");
            System.out.println("A) Registrar tester");
            System.out.println("B) Elegir matriz de juego");
            System.out.println("C) Registrar testeo");
            System.out.println("D) Consulta testers");
            System.out.println("E) Estadisticas");
            System.out.println("F) Salir");
            System.out.println("Ingrese opcion:");
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

        public void opcionA() {

            System.out.println("Ingrese nombre del tester:");
            String nombre = teclado.nextLine();

            while (sistema.existeTester(nombre)) {
                System.out.println("Ese nombre ya existe. Ingrese otro nombre:");
                nombre = teclado.nextLine();
            }

            System.out.println("Ingrese edad:");
            int edad = Integer.parseInt(teclado.nextLine());

            System.out.println("Ingrese años de experiencia:");
            int experiencia = Integer.parseInt(teclado.nextLine());

            sistema.agregarTester(nombre, edad, experiencia);

            System.out.println("Tester registrado correctamente.");

        }

        public void opcionB() {

            System.out.println("Matriz actual:");
            System.out.println(sistema.prepararTablero());

            System.out.println("Desea cambiar la matriz? (S/N)");
            String respuesta = teclado.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {

                System.out.println("Desea cargar matriz default o particular? (D/P)");
                String tipo = teclado.nextLine();

                if (tipo.equalsIgnoreCase("D")) {

                    sistema.cargarDefault();

                } else if (tipo.equalsIgnoreCase("P")) {

                    String[] filas = new String[8];

                    System.out.println("Ingrese las 8 filas de la matriz:");

                    for (int i = 0; i < filas.length; i++) {

                        boolean filaValida = false;

                        while (!filaValida) {

                            System.out.print("Fila " + (i + 1) + ": ");
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

                    sistema.cargarMatriz(filas);

                } else {
                    System.out.println("Opcion invalida. No se modifico la matriz.");
                }

                System.out.println("Matriz actualizada:");
                System.out.println(sistema.prepararTablero());

            } else if (respuesta.equalsIgnoreCase("N")) {
                System.out.println("No se modifico la matriz.");

            } else {
                System.out.println("Respuesta invalida.");
            }
        }

        public void opcionC() {

            ArrayList<Tester> lista = sistema.getTestersOrdenados();

            if (lista.isEmpty()) {
                System.out.println("No hay testers registrados.");
                return;
            }

            System.out.println("Seleccione un tester:");

            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ") " + lista.get(i).getNombre());
            }

            int posTester = Integer.parseInt(teclado.nextLine()) - 1;

            if (posTester < 0 || posTester >= lista.size()) {
                System.out.println("Tester invalido.");
                return;
            }

            Tester testerElegido = lista.get(posTester);

            System.out.println("Ingrese numero del testeo:");
            int numero = Integer.parseInt(teclado.nextLine());

            System.out.println("Ingrese caso (1, 2, 3, 4 o 5):");
            int caso = Integer.parseInt(teclado.nextLine());

            String matrizOriginal = sistema.prepararTablero();

            System.out.println("Ingrese parametros:");
            String parametros = teclado.nextLine();

            System.out.println("Ingrese comentario:");
            String comentario = teclado.nextLine();

            System.out.println("Ingrese resultado:");
            String resultado = teclado.nextLine();

            String matrizResultante = matrizOriginal;

            System.out.println("La matriz fue modificada? (S/N)");
            String modificada = teclado.nextLine();

            if (modificada.equalsIgnoreCase("S")) {

                String[] filas = new String[8];

                System.out.println("Ingrese la matriz resultante:");

                for (int i = 0; i < filas.length; i++) {
                    System.out.print("Fila " + (i + 1) + ": ");
                    filas[i] = teclado.nextLine().toUpperCase();
                }

                sistema.cargarMatriz(filas);
                matrizResultante = sistema.prepararTablero();
            }

            Testeo nuevo = new Testeo(numero, caso, matrizOriginal, matrizResultante,
                    parametros, comentario, resultado);

            testerElegido.agregarTesteo(nuevo);

            System.out.println("Testeo registrado correctamente.");
        }
    

    public void opcionD() {

        ArrayList<Tester> lista = sistema.getTestersOrdenados();

        if (lista.isEmpty()) {
            System.out.println("No hay testers registrados.");
            return;
        }

        System.out.println("Lista de testers:");

        Iterator<Tester> iterTester = lista.iterator();
        int i = 1;

        while (iterTester.hasNext()) {
            Tester testerActual = iterTester.next();
            System.out.println(i + ") " + testerActual.getNombre());
            i++;
        }

        System.out.println("Seleccione un tester:");
        int posTester = Integer.parseInt(teclado.nextLine()) - 1;

        if (posTester < 0 || posTester >= lista.size()) {
            System.out.println("Tester invalido.");
            return;
        }

        Tester testerElegido = lista.get(posTester);

        ArrayList<Testeo> testeos = testerElegido.getTesteos();

        if (testeos.isEmpty()) {
            System.out.println("El tester no tiene testeos registrados.");
            return;
        }

        // ordenar testeos por numero
        for (int a = 0; a < testeos.size() - 1; a++) {
            for (int b = a + 1; b < testeos.size(); b++) {

                if (testeos.get(a).getNumero() > testeos.get(b).getNumero()) {

                    Testeo auxiliar = testeos.get(a);
                    testeos.set(a, testeos.get(b));
                    testeos.set(b, auxiliar);
                }
            }
        }

        System.out.println("Lista de testeos:");

        Iterator<Testeo> iterTesteo = testeos.iterator();
        int j = 1;

        while (iterTesteo.hasNext()) {
            Testeo testeoActual = iterTesteo.next();
            System.out.println(j + ") Testeo numero: " + testeoActual.getNumero());
            j++;
        }

        System.out.println("Seleccione un testeo:");
        int posTesteo = Integer.parseInt(teclado.nextLine()) - 1;

        if (posTesteo < 0 || posTesteo >= testeos.size()) {
            System.out.println("Testeo invalido.");
            return;
        }

        Testeo testeoSeleccionado = testeos.get(posTesteo);

        System.out.println(testeoSeleccionado.mostrarDatos());
    }

    public void opcionE() {
        // estadisticas
    }
}