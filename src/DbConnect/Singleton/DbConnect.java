package DbConnect.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
   
/*
    데이트베이스 연동을 위한 클래스
    싱글톤 패턴 적용
*/
public class DbConnect {
    public static DbConnect instance;
    public Connection conn;

    public DbConnect() {
        try {
            // 오라클 드라이버 클래스 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // DB 연결 정보 설정
            final String DB_IP = "113.198.234.132";
            final String DB_PORT = "9090";
            final String DB_NAME = "moviedb";
            final String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

            // DB 연결 객체의 참조값 얻어와서 필드에 담기
            conn = DriverManager.getConnection(DB_URL, "jbg", "12341234");

            // 접속 성공 메시지 출력
            System.out.println("DB 접속 성공!");
        } catch (Exception e) {
            System.out.println("연결 실패");
        }
    }

  public static DbConnect getInstance() {
    if (instance == null) {
        synchronized (DbConnect.class) {
            if (instance == null) {
                instance = new DbConnect();
            }
        }
    }
    return instance;
}

    public Connection getConn() {
        return conn;
    }

}