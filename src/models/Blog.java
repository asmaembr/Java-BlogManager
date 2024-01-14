package models;

import java.util.ArrayList;
import java.util.List;

public class Blog {
    Integer id ;
    String titre;
    Blogueur proprietaire ;
    List<Article> articles;

    public Blog(Integer id, String titre, Blogueur proprietaire){
        this.id=id;
        this.titre=titre;
        this.proprietaire=proprietaire;
        this.articles= new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Blog{" +
                "articles=" + articles +
                ", id=" + id +
                ", titre='" + titre + '\'' +
                ", proprietaire=" + proprietaire +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Blogueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Blogueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
