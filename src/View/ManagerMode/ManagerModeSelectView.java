/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ManagerMode;

import View.ManagerMode.NoticeBoardManagerModeView;
import View.ManagerMode.MovieGenreManagerModeView;
import Controller.Main;
import View.FirstView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * 관리자 로그인에 성공 했을 때 각각의 모드를 들어갈 수 있게 해주는 선택 화면
 */
public class ManagerModeSelectView extends JFrame {

    JPanel p;
    JButton b1, b2, b3, back;

    public ManagerModeSelectView() {
        p = new JPanel();
        p.setLayout(null);
        setSize(600, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("관리자 화면");
        setVisible(true);

        b1 = new JButton("회원 정보 관리");
        b1.setLayout(null);
        b1.setBounds(20, 20, 150, 50);
        b1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b1.setBackground(Color.white);

        b2 = new JButton("영화 관리");
        b2.setLayout(null);
        b2.setBounds(215, 20, 150, 50);
        b2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b2.setBackground(Color.white);

        b3 = new JButton("게시판 관리");
        b3.setLayout(null);
        b3.setBounds(415, 20, 150, 50);
        b3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b3.setBackground(Color.white);

        back = new JButton("뒤로가기");
        back.setLayout(null);
        back.setBounds(494, 81, 100, 30);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        back.setBackground(Color.white);
        p.add(back);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        add(p);
        setVisible(true);

        b1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MembershipManagerModeView();
            }
        });

        b2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MovieGenreManagerModeView();
            }
        });

        b3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new NoticeBoardManagerModeView();
            }
        });
    
      back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
              setVisible(false);
              new FirstView();
            }
        });
    }

    public static void main(String[] args) {
        new ManagerModeSelectView();
    }
}
