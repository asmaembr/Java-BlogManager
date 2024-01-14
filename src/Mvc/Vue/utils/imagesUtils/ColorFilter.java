package Mvc.Vue.utils.imagesUtils;

import java.awt.*;
import java.awt.image.RGBImageFilter;

public class ColorFilter extends RGBImageFilter {
    private Color color;

    public ColorFilter(Color color) {
        this.color = color;
        canFilterIndexColorModel = true;
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int alpha = (rgb >> 24) & 0xff;
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
