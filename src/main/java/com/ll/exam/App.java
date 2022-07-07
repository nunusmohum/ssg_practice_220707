package com.ll.exam;

import java.util.HashMap;
import java.util.Scanner;

public class App {
    private HashMap<String, String> phrases = new HashMap<>();
    public void run(){
        System.out.println("=== 명언 SSG ===");

        Scanner sc = new Scanner(System.in);

        outer:
        while(true){
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            switch(cmd){
                case "종료":
                    break outer;
                case "등록":
                    System.out.printf("명언 : ");
                    String phrase = sc.nextLine();
                    System.out.printf("작가 : ");
                    String writer = sc.nextLine();
                    register(phrase, writer);
                    System.out.printf("%d번 명언이 등록되었습니다.\n", phrases.size());
            }
        }

        sc.close();

    }

    private void register(String phrase, String writer){
        phrases.put(phrase, writer);
    }
}
