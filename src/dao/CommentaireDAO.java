package dao;

import models.Blogueur;
import models.Commentaire;
import models.TypeBlogueur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CommentaireDAO implements IDao<Commentaire,Integer> {
    File commentaireTable = new File("MyFileBase/Commentaires.txt");



    private Commentaire mapToCommentaire(String fileLine){
        Commentaire commentaire = null;
        if(!fileLine.trim().isEmpty()) {
            try {
                var st = new StringTokenizer(fileLine, "\\|");
                var id = Integer.parseInt(st.nextToken());
                var contenu = st.nextToken();
                var value = st.nextToken();
                var idauteur = (value.equals("null") ? null : Integer.parseInt(value));
                var auteur = new BlogueurDAO().findById(idauteur);
                commentaire = new Commentaire(auteur,id,contenu);
            } catch (NumberFormatException e) {
                commentaire = null;
            }
        }
        return commentaire;
    }
    private String mapToLine(Commentaire commentaire){

        return    (commentaire.getId())    + "|"
                + (commentaire.getContenu()) + "|"
                + (commentaire.getAuteur()   == null ? "null" : commentaire.getAuteur().getId())+ "\n";
    }

    @Override
    public List<Commentaire> findAll() {
        List<Commentaire> commentaires = new ArrayList<>();

        try {
            var lines = Files.readAllLines(commentaireTable.toPath());
            lines.removeFirst();
            commentaires = lines .stream()
                    .map(line -> mapToCommentaire(line))
                    .filter(commentaire -> commentaire!=null)
                    .collect(Collectors.toList());// List<Commentaire>
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return commentaires;
    }


    @Override
    public Commentaire findById(Integer identifiant) {
        return findAll()
                .stream()
                .filter(commentaire-> commentaire.getId() == identifiant)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Commentaire save(Commentaire newElement) {
        try {
            var maxId =  findAll()
                    .stream()
                    .mapToInt(Commentaire::getId)
                    .max().orElse(0);
            newElement.setId(++maxId);
            Files.writeString( commentaireTable.toPath(),
                    mapToLine(newElement),
                    StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return newElement;
    }

    @Override
    public List<Commentaire> saveAll(Commentaire... elements) {
        return new ArrayList<>(Arrays.asList(elements)).stream()
                .map(commentaire->save(commentaire)).collect(Collectors.toList());
    }

    @Override
    public boolean update(Commentaire newValuesElement) {
        try{
            var lines = Files.readAllLines(commentaireTable.toPath());
            var id = newValuesElement.getId();
            var lineToUpdate =
                    lines.stream().filter(line -> {
                        var lineTab = line.split("\\|");
                        if(lineTab[0].trim().equals(id+"")) return true;
                        return false;
                    }).findFirst().orElse("");
            var position = lines.indexOf(lineToUpdate);
            lines.set(position,mapToLine(newValuesElement));
            commentaireTable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            newContent = newContent.replace("\n\n", "\n");
            Files.writeString( commentaireTable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Commentaire element) {

        return deleteById(element.getId());

    }

    @Override
    public boolean deleteById(Integer identifiant) {
        boolean removed = false;
        try {
            var lines = Files.readAllLines(commentaireTable.toPath());
            removed = lines.removeIf(line -> {
                var lineTab = line.split("\\|");
                if(lineTab[0].trim().equals(identifiant+""))
                    return true;
                else return false;
            });
            commentaireTable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            Files.writeString( commentaireTable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            removed = true;
        } catch (IOException e){
            //e.printStackTrace();
            removed =false; }
        return removed;
    }


}
