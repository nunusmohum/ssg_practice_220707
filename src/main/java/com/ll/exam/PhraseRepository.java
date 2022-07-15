package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PhraseRepository {

    PhraseRepository(){
        checkDataFolder();
        checkIndexFile();
    }

    public Phrase registPhrase(String content, String author){
        int id = getLastId();
        Phrase phrase = new Phrase(id, content, author);
        String writeData = Util.JsonHandler.jsonBuilder(phrase);
        String filePath = String.format("%s%d.json", App.getPhrasePath(), phrase.getId());

        try {
            Util.FileHandler.writeFile(filePath, writeData);
        } catch (IOException e) {
            System.out.println("[Error] file regist failed");
            e.printStackTrace();
        }

        setLastId(id);
        return phrase;
    }

    public void updatePhrase(String content, String author, int id){
        Phrase phrase = new Phrase(id, content, author);
        String writeData = Util.JsonHandler.jsonBuilder(phrase);
        String filePath = String.format("%s%d.json", App.getPhrasePath(), phrase.getId());

        try {
            Util.FileHandler.writeFile(filePath, writeData);
        } catch (IOException e) {
            System.out.println("[Error] file update failed");
            e.printStackTrace();
        }
    }

    public ArrayList<Phrase> getPhrasesList(){
        String folderPath = App.getPhrasePath();

        ArrayList<String> stringList = new ArrayList<>();
        try {
            stringList = Util.FileHandler.readFilesStringList(folderPath);
        } catch (IOException e) {
            System.out.println("[Error] fileStringlist read failed");
        }

        return Util.JsonHandler.jsonParser(stringList);
    }

    public boolean deletePhrase(int index) {
        String filePath = App.getPhrasePath() + index + ".json";
        return Util.FileHandler.deleteFile(filePath);
    }

    public void checkDataFolder(){
        Util.FileHandler.createFolder(App.getPhrasePath());
    }

    public void checkIndexFile(){
        String indexPath = App.getDataPath() + "index.txt";
        try {
            if(!Util.FileHandler.isFileExists(indexPath)){
                Util.FileHandler.writeFile(indexPath, "1");
            }
        } catch (IOException e) {
            System.out.println("[Error] inedx.txt file write failed");
            e.printStackTrace();
        }
    }

    public void createDataJson(String jsonString){
        String filePath = App.getDataPath() + "data.json";
        try {
            Util.FileHandler.writeFile(filePath, jsonString);
        } catch (IOException e) {
            System.out.println("[Error] data.json file write failed");
            e.printStackTrace();
        }
    }

    public Phrase findById(int id){
        ArrayList<Phrase> phrasesList = getPhrasesList();
        for(Phrase phrase : phrasesList){
            if(1 == phrase.getId()){
                return phrase;
            }
        }

        return null;
    }

    public int getLastId(){
        String indexPath = App.getDataPath() + "index.txt";
        int id = 0;
        try {
            id = Integer.parseInt(Util.FileHandler.readFile(indexPath));
        } catch (IOException e) {
            System.out.println("[Error] index.txt file read failed");
            e.printStackTrace();
        }

        return id;
    }

    public void setLastId(int id){
        String indexPath = App.getDataPath() + "index.txt";
        try {
            id++;
            System.out.println(id);
            Util.FileHandler.writeFile(indexPath, Integer.toString(id));
        } catch (IOException e) {
            System.out.println("[Error] index.txt file write failed");
            e.printStackTrace();
        }
    }



}

