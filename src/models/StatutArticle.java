package models;

public enum StatutArticle {
    PUBLIC,
    PRIVE;

    public static StatutArticle[] getStatutArticles() {
        return StatutArticle.values();
    }

    public static String[] getStringValues() {
        StatutArticle[] statutArticles = StatutArticle.values();
        String[] stringValues = new String[statutArticles.length];

        for (int i = 0; i < statutArticles.length; i++) {
            stringValues[i] = statutArticles[i].toString();
        }

        return stringValues;
    }
}
