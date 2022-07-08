package com.ll.exam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
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
        }

        sc.close();

    }

    private void register() {
        System.out.printf("명언 : ");
        String phrase = sc.nextLine();
        System.out.printf("작가 : ");
        String author = sc.nextLine();

        try {
            int index = FileAccesser.registFile(phrase, author);
            System.out.println(index + "번 명언이 등록되었습니다.");
        } catch (IOException e) {
            System.out.println("[Error] 파일 등록 실패");
            e.printStackTrace();
        }
    }

    private void printList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
    }


}
