package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pract2Model {
    private BufferedImage imagenOriginal;
    private BufferedImage imagenActual;

    public void cargarImagen(File file) throws IOException {
        imagenOriginal = ImageIO.read(file);
        imagenActual = imagenOriginal;
    }

    public void grayConversion() {
        if(imagenActual != null) {
            int w = imagenActual.getWidth();
            int h = imagenActual.getHeight();
            BufferedImage gray = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int rgb = imagenActual.getRGB(x, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = (rgb) & 0xFF;

                    int gr = (r+g+b) / 3;
                    gray.setRGB(x, y, new Color(gr, gr, gr).getRGB());
                }
            }

            this.imagenActual = gray;
        }
    }

    public BufferedImage[] crearCanalesRGB() {
        if (imagenActual == null) return null;

        int w = imagenActual.getWidth();
        int h = imagenActual.getHeight();

        BufferedImage rojo = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        BufferedImage verde = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        BufferedImage azul = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        // Extrae cada canal y genera una imagen nueva
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = imagenActual.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                rojo.setRGB(x, y, new Color(r, 0, 0).getRGB());
                verde.setRGB(x, y, new Color(0, g, 0).getRGB());
                azul.setRGB(x, y, new Color(0, 0, b).getRGB());
            }
        }
        // Devolvemos las 3 imágenes en un arreglo: [0]=Rojo, [1]=Verde, [2]=Azul
        return new BufferedImage[]{rojo, verde, azul};
    }

    public void modificarBrillo(int brillo, boolean enColor) {

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

        this.imagenActual = salida;
    }

    public void modificarContraste(float factor, boolean enColor) {

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

        this.imagenActual = salida;
    }


    public BufferedImage getImagenActual() {
        return imagenActual;
    }
}
