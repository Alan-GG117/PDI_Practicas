package View;

import Utils.HistogramPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pract2View_Histogram extends JFrame {
    private JTabbedPane tabbedPane;

    public Pract2View_Histogram() {
        this.setTitle("Practica 2: Generación del Histograma");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        this.add(tabbedPane);
    }

    //Construcción de la pestaña RGB recibiendo los datos ya calculados
    public void addRGBWindow(BufferedImage img, int[][] histRGB, String properties, int[] probR, int[] densR) {
        JPanel panel = new JPanel(new BorderLayout());

        //Imagen
        panel.add(new JScrollPane(new JLabel(new ImageIcon(img))), BorderLayout.WEST);

        //Histogramas
        JPanel panelHist = new JPanel(new GridLayout(3, 1));
        panelHist.add(new HistogramPanel(histRGB[0], Color.RED, "Canal R"));
        panelHist.add(new HistogramPanel(histRGB[1], Color.GREEN, "Canal G"));
        panelHist.add(new HistogramPanel(histRGB[2], Color.BLUE, "Canal B"));
        panel.add(panelHist, BorderLayout.CENTER);

        //Propiedades
        JTextArea txtProperties = new JTextArea(15, 20);
        txtProperties.setEditable(false);
        txtProperties.setLineWrap(true);
        txtProperties.setWrapStyleWord(true);
        txtProperties.setText(properties);
        panel.add(new JScrollPane(txtProperties), BorderLayout.EAST);

        //Probabilidad y densidad
        JPanel extraPanel = new JPanel(new GridLayout(2,1));
        extraPanel.add(new HistogramPanel(probR, Color.RED, "Probabilidad R"));
        extraPanel.add(new HistogramPanel(densR, Color.MAGENTA, "Densidad R"));

        tabbedPane.addTab("RGB", panel);
    }

    public void addTransformedWindow(String title, BufferedImage img, int[] hist, String properties, int[] prob, int[] dens) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(new JScrollPane(new JLabel(new ImageIcon(img))), BorderLayout.WEST);
        panel.add(new HistogramPanel(hist, Color.DARK_GRAY, "Histograma " + title), BorderLayout.CENTER);

        JTextArea txtProperties = new JTextArea(15, 20);
        txtProperties.setEditable(false);
        txtProperties.setText(properties);
        panel.add(new JScrollPane(txtProperties), BorderLayout.EAST);

        JPanel extraPanel = new JPanel(new GridLayout(2,1));
        extraPanel.add(new HistogramPanel(prob, Color.ORANGE, "Probabiliad "+ title));
        extraPanel.add(new HistogramPanel(dens, Color.CYAN, "Densidad " + title));
        panel.add(extraPanel, BorderLayout.SOUTH);

        tabbedPane.addTab(title, panel);
    }
}
