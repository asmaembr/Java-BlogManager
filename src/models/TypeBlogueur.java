package models;

public enum TypeBlogueur {
    AUTEUR,
    VISITEUR;
    public static TypeBlogueur[] getTypeBlogueur() {
        return TypeBlogueur.values();
    }

    public static String[] getStringValues() {
        TypeBlogueur[] TypeBlogueurs = TypeBlogueur.values();
        String[] stringValues = new String[TypeBlogueurs.length];

        for (int i = 0; i < TypeBlogueurs.length; i++) {
            stringValues[i] = TypeBlogueurs[i].toString();
        }

        return stringValues;
    }

}
