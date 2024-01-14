package Mvc.Vue.Panels;


import Mvc.Vue.utils.imagesUtils.Utils;

import javax.swing.*;
import java.awt.*;

public class BannerPanel extends JPanel {
    private JLabel lbl_Banner;

    private void initBanner(String banner){
        var image = new ImageIcon(banner);
        Image zoom = Utils.scaleImage(image.getImage(), 0.20d);//taille en pixels
        Icon iconScaled = new ImageIcon(zoom);
        lbl_Banner = new JLabel(iconScaled);
        lbl_Banner.setPreferredSize(new Dimension(350, 150));
    }

    private void initBanner(ImageIcon banner){
        Image zoom = Utils.scaleImage(banner.getImage(), 0.70d);//taille en pixels
        Icon iconScaled = new ImageIcon(zoom);
        lbl_Banner = new JLabel(iconScaled);
        lbl_Banner.setPreferredSize(new Dimension(350, 150));
    }
    public BannerPanel(String banner){
        initBanner(banner);
        setBackground(Color.white);
        setLayout(new GridLayout(1,1));
        add(lbl_Banner);
    }

    public BannerPanel(ImageIcon banner){
        initBanner(banner);
        setBackground(Color.white);
        setLayout(new GridLayout(1,1));
        add(lbl_Banner);
    }
}
