package Interfaz;

import DAO.ServicePaciente;
import DAO.ServiceTurno;
import Negocio.Paciente;
import Negocio.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelAdminPacientes extends JPanel {

    private PanelManager panelManager;

    //Declaracion titulo.
    private JLabel lblTitulo;
    private JPanel panelTitulo;

    //Declaracion tabla
    private JTable jTable;
    private JScrollPane scrollPane;
    private DefaultTableModel contenido;

    //Declaracion botones
    private JButton btnEliminarPaciente;
    private JButton btnBack;
    private JPanel panelBotonera;

    public PanelAdminPacientes(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        //Crea titulo
        lblTitulo = new JLabel("Pacientes");
        panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);

        //Crea Tabla
        contenido = new DefaultTableModel();
        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.white);
        jTable = new JTable(contenido);
        scrollPane.setViewportView(jTable);

        //Agrega rotulos (columnas) a tabla
        contenido.addColumn("DNI");
        contenido.addColumn("Nombre y Apellido");
        contenido.addColumn("Domicilio");
        contenido.addColumn("Usuario");
        contenido.addColumn("Fecha Alta");

        //Crea service para los pacientes
        ServicePaciente servicePaciente = new ServicePaciente();
        ArrayList<Paciente> lista = servicePaciente.listar();

        //Lista los pacientes (rellena la tabla)
        for (Paciente p: lista){
            //Usamos 5 datos: dni, nombre y apellido, domicilio, usuario, fechaalta
            Object [] fila = new Object[5];
            fila[0] = p.getDni();
            fila[1] = p.getApellido()+", "+p.getNombre();
            fila[2] = p.getDomicilio();
            fila[3] = p.getUsuario();
            fila[4] = p.getFechaAlta();
            contenido.addRow(fila);
        }

        //Creación de Botones
        btnEliminarPaciente = new JButton("Eliminar Paciente");
        btnBack = new JButton("Regresar");
        panelBotonera = new JPanel();
        panelBotonera.add(btnEliminarPaciente);
        panelBotonera.add(btnBack);
        panelBotonera.setBackground(Color.orange);

        //Agrega paneles al padre
        add(panelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotonera,BorderLayout.SOUTH);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });

        btnEliminarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int dni = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
                    servicePaciente.eliminar(dni);
                    JOptionPane.showMessageDialog(scrollPane, "Paciente eliminado con exito","Eliminacion de paciente",JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception a){
                    JOptionPane.showMessageDialog(scrollPane, "Debe seleccionar un paciente para poder eliminarlo", "No seleccionó un paciente", JOptionPane.ERROR_MESSAGE);
                }
                panelManager.mostrarPanelAdminPacientes();
            }
        });
    }
}

