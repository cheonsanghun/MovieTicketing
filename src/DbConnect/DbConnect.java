/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DbConnect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DbConnect {
      private Connection conn;
    
 
    public DbConnect(){
         try {
	    	 //오라클 드라이버 클래스 로딩(OracleDriver 클래스를 사용할 준비를 한다)
	         Class.forName("org.mariadb.jdbc.Driver");
	         //접속할 DB 의 정보
	       final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "test";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
	         //DB 연결 객체의 참조값 얻어와서 필드에 담기
	         conn=DriverManager.getConnection(DB_URL, "root", "12341234");
	         //예외가 발생하지 않고 여기까지 실행순서가 진행이되면 접속 성공이다.
	         System.out.println("Oracle DB 접속 성공!");
	         
	      }catch(Exception e){
                  System.out.println("연결 실패");
	      }
	}
    public Connection getConn() {
		return conn;
	}
  
          public static void main(String[] args) {
        DbConnect dbconnect = new DbConnect();
    }
}
