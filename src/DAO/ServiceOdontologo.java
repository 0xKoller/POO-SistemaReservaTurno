package DAO;
import Negocio.Odontologo;

import java.util.*;


public class ServiceOdontologo {

    IOdontologoDAO odontologoDAO;
   
    public ServiceOdontologo() {
        this.odontologoDAO = new OdontologoDAO();
    }
    
    public void guardar(Odontologo odontologo) {
        odontologoDAO.guardar(odontologo);
    }

    public ArrayList<Odontologo> listar() {
        return odontologoDAO.listar();
    }

    public Odontologo recuperar(int matricula) {
        return odontologoDAO.recuperar(matricula);
    }

}