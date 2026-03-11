package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pract1View extends JFrame {

    //Componentes
    private JButton btnCargar;
    private JButton btnCanales;
    private JButton btnGrises;
    private JButton btnBrillo;
    private JButton btnContraste;
    private JButton btnBrilloColor;
    private JButton btnContrasteColor;
    private JButton salir;
    private JLabel lblImg;
    private JLabel lblRes;

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public void setBtnCargar(JButton btnCargar) {
        this.btnCargar = btnCargar;
    }

    public JButton getBtnCanales() {
        return btnCanales;
    }

    public void setBtnCanales(JButton btnCanales) {
        this.btnCanales = btnCanales;
    }

    public JButton getBtnGrises() {
        return btnGrises;
    }

    public void setBtnGrises(JButton btnGrises) {
        this.btnGrises = btnGrises;
    }

    public JButton getBtnBrillo() {
        return btnBrillo;
    }

    public void setBtnBrillo(JButton btnBrillo) {
        this.btnBrillo = btnBrillo;
    }

    public JButton getBtnContraste() {
        return btnContraste;
    }

    public void setBtnContraste(JButton btnContraste) {
        this.btnContraste = btnContraste;
    }

    public JButton getBtnBrilloColor() {
        return btnBrilloColor;
    }

    public void setBtnBrilloColor(JButton btnBrilloColor) {
        this.btnBrilloColor = btnBrilloColor;
    }

    public JButton getBtnContrasteColor() {
        return btnContrasteColor;
    }

    public void setBtnContrasteColor(JButton btnContrasteColor) {
        this.btnContrasteColor = btnContrasteColor;
    }

    public JButton getSalir() {
        return salir;
    }

    public void setSalir(JButton salir) {
        this.salir = salir;
    }

    public JLabel getLblImg() {
        return lblImg;
    }

    public void setLblImg(JLabel lblImg) {
        this.lblImg = lblImg;
    }

    public JLabel getLblRes() {
        return lblRes;
    }

    public void setLblRes(JLabel lblRes) {
        this.lblRes = lblRes;
    }

    public Pract1View() {
        super("Pactica 1: Carga, modificación y visualización de imagenes");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Panel para imagenes
        JPanel panelImg = new JPanel();
        panelImg.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));

        //Panel para botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 4, 10, 20));

        //Botones para ejecutar tareas
        btnCargar = new JButton("Cargar imagen");
        btnCanales = new JButton("Canales RGB");
        btnGrises = new JButton("Convertir a Escala de Grises");
        btnBrillo = new JButton("Modificar Brillo (Grises)");
        btnContraste = new JButton("Modificar Contraste (Grises)");
        btnBrilloColor = new JButton("Modificar Brillo (Color)");
        btnContrasteColor = new JButton("Modificar Contraste (Color)");
        salir = new JButton("Salir del programa");

        //Label para mostrar imagenes
        lblImg = new JLabel("Imagen de entrada", JLabel.LEFT);
        lblRes = new JLabel("Imagen de Salida", JLabel.RIGHT);

        //Añadir componentes a los paneles
        panelBotones.add(btnCargar);
        panelBotones.add(btnCanales);
        panelBotones.add(btnGrises);
        panelBotones.add(btnBrillo);
        panelBotones.add(btnContraste);
        panelBotones.add(btnBrilloColor);
        panelBotones.add(btnContrasteColor);
        panelBotones.add(salir);

        panelImg.add(lblImg);
        panelImg.add(lblRes);

        //Añadir al frame
        add(panelBotones, BorderLayout.NORTH);
        add(panelImg, BorderLayout.CENTER);


        setVisible(true);
    }

    public File mostrarFileChooser() {
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    public void setImagen(ImageIcon icono) {
        lblImg.setIcon(icono);
        lblImg.setText("");
    }

    public void setRes(ImageIcon icono) {
        lblRes.setIcon(icono);
        lblRes.setText("");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void crearFrameCanales(BufferedImage arr[]) {
        JFrame frm = new JFrame("Canales RGB");
        frm.setLayout(new GridLayout(1, 3));
        frm.add(new JLabel(new ImageIcon(arr[0])));
        frm.add(new JLabel(new ImageIcon(arr[1])));
        frm.add(new JLabel(new ImageIcon(arr[2])));
        frm.pack();
        frm.setVisible(true);
    }

    public void agregarListenerBtnCargar(java.awt.event.ActionListener listener) {
        btnCargar.addActionListener(listener);
    }

    public void agregarListenerBtnCanales(java.awt.event.ActionListener listener) {
        btnCanales.addActionListener(listener);
    }

    public void agregarListenerBtnGrises(java.awt.event.ActionListener listener) {
        btnGrises.addActionListener(listener);
    }

    public void agregarListenerBtnBrilloEG(java.awt.event.ActionListener listener) {
        btnBrillo.addActionListener(listener);
    }

    public void agregarListenerBtnContrasteEG(java.awt.event.ActionListener listener) {
        btnContraste.addActionListener(listener);
    }

    public void agregarListenerBtnBrilloC(java.awt.event.ActionListener listener) {
        btnBrilloColor.addActionListener(listener);
    }

    public void agregarListenerBtnContrasteC(java.awt.event.ActionListener listener) {
        btnContrasteColor.addActionListener(listener);
    }

}
