package com.epam.jwd.entity;

public class CodeRow implements Row {
    private String body;

    public CodeRow(String codeString) {
        body = codeString;
    }

    @Override
    public String getBody() {
        return body;
    }
}
