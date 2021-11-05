package DAO;
import Negocio.Turno;

import java.util.*;

public interface ITurnoDAO{

    public void guardar(Turno turno);

    public ArrayList<Turno> listar();

    public Turno recuperar(int codigo);

    public void eliminar(int codigo);
}