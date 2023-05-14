/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import ManagerMode.ManagerLogin;
import Login.LoginFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JFrame {

    JButton b1, b2;
    JPanel j;

    public Home() {
          j = new JPanel();
          j.setLayout(null);  
          j.setBackground(Color.white);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("모드 선택");

      

     
        
        b1 = new JButton("사용자 모드");
        b1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b1.setBackground(Color.white);
        b1.setBounds(120, 230, 200, 100);
        b1.setLayout(null);
       
       

        b2 = new JButton("관리자 모드");
        b2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b2.setBackground(Color.white);
        b2.setBounds(450, 230, 200, 100);
        b2.setLayout(null);
        
          ImageIcon Logo = new ImageIcon("C:\\Users\\Admin\\OneDrive\\바탕 화면\\MovieTicketing\\src\\Main\\main.png");
        JLabel HDDLogo = new JLabel(Logo);
        HDDLogo.setBounds(300,0,250,250);
        HDDLogo.setLayout(null);
       
       
        j.add(b1);
        j.add(b2);
        j.add(HDDLogo);
        add(j);

          setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame loginFrame = new LoginFrame();
                setVisible(false);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerLogin managerlogin = new ManagerLogin();
                setVisible(false);
            }
        });

    }
}
