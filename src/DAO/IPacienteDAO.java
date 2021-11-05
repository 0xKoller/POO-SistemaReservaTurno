package DAO;
import Negocio.Paciente;

import java.util.*;

public interface IPacienteDAO {

    public void guardar(Paciente paciente);

    public void eliminar(int dni);

    public ArrayList<Paciente> listar();

    public Paciente recuperar(int dni);

}