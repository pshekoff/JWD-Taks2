package com.epam.jwd.comparator;

import com.epam.jwd.entity.SentencePart;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordVowelsProportionComparator implements Comparator<String> {
    private static final String VOWELS = "[AaEeIiOoUuYy]";

    @Override
    public int compare(String o1, String o2) {
        return getVowelsProportion(o1) - getVowelsProportion(o2);
    }

    public int getVowelsProportion(String word) {
        Pattern pattern = Pattern.compile(VOWELS);
        Matcher matcher = pattern.matcher(word);

        int vowelsCount = 0;
        while(matcher.find()) {
            vowelsCount++;
        }

        return 100*vowelsCount/word.length();
    }
}
