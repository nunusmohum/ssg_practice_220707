package com.ll.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileAccesser {
    public static int registPhrase(String phrase, String author) throws IOException {
        String writeData = JsonHandler.jsonBuilder(phrase, author);

        int index = getLastIndex();
        String filePath = String.format("src/data/phrases/%d.json", index);
        File file = new File(filePath);

        if(!file.exists()){
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter((new FileWriter(file, true)));
        writer.write(writeData);

        writer.flush();
        writer.close();

        return index;
    }

    public static int getPhrasesList(){

    }

    private static int getLastIndex(){
        String folderPath = "src/data/phrases";
        File dir = new File(folderPath);
        int lastIndex = Arrays.stream(dir.list())
                .map(fileName -> fileName.replace(".json", ""))
                .mapToInt(Integer::parseInt)
                .max()
                .getAsInt();
        return lastIndex+1;
    }
}

