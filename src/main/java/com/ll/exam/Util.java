package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Util {
    public static class JsonHandler{
        public static String jsonBuilder(Phrase phrase){
            return String.format("{\n\t\"id\": \"%d\",\n\t\"content\": \"%s\",\n\t\"author\": \"%s\"\n}"
                    ,phrase.getId()
                    ,phrase.getContent()
                    ,phrase.getAuthor());
        }

        public static String jsonBuilderDoubleTab(Phrase phrase){
            return String.format("\t{\n\t\t\"id\": \"%d\",\n\t\t\"content\": \"%s\",\n\t\t\"author\": \"%s\"\n\t}"
                    ,phrase.getId()
                    ,phrase.getContent()
                    ,phrase.getAuthor());
        }


        public static ArrayList<Phrase> jsonParser(ArrayList<String> stringList){
            ArrayList<Phrase> phrasesList = new ArrayList<>();
            for(String str : stringList){
                String[] strArr = str.split(",");
                int id = Integer.parseInt(strArr[0].
                        split(":")[1].trim()
                        .replace("\"",""));
                String context = strArr[1]
                        .split(":")[1].trim()
                        .replace("\"","");
                String author = strArr[2]
                        .split(":")[1].trim()
                        .replace("\"","")
                        .replace("}","");
                Phrase phrase = new Phrase(id, context, author);
                phrasesList.add(phrase);
            }
            return phrasesList;
        }

        public static String jsonDataBuilder(ArrayList<Phrase> phrasesList){
            StringBuilder sbJSON = new StringBuilder();
            sbJSON.append("[\n");
            for(int i=0; i < phrasesList.size(); i++){
                sbJSON.append(jsonBuilderDoubleTab(phrasesList.get(i)));
                if(i == phrasesList.size()-1){
                    sbJSON.append("\n");
                    break;
                }
                sbJSON.append(",\n");
            }
            sbJSON.append("]");

            return String.valueOf(sbJSON);
        }
    }

    static class FileHandler{
        public static void writeFile(String filePath, String writeData) throws IOException {
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter((new FileWriter(file, false)));
            writer.write(writeData);
            writer.flush();
            writer.close();
        }

        public static String readFile(String filePath) throws IOException {
            File file = new File(filePath);
            BufferedReader obj = new BufferedReader(new FileReader(file));

            StringBuilder sbResult = new StringBuilder();
            String str;
            while(true){
                if ((str = obj.readLine()) == null) break;

                sbResult.append(str);
            }
            obj.close();

            return String.valueOf(sbResult);
        }

        public static ArrayList<String> readFilesStringList(String folderPath) throws IOException {
            File dir = new File(folderPath);

            File[] filenames = dir.listFiles();
            ArrayList<String> stringList = new ArrayList<>();

            for(File file : filenames){
                BufferedReader obj = new BufferedReader(new FileReader(file));

                StringBuilder sbResult = new StringBuilder();
                String str;
                while(true){
                    if ((str = obj.readLine()) == null) break;

                    sbResult.append(str);
                }
                stringList.add(String.valueOf(sbResult));
                obj.close();
            }

            return stringList;
        }

        public static boolean deleteFile(String filePath){
            File file = new File(filePath);

            if(file.exists()){
                file.delete();
                return true;
            }
            return false;
        }

        public static void createFolder(String folderPath){
            Path path = Paths.get(folderPath);
            if(!Files.exists(path)){
                File folder = new File(folderPath);
                folder.mkdirs();
            }
        }

        public static boolean isFileExists(String filePath){
            Path path = Paths.get(filePath);
            return Files.exists(path);
        }

        public static void deleteDir(File file){
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    deleteDir(f);
                }
            }
            file.delete();
        }
    }
}
