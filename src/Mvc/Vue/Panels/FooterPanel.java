package Mvc.Vue.Panels;

import Mvc.Vue.Events.Events;
import Mvc.Vue.utils.themes.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static Mvc.Vue.utils.GraphicUtils.zoom;

public class FooterPanel extends JPanel {

TitlePanel date;
JButton retour;

private void initFooter(Color color){
    retour = new JButton("");
    retour.setIcon(zoom(Theme.icon_exit.getImage(),40,40));
    retour.setBackground(color);
    retour.setBorder(new EmptyBorder(20,10,0,10 ));
    retour.setBorderPainted(false);
    retour.setFocusable(false);
    date = new TitlePanel("My Blog  2023/2024 ", Theme.labelsTextColor,new Font("Optima",Font.BOLD,16),color,Theme.icon_copy);
    date.setBorder(new EmptyBorder(20,10,0,10 ));
}
    public FooterPanel(Color color) {
        Events e = new Events();
        initFooter(color);
        setBackground(color);
        add(date);
        add(retour);

    }

    public TitlePanel getDate() {
        return date;
    }

    public void setDate(TitlePanel date) {
        this.date = date;
    }

    public JButton getRetour() {
        return retour;
    }

    public void setRetour(JButton retour) {
        this.retour = retour;
    }
}
