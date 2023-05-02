/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moiveticketing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home extends JFrame {

    JButton b1, b2;
    JPanel j;

    Home() {

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("모드 선택");
        setBackground(Color.white);
        setVisible(true);

        j = new JPanel();
        j.setBackground(Color.white);
        j.setLayout(null);

        b1 = new JButton("사용자 모드");
        b1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b1.setBackground(Color.white);
        b1.setLayout(null);

        b2 = new JButton("관리자 모드");
        b2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        b2.setBackground(Color.white);
        b2.setLayout(null);

        add(b1);
        add(b2);
        add(j);

        b1.setBounds(120, 200, 200, 100);
        b2.setBounds(450, 200, 200, 100);

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

            }
        });

    }
}
