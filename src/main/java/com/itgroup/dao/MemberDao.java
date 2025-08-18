package com.itgroup.dao;

import java.sql.*;

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
}
