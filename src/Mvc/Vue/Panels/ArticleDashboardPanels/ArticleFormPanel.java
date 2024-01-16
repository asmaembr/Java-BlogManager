package Mvc.Vue.Panels.ArticleDashboardPanels;

import Mvc.Vue.Events.Session;
import Mvc.Vue.Panels.BannerPanel;
import Mvc.Vue.utils.CustomComboBoxRenderer;
import Mvc.Vue.utils.hintTextFields.HintPasswordField;
import Mvc.Vue.utils.hintTextFields.HintTextField;
import Mvc.Vue.utils.hintTextFields.NumericField;
import Mvc.Vue.utils.imagesUtils.Utils;
import Mvc.Vue.utils.themes.Template;
import Mvc.Vue.utils.themes.Theme;
import models.Article;
import models.Blogueur;
import models.StatutArticle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArticleFormPanel extends JPanel {
    String hintID = "ID",hintTitre="Titre",hintContenu="Contenu",hintstatut="Choix";
    HintTextField txt_Titre, txt_Contenu;
    NumericField txt_id;
    JComboBox<String> combo_statut;
    JPanel iconsPanel, fieldsPanel, bannerPanel;

    private void initComponents(){
        txt_id          = new NumericField      (hintID);
        txt_id.setEnabled(false);
        txt_id.setBackground(new Color(197, 195, 195));
        txt_Titre       = new HintTextField     (hintTitre);
        txt_Contenu   = new HintTextField     (hintContenu);

        var statutChoices = new ArrayList<String>(Arrays.asList(Utils.getEnumNames(StatutArticle.class)));
        statutChoices.add(0, hintstatut);
        String[] statut = statutChoices.toArray(new String[0]);
        combo_statut = new JComboBox<>(statut);
        combo_statut.setForeground(new Color(90, 80, 200));
        combo_statut.setFont(new Font("Optima", Font.BOLD, 17));
        ((JLabel)combo_statut.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        combo_statut.setRenderer(new CustomComboBoxRenderer(40));
        combo_statut.setPreferredSize(new Dimension(combo_statut.getPreferredSize().width, 40));

        JLabel id_icon      = new JLabel(new ImageIcon(Template.icons+"id.png"));
        JLabel titre   = new JLabel(new ImageIcon(Template.icons+"logoLogin.png"));
        JLabel contenu      = new JLabel(new ImageIcon(Template.icons+"firstName.png"));
        JLabel statut_icon     = new JLabel(new ImageIcon(Template.icons+"cb_Sexe.png"));

        iconsPanel = new JPanel();
        iconsPanel.setBackground(Template.FormPanelBgColor);
        iconsPanel.setLayout(new GridLayout(7, 1, 10,10));
        iconsPanel.setBorder(new EmptyBorder(10,10,10,10));
        iconsPanel.add(id_icon);
        iconsPanel.add(titre);
        iconsPanel.add(contenu);
        iconsPanel.add(statut_icon);

        fieldsPanel = new JPanel();
        fieldsPanel.setBackground(Template.FormPanelBgColor);
        fieldsPanel.setLayout(new GridLayout(7, 1, 10,10));
        fieldsPanel.setBorder(new EmptyBorder(10,10,10,10));
        fieldsPanel.add(txt_id);
        fieldsPanel.add(txt_Titre);
        fieldsPanel.add(txt_Contenu);
        fieldsPanel.add(combo_statut);


        bannerPanel = new BannerPanel(Theme.formBanner);
        bannerPanel.setBorder(new EmptyBorder(10,10,10,10));

    }
    public ArticleFormPanel(){
        initComponents();
        setBackground(Template.FormPanelBgColor);
        setBorder(new EmptyBorder(25, 20,70,20));
        setLayout(new BorderLayout());
        add(iconsPanel, BorderLayout.WEST);
        add(fieldsPanel, BorderLayout.CENTER);
        add(bannerPanel, BorderLayout.NORTH);
    }
    public Article formToArticle(){

        var articleId      =   txt_id.getValue()!=null  ? txt_id.getValue() : null;
        var articleTitre   =   txt_Titre.getText()!=null &&
                            txt_Titre.getText().trim().length()!=0 &&
                            !txt_Titre.getText().equals(hintTitre) ?    txt_Titre.getText() : null ;

        var articleContenu    =   txt_Contenu.getText()!=null &&
                            txt_Contenu.getText().trim().length()!=0  ? txt_Contenu.getText() : null ;

        var text        =   combo_statut.getSelectedItem().toString();
        var articleStatut    =   text!=null && text.trim().length() != 0 && !text.equals(hintstatut) ? text : null;

        var id          = (articleId      == null  ? null :articleId);
        var titre       = (articleTitre   == null  ? null :articleTitre);
        var contenu        = ((articleContenu    == null  ? null :articleContenu));
        var statut        = ((articleStatut    == null  ? null :
                (articleStatut.equals(StatutArticle.PRIVE.toString()) ? StatutArticle.PRIVE :StatutArticle.PUBLIC)));

        return new Article(id, statut,contenu,Session.getInstance().getBlogueur(), titre);
    }
    public void fillForm(Article articlevalues){
        txt_id.setText(articlevalues.getId().toString());
        txt_Titre.setText(articlevalues.getTitre());
        var fn = articlevalues.getContenu();
        txt_Contenu.setText( fn!=null ? fn : "");
        var statut = (articlevalues.getStatut()!=null) ? articlevalues.getStatut().toString() : "Statut";
        var choix = statut.equals("Statut")? 0 : StatutArticle.getStringValues().length;
        combo_statut.setSelectedIndex(choix);
    }



    public HintTextField getTxt_Titre() {
        return txt_Titre;
    }

    public void setTxt_Titre(HintTextField txt_Titre) {
        this.txt_Titre = txt_Titre;
    }

    public HintTextField getTxt_Contenu() {
        return txt_Contenu;
    }

    public void setTxt_Contenu(HintTextField txt_Contenu) {
        this.txt_Contenu = txt_Contenu;
    }

    public String getTxt_id() {
        return txt_id.getText();
    }

    public void setTxt_id(NumericField txt_id) {
        this.txt_id = txt_id;
    }

    public JComboBox<String> getCombo_statut() {
        return combo_statut;
    }

    public void setCombo_statut(JComboBox<String> combo_statut) {
        this.combo_statut = combo_statut;
    }
}
