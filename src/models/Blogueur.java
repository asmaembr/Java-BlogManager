package models;

import java.util.ArrayList;
import java.util.List;

public class Blogueur extends Utilisateur{
    private Integer id;
    private String nom;
    private String email;
    private TypeBlogueur type;
    private List<Blog> blogs;

    public Blogueur(Integer id,String nom,  String email, TypeBlogueur type,String pass) {
        super(email,pass);
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.type = type;
        this.blogs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Blogueur{" +
                "nom='" + nom + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", blogs=" + blogs +
                ", type=" + type +
                ", motdepasse='" + motdepasse + '\'' +
                '}';
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public TypeBlogueur getType() {
        return type;
    }

    public void setType(TypeBlogueur type) {
        this.type = type;
    }

    void commenter(Article article, Commentaire commentaire) {
        article.getCommentaires().add(commentaire);
    }

    void publierArticle(Article article, Blog blog) {
        if (blog.getProprietaire().equals(this)) {
            blog.getArticles().add(article);
        } else {
            System.out.println("Vous n'avez pas la permission de publier sur ce blog.");
        }
    }


    }



