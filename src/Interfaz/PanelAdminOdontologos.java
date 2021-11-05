package Interfaz;

import DAO.ServiceOdontologo;
import DAO.ServicePaciente;
import Negocio.Odontologo;
import Negocio.Paciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelAdminOdontologos extends JPanel {
    private PanelManager panelManager;

    //Declaracion titulo.
    private JLabel lblTitulo;
    private JPanel panelTitulo;

    //Declaracion tabla
    private JTable jTable;
    private JScrollPane scrollPane;
    private DefaultTableModel contenido;

    //Declaracion botones
    private JButton btnCrearOdontologo;
    private JButton btnBack;
    private JPanel panelBotonera;

    public PanelAdminOdontologos(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        //Crea titulo
        lblTitulo = new JLabel("Odontologos");
        panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);

        //Crea Tabla
        contenido = new DefaultTableModel();
        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.white);
        jTable = new JTable(contenido);
        scrollPane.setViewportView(jTable);

        //Agrega rotulos (columnas) a tabla
        contenido.addColumn("Matricula");
        contenido.addColumn("Nombre y Apellido");

        //Crea service para los turnos
        ServiceOdontologo serviceOdontologo = new ServiceOdontologo();
        ArrayList<Odontologo> lista = serviceOdontologo.listar();

        //Lista los turnos (rellena la tabla)
        for (Odontologo o: lista){
            //Usamos 2 datos: matricula, nombre y apellido
            Object [] fila = new Object[2];
            fila[0] = o.getMatricula();
            fila[1] = o.getApellido()+", "+o.getNombre();
            contenido.addRow(fila);
        }

        //Creación de Botones
        btnCrearOdontologo = new JButton("Crear Odontólogo");
        btnBack = new JButton("Regresar");
        panelBotonera = new JPanel();
        panelBotonera.add(btnCrearOdontologo);
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

        btnCrearOdontologo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelNuevoOdontologo();
            }
        });
    }
}
