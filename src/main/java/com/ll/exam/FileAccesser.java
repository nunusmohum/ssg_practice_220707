package com.ll.exam;

import java.io.*;
import java.util.ArrayList;

public class FileAccesser {
    public static void registPhrase(Phrase phrase) throws IOException {
        String writeData = JsonHandler.jsonBuilder(phrase);

        String filePath = String.format("data/phrases/%d.json", phrase.getIndex());
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
        String folderPath = "data/phrases";
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
        String filePath = "data/phrases/" + index + ".json";
        System.out.println(filePath);
        File file = new File(filePath);

        if(file.exists()){
            file.delete();
        } else {
            throw new FileNotFoundException();
        }
    }


}

