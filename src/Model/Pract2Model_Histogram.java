package Model;

import Utils.HistogramUtils;
import Utils.Transformations;

import java.awt.image.BufferedImage;

public class Pract2Model_Histogram {

    private BufferedImage imagenOriginal;

    public Pract2Model_Histogram(BufferedImage imagenOriginal) {
        this.imagenOriginal = imagenOriginal;
    }

    public BufferedImage getImagenOriginal() {
        return imagenOriginal;
    }

    public int[][] getRGBHistogram() {
        return HistogramUtils.calcularHistogramaRGB(imagenOriginal);
    }

    public String getRGBProperties() {
        int[][] histRGB = getRGBHistogram();
        return HistogramUtils.calcularPropiedades(histRGB, imagenOriginal);
    }

    public int[] getProbabilidadRojo() {
        return HistogramUtils.calcularProbabilidad(getRGBHistogram()[0], imagenOriginal);
    }

    public int[] getDensidadRojo() {
        return HistogramUtils.calcularDensidad(getRGBHistogram()[0], imagenOriginal);
    }

    public BufferedImage getTransformedImage(String type) {
        return Transformations.applyTransform(imagenOriginal, type);
    }

    public int[] getTransformedHistogram(String type) {
        BufferedImage imgTransformada = getTransformedImage(type);
        return HistogramUtils.calcularHistogramaGris(imgTransformada);
    }

    public String getTransformedProperties(String type) {
        BufferedImage transformedImage = getTransformedImage(type);
        int[] hist = getTransformedHistogram(type);
        return HistogramUtils.calcularPropiedades(hist, transformedImage);
    }

    public int[] getTransformedProbability(String type) {
        return HistogramUtils.calcularProbabilidad(getTransformedHistogram(type), getTransformedImage(type));
    }

    public int[] getTransformedDensity(String type) {
        return HistogramUtils.calcularDensidad(getTransformedHistogram(type), getTransformedImage(type));
    }
}
