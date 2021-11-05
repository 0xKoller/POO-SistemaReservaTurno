package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdmin extends JPanel {

    private PanelManager panelManager;

    //Titulo
    private JLabel lblTitulo;
    private JPanel panelTitulo;

    //Botonera
    private JButton btnTurnos;
    private JButton btnPacientes;
    private JButton btnOdontologos;
    private JPanel panelBotonera;

    //Panel logout
    private JButton btnLogOut;
    private JPanel panelLogout;

    public PanelAdmin (PanelManager panelManager){
        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        //Crea titulo
        lblTitulo = new JLabel("Menu Administrador");
        panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);

        //Crea botonera
        btnTurnos = new JButton("Turnos");
        btnPacientes = new JButton("Pacientes");
        btnOdontologos = new JButton("Odontólogos");

        panelBotonera = new JPanel();
        panelBotonera.setBackground(Color.white);
        panelBotonera.add(btnTurnos);
        panelBotonera.add(btnPacientes);
        panelBotonera.add(btnOdontologos);

        //Crea logout
        btnLogOut = new JButton("Cerrar sesión");
        panelLogout = new JPanel();
        panelLogout.add(btnLogOut);
        panelLogout.setBackground(Color.orange);

        //Agrega paneles al padre
        add(panelTitulo, BorderLayout.NORTH);
        add(panelBotonera, BorderLayout.CENTER);
        add(panelLogout,BorderLayout.SOUTH);

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelLogin();
            }
        });

        btnTurnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdminTurnos();
            }
        });

        btnPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdminPacientes();
            }
        });

        btnOdontologos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdminOdontologos();
            }
        });

    }


}
