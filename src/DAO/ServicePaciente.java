package DAO;
import DAO.IPacienteDAO;
import DAO.PacienteDAO;
import Negocio.Paciente;

import java.util.*;


public class ServicePaciente {

    IPacienteDAO pacienteDAO;

    public ServicePaciente() {
        this.pacienteDAO = new PacienteDAO();
    }

    public void guardar(Paciente paciente) {
        pacienteDAO.guardar(paciente);
    }

    public void eliminar(int dni) {
        pacienteDAO.eliminar(dni);
    }

    public ArrayList<Paciente> listar() {
        return pacienteDAO.listar();
    }

    public Paciente recuperar(int dni) {
        return pacienteDAO.recuperar(dni);
    }

}