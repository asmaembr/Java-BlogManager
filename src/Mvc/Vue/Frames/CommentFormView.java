package Mvc.Vue.Frames;

import Mvc.Vue.Panels.CommentPanels.CommentFormPanel;
import Mvc.Vue.Panels.CommentPanels.TableCommentPanel;
import Mvc.Vue.Panels.ConfimButtonsPanel;
import Mvc.Vue.utils.themes.Template;
import Mvc.Vue.utils.themes.Theme;
import Mvc.Vue.Events.Action;
import dao.IDao;
import models.Commentaire;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommentFormView extends JFrame {
    private Container mainContainer;
    private JPanel buttonsPanel;
    private CommentFormPanel formPanel;
    private Action action;
    private IDao<Commentaire,Integer> dao;
    private TableCommentPanel tablePanel;

    private void initPanels(String title, String okTxt) {
        buttonsPanel = new ConfimButtonsPanel(
                okTxt,
                "Cancel",
                Template.icon_save,
                Template.icon_cancel48,
                new Color(62, 144, 62),
                new Font("Optima", Font.BOLD, 18),
                new Color(238, 235, 235));
        formPanel = new CommentFormPanel();
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
            var commentaire = formPanel.formToCommentaire();
            if ( commentaire.getContenu() == null) {
                JOptionPane.showMessageDialog(this, "Id and Contenu are required", "Alert", JOptionPane.ERROR_MESSAGE, Theme.icon_Alert);
            } else {
                if (action.equals(Action.Save)) {
                    commentaire = dao.save(commentaire);
                    JOptionPane.showMessageDialog(this, "commentaire n°" + commentaire.getId() + " successfully added", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);
                    tablePanel.getTablesModel().initCommentaireData(dao.findAll());
                    tablePanel.getTablesModel().fireTableDataChanged();
                    dispose();
                }
                if (action.equals(Action.Update)) {
                    boolean updated = dao.update(commentaire);
                    if (updated) {
                        JOptionPane.showMessageDialog(this, "commentaire n°" + commentaire.getId() + " successfully updated", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);
                        tablePanel.getTablesModel().initCommentaireData(dao.findAll());
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

    public CommentFormView(String title, Action action, IDao<Commentaire,Integer> dao, TableCommentPanel source, Commentaire commentaire) {
        this.action = action;
        this.dao = dao;
        this.tablePanel = source;
        initContainer(title, action.toString());
        if (action.equals(Action.Update)) formPanel.fillCommentaire(commentaire);
        setUndecorated(true);
        setSize(500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public Container getMainContainer() {
        return mainContainer;
    }

    public void setMainContainer(Container mainContainer) {
        this.mainContainer = mainContainer;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }

    public CommentFormPanel getFormPanel() {
        return formPanel;
    }

    public void setFormPanel(CommentFormPanel formPanel) {
        this.formPanel = formPanel;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public IDao<Commentaire, Integer> getDao() {
        return dao;
    }

    public void setDao(IDao<Commentaire, Integer> dao) {
        this.dao = dao;
    }

    public TableCommentPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(TableCommentPanel tablePanel) {
        this.tablePanel = tablePanel;
    }
}
