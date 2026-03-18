package Model;

import javax.imageio.ImageIO;
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


    public BufferedImage getImagenActual() {
        return imagenActual;
    }
}
