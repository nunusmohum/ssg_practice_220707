package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PhraseRepository {

    PhraseRepository(){
    }

    public Phrase registPhrase(String content, String author){
        String indexPath = "data/index.txt";
        int id = 0;
        try {
            id = Integer.parseInt(Util.FileHandler.readFile(indexPath));
        } catch (IOException e) {
            System.out.println("[Error] index.txt file read failed");
            e.printStackTrace();
        }

        Phrase phrase = new Phrase(id, content, author);
        String writeData = Util.JsonHandler.jsonBuilder(phrase);
        String filePath = String.format("%s%d.json", App.DATA_PHRASES_PATH, phrase.getId());

        try {
            Util.FileHandler.writeFile(filePath, writeData);
        } catch (IOException e) {
            System.out.println("[Error] file regist failed");
            e.printStackTrace();
        }

        try {
            id++;
            System.out.println(id);
            Util.FileHandler.writeFile(indexPath, Integer.toString(id));
        } catch (IOException e) {
            System.out.println("[Error] index.txt file write failed");
            e.printStackTrace();
        }

        return phrase;
    }

    public void updatePhrase(String content, String author, int id){
        Phrase phrase = new Phrase(id, content, author);
        String writeData = Util.JsonHandler.jsonBuilder(phrase);
        String filePath = String.format("%s%d.json", App.DATA_PHRASES_PATH, phrase.getId());

        try {
            Util.FileHandler.writeFile(filePath, writeData);
        } catch (IOException e) {
            System.out.println("[Error] file update failed");
            e.printStackTrace();
        }
    }

    public ArrayList<Phrase> getPhrasesList(){
        String folderPath = App.DATA_PHRASES_PATH;

        ArrayList<String> stringList = new ArrayList<>();
        try {
            stringList = Util.FileHandler.readFilesStringList(folderPath);
        } catch (IOException e) {
            System.out.println("[Error] fileStringlist read failed");
        }

        return Util.JsonHandler.jsonParser(stringList);
    }

    public void deletePhrase(int index) {
        String filePath = App.DATA_PHRASES_PATH + index + ".json";
        Util.FileHandler.deleteFile(filePath);
    }

    public void checkDataFolder(){
        Util.FileHandler.createFolder(App.DATA_PHRASES_PATH);
    }

    public void checkIndexFile(){
        String filePath = "data/index.txt";
        try {
            if(!Util.FileHandler.isFileExists(filePath)){
                Util.FileHandler.writeFile(filePath, "1");
            }
        } catch (IOException e) {
            System.out.println("[Error] inedx.txt file write failed");
            e.printStackTrace();
        }
    }

    public void createDataJson(String jsonString){
        String filePath = "data/data.json";
        try {
            Util.FileHandler.writeFile(filePath, jsonString);
        } catch (IOException e) {
            System.out.println("[Error] data.json file write failed");
            e.printStackTrace();
        }
    }


}

