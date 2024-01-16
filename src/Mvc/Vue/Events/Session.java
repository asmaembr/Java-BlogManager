package Mvc.Vue.Events;

import models.Blogueur;

public class Session {

    private static Session instance;
    private Blogueur blogueur;
    // Private constructor to prevent instantiation
    private Session(Blogueur b) {
      this.blogueur= b;
    }

    // Public method to get the instance of Session
    public static synchronized Session getInstance(Blogueur b) {
        if (instance == null) {
            instance = new Session(b);
        }
        return instance;
    }
     public static Session getInstance(){
        return instance;
      }


    public Blogueur getBlogueur() {
        return blogueur;
    }

    public void setBlogueur(Blogueur blogueur) {
        this.blogueur = blogueur;
    }

    @Override
    public String toString() {
        return "Session{" +
                "blogueur=" + blogueur +
                '}';
    }
}
