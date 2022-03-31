package com.epam.jwd.entity;

import java.util.Map;
import java.util.TreeMap;

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

    public void setParts(Map<Integer, SentencePart> parts) {
        this.parts = parts;
    }

    public String getBody() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer,SentencePart> entry : parts.entrySet()) {
            stringBuilder.append(entry.getValue().getBody());
        }
        return stringBuilder.toString();
    }
}
