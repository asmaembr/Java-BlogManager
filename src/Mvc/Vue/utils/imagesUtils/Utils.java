package Mvc.Vue.utils.imagesUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

public class Utils {
    public static void adjustIconContrast(AbstractButton button, ImageIcon icon, Color color) {
        // Créer une copie de l'icône pour éviter de modifier l'icône d'origine
        ImageIcon newIcon = new ImageIcon(icon.getImage());

        // Récupérer l'image de l'icône
        Image image = newIcon.getImage();

        // Créer un objet de filtre pour ajuster la couleur
        ImageFilter filter = new ColorFilter(color);

        // Appliquer le filtre à l'image
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
        Image newImage = Toolkit.getDefaultToolkit().createImage(producer);

        // Mettre à jour l'icône du bouton avec la nouvelle image
        newIcon.setImage(newImage);
        button.setIcon(newIcon);
    }

    public static String[] getEnumNames(Class<? extends Enum<?>> enumClass) {
        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("La classe fournie n'est pas une énumération.");
        }

        String[] names = new String[enumConstants.length];
        for (int i = 0; i < enumConstants.length; i++) {
            names[i] = enumConstants[i].name();
        }

        return names;
    }


    public static Image scaleImage(Image source, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return img;
    }
    public static Image scaleImage(Image source, int size) {
        int width = source.getWidth(null);
        int height = source.getHeight(null);
        double f = 0;
        if (width < height) {//portrait
            f = (double) height / (double) width;
            width = (int) (size / f);
            height = size;
        } else {//paysage
            f = (double) width / (double) height;
            width = size;
            height = (int) (size / f);
        }
        return scaleImage(source, width, height);
    }

    //avec un facteur (<1 pour rétrécir, >1 pour agrandir):
    public static Image scaleImage(final Image source, final double factor) {
        int width = (int) (source.getWidth(null) * factor);
        int height = (int) (source.getHeight(null) * factor);
        return scaleImage(source, width, height);
    }
}
