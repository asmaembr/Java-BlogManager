package Mvc.Vue.Panels.CommentPanels;

import Mvc.Vue.Events.Session;
import Mvc.Vue.Panels.BannerPanel;
import Mvc.Vue.utils.CustomComboBoxRenderer;
import Mvc.Vue.utils.hintTextFields.HintPasswordField;
import Mvc.Vue.utils.hintTextFields.HintTextField;
import Mvc.Vue.utils.hintTextFields.NumericField;
import Mvc.Vue.utils.imagesUtils.Utils;
import Mvc.Vue.utils.themes.Template;
import Mvc.Vue.utils.themes.Theme;
import models.Commentaire;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CommentFormPanel extends JPanel {
    String hintID = "ID",hintContenu="Contenu";
    HintTextField txt_contenu;
    NumericField txt_id;
    JPanel iconsPanel, fieldsPanel, bannerPanel;

    private void initComponents(){
        txt_id          = new NumericField      (hintID);
        txt_id.setEnabled(false);
        txt_id.setBackground(new Color(197, 195, 195));
        txt_contenu   = new HintTextField     (hintContenu);

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
        fieldsPanel.add(txt_contenu);

        bannerPanel = new BannerPanel(Theme.formBanner);
        bannerPanel.setBorder(new EmptyBorder(10,10,10,10));

    }
    public CommentFormPanel(){
        initComponents();
        setBackground(Template.FormPanelBgColor);
        setBorder(new EmptyBorder(25, 20,70,20));
        setLayout(new BorderLayout());
        add(iconsPanel, BorderLayout.WEST);
        add(fieldsPanel, BorderLayout.CENTER);
        add(bannerPanel, BorderLayout.NORTH);
    }
    public Commentaire formToCommentaire(){

        var commentaireId      =   txt_id.getValue()!=null  ? txt_id.getValue() : null;

        var commentaireContenu      =   txt_contenu.getText()!=null &&
                            txt_contenu.getText().trim().length()!=0 &&
                            !txt_contenu.getText().equals(hintContenu) ? txt_contenu.getText() : null;


        var id          = ((commentaireId      == null  ? null :commentaireId));
        var contenu       = ((commentaireContenu   == null  ? null :commentaireContenu));

        return new Commentaire(Session.getInstance().getBlogueur(), id,contenu);
    }
    public void fillCommentaire(Commentaire commentaire){
        txt_id.setText(commentaire.getId().toString());
        var contenu = commentaire.getContenu();
        txt_contenu.setText( contenu!=null ? contenu : "");

    }

    public String hintID() {
        return hintID;
    }

    public String getTxt_contenu() {
        return txt_contenu.getText();
    }

    public void setTxt_contenu(HintTextField txt_contenu) {
        this.txt_contenu = txt_contenu;
    }

    public NumericField getTxt_id() {
        return txt_id;
    }

    public void setTxt_id(NumericField txt_id) {
        this.txt_id = txt_id;
    }
}
