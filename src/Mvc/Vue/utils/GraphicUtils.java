package Mvc.Vue.utils;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicUtils {

    public static ImageIcon zoom(Image img, int height, int width) {
        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        bufferedImage.getGraphics().drawImage(img, 0, 0, null);
        Image scaledImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
    }
}
