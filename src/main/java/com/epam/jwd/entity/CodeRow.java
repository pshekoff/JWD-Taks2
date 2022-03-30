package com.epam.jwd.entity;

public class CodeRow extends Row {
    private String body;

    public CodeRow(String codeString) {
        body = codeString;
    }

    public String getCodeBody() {
        return body;
    }

}
