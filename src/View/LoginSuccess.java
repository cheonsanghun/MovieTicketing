/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Model.Factory.TheaterPanel;

/**
 *
 * @author Admin
 */
public class LoginSuccess extends JFrame {
    JPanel j;
    JButton movie, noticeboard;
    
    public LoginSuccess(){
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
        movie.setBounds(110,150,150,50);
        
         noticeboard = new JButton("게시판 이동");
        noticeboard.setLayout(null);
         noticeboard.setBackground(Color.white);
        noticeboard.setBounds(315,150,150,50);
        
        
        j.add(movie);
        j.add(noticeboard);
        add(j);
        setVisible(true);
        
            noticeboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new NoticeBoard();
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
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            }
        });
    }
    
    
        
}
