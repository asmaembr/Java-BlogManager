package Mvc.Vue.Panels.ArticleDashboardPanels;

import Mvc.Vue.Frames.ArticleFormView;
import Mvc.Vue.utils.jtableUtils.JTableUtilities;
import Mvc.Vue.utils.jtableUtils.MyTablesModel;
import Mvc.Vue.utils.themes.Theme;
import dao.ArticleDAO;
import dao.IDao;
import Mvc.Vue.Events.Action;
import models.Article;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableArticlePanel extends JPanel {
    JTable table;
    MyTablesModel tablesModel;
    JScrollPane scrollPane;
    CRUDArticlePanel crudPanel;
    IDao<Article,Integer> dao;

    private Color panelColor = new Color(238, 235, 235),
            textColor = new Color(62, 144, 62);
    private Font buttonsFont = new Font("Optima", Font.BOLD, 19);

     void deleteAction(ActionEvent e){

        int selectedLine = table.getSelectedRow();
        if(selectedLine >= 0) {
            int id = (Integer)table.getValueAt(selectedLine, 0);
            int result = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to remove this Objet n° "+id+"?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            Theme.icon_question
                                                    );

            if (result == JOptionPane.YES_OPTION) {
                dao.deleteById(id);
                tablesModel.initArticleData(dao.findAll());
                tablesModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(null, "Article n°"+id+" successfully removed", "Info", JOptionPane.INFORMATION_MESSAGE, Theme.icon_info);

                                                    }
            }
        else
            JOptionPane.showMessageDialog(null, "Please Select the line to remove first !!", "Alert", JOptionPane.ERROR_MESSAGE, Theme.icon_Alert);


            crudPanel.getBtn_delete().setFocusable(false);
     }
     void updateAction(ActionEvent e){
         SwingUtilities.invokeLater(()->{
         int selectedLine = table.getSelectedRow();
         if(selectedLine >= 0) {
             int id = (Integer) table.getValueAt(selectedLine, 0);
             var article = dao.findById(id);
             var form = new ArticleFormView("Edit Article Form", Action.Update, (ArticleDAO)dao, this, article);
              }
         else
             JOptionPane.showMessageDialog(this, "Please Select the line to edit first !!", "Alert", JOptionPane.ERROR_MESSAGE, Theme.icon_Alert);
         });

     }
     void searchAction(ActionEvent e){

       String text =  crudPanel.getTxt_search().getText();
         if(text.trim().length()==0)
         {
             tablesModel.initArticleData(dao.findAll());
         }
         else {
             List<Article> articles = dao.findAll();
             var newList =
                     articles.stream()
                             .filter(article -> {
                                 return
                                         article.getId().toString().equals(text)  ||
                                                 article.getTitre().equals(text) ||
                                                 article.getContenu().equalsIgnoreCase(text)||
                                                article.getStatut().toString().equalsIgnoreCase(text);

                             })
                             .collect(Collectors.toList());

             tablesModel.initArticleData(newList);
         }

                 tablesModel.fireTableDataChanged();
                 crudPanel.getBtn_search().setFocusable(false);

     }
     void addAction(ActionEvent e){
         SwingUtilities.invokeLater(()->{
             var form = new ArticleFormView("Add Article Form", Action.Save, (ArticleDAO)dao, this, null);
         });
    }
    void initComponents(){

        tablesModel = new MyTablesModel();
        tablesModel.initColumns("ID", "Titre", "Contenu", "Statut","Nom Auteur");
        tablesModel.initArticleData(new ArticleDAO().findAll());

        table = new JTable(tablesModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setForeground(new Color(105, 59, 105));
        table.setFont(new Font("Optima", Font.PLAIN, 19));
        table.setRowHeight(50);
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        //table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        table.setBackground(panelColor);
        //table.setAutoCreateRowSorter(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        // trier les lignes du JTable par deuxième colonne
        List<RowSorter.SortKey> sortList = new ArrayList<>();
        sortList.add(new RowSorter.SortKey(1,SortOrder.ASCENDING));
        sorter.setSortKeys(sortList);
        table.setRowSorter(sorter);

        JTableHeader header = table.getTableHeader();
        header.setForeground(new Color(49, 83, 168));
        header.setFont(new Font("Optima", Font.BOLD, 25));
        header.setBackground(Color.WHITE);
        //header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        header.setPreferredSize(new Dimension(0, 55));

        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(panelColor);


        crudPanel =  new CRUDArticlePanel
                ("Add", "  Update","Delete","",
                        Theme.icon_add,Theme.icon_update, Theme.icon_delete, Theme.icon_search,
                        Theme.icon_addH,Theme.icon_updateH, Theme.icon_deleteH, Theme.icon_searchH,
                        textColor, buttonsFont,  panelColor);

        crudPanel.getTxt_search().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {}
            @Override
            public void removeUpdate(DocumentEvent e) {
                tablesModel.initArticleData(dao.findAll());
                tablesModel.fireTableDataChanged();
                insertUpdate(e);}
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = crudPanel.getTxt_search().getText();
                if(str.trim().length()==0) sorter.setRowFilter(null);
                else sorter.setRowFilter(RowFilter.regexFilter("(?i)"+str));
            }
        });
        crudPanel.getBtn_search().addActionListener(this::searchAction);
        crudPanel.getBtn_delete().addActionListener(this::deleteAction);
        crudPanel.getBtn_update().addActionListener(this::updateAction);
        crudPanel.getBtn_newElement().addActionListener(this::addAction);
    }

    public TableArticlePanel(IDao<Article,Integer> dao){
         this.dao = dao;
        initComponents();
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(crudPanel, BorderLayout.SOUTH);
    }


    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public MyTablesModel getTablesModel() {
        return tablesModel;
    }

    public void setTablesModel(MyTablesModel tablesModel) {
        this.tablesModel = tablesModel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public CRUDArticlePanel getCrudPanel() {
        return crudPanel;
    }

    public void setCrudPanel(CRUDArticlePanel crudPanel) {
        this.crudPanel = crudPanel;
    }
}



