package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Pract2View extends JFrame {
    private JButton btnCargar;
    private JButton btnGrises;
    private JButton btnHistograma;
    private JButton btnCanales;
    private JButton btnBrillo;
    private JButton btnContraste;
    private JButton btnBrilloColor;
    private JButton btnContrasteColor;
    private JLabel labelImagen;

    public Pract2View() {
        super("Práctica 2: Generacion del Histograma");
        this.btnCargar = new JButton("Cargar Imagen");
        this.btnGrises = new JButton("Convertir a Escala de Grises");
        this.btnHistograma = new JButton("Mostrar Histograma");
        this.btnCanales = new JButton("Canales RGB");
        this.btnBrillo = new JButton("Ajustar Brillo (Grises)");
        this.btnContraste = new JButton("Ajustar contraste (Grises)");
        this.btnBrilloColor = new JButton("Ajustar Brillo (Color)");
        this.btnContrasteColor = new JButton("Ajustar cinstraste (Color)");
        this.labelImagen = new JLabel("", JLabel.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCargar);
        panelBotones.add(btnGrises);
        panelBotones.add(btnHistograma);
        panelBotones.add(btnCanales);
        panelBotones.add(btnBrillo);
        panelBotones.add(btnContraste);
        panelBotones.add(btnBrilloColor);
        panelBotones.add(btnContrasteColor);

        this.add(panelBotones, BorderLayout.SOUTH);
        this.add(new JScrollPane(labelImagen), BorderLayout.CENTER);

        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void mostrarVentanasCanales(BufferedImage imgR, BufferedImage imgG, BufferedImage imgB) {
        JFrame frame = new JFrame("Canales RGB");
        frame.setLayout(new GridLayout(1, 3));

        frame.add(new JLabel(new ImageIcon(imgR)));
        frame.add(new JLabel(new ImageIcon(imgG)));
        frame.add(new JLabel(new ImageIcon(imgB)));

        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    public String pedirValor(String mensaje) {
        return JOptionPane.showInputDialog(this, mensaje);
    }

    public void mostrarImagen(BufferedImage img) {
        labelImagen.setIcon(new ImageIcon(img));
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void addBtnCargarListener(ActionListener listener) {
        btnCargar.addActionListener(listener);
    }

    public void addBtnGrisesListener(ActionListener listener) {
        btnGrises.addActionListener(listener);
    }

    public void addBtnHistogramaListener(ActionListener listener) {
        btnHistograma.addActionListener(listener);
    }

    public void addBtnCanalesListener(ActionListener listener) {
        btnCanales.addActionListener(listener);
    }

    public void addBtnBrilloListener(ActionListener listener) {
        btnBrillo.addActionListener(listener);
    }

    public void addBtnContrasteListener(ActionListener listener) {
        btnContraste.addActionListener(listener);
    }

    public void addBtnBrilloColorListener(ActionListener listener) {
        btnBrilloColor.addActionListener(listener);
    }

    public void addBtnContrasteColorListener(ActionListener listener) {
        btnContrasteColor.addActionListener(listener);
    }
}