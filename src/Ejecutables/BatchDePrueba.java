package Ejecutables;

import DAO.ServiceOdontologo;
import DAO.ServicePaciente;
import DAO.ServiceTurno;
import Negocio.Odontologo;
import Negocio.Paciente;
import Negocio.Turno;

import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Scanner;

public class BatchDePrueba {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ejecutar batch de prueba (Y/N): ");
        if (scanner.nextLine().toUpperCase().equals("Y")) {
            System.out.println("Ejecutando batch...");
            //Creación de Service
            ServiceTurno serviceTurno = new ServiceTurno();
            ServicePaciente servicePaciente = new ServicePaciente();
            ServiceOdontologo serviceOdontologo = new ServiceOdontologo();

            //Creación de Pacientes
            Paciente paciente = new Paciente("Zoe", "Landeyro", 43914961, "zoe", "zoe", "Pampa");
            Paciente paciente1 = new Paciente("Tino", "Mont", 43987654, "tinin", "banfieldpasion", "Banfield");
            Paciente paciente2 = new Paciente("Fran", "Latour", 43123456, "flatour", "aguantevelez", "Saint thomas");
            servicePaciente.guardar(paciente);
            servicePaciente.guardar(paciente1);
            servicePaciente.guardar(paciente2);

            //Creación de Odontologos
            Odontologo odontologo = new Odontologo("Mariano", "Closs", 456);
            Odontologo odontologo1 = new Odontologo("Juampi", "Dieguez", 123);
            Odontologo odontologo2 = new Odontologo("Koller", "Man", 789);
            serviceOdontologo.guardar(odontologo);
            serviceOdontologo.guardar(odontologo1);
            serviceOdontologo.guardar(odontologo2);

            //Creación de Turnos
            Turno turno = new Turno(odontologo, paciente, "20:00", "30/2/2021");
            Turno turno1 = new Turno(odontologo, paciente1, "21:00", "10/7/2022");
            Turno turno2 = new Turno(odontologo1, paciente2, "15:00", "24/11/2019");
            Turno turno3 = new Turno(odontologo1, paciente, "16:00", "7/11/2019");
            Turno turno4 = new Turno(odontologo2, paciente1, "19:50", "13/11/2019");
            Turno turno5 = new Turno(odontologo2, paciente2, "18:25", "28/11/2019");
            try {
                serviceTurno.guardar(turno);
                serviceTurno.guardar(turno1);
                serviceTurno.guardar(turno2);
                serviceTurno.guardar(turno3);
                serviceTurno.guardar(turno4);
                serviceTurno.guardar(turno5);
            }
            catch (Exception e) {
                System.out.println("Algún turno repetido");
            }
        }


        }
    }

