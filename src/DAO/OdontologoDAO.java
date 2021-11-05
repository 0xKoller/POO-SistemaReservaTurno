package DAO;
import Negocio.Odontologo;
import Negocio.Paciente;

import java.util.*;


public class OdontologoDAO implements IOdontologoDAO {

    Archivo archivo;

    public OdontologoDAO() {
        this.archivo = new Archivo("odontologos.txt");
    }
    
    public void guardar(Odontologo odontologo) {
        ArrayList odontologos = archivo.listar();
        for(Object o: odontologos){
            if (o instanceof Odontologo){
                if (((Odontologo)o).getMatricula() == odontologo.getMatricula()){
                    odontologos.remove(o);
                    break;
                }
            }
        }
        odontologos.add(odontologo);
        archivo.guardar(odontologos);
    }
    
    public ArrayList<Odontologo> listar() {
        ArrayList<Odontologo> odontologos = new ArrayList();
        ArrayList lista = archivo.listar();
        for (Object obj:lista){
            if (obj instanceof Odontologo){
                odontologos.add((Odontologo)obj);
            }
        }
        return odontologos;
    }
    
    public Odontologo recuperar(int matricula) {
        ArrayList lista = archivo.listar();
        Object objeto = null;
        for(Object o: lista){
            if (o instanceof Odontologo){
                if (matricula == ((Odontologo)o).getMatricula()){
                    objeto = o;
                }
            }
        }
        return (Odontologo) objeto;
    }

}