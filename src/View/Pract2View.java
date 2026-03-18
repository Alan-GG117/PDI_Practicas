package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Pract2View extends JFrame {
    private JButton btnCargar;
    private JButton btnGrises;
    private JLabel labelImagen;

    public Pract2View() {
        super("Práctica 2: Generacion del Histograma");
        this.btnCargar = new JButton("Cargar Imagen");
        this.btnGrises = new JButton("Convertir a Escala de Grises");
        this.labelImagen = new JLabel("", JLabel.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCargar);
        panelBotones.add(btnGrises);

        this.add(panelBotones, BorderLayout.SOUTH);
        this.add(new JScrollPane(labelImagen), BorderLayout.CENTER);

        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
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
}