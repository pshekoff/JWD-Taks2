package com.epam.jwd.main;

import com.epam.jwd.parsers.TextParser;
import com.epam.jwd.entity.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public final static String SOURCE_FILE_PATH = "src/main/resources/source_text.txt";
    public final static String RESTORED_FILE_PATH = "src/main/resources/restored_text.txt";

    public static void main(String[] args) {
        logger.info(SOURCE_FILE_PATH + " rows scanning...");
        List<String> FileStringList = FileOperations.getAllFileRows(SOURCE_FILE_PATH);

        TextParser textParser = new TextParser(FileStringList);
        Text textObject = textParser.parseText();
        logger.info("Text object has been created.");

        FileOperations.text2File(RESTORED_FILE_PATH, textObject);
        logger.info("Text object was saved to file " + RESTORED_FILE_PATH);

        TextOperations textOperationsTask7 = new TextOperations(textObject);
        textOperationsTask7.task7();

        TextOperations textOperationsTask12 = new TextOperations(textParser.parseText());
        textOperationsTask12.task12(5);
        FileOperations.text2File("src/main/resources/task_12_text.txt", textOperationsTask12.getText());
        logger.info("Edited text object was saved to file src/main/resources/task_12_text.txt");

        TextOperations textOperationsTask15 = new TextOperations(textParser.parseText());
        textOperationsTask15.task15();
        FileOperations.text2File("src/main/resources/task_15_text.txt", textOperationsTask15.getText());
        logger.info("Edited text object was saved to file src/main/resources/task_15_text.txt");

    }
}
