package Mvc.Vue.Panels.BlogDashboardPanels;

import Mvc.Vue.Panels.BannerPanel;
import Mvc.Vue.utils.hintTextFields.HintTextField;
import Mvc.Vue.utils.hintTextFields.NumericField;
import Mvc.Vue.utils.themes.Template;
import Mvc.Vue.utils.themes.Theme;
import models.Blog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BlogFormPanel extends JPanel {
    String hintID = "ID",hintTitre = "Titre";
    HintTextField txt_titre;
    NumericField txt_id;
    JPanel iconsPanel, fieldsPanel, bannerPanel;

    private void initComponents(){
        txt_id          = new NumericField      (hintID);
        txt_id.setEnabled(false);
        txt_id.setBackground(new Color(197, 195, 195));
        txt_titre    = new HintTextField     (hintTitre);

        JLabel id_icon      = new JLabel(new ImageIcon(Template.icons+"id.png"));
        JLabel fn_icon      = new JLabel(new ImageIcon(Template.icons+"firstName.png"));

        iconsPanel = new JPanel();
        iconsPanel.setBackground(Template.FormPanelBgColor);
        iconsPanel.setLayout(new GridLayout(7, 1, 10,10));
        iconsPanel.setBorder(new EmptyBorder(10,10,10,10));
        iconsPanel.add(id_icon);
        iconsPanel.add(fn_icon);

        fieldsPanel = new JPanel();
        fieldsPanel.setBackground(Template.FormPanelBgColor);
        fieldsPanel.setLayout(new GridLayout(7, 1, 10,10));
        fieldsPanel.setBorder(new EmptyBorder(10,10,10,10));
        fieldsPanel.add(txt_id);
        fieldsPanel.add(txt_titre);

        bannerPanel = new BannerPanel(Theme.formBanner);
        bannerPanel.setBorder(new EmptyBorder(10,10,10,10));

    }
    public BlogFormPanel(){
        initComponents();
        setBackground(Template.FormPanelBgColor);
        setBorder(new EmptyBorder(25, 20,70,20));
        setLayout(new BorderLayout());
        add(iconsPanel, BorderLayout.WEST);
        add(fieldsPanel, BorderLayout.CENTER);
        add(bannerPanel, BorderLayout.NORTH);
    }
    public Blog formToBlog(){

        var blogId      =   txt_id.getValue()!=null  ? txt_id.getValue() : null;
        var blogtitre   =   txt_titre.getText()!=null &&
                            txt_titre.getText().trim().length()!=0 &&
                            !txt_titre.getText().equals(hintTitre) ?    txt_titre.getText() : null ;
        var id          = ((blogId      == null  ? null :blogId));
        var titre       = ((blogtitre   == null  ? null :blogtitre));
        return new Blog(id,titre,null);
    }
    public void fillForm(Blog value){
        txt_id.setText(value.getId().toString());
        txt_titre.setText(value.getTitre());
    }

    public String hintID() {
        return hintID;
    }
    public String HintTitre() {
        return hintTitre;
    }

}
