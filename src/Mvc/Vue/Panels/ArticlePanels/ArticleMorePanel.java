package Mvc.Vue.Panels.ArticlePanels;

import Mvc.Vue.Panels.ButtonsPanel;
import Mvc.Vue.utils.themes.Theme;
import models.Article;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import static Mvc.Vue.utils.GraphicUtils.zoom;


public class ArticleMorePanel extends JPanel {


    private JLabel lbl_titre;
    private JLabel lbl_statut;
    private JTextArea txt_contenu;
    private JButton manage;

    private JPanel initentete(Article article,Color color, Font title_font, Font fontstatus , Color bgcolor ){
        var panel =new JPanel();
        manage= new JButton();
        manage.setIcon(zoom(Theme.icon_manage.getImage(), 60,60));
        manage.setBackground(null);
        manage.setBorder(new EmptyBorder(0,0,0,0));
        manage.setSize(manage.getIcon().getIconWidth(),manage.getIcon().getIconHeight());
        manage.setFocusable(false);
        manage.setHorizontalAlignment(SwingConstants.CENTER);

        lbl_titre = new JLabel(article.getTitre());
        lbl_titre.setForeground(color);
        lbl_titre.setBackground(bgcolor);
        lbl_titre.setFont(title_font);
        lbl_titre.setBorder(new EmptyBorder(20,0,0,0));
        lbl_titre.setHorizontalAlignment(SwingConstants.CENTER);


        lbl_statut=new JLabel(article.getStatut().toString());
        lbl_statut.setForeground(color);
        lbl_statut.setFont(fontstatus);
        lbl_statut.setBackground(bgcolor);
        lbl_statut.setBorder(new EmptyBorder(20,0,0,0));
        lbl_statut.setHorizontalAlignment(SwingConstants.CENTER);


        panel.setBackground(bgcolor);
        panel.setLayout(new GridLayout(3,1));
        panel.add(lbl_titre);
        panel.add(lbl_statut);
        return panel;


    }
    private void init(Article article ,Color color, Font font,Color bgcolor){

        txt_contenu =new JTextArea(article.getContenu());
        txt_contenu.setForeground(color);
        txt_contenu.setFont(font);
        txt_contenu.setBackground(bgcolor);
        txt_contenu.setBorder(new EmptyBorder(20,0,0,0));
        txt_contenu.setAutoscrolls(true);
        txt_contenu.setEditable(false);
        txt_contenu.setLineWrap(true);
        txt_contenu.setWrapStyleWord(true);
        txt_contenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        txt_contenu.setAlignmentY(Component.CENTER_ALIGNMENT);
        txt_contenu.setOpaque(false);
    }


    public  ArticleMorePanel( Article article, Color color , Font title_font,Font fontstatus , Font font,Color bgcolor){
        init(  article,color ,  font,bgcolor);
        setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setBackground(bgcolor);
        add(initentete(article,color,title_font,fontstatus,bgcolor));
        add(txt_contenu);
        add(manage);

    }

    public JButton getManage() {
        return manage;
    }

    public void setManage(JButton manage) {
        this.manage = manage;
    }

    public JLabel getLbl_titre() {
        return lbl_titre;
    }

    public void setLbl_titre(JLabel lbl_titre) {
        this.lbl_titre = lbl_titre;
    }

    public JLabel getLbl_statut() {
        return lbl_statut;
    }

    public void setLbl_statut(JLabel lbl_statut) {
        this.lbl_statut = lbl_statut;
    }

    public JTextArea getTxt_contenu() {
        return txt_contenu;
    }

    public void setTxt_contenu(JTextArea txt_contenu) {
        this.txt_contenu = txt_contenu;
    }



}
