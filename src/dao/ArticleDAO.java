package dao;

import models.Article;
import models.StatutArticle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ArticleDAO implements IDao<Article,Integer>{

    File articleTable = new File("MyFileBase/Articles.txt");


    private Article mapToArticle(String fileLine){
        Article article = null;
        if(fileLine.trim().length()!=0) {
            try {
                var st = new StringTokenizer(fileLine, "\\|");
                var id = Integer.parseInt(st.nextToken());
                var titre = st.nextToken();
                var value = st.nextToken();
                var statut = (value.equals("null") ? null :
                        (value.equals("PRIVE") ? StatutArticle.PRIVE : StatutArticle.PUBLIC));
                var contenu = st.nextToken();
                value = st.nextToken();
                var idauteur = (value.equals("null") ? null : Integer.parseInt(value));
                var auteur = new BlogueurDAO().findById(idauteur);
                var idcategorie =(value.equals("null") ? null : Integer.parseInt(value));
                article = new Article(id,statut,contenu,auteur,titre);
            } catch (NumberFormatException e) {
                article = null;
            }
        }
        return article;
    }
    private String mapToLine(Article article){

        return    (article.getId())    + "|"
                + (article.getTitre()) + "|"
                + (article.getStatut())  + "|"
                + (article.getContenu())+ "|"
                + (article.getAuteur()  == null ? "null" : article.getAuteur().getId() )  + "\n";
    }
    @Override
    public List<Article> findAll() {
        List<Article> articles= new ArrayList<>();

        try {
            var lines = Files.readAllLines(articleTable.toPath());
            lines.removeFirst();
            articles =
                    lines
                            .stream()
                            .map(line -> mapToArticle(line))
                            .filter(article-> article!=null)
                            .collect(Collectors.toList());// List<Article>
        }
        catch (IOException e){
            //  e.printStackTrace();
        }


        return articles;
    }

    @Override
    public Article findById(Integer identifiant) {
        return findAll()
                .stream()
                .filter(article-> article.getId() == identifiant)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Article save(Article newElement) {
        try {
            var maxId =  findAll()
                    .stream()
                    .mapToInt(Article::getId)
                    .max().orElse(0);
            newElement.setId(++maxId);
            Files.writeString(articleTable.toPath(),
                    mapToLine(newElement),
                    StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return newElement;
    }

    @Override
    public List<Article> saveAll(Article... elements) {
        return new ArrayList<>(Arrays.asList(elements)).stream()
                .map(article->save(article)).collect(Collectors.toList());
    }
    @Override
    public boolean update(Article newValuesElement) {
        try {
            var lines = Files.readAllLines(articleTable.toPath());
            var id = newValuesElement.getId();
            var lineToUpdate =
                    lines.stream().filter(line -> {
                        var lineTab = line.split("\\|");
                        if(lineTab[0].trim().equals(id+"")) return true;
                        return false;
                    }).findFirst().orElse("");
            var position = lines.indexOf(lineToUpdate);
            lines.set(position,mapToLine(newValuesElement));
            articleTable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            newContent = newContent.replace("\n\n", "\n");
            Files.writeString(articleTable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e){
            // e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Article element) {
        return deleteById(element.getId());
    }


    @Override
    public boolean deleteById(Integer identifiant) {
        boolean removed = false;
        try {
            var lines = Files.readAllLines(articleTable.toPath());
            removed = lines.removeIf(line -> {
                var lineTab = line.split("\\|");
                if(lineTab[0].trim().equals(identifiant+""))
                    return true;
                else return false;
            });
            articleTable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            Files.writeString( articleTable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            removed = true;
        } catch (IOException e){
            //e.printStackTrace();
            removed =false; }
        return removed;
    }





}
