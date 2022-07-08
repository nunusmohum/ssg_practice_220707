package com.ll.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static final String DATA_PHRASES_PATH = "data/phrases/";
    Scanner sc = new Scanner(System.in);
    public void run(){
        System.out.println("=== 명언 SSG ===");

        outer:
        while(true){
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            switch(cmd){
                case "종료":
                    break outer;
                case "등록":
                    register();
                    break;
                case "목록":
                    printList();
                    break;
            }
            if(cmd.contains("수정")){
                modify(cmd);
            }
            if(cmd.contains("삭제")){
                delete(cmd);
            }
        }

        sc.close();
    }

    private void register() {
        System.out.printf("명언 : ");
        String context = sc.nextLine();
        System.out.printf("작가 : ");
        String author = sc.nextLine();

        Phrase phrase = new Phrase(context, author);

        try {
            FileAccesser.registPhrase(phrase);
            System.out.println(phrase.getIndex() + "번 명언이 등록되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 등록 실패");
            e.printStackTrace();
        }
    }

    private void printList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        try {
            ArrayList<Phrase> phrasesList = FileAccesser.getPhrasesList();
            for(Phrase phrase : phrasesList){
                System.out.printf("%s / %s / %s\n"
                        ,phrase.getIndex()
                        ,phrase.getAuthor()
                        ,phrase.getContext());
            }
        } catch (IOException e) {
            System.out.println("[Error] 목록 읽기 실패");
            e.printStackTrace();
        }
    }

    private void modify(String cmd){
        if(!validateId(cmd)){
            return;
        }

        int id = Integer.parseInt(cmd.split("\\?id=")[1]);
        String context = "";
        String author = "";

        try {
            ArrayList<Phrase> phrasesList = FileAccesser.getPhrasesList();
            for(Phrase phrase : phrasesList){
                if(id == phrase.getIndex()){
                    context = phrase.getContext();
                    author = phrase.getAuthor();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("[Error] 목록 읽기 실패");
            e.printStackTrace();
        } finally {
            if(context.equals("") || author.equals("")){
                System.out.println("일치하는 id가 없습니다.");
                return;
            }
        }

        System.out.println(id + "번 명언을 수정합니다.");
        System.out.printf("기존 명언 : %s\n", context);
        System.out.printf("새 명언 : ");
        String newContext = sc.nextLine();

        Phrase phrase = new Phrase(newContext, author, id);
        try {
            FileAccesser.registPhrase(phrase);
            System.out.println(phrase.getIndex() + "번 명언이 수정되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 수정 실패");
            e.printStackTrace();
        }
    }

    private void delete(String cmd){
        if(!validateId(cmd)){
            return;
        }

        int id = Integer.parseInt(cmd.split("\\?id=")[1]);

        try {
            FileAccesser.deletePhrase(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 삭제 실패");
            e.printStackTrace();
        }
    }

    private boolean validateId(String cmd){
        int id = 0;
        try{
            id = Integer.parseInt(cmd.split("\\?id=")[1]);
        } catch (Exception e){
            System.out.println("입력 폼이 올바르지 않습니다.");
            return false;
        }

        if(id == 0 || id < 0){
            System.out.println("id 값이 올바르지 않습니다.");
            return false;
        }

        return true;
    }


}
