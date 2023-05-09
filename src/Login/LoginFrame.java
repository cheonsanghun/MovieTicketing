package Login;

import LoginDto.LoginDto;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Main.Home;
import Membership.Membership;

public class LoginFrame extends JFrame {

    List<LoginDto> companys;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    JButton b1, b2;
    JLabel l1, l2;
    JTextField id, pw;
    JPanel p;
    JButton back;

    //상태패턴
    public interface State {

        void execute();
    }

    public class LoginSuccessState implements State {

        @Override
        public void execute() {
            JOptionPane.showMessageDialog(null, "로그인 성공 !", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
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

        private State state;
        private String uid;
        private String upw;

        public LoginStateMachine(String uid, String upw) {
            this.uid = uid;
            this.upw = upw;
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

    //해당 클래스 프레임
    public LoginFrame() {
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.white);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("로그인 화면");
        setVisible(true);

        b1 = new JButton("로그인");
        b1.setBackground(Color.white);
        b1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b1.setBounds(500, 180, 100, 30);
        b1.setLayout(null);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uid = id.getText();
                String upw = pw.getText();
                LoginStateMachine stateMachine = new LoginStateMachine(uid, upw);
                stateMachine.login();
                stateMachine.state.execute();
            }
        });

        b2 = new JButton("회원가입");
        b2.setBackground(Color.white);
        b2.setBounds(500, 240, 100, 30);
        b2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                  
                Membership membership = new Membership();
          
            }
        });

        l1 = new JLabel("아이디 : ");
        l1.setBackground(Color.white);
        l1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        l1.setBounds(200, 180, 100, 30);

        l2 = new JLabel("비밀번호 : ");
        l2.setBackground(Color.white);
        l2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        l2.setBounds(200, 240, 100, 30);

        id = new JTextField();
        id.setBounds(275, 180, 185, 30);

        pw = new JTextField();
        pw.setBounds(275, 240, 185, 30);

        back = new JButton("돌아가기");
        back.setBackground(Color.white);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        back.setBounds(685, 431, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                setVisible(false);
            }
        });

       p.add(l1);
        p.add(l2);
        p.add(id);
        p.add(pw);
        p.add(b1);
        p.add(b2);
       p.add(back);
        add(p);
    }
}
