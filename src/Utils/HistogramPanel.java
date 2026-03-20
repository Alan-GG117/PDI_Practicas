package Utils;

import javax.swing.*;
import java.awt.*;

public class HistogramPanel extends JPanel {

    private int[] histogram;
    private Color color;
    private String title;

    public HistogramPanel(int[] histogram, Color color, String title) {
        this.histogram = histogram;
        this.color = color;
        this.title = title;
        this.setPreferredSize(new Dimension(400,200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());

        g.setColor(Color.BLACK);
        g.drawString(title, 10, 20);

        int w = getWidth();
        int h = getHeight() - 40;

        int max = 0;
        for(int a: histogram) {
            if (a > max) max = a;
        }

        if (max == 0) return;
        double scale = (double) h / max;
        int binWidth = Math.max(1, w / histogram.length);

        g.setColor(color);
        for(int i = 0; i < histogram.length; i++) {
            int hi = (int) (histogram[i] * scale);
            g.fillRect(i*binWidth,h - hi+40, binWidth, h);
        }
    }
}
