package models;

import java.util.ArrayList;
import java.util.List;

public class Commentaire {
    Blogueur auteur;
    Integer id;
    String contenu;

    public Commentaire(Blogueur auteur, Integer id, String contenu){
        this.auteur=auteur;
        this.contenu=contenu;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "auteur=" + auteur +
                ", id=" + id +
                ", contenu='" + contenu + '\'' +
                '}';
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }


}
