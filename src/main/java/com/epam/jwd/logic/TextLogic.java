package com.epam.jwd.logic;

import com.epam.jwd.comparator.WordVowelsProportionComparator;
import com.epam.jwd.entity.*;
import com.epam.jwd.parser.TextParser;

import java.util.*;
import java.util.stream.Collectors;

public class TextLogic {
    private static final String CONSONANTS = "BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvWwXxZz";

    private Text text;

    public TextLogic(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public Text task7() {
        List<String> allWords = new ArrayList<>();

        text.getRows().stream()
                .filter(row -> row instanceof ParagraphRow)
                .map(row -> ((ParagraphRow) row).getSentences())
                .flatMap(Collection::stream)
                .map(Sentence::getParts)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .map(Map.Entry::getValue)
                .filter(sentencePart -> sentencePart.getType() == PartType.WORD)
                .forEach(sentencePart -> allWords.add(sentencePart.getBody()));

        allWords.sort(new WordVowelsProportionComparator());

        TextParser textParser = new TextParser(allWords);
        Text sortedWords = textParser.parseText();

        return sortedWords;
    }

    public void task12(int wordLength) {
        text.getRows().stream()
                .filter(row -> row instanceof ParagraphRow)
                .map(row -> ((ParagraphRow) row).getSentences())
                .flatMap(Collection::stream)
                .forEach(sentence -> removeSentencePart(sentence, wordLength));
    }

    private void removeSentencePart(Sentence sentence, int wordLength) {
        Map<Integer, SentencePart> sentencePartMap = sentence.getParts().entrySet().stream()
                .filter(entry -> !isToRemove(entry.getValue(), wordLength))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, TreeMap::new));
        sentence.setParts(sentencePartMap);
    }

    private boolean isToRemove(SentencePart sentencePart, int wordLength) {
        return sentencePart.getType() == PartType.WORD
                && sentencePart.getBody().length() == wordLength
                && CONSONANTS.indexOf(sentencePart.getBody().charAt(0)) != -1;
    }

    public void task15() {
        text.getRows().stream()
                .filter(row -> row instanceof ParagraphRow)
                .map(row -> ((ParagraphRow) row).getSentences())
                .flatMap(Collection::stream)
                .map(Sentence::getParts)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .map(Map.Entry::getValue)
                .filter(sentencePart -> sentencePart.getType() == PartType.WORD)
                .filter(sentencePart -> sentencePart.getBody().length() > 1)
                .forEach(sentencePart -> sentencePart.setBody(modifyWord(sentencePart.getBody())));
    }

    private String modifyWord(String word) {
        StringBuilder newWord = new StringBuilder(word);

        String firstLetter = newWord.substring(0,1);
        String lastLetter = newWord.substring(newWord.length()-1);

        int index = newWord.indexOf(firstLetter);
        while(index != -1) {
            newWord.deleteCharAt(index);
            index = newWord.indexOf(firstLetter);
        }

        index = newWord.indexOf(lastLetter);
        while(index != -1) {
            newWord.deleteCharAt(index);
            index = newWord.indexOf(lastLetter);
        }

        if(!firstLetter.equals(lastLetter)) {
            newWord.insert(0,firstLetter);
            newWord.append(lastLetter);
        }

        return newWord.toString();
    }
}
