package com.itgroup;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MemberManager manager =  new MemberManager();

        while (true){
            System.out.println("메뉴 선택");
            System.out.println("0:종료, 1목록 조회, 2가입, 3수정, 4총회원수, 5탈퇴, 6회원정보, 7xx, 8xx,");
            int menu = scan.nextInt();//선택한 메뉴
            switch (menu){
                case 0:
                    System.out.println("프로그렘을 종료 합니다.");
                    System.exit(0);//운영체제에게 종료됨을 알리고 빠져 나가기

                case 1:
                    manager.selectAll();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    manager.getSize();
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
            }

        }
    }
}