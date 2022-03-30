package com.epam.jwd.entity;

import java.util.*;

public class Sentence {
    private Map<Integer,SentencePart> parts = new TreeMap<>();

    public Sentence() {
    }

    public Sentence(Map<Integer,SentencePart> parts) {
        this.parts = parts;
    }

    public void addPart(int index, SentencePart part) {
        parts.put(Integer.valueOf(index), part);
    }

    public Map<Integer,SentencePart> getParts() {
        return parts;
    }
}
