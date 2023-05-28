/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Proxy;

import Model.Proxy.GuardProxy;
import View.ManagerMode.ManagerModeSelectView;
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
import View.ManagerMode.ManagerModeSelectView;

import View.ManagerMode.ManagerModeSelectView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * 맨 처음 화면에서 관리자 모드 버튼을 클릭했을 때 접근을 하기 위해 관리자 로그인 화면을 보여주는 부분
 */
public class ManagerLoginView extends JFrame {

    //필드
    JButton login, back;
    JPanel j;
    JLabel word, infor, infor2;
    JPasswordField pwIn;
    private GuardProxy proxy;

    public ManagerLoginView() {
        //gui
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ??? ???? ???
        setTitle("관리자 로그인");
        setVisible(true);

        j = new JPanel();
        j.setBackground(Color.white);
        j.setLayout(null);

        login = new JButton("로그인");
        login.setBounds(200, 180, 100, 30);
        login.setBackground(Color.white);
        login.setFont(new Font("하얀 고딕", Font.BOLD, 14));
        login.setLayout(null);

        back = new JButton("뒤로가기");
        back.setBounds(385, 332, 100, 30);
        back.setBackground(Color.white);
        back.setFont(new Font("하얀 고딕", Font.BOLD, 14));

        word = new JLabel("비밀번호 : ");
        word.setFont(new Font("하얀 고딕", Font.BOLD, 14));
        word.setBounds(100, 120, 80, 25);

        pwIn = new JPasswordField();
        pwIn.setBounds(180, 120, 200, 30);

        infor = new JLabel("관리자 전용 로그인 화면입니다 !");
        infor2 = new JLabel("관리자 전용 비밀번호를 입력해주세요 .");
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

        proxy = new GuardProxy();
        // 로그인 버튼을 클릭했을 때 발생하는 이벤트
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

                String input = new String(pwIn.getPassword());

                if (proxy.check(input)) {

                    JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다", "로그인 성공!", JOptionPane.DEFAULT_OPTION);
                    setVisible(false);

                    new ManagerModeSelectView();

                } else {

                    String s1 = "<html><div width='115px' align='center'>";
                    String s2 = "</div></html>";
                    // ??? ????

                    String errorms = s1 + "Error." + s2;
                    setVisible(true);
                    JLabel ms = new JLabel(errorms);
                    JOptionPane.showMessageDialog(null, ms, "로그인에 실패 하셨습니다.", JOptionPane.WARNING_MESSAGE);

                }
            }
        });
        // 뒤로가기 버튼을 클릭했을 때 발생하는 이벤트
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });
    }

}
