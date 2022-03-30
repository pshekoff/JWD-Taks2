package com.epam.jwd.parsers;

import com.epam.jwd.entity.ParagraphRow;
import com.epam.jwd.entity.Sentence;
import com.epam.jwd.entity.SentencePart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RowParser {
    public static final Logger logger = LogManager.getLogger(RowParser.class);

    private static final Pattern SENTANCE = Pattern.compile("[A-Z0-9].+?[\\.!\\?:]\\.?");

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
        Matcher sentenceMatcher = SENTANCE.matcher(paragraph);
        while(sentenceMatcher.find()) {
            int startIndex = sentenceMatcher.start();
            int endIndex = sentenceMatcher.end();
            logger.debug("SENTENCE was found:\n" + paragraph.substring(startIndex,endIndex));
            Map<Integer,SentencePart> parts = sentenceParser.parseSentence(paragraph.substring(startIndex,endIndex));
            sentences.add(new Sentence(parts));
        }
        return new ParagraphRow(sentences);
    }
}
