package com.api.crud.tools;
import java.text.Normalizer;
public class Tool {
    public static String nameToSlug(String name){
        String slug = name;
        slug = removeTildes(slug);
        slug = slug.replaceAll("[^a-zA-Z\\s]", "");
        slug = slug.replaceAll(" ","-");
        slug = slug.toLowerCase();
        return slug;
    }

    private static String removeTildes(String textWithTilde) {
        String normalizedText = Normalizer.normalize(textWithTilde, Normalizer.Form.NFD);
        return normalizedText.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}