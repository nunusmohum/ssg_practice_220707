package com.ll.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhraseController {
    PhraseRepository phraseRepository;
    Scanner sc;

    PhraseController(Scanner sc){
        phraseRepository = new PhraseRepository();
        phraseRepository.checkDataFolder();
        this.sc = sc;
    }

    public void register() {
        System.out.printf("명언 : ");
        String context = sc.nextLine();
        System.out.printf("작가 : ");
        String author = sc.nextLine();

        Phrase phrase = new Phrase(context, author);

        try {
            phraseRepository.registPhrase(phrase);
            System.out.println(phrase.getIndex() + "번 명언이 등록되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 등록 실패");
            e.printStackTrace();
        }
    }

    public void printList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        try {
            ArrayList<Phrase> phrasesList = phraseRepository.getPhrasesList();
            for(Phrase phrase : phrasesList){
                System.out.printf("%s / %s / %s\n"
                        ,phrase.getIndex()
                        ,phrase.getAuthor()
                        ,phrase.getContent());
            }
        } catch (IOException e) {
            System.out.println("[Error] 목록 읽기 실패");
            e.printStackTrace();
        }
    }

    public void update(Req req){
        int id = req.getIntParam("id", 0);
        String context = "";
        String author = "";

        if(id <= 0){
            System.out.println("id 값이 올바르지 않습니다.");
            return;
        }

        try {
            ArrayList<Phrase> phrasesList = phraseRepository.getPhrasesList();
            for(Phrase phrase : phrasesList){
                if(id == phrase.getIndex()){
                    context = phrase.getContent();
                    author = phrase.getAuthor();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("[Error] 목록 읽기 실패");
            e.printStackTrace();
        }

        if(context.equals("") || author.equals("")){
            System.out.println("일치하는 id가 없습니다.");
            return;
        }

        System.out.println(id + "번 명언을 수정합니다.");
        System.out.printf("기존 명언 : %s\n", context);
        System.out.printf("새 명언 : ");
        String newContext = sc.nextLine();

        Phrase phrase = new Phrase(newContext, author, id);
        try {
            phraseRepository.registPhrase(phrase);
            System.out.println(phrase.getIndex() + "번 명언이 수정되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 수정 실패");
            e.printStackTrace();
        }
    }

    public void delete(Req req){
        int id = req.getIntParam("id", 0);
        if(id <= 0){
            System.out.println("id 값이 올바르지 않습니다.");
            return;
        }

        try {
            phraseRepository.deletePhrase(id);
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 삭제 실패");
            e.printStackTrace();
        }
    }

    public void build(Req req){
        try {
            ArrayList<Phrase> phrasesList = phraseRepository.getPhrasesList();
            String dataJSON = JsonHandler.jsonDataBuilder(phrasesList);
            phraseRepository.createDataJson(dataJSON);
        } catch (IOException e) {
            System.out.println("[Error] data.json 생성 실패");
            e.printStackTrace();
        } finally {
            System.out.println("data.json 이 생성되었습니다.");
        }
    }
}
