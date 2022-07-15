package com.ll.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhraseController {
    PhraseService phraseService;
    Scanner sc;

    PhraseController(Scanner sc){
        phraseService = new PhraseService();
        this.sc = sc;
    }

    public void regist() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        Phrase phrase = phraseService.regist(content, author);
        System.out.println(phrase.getId() + "번 명언이 등록되었습니다.");
    }

    public void printList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        ArrayList<Phrase> phrasesList = phraseService.getPhraseList();

        for(Phrase phrase : phrasesList){
            System.out.printf("%s / %s / %s\n"
                    ,phrase.getId()
                    ,phrase.getAuthor()
                    ,phrase.getContent());
        }
    }

    public void update(Req req){
        int id = req.getIntParam("id", 0);
        if(id <= 0){
            System.out.println("id 값이 올바르지 않습니다.");
            return;
        }
        Phrase phrase = phraseService.findById(id);

        System.out.println(id + "번 명언을 수정합니다.");
        System.out.printf("기존 명언 : %s\n", phrase.getContent());
        System.out.print("새 명언 : ");
        String newContent = sc.nextLine();

        phraseService.update(newContent, phrase.getAuthor(), id);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    public void delete(Req req){
        int id = req.getIntParam("id", 0);
        if(id <= 0){
            System.out.println("id 값이 올바르지 않습니다.");
            return;
        }
        phraseService.delete(id);

        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void build(Req req){
        phraseService.build();

        System.out.println("data.json 이 생성되었습니다.");
    }
}
