package Mvc.Vue.Panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Mvc.Vue.utils.GraphicUtils.zoom;

public class TitlePanel extends JPanel {

    private JLabel  lbl_title;
    private  JLabel Logo;


    private void initLabels(String title, Color textColor, Font font ,ImageIcon img){

        lbl_title = new JLabel(title);
        lbl_title.setForeground(textColor);
        lbl_title.setFont(font);
        lbl_title.setHorizontalAlignment(JLabel.LEFT);
        Logo = new JLabel();
        Logo.setIcon(img);
        Logo.setHorizontalAlignment(JLabel.CENTER);

    }

    public TitlePanel(String title, Color textColor, Font font, Color bgColor,ImageIcon img){
        initLabels(title, textColor, font,zoom(img.getImage(),40,40));
        setBackground(bgColor);
        add(Logo);
        add(lbl_title);
        setBorder(new EmptyBorder(50, 0, 5, 10));

    }

    public JLabel getLbl_title() {
        return lbl_title;
    }

    public void setLbl_title(JLabel lbl_title) {
        this.lbl_title = lbl_title;
    }

    public JLabel getLogo() {
        return Logo;
    }

    public void setLogo(JLabel logo) {
        Logo = logo;
    }
}
