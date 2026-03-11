package View;

import javax.swing.*;
import java.awt.*;

public class Pract2View extends JFrame {
    public Pract2View() {
        //Ajustes de ventana
        super("Práctica 2: Generación del Histograma");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel de prueba
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Angel Puto");
        jPanel1.add(label1);

        add(jPanel1);
        //Establecer visibiliad
        setVisible(true);
    }
}
