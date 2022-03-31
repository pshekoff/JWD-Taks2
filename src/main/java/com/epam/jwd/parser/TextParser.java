package com.epam.jwd.parser;

import com.epam.jwd.entity.BlankRow;
import com.epam.jwd.entity.CodeRow;
import com.epam.jwd.entity.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextParser {
    private static final String TITLE_ROW = "([0-9]+\\.)+(\\s*)[A-Z][^\\.!\\?:]+?";
    private static final String PARAGRAPH_ROW = "(\\s*)[A-Z].+?[\\.!\\?:]";
    private static final String CODE_ROW = "(\\s*)[(A-Z)(a-z)}/].*?";

    private List<String> rows = new ArrayList<>();

    public TextParser(List<String> fileRows) {
        rows = fileRows;
    }

    public Text parseText() {
        Text text = new Text();
        RowParser rowParser = new RowParser();

        for(String row : rows) {
             if(Pattern.matches(TITLE_ROW, row)) {
                text.addRow(rowParser.parseTitle(row));
            }
            else if (Pattern.matches(PARAGRAPH_ROW, row)) {
                text.addRow(rowParser.parseParagraph(row));
            }
            else if (Pattern.matches(CODE_ROW, row)) {
                text.addRow(new CodeRow(row));
            }
            else {
                text.addRow(new BlankRow());
             }
        }
        return text;
    }
}
