package Mvc.Vue.utils.jtableUtils;

import dao.BlogueurDAO;
import models.Article;
import models.Blog;
import models.Commentaire;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTablesModel extends AbstractTableModel {

    private String[] columnNames;
    private Object[][] data;
    public MyTablesModel(){
       // initColumns("ID", "Login", "Password", "FirstName", "LastName", "Age", "Sexe");
    }

    public void initColumns(String... columns){
        columnNames = columns;
    }


    public void initBlogData(List<Blog> Blogs){

        data = new Object[Blogs.size()][columnNames.length];

        int i = 0;
        for(Blog blog : Blogs){
            data[i][0] = blog.getId();
            data[i][1] = blog.getTitre();
           if(blog.getProprietaire()==null)data[i][2] = "----";
           else data[i][2] = new BlogueurDAO().findById(blog.getProprietaire().getId()).getNom();
           i++;
        }
    }

    public void initArticleData(List<Article> articles){

        data = new Object[articles.size()][columnNames.length];

        int i = 0;
        for(Article article : articles){
            data[i][0] = article.getId();
            data[i][1] = article.getTitre();
            data[i][2] = article.getContenu();
            data[i][3] = article.getStatut();
            if(article.getAuteur()==null) data[i][4] = "----" ;
            else data[i][4] = new BlogueurDAO().findById(article.getAuteur().getId()).getNom();
            i++;
        }
    }


    public void initCommentaireData(List<Commentaire> Commentaires){

        data = new Object[Commentaires.size()][columnNames.length];

        int i = 0;
        for(Commentaire commentaire : Commentaires){
            data[i][0] = commentaire.getId();
            data[i][1] = commentaire.getContenu();
            i++;
        }
    }



    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
}
