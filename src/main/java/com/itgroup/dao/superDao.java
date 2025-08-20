package com.itgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class superDao {
    public superDao() {
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




}
