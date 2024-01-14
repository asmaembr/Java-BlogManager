package models;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    String nomutilisateur;
    String motdepasse;

    public Utilisateur(String nomutilisateur, String motdepasse){
        this.motdepasse=motdepasse;
        this.nomutilisateur=nomutilisateur;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nomutilisateur='" + nomutilisateur + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }

    public String getNom() {
        return nomutilisateur;
    }

    public void setNom(String nom) {
        this.nomutilisateur = nomutilisateur;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }


    }
