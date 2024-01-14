package Mvc.Vue.Panels;


import Mvc.Vue.utils.imagesUtils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfimButtonsPanel extends JPanel {
    private JButton btn_ok, btn_cancel;

    private void initButtons(String okTxt, String cancelTxt,
                             ImageIcon okIcon, ImageIcon cancelIcon,
                             Color textColor, Font font){
        btn_ok = new JButton(okTxt, okIcon);
        btn_ok.setHorizontalTextPosition(JButton.RIGHT);
        btn_ok.setFont(font);
        btn_ok.setHorizontalAlignment(JButton.CENTER);
        btn_ok.setForeground(textColor);
        btn_ok.setBackground(null);
        btn_ok.setBorderPainted(true);
        btn_ok.setPreferredSize(new Dimension(160, 70));
        btn_ok.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon = (ImageIcon)btn_ok.getIcon();
                btn_ok.setForeground(new Color(4, 49, 176, 255));
                // Code exécuté lorsque la souris entre dans la zone du bouton (hover)
                //Utils.adjustIconContrast(btn_ok, icon, new Color(71, 117, 242, 255) ); // Changer la couleur de l'icône
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon = (ImageIcon)btn_ok.getIcon();
                btn_ok.setForeground(textColor);
                // Code exécuté lorsque la souris quitte la zone du bouton
               // Utils.adjustIconContrast(btn_ok, icon,new Color(202, 211, 242, 105) );  // Rétablir la couleur de l'icône
            }
        });
        btn_cancel = new JButton(cancelTxt, cancelIcon);
        btn_cancel.setHorizontalTextPosition(JButton.RIGHT);
        btn_cancel.setFont(font);
        btn_cancel.setHorizontalAlignment(JButton.CENTER);
        btn_cancel.setForeground(Color.RED);
        btn_cancel.setBorderPainted(true);
        btn_cancel.setBackground(null);
        btn_cancel.setPreferredSize(new Dimension(160, 70));
        btn_cancel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon = (ImageIcon)btn_cancel.getIcon();
                btn_cancel.setForeground(new Color(150, 127, 126, 255));
                // Code exécuté lorsque la souris entre dans la zone du bouton (hover)
                Utils.adjustIconContrast(btn_cancel, icon, new Color(220, 217, 217, 0) ); // Changer la couleur de l'icône
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon = (ImageIcon)btn_cancel.getIcon();
                btn_cancel.setForeground(new Color(210, 19, 19));
                // Code exécuté lorsque la souris quitte la zone du bouton
                Utils.adjustIconContrast(btn_cancel, icon,new Color(210, 19, 19) );  // Rétablir la couleur de l'icône
            }
        });
    }


    public ConfimButtonsPanel(String okTxt, String cancelTxt,
                              ImageIcon okIcon, ImageIcon cancelIcon,
                              Color textColor, Font font, Color bgColor){
        initButtons(okTxt, cancelTxt, okIcon, cancelIcon, textColor, font);
        setBackground(bgColor);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        add(btn_ok); add(btn_cancel);
    }


    public JButton getbtn_ok() {
        return btn_ok;
    }

    public void setbtn_ok(JButton btn_ok) {
        this.btn_ok = btn_ok;
    }

    public JButton getBtn_cancel() {
        return btn_cancel;
    }

    public void setBtn_cancel(JButton btn_cancel) {
        this.btn_cancel = btn_cancel;
    }
}
