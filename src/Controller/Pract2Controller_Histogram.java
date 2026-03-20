package Controller;

import Model.Pract2Model_Histogram;
import View.Pract2View_Histogram;

import java.awt.image.BufferedImage;

public class Pract2Controller_Histogram {
    private Pract2Model_Histogram model;
    private Pract2View_Histogram view;

    public Pract2Controller_Histogram(BufferedImage img) {
        this.model = new Pract2Model_Histogram(img);
        this.view = new Pract2View_Histogram();
    }

    public void init() {
        view.addRGBWindow(
                model.getImagenOriginal(),
                model.getRGBHistogram(),
                model.getRGBProperties(),
                model.getProbabilidadRojo(),
                model.getDensidadRojo()
        );

        String[] transformations = {"YIQ", "HSV", "HSI"};
        for(String type: transformations) {
            view.addTransformedWindow(
                    type,
                    model.getTransformedImage(type),
                    model.getTransformedHistogram(type),
                    model.getTransformedProperties(type),
                    model.getTransformedProbability(type),
                    model.getTransformedDensity(type)
            );
        }

        view.setVisible(true);
    }
}
