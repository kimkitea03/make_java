package com.itgroup;

import com.itgroup.been.Board;
import com.itgroup.been.Member;
import com.itgroup.dao.MemberDao;
import com.itgroup.dao.boardDao;

import java.util.List;
import java.util.Scanner;

//메인 클래스 대신 실제 모든 업무를 총 책임지는 메니저 클래스
public class DataManager {
    private MemberDao mdao = null; // 실제 데이터 베이스와 연동하는 다오 클레스
    private boardDao bdao = null;
    private  Scanner scan = null ;//회원 정보 압력받기 위한 스캐너

    public DataManager() {

        this.mdao = new MemberDao();
        this.bdao = new boardDao();
        this.scan = new Scanner(System.in);
    }


    public void selectAll() {//모든 회원 정보 조회
        List<Member> members = mdao.selectAll();
        System.out.println("이름\t급여\t주소");
        for(Member bean : members){
            String name = bean.getName();
            int salary = bean.getSalary();
            String address = bean.getAddress();
            String message = name + "\t" + salary + "\t" + address+ "\t";
            System.out.println(message);
        }
    }

    public void findByGender() {
        String gender ="여자";
        List <Member> mydata = mdao.findByGender(gender);
        //여기서 출력
        System.out.println(gender+"에 대한 정보 출력");
        System.out.println("이름\t급여\t성별");
        for (Member bean : mydata){
            String name = bean.getName();
            int salary = bean.getSalary();
            String genders = bean.getGender();
            String message = name + "\t" + salary + "\t" + genders+ "\t";
            System.out.println(message);
        }

    }

    public void getSize() {//몇명의 회원인지 조회하는 문구입니다.
        int cut = mdao.getsize();
        String message;
       if(cut == 0) {
           message = "검색된 회원이 존재하지 않습니다.";
       }else{
           message="검색된 회원은 총 "+cut+"명입니다.";
       }
        System.out.println(message);

    }


    public void getMemberOne() {
        String findId = "lee"; // 찾고자 하는 회원
        Member someone = mdao.getMemberOne(findId);

//        String id = someone.getId();
//        String name = someone.getName();
//        String gender = someone.getGender();
//        String message = id+"\t"+name+"\t"+gender;
//        System.out.println(message);

        if(someone == null){
            System.out.println("찾으시는 회원이 존재하지 않습니다.");
        }else {
            System.out.println("아이디\t이름\t성별");
            String id = someone.getId();
            String name = someone.getName();
            String gender = someone.getGender();
            String message = id+"\t"+name+"\t"+gender;
            System.out.println(message);
        }

    }

    public void deleteData() { // 나의 id를 사용한 탈퇴
        String id = "yusin";
        int cnt =-1;
        cnt = mdao.deleteData(id);
        if(cnt == -1){
            System.out.println("회원 탈퇴 실패(접속, 네트워크 오류)");
        } else if (cnt == 0) {
            System.out.println("탈퇴할 회원이 존재하지 않습니다.");
        } else if (cnt > 0) {
            System.out.println("회원 탈퇴 완료");
        }else{

        }
    }

    public void insertData() {
        Member bean = new Member();
        int cnt = -1 ;

        //2~3개만 입력받도록 합니다
        System.out.println("id 입력 : ");
        String id = scan.next();
        System.out.println("이름 입력 :  ");
        String name= scan.next();

        bean.setId(id);
        bean.setName(name);
        bean.setPassword("abc123");
        bean.setGender("남자");
        bean.setBrith("2025/08/20");
        bean.setMarriage("결혼");
        bean.setSalary(100);
        bean.setAddress("서대문");
        bean.setManager(null);

        if(cnt ==-1){
            System.out.println("회원가입 실패 (접속, 네트워크 오류)");
        } else if (cnt == 1) {
            System.out.println("회원 아이디 "+ id +"로 생성이 완료 됐습니다.");
        }else {

        }


        cnt = mdao.insertData(bean);

    }

    public void updateData() {
        int cnt=-1;

        System.out.println("수정하고자 하는 회원 id 입력 : ");//yusin
        String findId= scan.next();

        //여기서 bean은 이전에 입력했던 나의 정보입니다.
        Member bean = mdao.getMemberOne(findId);

        //편의상 내 이름과 결혼 여부를 변경
        System.out.println("이름 입력 : ");
        String name = scan.next();

        System.out.println("결혼 여부 입력 : ");
        String marriage = scan.next();

        bean.setName(name);
        bean.setMarriage(marriage);
        bean.setPassword("abc123");
        bean.setGender("남자");
        bean.setBrith("90/12/25");
        bean.setSalary(220);
        bean.setAddress("마포");
        bean.setManager(null);
        bean.setId(findId);

        cnt=mdao.updateData(bean);

        if (cnt == -1){
            System.out.println("업데이트 실패");
        } else if (cnt == 1) {
            System.out.println("업데이트 성공");
        }else {

        }
    }

    public void selectAllBoard() {
        List<Board> boardList = bdao.selectAll();

        System.out.println("글번호\t작성자\t글제목\t글내용");

        for(Board bean : boardList){
            //여기서 출력
            int no = bean.getNo();
            String writer = bean.getWriter();
            String Subject = bean.getSubject();
            String content = bean.getContent();
            String message = no+"\t"+writer+"\t"+Subject+"\t"+content+"\t";
            System.out.println(message);
        }

    }

    public void selectEvenData() { //짝수만 조회
        List<Board> boardList = bdao.selectEvenData();


        System.out.println("글번호\t작성자\t글제목\t글내용");
        for(Board bean : boardList){
            int no = bean.getNo() ;
            String writer = bean.getWriter();
            String subject = bean.getSubject();
            String content = bean.getContent();
            String message = no + "\t" + writer + "\t" + subject + "\t" + content ;
            System.out.println(message);
        }

    }
}
