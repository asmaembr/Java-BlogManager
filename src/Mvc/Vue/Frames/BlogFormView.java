package Mvc.Vue.Frames;


import Mvc.Vue.Panels.BlogDashboardPanels.BlogFormPanel;
import Mvc.Vue.Panels.BlogDashboardPanels.TableBlogPanel;

import Mvc.Vue.Panels.ConfimButtonsPanel;
import Mvc.Vue.utils.themes.Template;
import Mvc.Vue.utils.themes.Theme;
import dao.IDao;
import Mvc.Vue.Events.Action;
import models.Blog;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BlogFormView extends JFrame {
    private Container mainContainer;
    private JPanel buttonsPanel;
    private BlogFormPanel formPanel;
    private Action action;
    private IDao<Blog,Integer> dao;
    private TableBlogPanel tablePanel;

    private void initPanels(String title, String okTxt) {
        buttonsPanel = new ConfimButtonsPanel(
                okTxt,
                "Cancel",
                Template.icon_save,
                Template.icon_cancel48,
                new Color(62, 144, 62),
                new Font("Optima", Font.BOLD, 18),
                new Color(238, 235, 235));
        formPanel = new BlogFormPanel();
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
            var blog = formPanel.formToBlog();
            if (blog.getTitre() == null) {
                JOptionPane.showMessageDialog(this, "Titre is required", "Alert", JOptionPane.ERROR_MESSAGE, Theme.icon_Alert);
            } else {
                if (action.equals(Action.Save)) {
                    blog = dao.save(blog);
                    JOptionPane.showMessageDialog(this, "Blog n°" + blog.getId().toString() + " successfully added", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);
                    tablePanel.getTablesModel().initBlogData(dao.findAll());
                    tablePanel.getTablesModel().fireTableDataChanged();
                    dispose();
                }
                if (action.equals(Action.Update)) {
                    boolean updated = dao.update(blog);
                    if (updated) {
                        JOptionPane.showMessageDialog(this, "Blog n°" + blog.getId() + " successfully updated", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);
                        tablePanel.getTablesModel().initBlogData(dao.findAll());
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

    public BlogFormView(String title, Action action, IDao<Blog,Integer> dao, TableBlogPanel source, Blog blog) {
        this.action = action;
        this.dao = dao;
        this.tablePanel = source;
        initContainer(title, action.toString());
        if (action.equals(Action.Update)) formPanel.fillForm(blog);
        setUndecorated(true);
        setSize(500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public BlogFormPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(BlogFormPanel formPanel) {
        this.formPanel = formPanel;
    }

    public TableBlogPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(TableBlogPanel tablePanel) {
        this.tablePanel = tablePanel;
    }
}

