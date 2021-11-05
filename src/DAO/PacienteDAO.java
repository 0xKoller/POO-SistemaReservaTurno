package DAO;
import Negocio.Paciente;

import java.util.*;

public class PacienteDAO implements IPacienteDAO {

    Archivo archivo;

    public PacienteDAO(){
        this.archivo = new Archivo("pacientes.txt");
    }

    public void guardar(Paciente paciente) {
        ArrayList pacientes = archivo.listar();
        for(Object p: pacientes){
            if (p instanceof Paciente){
                if (((Paciente)p).getDni() == paciente.getDni()){
                    pacientes.remove(p);
                    break;
                }
            }
        }
        pacientes.add(paciente);
        archivo.guardar(pacientes);
    }

    public void eliminar(int dni) {
        ArrayList lista = archivo.listar();
        Object objeto = null;
        for(Object p: lista){
            if (p instanceof Paciente){
                if (dni == ((Paciente)p).getDni()){
                    objeto = (Paciente) p;
                }
            }
        }

        if (objeto != null) {
            int indice = lista.indexOf(objeto);
            archivo.eliminar(indice);
        }

    }

    public ArrayList<Paciente> listar() {
        ArrayList<Paciente> pacientes = new ArrayList();
        ArrayList lista = archivo.listar();
        for (Object obj:lista){
            if (obj instanceof Paciente){
                pacientes.add((Paciente)obj);
            }
        }
        return pacientes;
    }

    public Paciente recuperar(int dni) {
        ArrayList lista = archivo.listar();
        Object objeto = null;
        for(Object p: lista){
            if (p instanceof Paciente){
                if (dni == ((Paciente)p).getDni()){
                    objeto = p;
                }
            }
        }
        return (Paciente) objeto;
    }

}