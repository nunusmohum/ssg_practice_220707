package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PhraseRepository {
    JsonHandler jsonHandler;

    PhraseRepository(){
        jsonHandler = new JsonHandler();
    }

    public Phrase registPhrase(String content, String author){
        Phrase phrase = new Phrase(content, author);
        String writeData = jsonHandler.jsonBuilder(phrase);

        String filePath = String.format("%s%d.json", App.DATA_PHRASES_PATH, phrase.getIndex());
        File file = new File(filePath);

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            BufferedWriter writer = null;
            writer = new BufferedWriter((new FileWriter(file, false)));
            writer.write(writeData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return phrase;
    }

    public void updatePhrase(String content, String author, int id){
        Phrase phrase = new Phrase(content, author, id);
        String writeData = jsonHandler.jsonBuilder(phrase);

        String filePath = String.format("%s%d.json", App.DATA_PHRASES_PATH, phrase.getIndex());
        File file = new File(filePath);

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            BufferedWriter writer = null;
            writer = new BufferedWriter((new FileWriter(file, false)));
            writer.write(writeData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Phrase> getPhrasesList(){
        String folderPath = App.DATA_PHRASES_PATH;
        File dir = new File(folderPath);

        File[] filenames = dir.listFiles();
        ArrayList<String> stringList = new ArrayList<>();

        for(File file : filenames){
            BufferedReader obj = null;
            try {
                obj = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            StringBuilder sbResult = new StringBuilder();
            String str;
            while(true){
                try {
                    if ((str = obj.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sbResult.append(str);
            }
            stringList.add(String.valueOf(sbResult));
            try {
                obj.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<Phrase> phrasesList = jsonHandler.jsonParser(stringList);
        return phrasesList;
    }

    public void deletePhrase(int index) {
        String filePath = App.DATA_PHRASES_PATH + index + ".json";
        System.out.println(filePath);
        File file = new File(filePath);

        if(file.exists()){
            file.delete();
        }

    }

    public void checkDataFolder(){
        Path path = Paths.get(App.DATA_PHRASES_PATH);
        if(!Files.exists(path)){
            File folder = new File(App.DATA_PHRASES_PATH);
            folder.mkdirs();
        }
    }

    public void createDataJson(String jsonString){
        String filePath = "data/data.json";
        File file = new File(filePath);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter((new FileWriter(file, false)));
            writer.write(jsonString);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

