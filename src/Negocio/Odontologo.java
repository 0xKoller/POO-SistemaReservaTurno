package Negocio;

import java.util.List;

public class Odontologo extends Persona{

    private int matricula;

    public Odontologo(String nombre, String apellido, int matricula) {
        super(nombre, apellido);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

}