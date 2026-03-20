package Controller;

import Model.Pract2Model;
import View.Pract2View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pract2Controller {
    private Pract2Model model;
    private Pract2View view;

    public Pract2Controller(Pract2Model model, Pract2View view) {
        this.model = model;
        this.view = view;

        this.view.addBtnCargarListener(new CargarListener());
        this.view.addBtnGrisesListener(new GrisesListener());
        this.view.addBtnHistogramaListener(new HistogramListener());
        this.view.addBtnBrilloListener(new BrilloListener(false));
        this.view.addBtnBrilloColorListener(new BrilloListener(true));
        this.view.addBtnContrasteListener(new ContrasteListener(false));
        this.view.addBtnContrasteColorListener(new ContrasteListener(true));
        this.view.addBtnCanalesListener(new CanalesListener());
    }

    public void init() {
        this.view.setVisible(true);
    }

    class CargarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            if(chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    model.cargarImagen(file);
                    view.mostrarImagen(model.getImagenActual());
                } catch (IOException ex) {
                    view.mostrarError("Error al cargar la imagen: " + ex);
                }
            }
        }
    }

    class GrisesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.grayConversion();
            view.mostrarImagen(model.getImagenActual());
        }
    }

    class HistogramListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage imgActual = model.getImagenActual();
            if(imgActual != null) {
                Pract2Controller_Histogram ctrl = new Pract2Controller_Histogram(imgActual);
                ctrl.init();
            }
        }
    }

    class BrilloListener implements ActionListener {

        private boolean enColor;

        public BrilloListener(boolean enColor) {
            this.enColor = enColor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.getImagenActual() == null) {
                view.mostrarError("Primero carga una imagen");
                return;
            }

            String input = view.pedirValor("Ingresa el valor de brillo (-100 a 100)");
            if(input != null && !input.isEmpty()) {
                try {
                    int brillo = Integer.parseInt(input);
                    model.modificarBrillo(brillo, enColor);
                    view.mostrarImagen(model.getImagenActual());
                } catch (NumberFormatException ex) {
                    view.mostrarError("Valor de brillo inválido: " + ex);
                }
            }
        }
    }

    class ContrasteListener implements ActionListener {

        private boolean enColor;

        public ContrasteListener(boolean enColor) {
            this.enColor = enColor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.getImagenActual() == null) {
                view.mostrarError("Primero carga una imagen");
                return;
            }

            String input = view.pedirValor("Ingresa el factor de contraste (0.5 a 2)");

            if(input != null && !input.isEmpty()) {
                try {
                    float factor = Float.parseFloat(input);
                    model.modificarContraste(factor, enColor);
                    view.mostrarImagen(model.getImagenActual());
                } catch(NumberFormatException ex) {
                    view.mostrarError("Valor de contraste inválido: " + ex);
                }
            }
        }
    }

    class CanalesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.getImagenActual() == null) {
                view.mostrarError("Primero carga una imagen");
                return;
            }

            BufferedImage[] canales = model.crearCanalesRGB();
            view.mostrarVentanasCanales(canales[0], canales[1], canales[2]);
        }
    }
}
