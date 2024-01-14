package Mvc.Vue.Panels.ArticlePanels;

import Mvc.Vue.utils.themes.Theme;
import dao.ArticleDAO;
import models.Article;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ArticleCardsPanel extends JPanel {
    private CardLayout cardLayout = new CardLayout();
    private List<Article> Articles = new ArticleDAO().findAll();

    private List<ArticleMorePanel> Panels=new ArrayList<>();


    private void init(Color bg){
        for (Article article : Articles) {
            Panels.add(new ArticleMorePanel(article, Theme.titlesTextColor,Theme.titlesFont,Theme.titlesFont,Theme.textFont,bg));
        }
    }

    public ArticleCardsPanel(Color bg){
       init(bg);
       setBackground(bg);
       setLayout(cardLayout);
       setBorder(new EmptyBorder(50,100,50,100));
       for (ArticleMorePanel a : Panels){
           add(a);
       }
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

    public List<ArticleMorePanel> getPanels() {
        return Panels;
    }

    public void setPanels(List<ArticleMorePanel> panels) {
        Panels = panels;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }
}
