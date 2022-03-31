package com.epam.jwd.entity;

public class SentencePart {
    private String body;
    private PartType type;

    public SentencePart(String body, PartType type) {
        this.body = body;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PartType getType() {
        return type;
    }

}
