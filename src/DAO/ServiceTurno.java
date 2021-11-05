package DAO;
import Negocio.Turno;

import java.util.*;

public class ServiceTurno {

    ITurnoDAO turnoDAO;

    public ServiceTurno() {
        this.turnoDAO = new TurnoDAO();
    }

    public void guardar(Turno turno) throws Exception {
        ArrayList turnos = listar();
        for (Object t : turnos) {
            if (turno.compareTo(t) == 1) {
                throw new Exception();
            }
        }
        turnoDAO.guardar(turno);
    }

    public ArrayList<Turno> listar() {
        return turnoDAO.listar();
    }

    public Turno recuperar(int codigo) {
        return turnoDAO.recuperar(codigo);
    }

    public void eliminar(int codigo){  turnoDAO.eliminar(codigo);}

}