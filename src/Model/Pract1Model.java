package Model;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Pract1Model {
    private BufferedImage imagenOriginal;

    public void cargarImagen(File archivo) throws IOException {
        imagenOriginal = ImageIO.read(archivo);
    }

    public BufferedImage getImagenOriginal() {
        return imagenOriginal;
    }

    public BufferedImage[] crearCanalesRGB() {
        if (imagenOriginal == null) return null;

        int w = imagenOriginal.getWidth();
        int h = imagenOriginal.getHeight();

        //Creacion de imagenes vacias RGB
        /* 0=red, 1=green, 2=blue */
        BufferedImage[] rgbArr = new BufferedImage[3];
        //Inicializar elementos del array
        for(int i = 0; i < rgbArr.length; i++) {
            rgbArr[i] = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        }

        //Recorrer todo el array de la imagen
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                int rgb = imagenOriginal.getRGB(x, y);
                int r = (rgb>>16) & 0xFF;
                int g = (rgb>>8) & 0xFF;
                int b = (rgb) & 0xFF;

                //Construcción de las imagenes de un solo canal
                rgbArr[0].setRGB(x, y, new Color(r, 0, 0).getRGB());
                rgbArr[1].setRGB(x, y, new Color(0, g, 0).getRGB());
                rgbArr[2].setRGB(x, y, new Color(0, 0, b).getRGB());
            }
        }

        return rgbArr;
    }

    public BufferedImage convertirGrises() {
        if(imagenOriginal == null) return null;

        int w = imagenOriginal.getWidth();
        int h = imagenOriginal.getHeight();
        BufferedImage gris = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);

        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                int rgb = imagenOriginal.getRGB(x, y);
                int r = (rgb>>16) & 0xFF;
                int g = (rgb>>8) & 0xFF;
                int b = (rgb) & 0xFF;

                int gray = (r+g+b) / 3;
                gris.setRGB(x, y, new Color(gray, gray, gray).getRGB());
            }
        }

        return gris;
    }

    public BufferedImage modificarBrillo(boolean enColor) {
        if(imagenOriginal==null) return null;

        String input = JOptionPane.showInputDialog("Ingresa valor de brillo (-100 a 100)");
        int brillo = Integer.parseInt(input);

        int w = imagenOriginal.getWidth();
        int h = imagenOriginal.getHeight();
        BufferedImage salida = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                int rgb = imagenOriginal.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                if(!enColor) {//Escala de grises
                    int gray = (r+g+b) / 3;
                    gray = Math.min(255, Math.max(0, gray+brillo));
                    salida.setRGB(x, y, new Color(gray, gray, gray).getRGB());
                } else { //Color
                    r = Math.min(255, Math.max(0, r+brillo));
                    g = Math.min(255, Math.max(0, g + brillo));
                    b = Math.min(255, Math.max(0, b + brillo));
                    salida.setRGB(x, y, new Color(r, g, b).getRGB());
                }
            }
        }
        return salida;
    }

    public BufferedImage modificarContraste(boolean enColor) {
        if(imagenOriginal == null) return null;

        String input = JOptionPane.showInputDialog("Ingrese factor de contraste (0.5=menos, 2=más)");
        double factor = Double.parseDouble(input);

        int w = imagenOriginal.getWidth();
        int h = imagenOriginal.getHeight();
        BufferedImage salida = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for(int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = imagenOriginal.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;

                if(!enColor) { //Escala de grises
                    int gray = (r + g + b) / 3;
                    gray = (int) ((gray -  128) * factor +128);
                    gray = Math.min(255, Math.max(0, gray));
                    salida.setRGB(x, y, new Color(gray, gray, gray).getRGB());
                } else { //Color
                    r = (int) ((r - 128) * factor + 128);
                    g = (int) ((g - 128) * factor + 128);
                    b = (int) ((b - 128) * factor + 128);
                    r = Math.min(255, Math.max(0, r));
                    g = Math.min(255, Math.max(0, g));
                    b = Math.min(255, Math.max(0, b));
                    salida.setRGB(x, y, new Color(r, g, b).getRGB());
                }
            }
        }

        return salida;
    }
}
