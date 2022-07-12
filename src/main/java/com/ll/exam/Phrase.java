package com.ll.exam;

import java.io.File;
import java.util.Arrays;

public class Phrase {
    private final int index;
    private final String content;
    private final String author;

    Phrase(String content, String author){
        this.content = content;
        this.author = author;
        this.index = getLastIndex();
    }

    Phrase(String content, String author, int index){
        this.content = content;
        this.author = author;
        this.index = index;
    }

    private int getLastIndex(){
        File dir = new File(App.DATA_PHRASES_PATH);

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

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
