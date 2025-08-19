package com.itgroup.dao;

import com.itgroup.been.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//데이터 베이스와 직접 연동하여 CRUD 작업을 수행해주는 DAO 클래스
public class MemberDao {
    public MemberDao() {
        //해당 드라이버는 ojdbcDriver 클래스는 ojdbc.jar 파일에 포함되어 있는 자바 클래스 입니다.
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {//동적 객체 생성하는 문법입니다.
            System.out.println("해당 드라이버가 존재하지 않습니다.");
            e.printStackTrace();
        }
    }


    public Connection getconnection(){
        Connection conn= null;//접속 객체

        String url= "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "oraman";
        String passward = "oracle";


        try {
            conn = DriverManager.getConnection(url,id,passward);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public int getsize() {
        String sql = "select count (*) as cut from members";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cut = 0 ;//검색된 회원 명수

        Connection conn = null; //접속 객체 구하기
        try {
            conn = this.getconnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()){
                cut =rs.getInt("cut");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cut ;
    }

    public List<Member> selectAll() {

        List<Member> members =  new ArrayList<>();
        PreparedStatement Pstmt = null;
        ResultSet rs = null;
        String sql = "select * from members order by name asc ";
        Connection conn = null;


        try{
            conn = this.getconnection() ;
            Pstmt= conn.prepareStatement(sql);
            rs = Pstmt.executeQuery();

            while ((rs.next())){
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getInt(7));
//                System.out.println(rs.getString("id"));
//                System.out.println(rs.getString("gender"));
                Member bean =  new Member();
                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setGender(rs.getString("gender"));
                bean.setBrith(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));

                members.add(bean);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                if (rs != null) {rs.close();}
                if(Pstmt != null){Pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return  members;
    }

    public List<Member> findByGender(String gender) {
        //성별 칼럼 gender을 사용하여 특정 성별의 회원들만 조회합니다.
        String sql = "select * from members where gender = ?";

        List<Member> members = new ArrayList<Member>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;



        try {
            conn = this.getconnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, gender);//첫번째 ?를 id로 치환해줘라는 구문
            rs=pstmt.executeQuery();

            while (rs.next()){
                Member bean =  new Member();

                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setGender(rs.getString("gender"));
                bean.setBrith(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));

                members.add(bean);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


        return  members;
    }

    public Member getMemberOne(String id) {
        //로그인 id 정보를 이용하여 해당 사용자의 정보를 bean 형태로 반환해줍니다.
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member bean = null; //찾고자 하는 회원의 정보

        String sql = "select * from members where id = ?";//? 치환은 executeQuery 전에 치환해주면 된다.

        try{
            conn = this.getconnection();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, id);//첫번째 ?를 id로 치환해줘라는 구문
            rs=pstmt.executeQuery();

            if(rs.next()){ //한건 발견됨
                bean = new Member(); //찾은 사람이 있어야 객체생성

                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setPassword(rs.getString("password"));
                bean.setGender(rs.getString("gender"));
                bean.setBrith(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(conn != null){conn.close();}
                if(pstmt != null){pstmt.close();}
                if(rs != null){rs.close();}
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return bean;
    }

    public int deleteData(String id) {//기본키를 사용하여 회원 탈퇴를 시도합니다.
        int cnt = -1;
        String sql = "delete from members where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;


        try{
            conn=this.getconnection();
            pstmt =conn.prepareStatement(sql);
            pstmt.setString(1,id);

            cnt=pstmt.executeUpdate();

            conn.commit();

        }catch (Exception ex){
            try{
                conn.rollback();
            }catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex.printStackTrace();
        }finally {
                try{
                    if(conn != null){conn.close();}
                    if(pstmt != null){pstmt.close();}
                }catch (Exception ex){
                    ex.printStackTrace();
            }

        }
        return cnt;
    }
}
