package com.ll.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static String MODE = "data";
    Scanner sc;

    public App(Scanner sc){
        this.sc = sc;
    }

    public static String getPhrasePath(){
        return App.MODE + "/phrases/";
    }

    public static String getDataPath(){
        return App.MODE + "/";
    }

    public void run(){
        System.out.println("=== 명언 SSG ===");

        PhraseController phraseController = new PhraseController(sc);

        outer:
        while(true){
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            Req req = new Req(cmd);

            switch(req.getPath()) {
                case "등록":
                    phraseController.regist();
                    break;
                case "목록":
                    phraseController.printList();
                    break;
                case "수정":
                    phraseController.update(req);
                    break;
                case "삭제":
                    phraseController.delete(req);
                    break;
                case "빌드":
                    phraseController.build(req);
                    break;
                case "종료":
                    break outer;

            }
        }

        sc.close();
    }



}
