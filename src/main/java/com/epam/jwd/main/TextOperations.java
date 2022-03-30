package com.epam.jwd.main;

import com.epam.jwd.comparator.WordVowelsProportionComparator;
import com.epam.jwd.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextOperations {
    public static final Logger logger = LogManager.getLogger(TextOperations.class);

    private Text text;

    public TextOperations(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void task7() {
        List<SentencePart> allWords = new ArrayList<>();
        for(Row row : text.getRows()) {
            if(row instanceof ParagraphRow) {
                for(Sentence sentence : ((ParagraphRow) row).getSentences()) {
                    for(Map.Entry<Integer, SentencePart> entry: sentence.getParts().entrySet()) {
                        if(entry.getValue().getType() == PartTypes.WORD) {
                            allWords.add(entry.getValue());
                        }
                    }
                }
            }
        }
        allWords.sort(new WordVowelsProportionComparator());

        System.out.println("All words in the text sorted by vowels to word length proportion:");
        for(SentencePart word : allWords) {
            System.out.println(word.getBody());
        }
    }

    public void task12(int wordLength) {
        for(Row row : text.getRows()) {
            if(row instanceof ParagraphRow) {
                for(Sentence sentence : ((ParagraphRow) row).getSentences()) {
                    Map<Integer, SentencePart> copy = new HashMap<>(sentence.getParts());
                    for(Map.Entry<Integer, SentencePart> entry: copy.entrySet()) {
                        if((entry.getValue().getType() == PartTypes.WORD)
                                && (entry.getValue().getBody().length() == wordLength)
                                && ("BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvWwXxZz".indexOf(entry.getValue().getBody().charAt(0)) != -1)) {
                            sentence.getParts().remove(entry.getKey());
                        }
                    }
                }
            }
        }
    }

    public void task15() {
        for(Row row : text.getRows()) {
            if(row instanceof ParagraphRow) {
                for(Sentence sentence : ((ParagraphRow) row).getSentences()) {
                    for(Map.Entry<Integer, SentencePart> entry: sentence.getParts().entrySet()) {
                        if((entry.getValue().getType() == PartTypes.WORD)
                                && (entry.getValue().getBody().length() > 1)) {
                            StringBuilder word = new StringBuilder(entry.getValue().getBody());
                            String first = word.substring(0,1);
                            String last = word.substring(word.length()-1);
                            int index = word.indexOf(first);
                            while(index != -1) {
                                word.deleteCharAt(index);
                                index = word.indexOf(first);
                            }
                            index = word.indexOf(last);
                            while(index != -1) {
                                word.deleteCharAt(index);
                                index = word.indexOf(last);
                            }
                            if(!first.equals(last)) {
                                word.insert(0,first);
                                word.append(last);
                            }
                            try {
                                Field field = entry.getValue().getClass().getDeclaredField("body");
                                field.setAccessible(true);
                                field.set(entry.getValue(),word.toString());
                            } catch (NoSuchFieldException | IllegalAccessException exception) {
                                logger.error(exception.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }
}
