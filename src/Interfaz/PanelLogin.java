package Interfaz;

import DAO.ServicePaciente;
import Negocio.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PanelLogin extends JPanel {

    private PanelManager panelManager;

    //Titulo
    private JLabel lblTitulo;
    private JPanel panelTitulo;

    //componentes
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JPanel panelComponentes;

    //botonera
    private JButton btnLogin;
    private JButton btnRegistrar;
    private JPanel panelBotonera;

    public PanelLogin(PanelManager panelManager){
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        //Crea titulo
        lblTitulo = new JLabel("Reserva de turnos");
        panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);

        //Crea formulario de login
        lblUsername = new JLabel("Username:      ");
        lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword = new JLabel("Password:      ");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        txtPassword = new JPasswordField(10);
        txtUsername = new JTextField(10);
        panelComponentes = new JPanel(new GridLayout(2,2));
        panelComponentes.add(lblUsername);
        panelComponentes.add(txtUsername);
        panelComponentes.add(lblPassword);
        panelComponentes.add(txtPassword);
        panelComponentes.setBackground(Color.WHITE);

        //Crea botones
        btnLogin = new JButton("Log In");
        btnRegistrar = new JButton("Registrarse");
        panelBotonera = new JPanel();
        panelBotonera.add(btnLogin);
        panelBotonera.add(btnRegistrar);
        panelBotonera.setBackground(Color.orange);

        //Añade al panel padre
        add(panelTitulo, BorderLayout.NORTH);
        add(panelComponentes, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);


        //Aprieta el boton de log in con el enter
        txtPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent f) {

            }

            @Override
            public void keyPressed(KeyEvent f) {
                if(f.getKeyCode()==KeyEvent.VK_ENTER){
                    //Llama a validar login (true-false) con los datos ingresados en los TextField
                    iniciarSesion();
                }
            }

            @Override
            public void keyReleased(KeyEvent f) {

            }
        });
        //Aprieta el boton de log in con el enter desde el username
        txtUsername.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent f) {

            }

            @Override
            public void keyPressed(KeyEvent f) {
                if(f.getKeyCode()==KeyEvent.VK_ENTER){
                    //Llama a validar login (true-false) con los datos ingresados en los TextField
                    iniciarSesion();
                }
            }

            @Override
            public void keyReleased(KeyEvent f) {

            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Llama a validar login (true-false) con los datos ingresados en los TextField
                iniciarSesion();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelRegistrar();
            }
        });

    }

    public void iniciarSesion(){
        if (!(txtUsername.getText().equals("admin")) && validarLogin(txtUsername.getText(),txtPassword.getText())){
            //(Si se logeo correctamente) Obtenemos el objeto Paciente del user y lo seteamos como variable del manager
            ServicePaciente servicePaciente = new ServicePaciente();
            ArrayList<Paciente> lista = servicePaciente.listar();
            for (Paciente p : lista){
                if (p.getUsuario().equals(txtUsername.getText())){
                    panelManager.setUserlogueado(p);
                    break;
                }
            }
            //Como se inicio sesion, armamos el resto de paneles
            panelManager.armarPanelesTurnos();
            //Pasamos al panel de turnos que es el default despues de ingresar
            panelManager.mostrarPanelTurnos();
        }
        else if (txtUsername.getText().equals("admin")){
            panelManager.armarPanelesAdmin();
            panelManager.mostrarPanelAdmin();
        }
        else{
            //(si no se loguea correctamente) Muestra mensaje de error
            JOptionPane.showMessageDialog(panelComponentes, "Credenciales invalidas","Error de inico de sesion",JOptionPane.ERROR_MESSAGE);

        }
    }

    //Funcion para validar el login
    private boolean validarLogin(String usernameIngresado, String passwordIngresada){
        ServicePaciente servicePaciente = new ServicePaciente();
        ArrayList<Paciente> lista = servicePaciente.listar();
        String pass = null;
        for (Paciente p : lista){
            if (p.getUsuario().equals(usernameIngresado)){
                pass = p.getContraseña();
                break;
            }
        }
        if (pass != null){
            if (pass.equals(passwordIngresada)) {
                return true;
            }
        }
        return false;
    }
}
