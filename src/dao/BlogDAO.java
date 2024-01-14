package dao;


import models.Blog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BlogDAO implements IDao<Blog,Integer> {
    File BlogTable = new File("MyFileBase/Blogs.txt");

    private Blog mapToBlog(String fileLine){
        Blog blog = null;
        if(fileLine.trim().length()!=0) {
            try {
                var st = new StringTokenizer(fileLine, "\\|");
                var id = Integer.parseInt(st.nextToken());
                var titre = st.nextToken();
                var value =st.nextToken();
                var idpropretaire = (value.equals("null") ? null : Integer.parseInt(value));
                var propretaire = new BlogueurDAO().findById(idpropretaire);
                blog = new Blog(id,titre,propretaire);
            } catch (NumberFormatException e) {
                blog = null;
            }
        }
        return blog;
    }
    private String mapToLine(Blog blog){

        return    (blog.getId())    + "|"
                + (blog.getTitre()) + "|"
        +(blog.getProprietaire()== null ? "null" : blog.getProprietaire().getId())
                + "\n";
    }
    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = new ArrayList<>();

        try {
            var lines = Files.readAllLines(BlogTable.toPath());
            lines.removeFirst();
            blogs = lines.stream()
                    .map(line -> mapToBlog(line))
                    .filter(blog -> blog!=null)
                    .collect(Collectors.toList());// List<Blog>
        }
        catch (IOException e){
            //  e.printStackTrace();
        }

        return blogs;
    }

    @Override
    public Blog findById(Integer identifiant) {
        return findAll()
                .stream()
                .filter(blog-> blog.getId() == identifiant)
                .findFirst()
                .orElse(null);
    }



    @Override
    public Blog save(Blog newElement) {
        try {
            var maxId =  findAll()
                    .stream()
                    .mapToInt(Blog::getId)
                    .max().orElse(0);
            newElement.setId(++maxId);
            Files.writeString(BlogTable.toPath(),
                    mapToLine(newElement),
                    StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return newElement;
    }

    @Override
    public List<Blog> saveAll(Blog... elements) {
        return new ArrayList<>(Arrays.asList(elements)).stream()
                .map(blog->save(blog)).collect(Collectors.toList());
    }
    @Override
    public boolean update(Blog newValuesElement) {
        try {
            var lines = Files.readAllLines(BlogTable.toPath());
            var id = newValuesElement.getId();
            var lineToUpdate =
                    lines.stream().filter(line -> {
                        var lineTab = line.split("\\|");
                        if(lineTab[0].trim().equals(id+"")) return true;
                        return false;
                    }).findFirst().orElse("");
            var position = lines.indexOf(lineToUpdate);
            lines.set(position,mapToLine(newValuesElement));
            BlogTable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            newContent = newContent.replace("\n\n", "\n");
            Files.writeString( BlogTable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e){
            // e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Blog element) {
        return deleteById(element.getId());
    }


    @Override
    public boolean deleteById(Integer identifiant) {
        boolean removed = false;
        try {
            var lines = Files.readAllLines(BlogTable.toPath());
            removed = lines.removeIf(line -> {
                var lineTab = line.split("\\|");
                if(lineTab[0].trim().equals(identifiant+""))
                    return true;
                else return false;
            });
            BlogTable.delete();
            var newContent = String.join(System.lineSeparator(), lines);
            newContent += "\n";
            Files.writeString( BlogTable.toPath(),
                    newContent,
                    StandardOpenOption.CREATE_NEW);
            removed = true;
        } catch (IOException e){
            //e.printStackTrace();
            removed =false; }
        return removed;
    }



    // Autres méthodes pour gérer les utilisateurs, les commentaires, etc.
}

