package Mvc.Vue.Panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Mvc.Vue.utils.GraphicUtils.zoom;

public class ButtonsPanel extends JPanel  {
    private JButton btn1, btn2;

    private void initButtons(String txt1, String txt2, ImageIcon icon1, ImageIcon icon2, Color textColor, Font font){

        btn1 = new JButton(txt1,zoom(icon1.getImage(),60,60));
        btn1.setHorizontalTextPosition(JButton.RIGHT);
        btn1.setFont(font);
        btn1.setHorizontalAlignment(JButton.CENTER);
        btn1.setForeground(textColor);
        btn1.setBorder(null);
        btn1.setBackground(null);
        btn1.setPreferredSize(new Dimension(btn1.getIcon().getIconHeight(),btn1.getIcon().getIconWidth()));

        btn2 = new JButton(txt2,zoom(icon2.getImage(),60,60));
        btn2.setHorizontalTextPosition(JButton.RIGHT);
        btn2.setFont(font);
        btn2.setHorizontalAlignment(JButton.CENTER);
        btn2.setForeground(textColor);
        btn2.setBorder(null);
        btn2.setBackground(null);
        btn2.setPreferredSize(new Dimension(btn2.getIcon().getIconHeight(),btn2.getIcon().getIconWidth()));

    }
    public ButtonsPanel( String txt1, String txt2, ImageIcon icon1, ImageIcon icon2, Color textColor, Font font , Color bgColor){

        initButtons(txt1, txt2, icon1, icon2, textColor, font);
        setBackground(bgColor);
        add(btn1);
        add(btn2);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(new EmptyBorder(10,10,10,10));

    }

    public JButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public JButton getBtn2() {
        return btn2;
    }
}
