package Interfaz;

import DAO.ServicePaciente;
import Negocio.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRegistrar extends JPanel {

    private PanelManager panelManager;

    // Título
    private JPanel panelTitulo;
    private JLabel lblTitulo;

    // Componentes
    private JPanel panelComponentes;
    private JLabel lblNombreUsuario;
    private JTextField txtNombreUsuario;
    private JLabel lblContrasenia1;
    private JTextField txtContrasenia1;
    private JLabel lblContrasenia2;
    private JTextField txtContrasenia2;
    private JLabel lblDni;
    private JTextField txtDni;
    private JLabel lblNombrePaciente;
    private JTextField txtNombrePaciente;
    private JLabel lblApellido;
    private JTextField txtApellido;
    private JLabel lblDomicilio;
    private JTextField txtDomicilio;

    // Botonera
    private JPanel panelBotones;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public PanelRegistrar(PanelManager panelManager) {

        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        // Título

        panelTitulo = new JPanel();
        lblTitulo = new JLabel("Pantalla Registro");
        panelTitulo.add(lblTitulo);

        // Otros componentes

        panelComponentes = new JPanel();
        panelComponentes.setLayout(new GridLayout(7, 2));
        panelComponentes.setBackground(Color.white);
        lblNombreUsuario = new JLabel("Usuario:      ");
        lblNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        txtNombreUsuario = new JTextField(10);
        lblContrasenia1 = new JLabel("Contraseña:      ");
        lblContrasenia1.setHorizontalAlignment(SwingConstants.RIGHT);
        txtContrasenia1 = new JPasswordField(10);
        lblContrasenia2 = new JLabel("Repita la contraseña:      ");
        lblContrasenia2.setHorizontalAlignment(SwingConstants.RIGHT);
        txtContrasenia2 = new JPasswordField(10);
        lblDni = new JLabel("DNI:      ");
        lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDni = new JTextField(10);
        lblNombrePaciente = new JLabel("Nombre/s:      ");
        lblNombrePaciente.setHorizontalAlignment(SwingConstants.RIGHT);
        txtNombrePaciente = new JTextField(10);
        lblApellido = new JLabel("Apellidos/s:      ");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        txtApellido = new JTextField(10);
        lblDomicilio = new JLabel("Domicilio (calle y altura):      ");
        lblDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDomicilio = new JTextField(10);
        panelComponentes.add(lblNombreUsuario);
        panelComponentes.add(txtNombreUsuario);
        panelComponentes.add(lblContrasenia1);
        panelComponentes.add(txtContrasenia1);
        panelComponentes.add(lblContrasenia2);
        panelComponentes.add(txtContrasenia2);
        panelComponentes.add(lblDni);
        panelComponentes.add(txtDni);
        panelComponentes.add(lblNombrePaciente);
        panelComponentes.add(txtNombrePaciente);
        panelComponentes.add(lblApellido);
        panelComponentes.add(txtApellido);
        panelComponentes.add(lblDomicilio);
        panelComponentes.add(txtDomicilio);

        // Botones

        panelBotones = new JPanel();
        panelBotones.setBackground(Color.orange);
        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al propio panel
        add(panelTitulo, BorderLayout.NORTH);
        add(panelComponentes, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones botones

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelLogin();
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtNombreUsuario.getText();
                String contrasenia1 = txtContrasenia1.getText();
                String contrasenia2 = txtContrasenia2.getText();
                String dni = txtDni.getText();
                String nombre = txtNombrePaciente.getText();
                String apellido = txtApellido.getText();
                String domicilio = txtDomicilio.getText();
                int numeroDni = Integer.parseInt(dni);

                if (usuario.equals("") || contrasenia1.equals("") || contrasenia2.equals("") || dni.equals("") ||
                        nombre.equals("") || apellido.equals("") || domicilio.equals("")) {
                    JOptionPane.showMessageDialog(panelComponentes,
                            "¡Algún campo está vacío!",
                            "Omisión de datos", JOptionPane.ERROR_MESSAGE);
                }
                else if (!contrasenia1.equals(contrasenia2)) {
                    JOptionPane.showMessageDialog(panelComponentes,
                            "¡Las contraseñas no coinciden!",
                            "Contraseñas no coinciden", JOptionPane.ERROR_MESSAGE);
                }
                else if (numeroDni >= 10000000 && numeroDni <= 99999999) {
                    JOptionPane.showMessageDialog(panelComponentes,
                            "¡El DNI no es válido! Debe tener 8 dígitos.",
                            "DNI inválido", JOptionPane.ERROR_MESSAGE);
                }
                else if (1 == 1) {
                    // DNI existente

                }
                else if (1 == 1) {
                    // Usuario existente

                }
                else {
                    Paciente paciente = new Paciente(nombre, apellido, numeroDni, usuario, contrasenia1, domicilio);
                    ServicePaciente servicePaciente = new ServicePaciente();
                    servicePaciente.guardar(paciente);

                    JOptionPane.showMessageDialog(panelComponentes,
                            "¡Usuario creado con éxito!",
                            "Usuario creado", JOptionPane.INFORMATION_MESSAGE);

                    panelManager.mostrarPanelLogin();
                }

            }
        });

    }

}
