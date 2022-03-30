package com.epam.jwd.main;

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
import java.util.Map;

public class FileOperations {
    public static final Logger logger = LogManager.getLogger(FileOperations.class);

    public static List<String> getAllFileRows(String path) {
        File textFile = new File(path);
        List<String> fileRows = new ArrayList<>();

        try {
            fileRows = Files.readAllLines(textFile.toPath(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
        logger.info(path + " file rows were scanned successfully.");
        return fileRows;
    }

    public static void text2File(String path, Text text) {
        File file = new File(path);

        try {
            if (file.delete()) {
                file.createNewFile();
            }
            else {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, true);

            for(Row row : text.getRows()) {
                if(row instanceof ParagraphRow) {
                    for(Sentence sentence : ((ParagraphRow) row).getSentences()) {
                        for(Map.Entry<Integer,SentencePart> entry: sentence.getParts().entrySet()) {
                            fileWriter.write(entry.getValue().getBody());
                        }
                        fileWriter.write(" ");
                    }
                }
                else if(row instanceof CodeRow) {
                    fileWriter.write(((CodeRow) row).getCodeBody());
                }
                fileWriter.write("\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

}
