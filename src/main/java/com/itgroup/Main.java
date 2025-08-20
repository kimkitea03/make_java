package com.itgroup;

import com.itgroup.been.Member;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DataManager manager =  new DataManager();


        while (true){
            System.out.println("메뉴 선택");
            System.out.println("0:종료, 1목록 조회, 2가입, 3수정, 4총회원수, 5탈퇴, 6회원정보, 7성별조회");
            System.out.println("11:게시물 전체, 12: 등록, 13: 수정, 14: 전체 건수, 15: 삭제, 16: 1건 정보, 17: 짝수만 조회");
            int menu = scan.nextInt();//선택한 메뉴
            switch (menu){
                case 0:
                    System.out.println("프로그렘을 종료 합니다.");
                    System.exit(0);//운영체제에게 종료됨을 알리고 빠져 나가기

                case 1:
                    manager.selectAll();
                    break;
                case 2:
                    manager.insertData();
                    break;
                case 3:
                    manager.updateData();
                    break;
                case 4:
                    manager.getSize();
                    break;
                case 5:
                    manager.deleteData();
                    break;
                case 6:
                    manager.getMemberOne();
                    break;
                case 7:
                    manager.findByGender();
                    break;

                case 11:
                    manager.selectAllBoard();
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    manager.selectEvenData();
                    break;
            }

        }
    }
}