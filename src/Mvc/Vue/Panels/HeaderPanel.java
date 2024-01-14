package Mvc.Vue.Panels;

import Mvc.Vue.Events.Events;
import Mvc.Vue.utils.themes.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Mvc.Vue.utils.GraphicUtils.zoom;
import static Mvc.Vue.utils.themes.Theme.FormPanelBgColor;
import static Mvc.Vue.utils.themes.Theme.icon_logo;

public class HeaderPanel extends JPanel {

    private TitlePanel titlePanel;

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;


    private void initPanels(Color bgcolor,String txt1,String txt2,String txt3){
        titlePanel = new TitlePanel(" My Blog Manager ",Theme.titlesTextColor, Theme.titlesFont, bgcolor, icon_logo);
        titlePanel.setBorder(new EmptyBorder(30,10,0,10 ));

        btn1 =new JButton(txt1);
        btn1.setFont(Theme.buttonsFont);
        btn1.setBackground(bgcolor);
        btn1.setBorder(new EmptyBorder(20,10,0,10 ));
        btn1.setBorderPainted(false);
        btn1.setFocusable(false);

        btn2 =new JButton(txt2);
        btn2.setFont(Theme.buttonsFont);
        btn2.setBackground(bgcolor);
        btn2.setBorder(new EmptyBorder(20,10,0,10 ));
        btn2.setBorderPainted(false);
        btn2.setFocusable(false);


        btn3 =new JButton(txt3);
        btn3.setFont(Theme.buttonsFont);
        btn3.setBackground(bgcolor);
        btn3.setBorder(new EmptyBorder(20,10,0,10 ));
        btn3.setBorderPainted(false);
        btn3.setFocusable(false);


    }

    public HeaderPanel(Color bgcolor) {
        Events e = new Events();
        initPanels(bgcolor,"Mes Blogs","Mes Articles","Mes commentaires");
        setBackground(FormPanelBgColor);
        setLayout(new GridLayout(1,3));
        add(titlePanel);
        add(btn1);
        add(btn2);
        add(btn3);
    }

    public TitlePanel getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(TitlePanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    public JButton getBtn2() {
        return btn2;
    }

    public void setBtn2(JButton btn2) {
        this.btn2 = btn2;
    }

    public JButton getBtn1() {
        return btn1;
    }

    public void setBtn1(JButton btn1) {
        this.btn1 = btn1;
    }

    public JButton getBtn3() {
        return btn3;
    }

    public void setBtn3(JButton btn3) {
        this.btn3 = btn3;
    }
}
