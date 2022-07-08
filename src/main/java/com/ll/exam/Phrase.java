package com.ll.exam;

import java.io.File;
import java.util.Arrays;

public class Phrase {
    private final int index;
    private final String phrase;
    private final String author;

    Phrase(String phrase, String author){
        this.phrase = phrase;
        this.author = author;
        this.index = getLastIndex();
    }

    private int getLastIndex(){
        String folderPath = "src/data/phrases";
        File dir = new File(folderPath);
        int lastIndex = Arrays.stream(dir.list())
                .map(fileName -> fileName.replace(".json", ""))
                .mapToInt(Integer::parseInt)
                .max()
                .getAsInt();
        return lastIndex+1;
    }

    public int getIndex() {
        return index;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getAuthor() {
        return author;
    }
}
