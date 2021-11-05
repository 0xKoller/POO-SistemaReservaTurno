package Negocio;
import java.io.Serializable;

import java.util.Date;

public class Turno implements Serializable/*, Comparable */{

    private String horario;
    private String fecha;
    private int codigo;
    private Odontologo odontologo;
    private Paciente paciente;

    public Turno(Odontologo odontologo, Paciente paciente, String horario, String fecha) {;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.horario = horario;
        this.fecha = fecha;
        this.codigo = 0;
    }

    public String getHorario() {
        return horario;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCodigo(int cod){
        this.codigo = cod;
    }

    public int compareTo(Object o) {
        int resultado = 0;
        if (o instanceof Turno){
            Turno a = (Turno)o;
            if (a.getOdontologo().getMatricula() == this.getOdontologo().getMatricula())
                 if (a.getHorario().equals(this.getHorario()))
                    if (a.getFecha().equals(this.getFecha()))
                        resultado = 1;
        }
        return resultado;
    }
}
