package com.epam.jwd.parser;

import com.epam.jwd.entity.PartType;
import com.epam.jwd.entity.SentencePart;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {
    private static final Pattern WORD = Pattern.compile("[A-Za-z]+([-'][a-z]+)*");
    private static final Pattern PUNCTUATION = Pattern.compile("[\\(\\)\"'\\.,:;!\\?]");
    private static final Pattern SYMBOL = Pattern.compile("[<>=%]+");
    private static final Pattern DIGITS = Pattern.compile("[0-9]+");
    private static final Pattern SPACE = Pattern.compile("\\s+");

    public SentenceParser() {}

    public Map<Integer,SentencePart> parseSentence(String sentence) {
        Map<Integer,SentencePart> sentenceParts = new TreeMap<>();

        parse(WORD, sentence, sentenceParts, PartType.WORD);
        parse(PUNCTUATION, sentence, sentenceParts, PartType.PUNCTUATION_MARK);
        parse(SYMBOL, sentence, sentenceParts, PartType.SYMBOL);
        parse(DIGITS, sentence, sentenceParts, PartType.DIGITS);
        parse(SPACE, sentence, sentenceParts, PartType.SPACE);

        return sentenceParts;
    }

    private void parse(Pattern pattern, String sentence, Map<Integer, SentencePart> sentenceParts, PartType type) {
        Matcher matcher = pattern.matcher(sentence);
        while(matcher.find()) {
            int startIndex = matcher.start();
            sentenceParts.put(startIndex, new SentencePart(matcher.group(), type));
        }
    }
}
