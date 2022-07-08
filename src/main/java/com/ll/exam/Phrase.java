package com.ll.exam;

import java.io.File;
import java.util.Arrays;

public class Phrase {
    private final int index;
    private final String context;
    private final String author;

    Phrase(String context, String author){
        this.context = context;
        this.author = author;
        this.index = getLastIndex();
    }

    Phrase(String context, String author, int index){
        this.context = context;
        this.author = author;
        this.index = index;
    }

    private int getLastIndex(){
        String folderPath = "data/phrases";
        File dir = new File(folderPath);

        if(dir.list().length == 0){
            return 1;
        }
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

    public String getContext() {
        return context;
    }

    public String getAuthor() {
        return author;
    }
}
