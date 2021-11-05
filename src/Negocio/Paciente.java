package Negocio;

import java.util.Date;
import java.util.List;

public class Paciente extends Persona {

    private String domicilio;
    private int dni;
    private String fechaAlta;
    private String usuario;
    private String contraseña;

    public Paciente(String nombre, String apellido, int dni, String usuario, String contraseña, String domicilio) {
        super(nombre, apellido);
        this.dni = dni;
        fechaAlta = String.valueOf(java.time.LocalDate.now()).replace("-","/");
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.domicilio = domicilio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public int getDni() {
        return dni;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

}
