package DAO;
import Negocio.Paciente;
import Negocio.Turno;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.sound.midi.Soundbank;
import java.util.*;

public class TurnoDAO implements ITurnoDAO {

    Archivo archivo;

    public TurnoDAO() {
        this.archivo = new Archivo("turnos.txt");
    }

    public void guardar(Turno turno){
        ArrayList turnos = archivo.listar();
        if(turno.getCodigo()==0) {
            turno.setCodigo(calcularID(turnos));
        }
        else{
            for (Object o:turnos){
                if (o instanceof Turno){
                    if (((Turno)o).getCodigo()==turno.getCodigo()){
                        turnos.remove(o);
                        break;
                    }
                }
            }
        }
        turnos.add(turno);
        archivo.guardar(turnos);
    }

    public ArrayList<Turno> listar() {
        ArrayList<Turno> turnos = new ArrayList();
        ArrayList lista = archivo.listar();
        for (Object obj:lista){
            if (obj instanceof Turno){
                turnos.add((Turno) obj);
            }
        }
        return turnos;
    }

    public Turno recuperar(int codigo) {
        ArrayList lista = archivo.listar();
        Object objeto = null;
        for(Object t: lista){
            if (t instanceof Turno){
                if (codigo == ((Turno)t).getCodigo()){
                    objeto = t;
                }
            }
        }
        return (Turno) objeto;
    }

    public void eliminar(int codigo){
        ArrayList lista = archivo.listar();
        Object objeto = null;
        for(Object p: lista){
            if (p instanceof Turno){
                if (codigo == ((Turno)p).getCodigo()){
                    objeto = (Turno) p;
                }
            }
        }

        if (objeto != null) {
            int indice = lista.indexOf(objeto);
            archivo.eliminar(indice);
        }
    }

    private int calcularID(ArrayList turnos){
        //Determina siguiente ID libre
        int maximo = 0;
        for (Object t : turnos) {
            if(((Turno)t).getCodigo() > maximo) {
                maximo = ((Turno)t).getCodigo();
            }
        }
        return maximo+1;
    }
}