/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moiveticketing;

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

/**
 *
 * @author Admin
 */
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

    LoginFrame() {
        p = new JPanel();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("로그인 화면");
        setVisible(true);
        
        b1 = new JButton("로그인");
        b1.setBounds(500, 180, 100, 30);
        b1.setLayout(null);
        b2 = new JButton("회원가입");
        b2.setBounds(500, 240, 100, 30);
        b2.setLayout(null);

        back = new JButton("뒤로가기");
        back.setBounds(685, 431, 100, 30);
        back.setLayout(null);

        l1 = new JLabel("아이디 : ");
        l1.setBounds(200, 180, 100, 30);
        l1.setLayout(null);
        l2 = new JLabel("비밀번호 : ");
        l2.setBounds(200, 240, 100, 30);
        l2.setLayout(null);

        id = new JTextField();
        id.setBounds(275, 180, 185, 30);
        id.setLayout(null);

        pw = new JTextField();
        pw.setBounds(275, 240, 185, 30);
        pw.setLayout(null);

        add(b1);
        add(b2);
        add(back);
        add(l1);
        add(l2);
        add(id);
        add(pw);
        add(p);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                String uid = id.getText();
                String upw = pw.getText();
                 try {
                     String sql = String.format("select pw from profile where id ='%s' and pw = '%s'",uid,upw);	
                    final String driver = "org.mariadb.jdbc.Driver";
		final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "test";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
                    conn= DriverManager.getConnection(DB_URL,"root", "12341234");                   
                        stmt = conn.prepareStatement(sql);
                       rs=stmt.executeQuery();     
                       rs.next();
                       
               if(uid.length()==0){
                     JOptionPane.showMessageDialog(null, "아이디를 입력하셔야 합니다.", "아이디 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
               }  
                if(upw.length()==0){
                     JOptionPane.showMessageDialog(null, "비밀번호를 입력하셔야 합니다.", "비밀번호 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
               }
               if (uid.length() == 0 && upw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
               
                 
                    if(pw.equals(rs.getString(1)))
                         System.out.println("성공 !");
                        JOptionPane.showMessageDialog(null, "로그인 성공 !","로그인 확인!",JOptionPane.DEFAULT_OPTION);
                      
                                    
                } 
                catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다", "로그인 실패", 1);
				System.out.println("SQLException" + ex);
                }
                 if(uid.length()==0 && upw.length()==0){
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.", "로그인 오류", JOptionPane.PLAIN_MESSAGE);
                }
                if(uid.length()==0 &&upw.length()!=0){
                    JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "로그인 오류", JOptionPane.PLAIN_MESSAGE);
                }
                  if(uid.length()!=0 &&upw.length()==0){
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "로그인 오류", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Membership membership = new Membership();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
            }
        });
    }
}
