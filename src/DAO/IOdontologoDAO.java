package DAO;
import Negocio.Odontologo;

import java.util.*;


public interface IOdontologoDAO {

    public void guardar(Odontologo odontologo);

    public ArrayList<Odontologo> listar();

    public Odontologo recuperar(int matricula);

}