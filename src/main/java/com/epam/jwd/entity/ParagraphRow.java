package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;

public class ParagraphRow implements Row {
    private static final String SENTENCE_SEPARATOR = " ";

    private List<Sentence> sentences = new ArrayList<>();

    public ParagraphRow(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String getBody() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Sentence sentence : sentences) {
            stringBuilder.append(sentence.getBody()).append(SENTENCE_SEPARATOR);
        }
        return stringBuilder.toString().trim();
    }
}
