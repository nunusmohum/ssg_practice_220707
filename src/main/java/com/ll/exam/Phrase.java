package com.ll.exam;

import java.io.File;
import java.util.Arrays;

public class Phrase {
    private final int id;
    private final String content;
    private final String author;

    Phrase(int id, String content, String author){
        this.content = content;
        this.author = author;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
