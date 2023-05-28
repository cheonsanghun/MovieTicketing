/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import View.Login.LoginFrameView;
import Model.Proxy.ManagerLoginView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
    /*
    프로그램의 맨 처음 화면
*/
public class FirstView extends JFrame {

    JButton b1, b2;
    JPanel j;

    public FirstView() {
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
        
          ImageIcon Logo = new ImageIcon("C:\\Users\\Admin\\OneDrive\\바탕 화면\\MovieTicketing\\src\\image\\main.png");
        JLabel movieLogo = new JLabel(Logo);
        movieLogo.setBounds(300,0,250,250);
        movieLogo.setLayout(null);
       
       
        j.add(b1);
        j.add(b2);
        j.add(movieLogo);
        add(j);

          setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrameView loginFrame = new LoginFrameView();
                setVisible(false);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerLoginView managerlogin = new ManagerLoginView();
                setVisible(false);
            }
        });

    }
}
