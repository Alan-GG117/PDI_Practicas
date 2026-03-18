package Controller;

import Model.Pract2Model;
import View.Pract2View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
}
