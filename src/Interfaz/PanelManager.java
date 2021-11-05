package Interfaz;

import Negocio.Paciente;
import Negocio.Turno;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    private JFrame jFrame;
    Image img = Toolkit.getDefaultToolkit().createImage("logoDiente.png");

    //Paneles
    private PanelLogin panelLogin;
    private PanelRegistrar panelRegistrar;
    private PanelTurnos panelTurnos;
    private PanelNuevoTurno panelNuevoTurno;
    private PanelAdmin panelAdmin;
    private PanelAdminTurnos panelAdminTurnos;
    private PanelAdminPacientes panelAdminPacientes;
    private PanelAdminOdontologos panelAdminOdontologos;
    private PanelNuevoOdontologo panelNuevoOdontologo;

    //Vars globales
    private Paciente userlogueado;

    public PanelManager() {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setIconImage(img);

        //panel default
        panelLogin = new PanelLogin(this);
        mostrarPanelLogin();
    }

    public void mostrarPanelLogin(){
        jFrame.setBounds(200,200,300,150);
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panelLogin);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelTurnos(){
        this.armarPanelesTurnos();
        jFrame.setBounds(200,200,1280,720);
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panelTurnos);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelNuevoTurno(){
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,400,180);
        jFrame.getContentPane().add(panelNuevoTurno);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelNuevoTurno(Turno turno){
        //Usamos el panel de nuevo turno para modificar turnos tambien
        panelNuevoTurno.cargarTurno(turno); //Setea el panel para modificar
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,400,220);
        jFrame.getContentPane().add(panelNuevoTurno);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelAdmin(){
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,400,150);
        jFrame.getContentPane().add(panelAdmin);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelAdminTurnos(){
        armarPanelesAdmin();
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,1280,720);
        jFrame.getContentPane().add(panelAdminTurnos);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelAdminPacientes(){
        armarPanelesAdmin();
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,1280,720);
        jFrame.getContentPane().add(panelAdminPacientes);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelAdminOdontologos(){
        armarPanelesAdmin();
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,1280,720);
        jFrame.getContentPane().add(panelAdminOdontologos);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelNuevoOdontologo(){
        armarPanelesAdmin();
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,400,180);
        jFrame.getContentPane().add(panelNuevoOdontologo);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(true);
        jFrame.setLocationRelativeTo(null);
    }

    public void mostrarPanelRegistrar() {
        this.armarPanelRegistrar();
        jFrame.getContentPane().removeAll();
        jFrame.setBounds(200,200,450,320);
        jFrame.getContentPane().add(panelRegistrar);
        jFrame.getContentPane().validate();
        jFrame.getContentPane().repaint();
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
    }

    public void armarPanelRegistrar() {
        panelRegistrar =  new PanelRegistrar(this);
    }

    //Arma todos los paneles excepto login (esto es para evitar errores en el uso del usuario logueado)
    public void armarPanelesTurnos() {
        panelTurnos = new PanelTurnos(this);
        panelNuevoTurno = new PanelNuevoTurno(this);
    }

    public void armarPanelesAdmin() {
        panelAdmin = new PanelAdmin(this);
        panelAdminTurnos = new PanelAdminTurnos(this);
        panelAdminPacientes = new PanelAdminPacientes(this);
        panelAdminOdontologos = new PanelAdminOdontologos(this);
        panelNuevoOdontologo = new PanelNuevoOdontologo(this);
    }


    public void setUserlogueado(Paciente userlogueado) {
        this.userlogueado = userlogueado;
    }

    public Paciente getUserlogueado() {
        return userlogueado;
    }
}
