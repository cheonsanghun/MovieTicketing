/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManagerMode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.FileNotFoundException;

/**
 *
 * @author Admin
 */
public class ManagerLogin extends JFrame {

    JButton login, back;
    JPanel j;
    JLabel word, infor, infor2;
    JPasswordField pwIn;

    public ManagerLogin() {
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("관리자 로그인 화면");
        setVisible(true);

        j = new JPanel();
        j.setBackground(Color.white);
        j.setLayout(null);

        login = new JButton("로그인");
        login.setBounds(200, 180, 100, 30);
        login.setBackground(Color.white);
        login.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        login.setLayout(null);

        back = new JButton("뒤로가기");
        back.setBounds(385, 332, 100, 30);
        back.setBackground(Color.white);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        word = new JLabel("비밀번호 : ");
        word.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        word.setBounds(100, 120, 80, 25);

        pwIn = new JPasswordField();
        pwIn.setBounds(180, 120, 200, 30);

        infor = new JLabel("관리자 로그인하는 화면 입니다 !");
        infor2 = new JLabel("관리자 전용 비밀번호를 입력해주세요.");
        infor.setBounds(155, 40, 250, 30);
        infor2.setBounds(140, 60, 250, 30);
        infor.setLayout(null);
        infor2.setLayout(null);

        j.add(word);
        j.add(pwIn);
        j.add(login);
        j.add(back);
        j.add(infor);
        j.add(infor2);
        add(j);
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                String PW = "010203";
                String input = new String(pwIn.getPassword());

                if (input.equals(PW)) {

                    JOptionPane.showMessageDialog(null, "로그인 확인", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
                    setVisible(false);

                    ManagerMode Managermode = new ManagerMode();

                } else {

                    String s1 = "<html><div width='115px' align='center'>";
                    String s2 = "</div></html>";
                    // 중앙 정렬

                    String errorms = s1 + "비밀번호가 틀렸습니다." + s2;
                    setVisible(true);
                    JLabel ms = new JLabel(errorms);
                    JOptionPane.showMessageDialog(null, ms, "비밀번호 오류", JOptionPane.WARNING_MESSAGE);

                }
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
