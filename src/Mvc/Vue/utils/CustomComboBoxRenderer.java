package Mvc.Vue.utils;

import javax.swing.*;
import java.awt.*;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {

    int hauteur;
    public CustomComboBoxRenderer(int height){
        this.hauteur = height;
    }
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Définir la hauteur personnalisée pour chaque élément (par exemple, 30 pixels)
        renderer.setPreferredSize(new Dimension(renderer.getPreferredSize().width, hauteur));

        return renderer;
    }
}
