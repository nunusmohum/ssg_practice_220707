package com.ll.exam;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonHandler{
    public static String jsonBuilder(Phrase phrase){
        return String.format("{\n\t\"id\": \"%d\",\n\t\"context\": \"%s\",\n\t\"author\": \"%s\"\n}"
                , phrase.getIndex()
                , phrase.getContext()
                , phrase.getAuthor());
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
}