package Mvc.Vue.Frames;

import Mvc.Vue.Events.Session;
import Mvc.Vue.Panels.ArticleDashboardPanels.ArticleFormPanel;
import Mvc.Vue.Panels.ArticleDashboardPanels.TableArticlePanel;
import Mvc.Vue.Panels.ConfimButtonsPanel;
import Mvc.Vue.utils.themes.Template;
import Mvc.Vue.utils.themes.Theme;
import Mvc.Vue.Events.Action;
import dao.IDao;
import models.Article;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ArticleFormView extends JFrame {
    private Container mainContainer;
    private JPanel buttonsPanel;
    private ArticleFormPanel formPanel;
    private Action action;
    private IDao<Article,Integer> dao;
    private TableArticlePanel tablePanel;

    private void initPanels(String title, String okTxt) {
        buttonsPanel = new ConfimButtonsPanel(
                okTxt,
                "Cancel",
                Template.icon_save,
                Template.icon_cancel48,
                new Color(62, 144, 62),
                new Font("Optima", Font.BOLD, 18),
                new Color(238, 235, 235));
        formPanel = new ArticleFormPanel();
        formPanel.setBorder(new EmptyBorder(20, 15, 20, 15));
        ((ConfimButtonsPanel) buttonsPanel)
                .getBtn_cancel()
                .addActionListener(this::cancelAction);
        ((ConfimButtonsPanel) buttonsPanel)
                .getbtn_ok()
                .addActionListener(this::okAction);
    }

    private void initContainer(String title, String okTxt) {
        initPanels(title, okTxt);
        mainContainer = this.getContentPane();
        mainContainer.setBackground(Color.white);
        mainContainer.setLayout(new BorderLayout());
        mainContainer.add(buttonsPanel, BorderLayout.SOUTH);
        mainContainer.add(formPanel, BorderLayout.CENTER);
    }

    public void okAction(ActionEvent e) {

        SwingUtilities.invokeLater(() -> {
            var article = formPanel.formToArticle();
            if (article.getTitre() == null || article.getContenu() == null) {
                JOptionPane.showMessageDialog(this, "Titre and Contenu are required", "Alert", JOptionPane.ERROR_MESSAGE, Theme.icon_Alert);
            } else {
                if (action.equals(Action.Save)) {
                    article = dao.save(article);
                    JOptionPane.showMessageDialog(this, "Article n°" + article.getId() + " successfully added", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);
                    tablePanel.getTablesModel().initArticleData(dao.findAll()
                            .stream()
                            .filter(a->a.getAuteur().getId().equals(Session.getInstance().getBlogueur().getId()))
                            .toList());
                    tablePanel.getTablesModel().fireTableDataChanged();
                    dispose();
                }
                if (action.equals(Action.Update)) {
                    boolean updated = dao.update(article);
                    if (updated) {
                        JOptionPane.showMessageDialog(this, "Article n°" + article.getId() + " successfully updated", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);
                        tablePanel.getTablesModel().initArticleData(dao.findAll()
                                .stream()
                                .filter(a->a.getAuteur().getId().equals(Session.getInstance().getBlogueur().getId()))
                                .toList());
                        tablePanel.getTablesModel().fireTableDataChanged();
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(this, "Problem while updating", "Alert", JOptionPane.ERROR_MESSAGE, Theme.icon_Alert);
                }


            }

        });
    }

    public void cancelAction(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            dispose();
        });
    }

    public ArticleFormView(String title, Action action, IDao<Article,Integer> dao, TableArticlePanel source, Article ArticleValues) {
        this.action = action;
        this.dao = dao;
        this.tablePanel = source;
        initContainer(title, action.toString());
        if (action.equals(Action.Update)) formPanel.fillForm(ArticleValues);
        setUndecorated(true);
        setSize(500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ArticleFormPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(ArticleFormPanel formPanel) {
        this.formPanel = formPanel;
    }

    public TableArticlePanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(TableArticlePanel tablePanel) {
        this.tablePanel = tablePanel;
    }
}
