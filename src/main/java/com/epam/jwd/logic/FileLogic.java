package com.epam.jwd.logic;

import com.epam.jwd.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileLogic {
    private static final Logger logger = LogManager.getLogger(FileLogic.class);
    private static final String PARAGRAPH_SEPARATOR = "\n";

    public static List<String> fileReader(String path) {
        File textFile = new File(path);
        List<String> fileRows = new ArrayList<>();

        try {
            fileRows = Files.readAllLines(textFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        logger.info(path + " file rows were read successfully.");
        return fileRows;
    }

    public static void fileWriter(String path, Text text) {
        File file = new File(path);

        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);

            for (Row row : text.getRows()) {
                if (row instanceof ParagraphRow) {
                    fileWriter.write(((ParagraphRow) row).getBody());
                }
                else if (row instanceof CodeRow) {
                    fileWriter.write(((CodeRow) row).getBody());
                }
                fileWriter.write(PARAGRAPH_SEPARATOR);
            }
            fileWriter.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

}
