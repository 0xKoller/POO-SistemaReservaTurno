package Interfaz;

import DAO.ServiceOdontologo;
import Negocio.Odontologo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelNuevoOdontologo extends JPanel {

    private PanelManager panelManager;

    //Paneles
    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotonera;

    //Titulo
    private JLabel lblTitulo;

    //Componentes labels
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblMatricula;

    //Componentes txtfields
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtMatricula;

    //Botones
    private JButton btnGuardar;
    private JButton btnCancelar;

    public PanelNuevoOdontologo(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        //TITULO
        panelTitulo = new JPanel();
        lblTitulo = new JLabel("Nuevo Odontólogo");
        panelTitulo.add(lblTitulo);

        //Formulario
        panelFormulario = new JPanel(new GridLayout(3, 2));
        panelFormulario.setBackground(Color.white);

        lblMatricula = new JLabel("Matricula:      ");
        lblMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre = new JLabel("Nombre:      ");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblApellido = new JLabel("Apellido:      ");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);

        txtMatricula = new JTextField(10);
        txtNombre = new JTextField(10);
        txtApellido = new JTextField(10);

        //Panel default es para crear nuevo
        panelFormulario.add(lblMatricula);
        panelFormulario.add(txtMatricula);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblApellido);
        panelFormulario.add(txtApellido);

        // BOTONERA
        panelBotonera = new JPanel();
        panelBotonera.setBackground(Color.orange);
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotonera.add(btnGuardar);
        panelBotonera.add(btnCancelar);

        //AGREGA PANELES AL PADRE
        add(panelTitulo, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdminOdontologos();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceOdontologo serviceOdontologo = new ServiceOdontologo();
                Odontologo odontologo = new Odontologo(txtNombre.getText(),txtApellido.getText(),Integer.parseInt(txtMatricula.getText()));
                serviceOdontologo.guardar(odontologo);
                JOptionPane.showMessageDialog(panelFormulario, "Odontologo creado con éxito","Creación de odontólogo",JOptionPane.INFORMATION_MESSAGE);
                panelManager.mostrarPanelAdminOdontologos();
            }
        });
    }
}
