package Interfaz;

import DAO.ServiceTurno;
import Negocio.Turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelTurnos extends JPanel {
    private PanelManager panelManager;

    //Declaracion titulo.
    private JLabel lblTitulo;
    private JPanel panelTitulo;

    //Declaracion tabla
    private JTable jTable;
    private JScrollPane scrollPane;
    private DefaultTableModel contenido;

    //Declaracion botones
    private JButton btnNuevoTurno;
    private JButton btnModificarTurno;
    private JButton btnCancelarTurno;
    private JButton btnLogOut;
    private JPanel panelBotonera;

    public PanelTurnos(PanelManager panelManager){
        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        //Crea titulo
        lblTitulo = new JLabel("Turnos Asignados");
        panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);

        //Crea Tabla
        contenido = new DefaultTableModel();
        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.white);
        jTable = new JTable(contenido);
        scrollPane.setViewportView(jTable);

        //Agrega rotulos (columnas) a tabla
        contenido.addColumn("Codigo");
        contenido.addColumn("Fecha");
        contenido.addColumn("Horario");
        contenido.addColumn("Odontólogo");
        contenido.addColumn("Paciente");

        //Crea service para los turnos
        ServiceTurno serviceTurno = new ServiceTurno();
        ArrayList<Turno> lista = serviceTurno.listar();

        //Lista los turnos (rellena la tabla)
        for (Turno t: lista){
            //Usamos 5 datos: codigo, fecha, hora, odontologo, paciente
            Object [] fila = new Object[5];
            //Solo listamos si son los turnos del paciente logueado
            if (t.getPaciente().getDni() == panelManager.getUserlogueado().getDni()) {
                fila[0] = t.getCodigo();
                fila[1] = t.getFecha();
                fila[2] = t.getHorario();
                fila[3] = t.getOdontologo().getApellido() + ", " + t.getOdontologo().getNombre();
                fila[4] = t.getPaciente().getApellido() + ", " + t.getPaciente().getNombre();
                contenido.addRow(fila);
            }
        }

        //Creación de Botones
        btnNuevoTurno = new JButton("Nuevo turno");
        btnModificarTurno = new JButton("Modificar turno");
        btnCancelarTurno = new JButton("Cancelar turno");
        btnLogOut = new JButton("Cerrar sesión");
        panelBotonera = new JPanel();
        panelBotonera.add(btnNuevoTurno);
        panelBotonera.add(btnModificarTurno);
        panelBotonera.add(btnCancelarTurno);
        panelBotonera.add(btnLogOut);
        panelBotonera.setBackground(Color.orange);


        //Agrega paneles al padre
        add(panelTitulo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotonera,BorderLayout.SOUTH);

        //log out
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelLogin();
            }
        });

        //Cancelar turno
        btnCancelarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceTurno serviceTurno = new ServiceTurno();
                try {
                    int id = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
                    serviceTurno.eliminar(id);
                    JOptionPane.showMessageDialog(scrollPane, "Turno cancelado con exito","Cancelacion de turno",JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(scrollPane, "Debe seleccionar un turno para poder cancelarlo", "No seleccionó un turno", JOptionPane.ERROR_MESSAGE);
                }
                panelManager.mostrarPanelTurnos();
            }
        });

        //Modificar turno
        btnModificarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceTurno serviceTurno1 = new ServiceTurno();
                try {
                    int id = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
                    Turno turno = serviceTurno1.recuperar(id);
                    panelManager.mostrarPanelNuevoTurno(turno);
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(scrollPane, "Debe seleccionar un turno para poder modificarlo", "No seleccionó un turno", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Nuevo turno
        btnNuevoTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelNuevoTurno();
            }
        });
    }
}
