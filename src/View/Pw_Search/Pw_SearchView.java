/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Pw_Search;

import Controller.Pw_SearchController;
import Controller.LoginDto;
import Model.ProfileManagerModeDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Model.PwSearchDao;

/*
 *
 * 비밀번호 찾기 화면
 */
public class Pw_SearchView extends JFrame implements ActionListener {

    private PwSearchDao pwsearchdao;
    JPanel p;
    JLabel l1, l2;
    JTextField id, name;
    JButton b1, b2;
    boolean isSuccess;

    public Pw_SearchView() {
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.white);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("비밀번호찾기");

        l1 = new JLabel("아이디 : ");
        l1.setBounds(150, 130, 100, 30);
        l1.setLayout(null);
        l1.setBackground(Color.white);

        l2 = new JLabel("이름 : ");
        l2.setBounds(150, 175, 100, 30);
        l2.setLayout(null);
        l2.setBackground(Color.white);

        id = new JTextField();
        id.setBounds(230, 130, 150, 30);
        id.setLayout(null);
        id.setBackground(Color.white);

        name = new JTextField();
        name.setBounds(230, 175, 150, 30);
        name.setLayout(null);
        name.setBackground(Color.white);

        b1 = new JButton("확인");
        b1.setBounds(165, 245, 95, 30);
        b1.setLayout(null);
        b1.setBackground(Color.white);

        b2 = new JButton("취소");
        b2.setBounds(305, 245, 95, 30);
        b2.setLayout(null);
        b2.setBackground(Color.white);
        p.add(l1);
        p.add(l2);
        p.add(id);
        p.add(name);
        p.add(b1);
        p.add(b2);
        add(p);

        b1.addActionListener(this);
        b1.setActionCommand("add");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("add")) {

                    String uid = id.getText();
                    String uname = name.getText();
                    Pw_SearchController psc = new Pw_SearchController();
                    psc.addAction(uid, uname);
                }

            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
