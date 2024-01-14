package Mvc.Vue.Events;

import Mvc.Vue.Frames.*;
import dao.BlogueurDAO;
import models.Blogueur;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Events {
    public JFrame source;


    public void login (ActionEvent e,String emailo,String mdp){
        SwingUtilities.invokeLater(() -> {
            Blogueur blo = new BlogueurDAO().findByLoginAndPass(emailo,mdp);
            if(blo==null)
            {JOptionPane.showMessageDialog(null, "Donnez des infos valides");}
            else {
                source.dispose();
                new BlogDashboardView();
            }

        });
    }
    public  void loginCancel(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    public  void BlogDasboard(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            source.dispose();
            new BlogDashboardView();
        });

    }


    public  void ArticleView(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            source.dispose();
            new ArticleView();
        });

    }

    public void ArticleDashboardView(ActionEvent e ){
        SwingUtilities.invokeLater(() -> {
            source.dispose();
            new ArticleDasboardView();
        });
    }

    public  void CommentDashboard(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            source.dispose();
            new CommentDashboardView();
        });

    }



    public Events(){}
    public Events(JFrame source){this.source = source;}

    public JFrame getSource() {
        return source;
    }

    public void setSource(JFrame source) {
        this.source = source;
    }

}
