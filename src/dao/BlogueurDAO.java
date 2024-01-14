package dao;

import models.Blogueur;
import models.TypeBlogueur;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BlogueurDAO implements IDao<Blogueur,Integer> {
    File blogeurtable = new File("MyFileBase/Blogueurs.txt");



    private Blogueur mapToBlogueur(String fileLine){
        Blogueur blogueur = null;
        if(!fileLine.trim().isEmpty()) {
            try {
                var st = new StringTokenizer(fileLine, "\\|");
                var id = Integer.parseInt(st.nextToken());
                var nom = st.nextToken();
                var email = st.nextToken();
                var value = st.nextToken();
                var type = (value.equals("null") ? null :
                        (value.equals("AUTEUR") ? TypeBlogueur.AUTEUR : TypeBlogueur.VISITEUR));
                var pass = st.nextToken();
                blogueur = new Blogueur(id, nom, email, type,pass);
            } catch (NumberFormatException e) {
                blogueur = null;
            }
        }
        return blogueur;
    }
    private String mapToLine(Blogueur blogueur){

        return    (blogueur.getId())    + "|"
                + (blogueur.getNom()) + "|"
                + (blogueur.getEmail())  + "|"
                + (blogueur.getType()      == null ? "null" : blogueur.getType().toString().charAt(0))  + "|"
                + (blogueur.getMotdepasse()) +"\n";
    }

    @Override
    public List<Blogueur> findAll() {
        List<Blogueur> blogueurs = new ArrayList<>();

        try {
            var lines = Files.readAllLines(blogeurtable.toPath());
            lines.removeFirst();
            blogueurs = lines .stream()
                            .map(line -> mapToBlogueur(line))
                            .filter(blogueur -> blogueur!=null)
                            .collect(Collectors.toList());// List<Blogeur>
        }
        catch (IOException e){
             e.printStackTrace();
        }
        return blogueurs;
    }


    @Override
    public Blogueur findById(Integer identifiant) {
        return findAll()
                .stream()
                .filter(blogueur-> blogueur.getId() == identifiant)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Blogueur save(Blogueur newElement) {
        try {
            var maxId =  findAll()
                    .stream()
                    .mapToInt(Blogueur::getId)
                    .max().orElse(0);
            newElement.setId(++maxId);
            Files.writeString( blogeurtable.toPath(),
                    mapToLine(newElement),
                    StandardOpenOption.APPEND);
            System.out.println("Blogueur added successfully ^_^");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return newElement;
    }

    @Override
    public List<Blogueur> saveAll(Blogueur... elements) {
        return new ArrayList<>(Arrays.asList(elements)).stream()
                .map(blogueur->save(blogueur)).collect(Collectors.toList());
    }

    @Override
    public boolean update(Blogueur newValuesElement) {
        try{
            var lines = Files.readAllLines(blogeurtable.toPath());
            var id = newValuesElement.getId();
            var lineToUpdate =
                    lines.stream().filter(line -> {
                        var lineTab = line.split("\\|");
                        if(lineTab[0].trim().equals(id+"")) return true;
                        return false;
                    }).findFirst().orElse("");
            var position = lines.indexOf(lineToUpdate);
            lines.set(position,mapToLine(newValuesElement));
            blogeurtable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            newContent = newContent.replace("\n\n", "\n");
            Files.writeString( blogeurtable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Blogueur element) {

        return deleteById(element.getId());

    }

    @Override
    public boolean deleteById(Integer identifiant) {
        boolean removed = false;
        try {
            var lines = Files.readAllLines(blogeurtable.toPath());
            removed = lines.removeIf(line -> {
                var lineTab = line.split("\\|");
                if(lineTab[0].trim().equals(identifiant+""))
                    return true;
                else return false;
            });
            blogeurtable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            Files.writeString( blogeurtable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            removed = true;
        } catch (IOException e){
            //e.printStackTrace();
            removed =false; }
        return removed;
    }


    public Blogueur findByLoginAndPass(String login, String pass) {
        return findAll().stream()
                .filter(blogueur -> blogueur.getEmail().equals(login) && blogueur.getMotdepasse().equals(pass))
                .findFirst().orElse(null);
    }




}
