package View;

import Controller.LoginController;
import Controller.LoginController.LoginStateMachine;
import Controller.LoginDto;
import Controller.LoginDto;
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
import View.FirstView;
import View.MembershipView;
import View.Pw_SearchView;
import Model.Factory.TheaterPanel;

public class LoginFrameView extends JFrame {

    List<LoginDto> companys;
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;

    JButton b1, b2, b3;
    JLabel l1, l2;
    JTextField id, pw;
    JPanel p;
    JButton back;
    JFrame frame;
    //상태패턴
    
    //해당 클래스 프레임
    public LoginFrameView() {
        frame=this;
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
                LoginController lc = new LoginController();
                LoginController.LoginStateMachine stateMachine = lc.new LoginStateMachine(uid, upw);
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
          
                  
                MembershipView membership = new MembershipView();
          
            }
        });
         b3 = new JButton("비밀번호 찾기");
        b3.setBackground(Color.white);
        b3.setBounds(500, 340, 150, 30);
        b3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
           b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                  
                new Pw_SearchView();
          
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
                FirstView home = new FirstView();
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
       p.add(b3);
        add(p);
    }
}
