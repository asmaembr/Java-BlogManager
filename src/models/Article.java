package models;


import java.util.ArrayList;
import java.util.List;

public class Article  {
    Integer id;
    String titre;
    StatutArticle statut ;
    String contenu;
    Blogueur auteur;
    List<Commentaire> Commentaires ;



    public Article(Integer id,StatutArticle statut ,String contenu,  Blogueur auteur, String titre){
        this.auteur=auteur;
        this.statut=statut;
        this.titre=titre;
        this.id=id;
        this.contenu=contenu;
        this.Commentaires=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", Commentaires=" + Commentaires +
                ", statut=" + statut +
                ", contenu='" + contenu + '\'' +
                ", auteur=" + auteur +
                '}';
    }

    public List<Commentaire> getCommentaires() {
        return Commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        Commentaires = commentaires;
    }

    public StatutArticle getStatut() {
        return statut;
    }

    public void setStatut(StatutArticle statut) {
        this.statut = statut;
    }


    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blogueur getAuteur() {
        return auteur;
    }

    public void setAuteur(Blogueur auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
