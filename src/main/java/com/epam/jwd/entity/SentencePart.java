package com.epam.jwd.entity;

public class SentencePart {
    private String body;
    private PartTypes type;

    public SentencePart(String string, PartTypes type) {
        body = string;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public PartTypes getType() {
        return type;
    }

}
