package Mvc.Vue.Panels;



import Mvc.Vue.utils.themes.Template;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class TextIconPanel extends JPanel {
    private JLabel lbl_icon, lbl_title;

    private void initLabels(String title){
        lbl_title = new JLabel(title);
        lbl_title.setFont(Template.titleFont);
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setForeground(Template.labelsTextColor);

        lbl_icon = new JLabel(Template.icon_logo);
        lbl_icon.setHorizontalAlignment(JLabel.CENTER);
    }

    public TextIconPanel(String title){
        initLabels(title);
        setBackground(Template.panelsBackground);
        setBorder(new EmptyBorder(10, 10, 10, 30));
        setLayout(new BorderLayout());
        add(lbl_icon, BorderLayout.WEST);
        add(lbl_title, BorderLayout.CENTER);

    }


    public JLabel getLbl_icon() {
        return lbl_icon;
    }

    public void setLbl_icon(JLabel lbl_icon) {
        this.lbl_icon = lbl_icon;
    }

    public JLabel getLbl_title() {
        return lbl_title;
    }

    public void setLbl_title(JLabel lbl_title) {
        this.lbl_title = lbl_title;
    }
}
