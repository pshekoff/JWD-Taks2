package com.epam.jwd.parsers;

import com.epam.jwd.entity.PartTypes;
import com.epam.jwd.entity.SentencePart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {
    public static final Logger logger = LogManager.getLogger(SentenceParser.class);

    private static final Pattern WORD = Pattern.compile("[A-Za-z]+([-'][a-z]+)*");
    private static final Pattern PUNCTUATION = Pattern.compile("[\\(\\)\"'\\.,:;!\\?]");
    private static final Pattern SYMBOL = Pattern.compile("[<>=%]+");
    private static final Pattern DIGITS = Pattern.compile("[0-9]+");
    private static final Pattern SPACE = Pattern.compile("\\s+");

    public SentenceParser() {
    };

    public Map<Integer,SentencePart> parseSentence(String sentence) {
        logger.debug("Sentence parsing has been started.");
        Map<Integer,SentencePart> sentenceParts = new TreeMap<>();

        Matcher wordMatcher = WORD.matcher(sentence);
        while(wordMatcher.find()) {
            int startIndex = wordMatcher.start();
            int endIndex = wordMatcher.end();
            sentenceParts.put(startIndex, new SentencePart(sentence.substring(startIndex, endIndex), PartTypes.WORD));
            logger.debug("WORD was found: " + sentence.substring(startIndex, endIndex));
        }

        Matcher punctuationMatcher = PUNCTUATION.matcher(sentence);
        while(punctuationMatcher.find()) {
            int startIndex = punctuationMatcher.start();
            int endIndex = punctuationMatcher.end();
            sentenceParts.put(startIndex, new SentencePart(sentence.substring(startIndex, endIndex), PartTypes.PUNCTUATION_MARK));
            logger.debug("PUNCTUATION_MARK was found: " + sentence.substring(startIndex, endIndex));
        }

        Matcher symbolMatcher = SYMBOL.matcher(sentence);
        while(symbolMatcher.find()) {
            int startIndex = symbolMatcher.start();
            int endIndex = symbolMatcher.end();
            sentenceParts.put(startIndex, new SentencePart(sentence.substring(startIndex, endIndex), PartTypes.SYMBOL));
            logger.debug("SYMBOL was found: " + sentence.substring(startIndex, endIndex));
         }

        Matcher digitsMatcher = DIGITS.matcher(sentence);
        while(digitsMatcher.find()) {
            int startIndex = digitsMatcher.start();
            int endIndex = digitsMatcher.end();
            sentenceParts.put(startIndex, new SentencePart(sentence.substring(startIndex, endIndex), PartTypes.DIGITS));
            logger.debug("DIGITS were found: " + sentence.substring(startIndex, endIndex));
        }

        Matcher spaveMatcher = SPACE.matcher(sentence);
        while(spaveMatcher.find()) {
            int startIndex = spaveMatcher.start();
            int endIndex = spaveMatcher.end();
            sentenceParts.put(startIndex, new SentencePart(sentence.substring(startIndex, endIndex), PartTypes.SPACE));
            logger.debug("SPACE was found: " + sentence.substring(startIndex, endIndex));
        }
        return sentenceParts;
    }
}
