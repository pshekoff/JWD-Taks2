package com.epam.jwd.comparator;

import com.epam.jwd.entity.SentencePart;

import java.util.Comparator;
import java.util.regex.Pattern;

public class WordVowelsProportionComparator implements Comparator<SentencePart> {

    @Override
    public int compare(SentencePart o1, SentencePart o2) {
        return getVowelsProportion(o1) - getVowelsProportion(o2);
    }

    public int getVowelsProportion(SentencePart word) {
        char[] charArray = word.getBody().toCharArray();

        int vowelsCount = 0;
        for(char character : charArray) {
            if("AaEeIiOoUuYy".indexOf(character) != -1) {
                vowelsCount++;
            }
        }

        return 1000*vowelsCount/word.getBody().length();
    }
}
