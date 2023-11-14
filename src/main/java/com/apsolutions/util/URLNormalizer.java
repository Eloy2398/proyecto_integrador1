package com.apsolutions.util;

import java.text.Normalizer;
import java.util.Locale;

public class URLNormalizer {

    public static String encode(String input) {
        String normalizedString = input.replaceAll("\\s+", "-");

        normalizedString = Normalizer.normalize(normalizedString, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}-]+", "");

        normalizedString = normalizedString.toLowerCase(Locale.ENGLISH);

        return normalizedString;
    }
}
