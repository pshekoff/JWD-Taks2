package com.epam.jwd.parser;

import com.epam.jwd.entity.ParagraphRow;
import com.epam.jwd.entity.Sentence;
import com.epam.jwd.entity.SentencePart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowParser {
    private static final Pattern SENTENCE = Pattern.compile("[A-Z0-9].+?[\\.!\\?:]\\.?");

    public RowParser() {};

    public ParagraphRow parseTitle(String title) {
        List<Sentence> sentences = new ArrayList<>();

        SentenceParser sentenceParser = new SentenceParser();
        Map<Integer,SentencePart> parts = sentenceParser.parseSentence(title);
        sentences.add(new Sentence(parts));

        return new ParagraphRow(sentences);
    }

    public ParagraphRow parseParagraph(String paragraph) {
        List<Sentence> sentences = new ArrayList<>();

        SentenceParser sentenceParser = new SentenceParser();

        Matcher sentenceMatcher = SENTENCE.matcher(paragraph);
        while(sentenceMatcher.find()) {
            Map<Integer,SentencePart> parts = sentenceParser.parseSentence(sentenceMatcher.group());
            sentences.add(new Sentence(parts));
        }
        return new ParagraphRow(sentences);
    }
}
