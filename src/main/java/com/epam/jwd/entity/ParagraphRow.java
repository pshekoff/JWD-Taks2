package com.epam.jwd.entity;

import java.util.ArrayList;
import java.util.List;

public class ParagraphRow extends Row {
    private List<Sentence> sentences = new ArrayList<>();

    public ParagraphRow() {
    }

    public ParagraphRow(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
