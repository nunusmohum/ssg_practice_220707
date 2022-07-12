package com.ll.exam;

import java.util.ArrayList;

public class JsonHandler{
    public static String jsonBuilder(Phrase phrase){
        return String.format("{\n\t\"id\": \"%d\",\n\t\"content\": \"%s\",\n\t\"author\": \"%s\"\n}"
                ,phrase.getIndex()
                ,phrase.getContent()
                ,phrase.getAuthor());
    }

    public static String jsonBuilderDoubleTab(Phrase phrase){
        return String.format("\t{\n\t\t\"id\": \"%d\",\n\t\t\"content\": \"%s\",\n\t\t\"author\": \"%s\"\n\t}"
                ,phrase.getIndex()
                ,phrase.getContent()
                ,phrase.getAuthor());
    }


    public static ArrayList<Phrase> jsonParser(ArrayList<String> stringList){
        ArrayList<Phrase> phrasesList = new ArrayList<>();
        for(String str : stringList){
            String[] strArr = str.split(",");
            int index = Integer.parseInt(strArr[0].split(":")[1].trim().replace("\"",""));
            String context = strArr[1].split(":")[1].trim().replace("\"","");
            String author = strArr[2].split(":")[1].trim().replace("\"","").replace("}","");
            Phrase phrase = new Phrase(context, author, index);
            phrasesList.add(phrase);
        }
        return phrasesList;
    }

    public static String jsonDataBuilder(ArrayList<Phrase> phrasesList){
        StringBuilder sbJSON = new StringBuilder();
        sbJSON.append("[\n");
        for(int i=0; i < phrasesList.size(); i++){
            sbJSON.append(JsonHandler.jsonBuilderDoubleTab(phrasesList.get(i)));
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