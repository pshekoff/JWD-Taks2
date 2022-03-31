package com.epam.jwd.main;

import com.epam.jwd.logic.FileLogic;
import com.epam.jwd.logic.TextLogic;
import com.epam.jwd.parser.TextParser;
import com.epam.jwd.entity.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private final static String SOURCE_FILE_PATH = "src/main/resources/source_text.txt";
    private final static String RESTORED_FILE_PATH = "src/main/resources/restored_text.txt";
    private final static String TASK_7_FILE_PATH = "src/main/resources/task_7_text.txt";
    private final static String TASK_12_FILE_PATH = "src/main/resources/task_12_text.txt";
    private final static String TASK_15_FILE_PATH = "src/main/resources/task_15_text.txt";

    public static void main(String[] args) {
        logger.info(SOURCE_FILE_PATH + " rows scanning...");
        List<String> FileStringList = FileLogic.fileReader(SOURCE_FILE_PATH);

        TextParser textParser = new TextParser(FileStringList);
        Text textObject = textParser.parseText();
        logger.info("Text object has been created.");

        FileLogic.fileWriter(RESTORED_FILE_PATH, textObject);
        logger.info("Text object was saved to file " + RESTORED_FILE_PATH);

        TextLogic textOperationsTask7 = new TextLogic(textObject);
        FileLogic.fileWriter(TASK_7_FILE_PATH, textOperationsTask7.task7());

        TextLogic textOperationsTask12 = new TextLogic(textParser.parseText());
        textOperationsTask12.task12(5);
        FileLogic.fileWriter(TASK_12_FILE_PATH, textOperationsTask12.getText());

        TextLogic textOperationsTask15 = new TextLogic(textParser.parseText());
        textOperationsTask15.task15();
        FileLogic.fileWriter(TASK_15_FILE_PATH, textOperationsTask15.getText());
    }
}
