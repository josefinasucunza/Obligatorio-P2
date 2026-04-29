package dominio;

import java.util.*;

public class Tester {

    private String nombre;
    private int edad;
    private int experiencia;
    private ArrayList<Testeo> testeos;

    public Tester(String nombre, int edad, int experiencia) {
        this.nombre = nombre;
        this.edad = edad;
        this.experiencia = experiencia;
        this.testeos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public ArrayList<Testeo> getTesteos() {
        return testeos;
    }

    public void agregarTesteo(Testeo t) {
        testeos.add(t);
    }

    @Override
    public String toString() {
        return nombre + " - edad: " + edad + " - experiencia: " + experiencia;
    }
}