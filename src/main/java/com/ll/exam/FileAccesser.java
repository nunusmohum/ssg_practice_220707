package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileAccesser {
    public static void registPhrase(Phrase phrase) throws IOException {
        String writeData = JsonHandler.jsonBuilder(phrase);

        String filePath = String.format("%s%d.json", App.DATA_PHRASES_PATH, phrase.getIndex());
        File file = new File(filePath);

        if(!file.exists()){
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter((new FileWriter(file, false)));
        writer.write(writeData);

        writer.flush();
        writer.close();
    }

    public static ArrayList<Phrase> getPhrasesList() throws IOException {
        String folderPath = App.DATA_PHRASES_PATH;
        File dir = new File(folderPath);

        File[] filenames = dir.listFiles();
        ArrayList<String> stringList = new ArrayList<>();

        for(File file : filenames){
            BufferedReader obj = new BufferedReader(new FileReader(file));
            StringBuilder sbResult = new StringBuilder();
            String str;
            while((str = obj.readLine())!=null){
                sbResult.append(str);
            }
            stringList.add(String.valueOf(sbResult));
            obj.close();
        }

        ArrayList<Phrase> phrasesList = JsonHandler.jsonParser(stringList);
        return phrasesList;
    }

    public static void deletePhrase(int index) throws FileNotFoundException {
        String filePath = App.DATA_PHRASES_PATH + index + ".json";
        System.out.println(filePath);
        File file = new File(filePath);

        if(file.exists()){
            file.delete();
        } else {
            throw new FileNotFoundException();
        }
    }

    public static void checkDataFolder(){
        Path path = Paths.get(App.DATA_PHRASES_PATH);
        if(!Files.exists(path)){
            File folder = new File(App.DATA_PHRASES_PATH);
            folder.mkdirs();
        }
    }


}

