package Utils;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class HistogramUtils {

    public static int[] calcularHistogramaGris(BufferedImage img) {
        int[] hist = new int[256];
        int w = img.getWidth();
        int h = img.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x,y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (r+g+b) / 3;
                hist[gray]++;
            }
        }

        return hist;
    }

    public static int[][] calcularHistogramaRGB(BufferedImage img) {
        int[][] hist = new int[3][256];
        int w = img.getWidth();
        int h = img.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x,y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                hist[0][r]++;
                hist[1][g]++;
                hist[2][b]++;
            }
        }

        return hist;
    }

    public static String calcularPropiedades(int[] hist, BufferedImage img) {
        int totalPixeles = img.getWidth() * img.getHeight();
        double media = 0, varianza = 0, energia = 0, entropia = 0;

        //Probabilidades de cada nivel
        double[] pr = new double[256];
        for (int i = 0; i < 256; i++) {
            pr[i] = (double)(hist[i] / totalPixeles);
        }

        //Media ponderada
        for (int i = 0; i < 256; i++) {
            media += i * pr[i];
        }

        //Varianza
        for (int i = 0; i < 256; i++) {
            varianza = Math.pow(i-media, 2) * pr[i];
        }

        double desviacion = Math.sqrt(varianza);

        //Energía y entropía
        for(int i = 0; i < 256; i++) {
            energia += pr[i] * pr[i];
            if(pr[i] > 0) {
                entropia += pr[i] * (Math.log(pr[i]) / Math.log(2));
            }
        }

        entropia *= -1;

        return "Propiedades del histograma: \n" +
                String.format("Media: %.2f", media)
                + String.format("Varianza: %.2f",varianza)
                + String.format("Desviacion: %.2f", desviacion)
                + String.format("Energía: %.4f", energia)
                + String.format("Entropía: %.4f", entropia);
    }

    public static String calcularPropiedades(int[][] histRGB, BufferedImage img) {
        StringBuilder sb = new StringBuilder("=== Canales RGB ===");
        String[] canales = {"Rojo", "Verde", "Azul"};

        for (int i = 0; i < 3; i++) {
            sb.append("\n --- Canal ").append(canales[i]).append(" ---");
            sb.append(calcularPropiedades(histRGB[i], img)).append("\n");
        }

        return sb.toString();
    }

    public static int[] calcularProbabilidad(int[] hist, BufferedImage img) {
        int totalPixeles = img.getWidth() * img.getHeight();
        int[] prob = new int[256];

        for (int i = 0; i < 256; i++) {
            prob[i] = (int) (((double) hist[i] / totalPixeles) * 1000);
        }
        return prob;
    }

    public static int[] calcularDensidad(int[] hist, BufferedImage img) {
        int totalPixeles = img.getWidth() * img.getHeight();
        double[] p = new double[256];
        int[] densidad = new int[256];

        for (int i = 0; i < 256; i++) {
            p[i] = (double) hist[i] / totalPixeles;
        }

        for (int i = 0; i < 256; i++) {
            densidad[i] = (int) (p[i] * p[i] * 1000);
        }
        return densidad;
    }
}
