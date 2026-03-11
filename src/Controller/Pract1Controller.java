package Controller;

import Model.Pract1Model;
import View.Pract1View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Pract1Controller {
    private Pract1Model model;
    private Pract1View view;

    public Pract1Controller(Pract1Model model, Pract1View view) {
        this.model = model;
        this.view = view;

        this.view.agregarListenerBtnCargar(new CargarListenerBtnCargar());
        this.view.agregarListenerBtnCanales(new CargarListenerCanales());
        this.view.agregarListenerBtnGrises(new CargarListenerGrises());
        this.view.agregarListenerBtnBrilloEG(new CargarListenerBrilloEG());
        this.view.agregarListenerBtnContrasteEG(new CargarListenerContrasteEG());
        this.view.agregarListenerBtnBrilloC(new CargarListenerBrilloC());
        this.view.agregarListenerBtnContrasteC(new CargarListenerContrasteC());
    }

    //Clase interna que maneja el evento del botón
    class CargarListenerBtnCargar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File seleccionado = view.mostrarFileChooser();

            if(seleccionado != null) {
                try {

                    model.cargarImagen(seleccionado);

                    int anchoFijo = 320;
                    int altoFijo = 160;

                    Image escalado = model.getImagenOriginal().getScaledInstance(anchoFijo, altoFijo, Image.SCALE_SMOOTH);


                    ImageIcon icono = new ImageIcon(escalado);
                    view.setImagen(icono);

                } catch (IOException ex) {
                    view.mostrarError("Error al cargar la imagen: \n" + ex.getMessage());
                }
            }
        }
    }

    class CargarListenerCanales implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage[] arr = model.crearCanalesRGB();

            if(arr == null) return;

            view.crearFrameCanales(arr);
        }
    }

    class CargarListenerGrises implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage bufferedImage = model.convertirGrises();
            Image img = bufferedImage.getScaledInstance(320, 160, BufferedImage.SCALE_SMOOTH);


            view.setRes(new ImageIcon(img));
        }
    }

    class CargarListenerContrasteEG implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage contrEG = model.modificarContraste(false);
            Image img = contrEG.getScaledInstance(320, 160, BufferedImage.SCALE_SMOOTH);
            view.setRes(new ImageIcon(img));
        }
    }

    class CargarListenerBrilloEG implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage brilloEG = model.modificarBrillo(false);
            Image img = brilloEG.getScaledInstance(320, 160, BufferedImage.SCALE_SMOOTH);
            view.setRes(new ImageIcon(img));

        }
    }

    class CargarListenerContrasteC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage contrasteC = model.modificarContraste(true);
            Image img = contrasteC.getScaledInstance(320, 160, BufferedImage.SCALE_SMOOTH);
            view.setRes(new ImageIcon(img));
        }
    }

    class CargarListenerBrilloC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage brilloC = model.modificarBrillo(true);
            Image img = brilloC.getScaledInstance(320, 160, BufferedImage.SCALE_SMOOTH);
            view.setRes(new ImageIcon(img));
        }
    }
}
