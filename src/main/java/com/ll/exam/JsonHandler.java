package com.ll.exam;

public class JsonHandler{
    public static String jsonBuilder(String txt1, String txt2){
        return String.format("{\n\t\"phrase\": \"%s\",\n\t\"author\": \"%s\"\n}"
                , txt1
                , txt2);
    }
}