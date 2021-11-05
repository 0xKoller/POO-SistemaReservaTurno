package Interfaz;

import DAO.ServiceOdontologo;
import DAO.ServiceTurno;
import Negocio.Odontologo;
import Negocio.Turno;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

public class PanelNuevoTurno extends JPanel {
    private PanelManager panelManager;

    //Paneles
    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotonera;

    //Titulo
    private JLabel lblTitulo;

    //Componentes labels
    private JLabel lblNombrePaciente;
    private JLabel lblApellidoPaciente;
    private JLabel lblFecha;
    private JLabel lblHora;
    private JLabel lblApellidoOdontologo;
    private JLabel lblCodigo;

    //Componentes txtfields
    private JTextField txtNombrePaciente;
    private JTextField txtApellidoPaciente;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtApellidoOdontologo;
    private JTextField txtCodigo;

    //Botones
    private JButton btnGuardar;
    private JButton btnCancelar;

    //Calendario
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private UtilDateModel model;

    //INICIALIZAMOS PANELES
    public PanelNuevoTurno(PanelManager panelManager){
        this.panelManager = panelManager;
        this.setLayout(new BorderLayout());

        //TITULO
        panelTitulo = new JPanel();
        lblTitulo = new JLabel("Nuevo Turno");
        panelTitulo.add(lblTitulo);

        //Formulario
        panelFormulario = new JPanel(new GridLayout(3,2));
        panelFormulario.setBackground(Color.white);

        lblApellidoOdontologo = new JLabel("Odontologo:      ");
        lblApellidoOdontologo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblHora = new JLabel("Hora:      ");
        lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha = new JLabel("Fecha:      ");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCodigo = new JLabel("Codigo:      ");
        lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);

        txtApellidoOdontologo = new JTextField(10);
        txtFecha = new JTextField(10);
        txtHora = new JTextField(10);
        txtCodigo = new JTextField(10);
        txtCodigo.setText("0");

        //Calendario
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today","Today"); p.put("text.month","Month"); p.put("text.year","Year");
        datePanel = new JDatePanelImpl(model,p); model.setSelected(true);
        datePicker = new JDatePickerImpl(datePanel,new DateComponentFormatter());

        //Panel default es para crear nuevo. No muestra el campo de codigo
        panelFormulario.add(lblApellidoOdontologo);
        panelFormulario.add(txtApellidoOdontologo);
        panelFormulario.add(lblFecha);
        panelFormulario.add(datePicker);
        //panelFormulario.add(txtFecha);
        panelFormulario.add(lblHora);
        panelFormulario.add(txtHora);

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


        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServiceTurno serviceTurno = new ServiceTurno();
                String hora = txtHora.getText();
                //String fecha = txtFecha.getText();
                String fecha = String.valueOf(datePicker.getModel().getDay())+"/"+
                        String.valueOf(datePicker.getModel().getMonth()+1)+"/"+String.valueOf(datePicker.getModel().getYear());
                int Codigo = Integer.parseInt(txtCodigo.getText());
                String apellidoOdontologo = txtApellidoOdontologo.getText();
                ServiceOdontologo serviceOdontologo = new ServiceOdontologo();
                ArrayList<Odontologo> lista = serviceOdontologo.listar();
                for (Odontologo o : lista) {
                    if (apellidoOdontologo.equals(o.getApellido())) {
                        Turno turno = new Turno(o, panelManager.getUserlogueado(), hora, fecha);
                        turno.setCodigo(Codigo);
                        try{
                            serviceTurno.guardar(turno);
                            JOptionPane.showMessageDialog(panelFormulario, "Turno guardado con exito","Gestion de turno",JOptionPane.INFORMATION_MESSAGE);
                            panelManager.mostrarPanelTurnos();

                        }catch (Exception a){
                            JOptionPane.showMessageDialog(panelFormulario, "No puede generar un turno ya existente", "El turno ya existe", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }



            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelTurnos();
            }
        });
    }

    public void cargarTurno(Turno turno){
        lblTitulo.setText("Modificar Turno");
        panelTitulo.add(lblTitulo);
        panelFormulario.setLayout(new GridLayout(4,2));
        panelFormulario.add(lblCodigo);panelFormulario.add(txtCodigo);
        txtCodigo.setEnabled(false);
        txtApellidoOdontologo.setText(String.valueOf(turno.getOdontologo().getApellido()));
        txtHora.setText(String.valueOf(turno.getHorario()));
        String [] arrayFecha = turno.getFecha().split("/");
        model.setDate(Integer.parseInt(arrayFecha[2]),Integer.parseInt(arrayFecha[1])-1,Integer.parseInt(arrayFecha[0]));
        //txtFecha.setText(String.valueOf(turno.getFecha()));
        txtCodigo.setText(String.valueOf(turno.getCodigo()));
    }
}