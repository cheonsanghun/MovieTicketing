/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Login;

import View.FirstView;
import Model.observer.NoticeBoardView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Model.Factory.TheaterPanel;
import View.MyPageView.MyPageView;

/**
 *
 * @author Admin
 */
public class LoginSuccessView extends JFrame {

    JPanel j;
    JButton movie, noticeboard, back, reserverok;
    FirstView home;
    public LoginSuccessView() {
        j = new JPanel();
        j.setLayout(null);
        j.setBackground(Color.white);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ??硫? 媛??대?? 諛곗?
        setTitle("선택 화면");

        movie = new JButton("영화 예매");
        movie.setLayout(null);
        movie.setBackground(Color.white);
        movie.setBounds(35, 150, 150, 50);
        
         back = new JButton("뒤로 가기");
        back.setLayout(null);
        back.setBackground(Color.white);
        back.setBounds(484, 331, 100, 30);
        
        reserverok = new JButton("마이 페이지");
        reserverok.setLayout(null);
        reserverok.setBackground(Color.white);
        reserverok.setBounds(215, 150, 150, 50);
        
        noticeboard = new JButton("게시판 이동");
        noticeboard.setLayout(null);
        noticeboard.setBackground(Color.white);
        noticeboard.setBounds(395, 150, 150, 50);
        
        j.add(reserverok);
        j.add(back);
        j.add(movie);
        j.add(noticeboard);
        add(j);
        setVisible(true);

        noticeboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new NoticeBoardView();
            }
        });
        reserverok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MyPageView();
            }
        });
        movie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame frame = new JFrame("영화 예매 시스템");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // TheaterPanel 생성 및 추가
                TheaterPanel theaterPanel = new TheaterPanel();
                frame.getContentPane().add(theaterPanel);

                // JFrame 크기 및 위치 설정 후 보이도록 함
                frame.setSize(800, 500);
                frame.setBackground(Color.white);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
         back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
            }
        });
    }
}
