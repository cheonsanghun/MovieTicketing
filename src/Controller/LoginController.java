/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.Factory.TheaterPanel;
import DbConnect.DbConnect;
import View.LoginSuccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author kjbg4
 */
public class LoginController {
    
    private Connection conn;
    PreparedStatement stmt = null;
        ResultSet rs;
    JFrame frame;
  
    public interface State {

        void execute();
    }

    public class LoginSuccessState implements State {
        
        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, "로그인 성공 !", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
              new LoginSuccess();
          
        }
    }

    public class LoginFailureState implements State {

        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다", "로그인 실패", 1);

        }
    }

    public class LoginErrorState implements State {

        private String errorMessage;

        public LoginErrorState(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, errorMessage, "로그인 오류", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public class LoginStateMachine {

        private State loginSuccessState = new LoginSuccessState();
        private State loginFailureState = new LoginFailureState();

        public State state;
        private String uid;
        private String upw;

        public LoginStateMachine(String uid, String upw) {
            this.uid = uid;
            this.upw = upw;
        }

        public LoginStateMachine() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public void setState(State state) {
            this.state = state;
        }

        //로그인 일치여부
        public void login() {
            String str = "...";
            try {
                if (uid.replaceAll(" ", "").equals("") || upw.replaceAll(" ", "").equals("")) {
                    JOptionPane.showMessageDialog(null, "공백입니다. 아이디 또는 비밀번호를 입력하세요.", "입력 오류", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (uid.length() == 0) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력하셔야 합니다.", "아이디 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (upw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력하셔야 합니다.", "비밀번호 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (uid.length() == 0 && upw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                //db에 있는 데이터로 db드라이버 연결 후 데이터 일치하는지 확인여부
                final String driver = "org.mariadb.jdbc.Driver";
                final String DB_IP = "localhost";
                final String DB_PORT = "3306";
                final String DB_NAME = "test";
                final String DB_URL
                        = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
                conn = DriverManager.getConnection(DB_URL, "root", "12341234");

                String sql = String.format("select pw from profile where id ='%s' and pw = '%s'", uid, upw);
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                rs.next();

                if (upw.equals(rs.getString(1))) {
                    setState(loginSuccessState);
                } else {
                    setState(loginFailureState);
                }

            } catch (SQLException ex) {
                setState(loginFailureState);
                System.out.println("SQLException" + ex);
            }
        }
    }
}
