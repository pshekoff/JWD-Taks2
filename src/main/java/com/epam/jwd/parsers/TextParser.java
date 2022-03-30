package com.epam.jwd.parsers;

import com.epam.jwd.entity.BlankRow;
import com.epam.jwd.entity.CodeRow;
import com.epam.jwd.entity.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextParser {
    public static final Logger logger = LogManager.getLogger(TextParser.class);

    private static final String TITLE_ROW = "([0-9]+\\.)+(\\s*)[A-Z][^\\.!\\?:]+?";
    private static final String PARAGRAPH_ROW = "(\\s*)[A-Z].+?[\\.!\\?:]";
    private static final String CODE_ROW = "(\\s*)[(A-Z)(a-z)}/].*?";

    private List<String> rows = new ArrayList<>();

    public TextParser(List<String> fileRows) {
        rows = fileRows;
    }

    public Text parseText() {
        logger.info("File rows parsing has been started.");
        Text text = new Text();
        RowParser rowParser = new RowParser();

        for(String row : rows) {
             if(Pattern.matches(TITLE_ROW, row)) {
                logger.debug("TITLE_ROW was found:\n" + row);
                text.addRow(rowParser.parseTitle(row));
            }
            else if (Pattern.matches(PARAGRAPH_ROW, row)) {
                logger.debug("PARAGRAPH_ROW was found:\n" + row);
                text.addRow(rowParser.parseParagraph(row));
            }
            else if (Pattern.matches(CODE_ROW, row)) {
                logger.debug("CODE_ROW was found:\n" + row);
                text.addRow(new CodeRow(row));
            }
            else {
                logger.debug("BLANK_ROW was found.");
                text.addRow(new BlankRow());
             }
        }
        return text;
    }
}
