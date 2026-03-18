package Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Transformations {

    public static BufferedImage applyTransform(BufferedImage img, String tipo) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage salida = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                int value = 0;

                switch (tipo) {
                    case "YIQ":
                        value = (int)(0.299 * r + 0.587 * g + 0.114 * b);
                        break;
                    case "HSV":
                        value = Math.max(r, Math.max(g,b));
                        break;
                    case "HSI":
                        value = (r+g+b) / 3;
                        break;
                    default:
                        value = (r+g+b) / 3;
                }

                value = Math.min(255, Math.max(0, value));

                Color gris = new Color(value, value, value);
                salida.setRGB(x, y, gris.getRGB());
            }
        }
        return salida;
    }
}
