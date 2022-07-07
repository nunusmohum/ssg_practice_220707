package com.ll.exam;

import java.util.Scanner;

public class App {
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
            }
        }

        sc.close();

    }
}
