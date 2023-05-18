/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.LoginDto;
import Controller.ManagerModeController;
import Model.ProfileManagerModeDao;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kjbg4
 */
public class MovieManagerMode extends JFrame {

    JPanel p;
    JButton insert, delete, back;
    List<LoginDto> companys;
    DefaultTableModel dft;
    JTable table;
    JLabel label1, label2;
    JTextField genre, movie;

    MovieManagerMode() {
        //관리자화면 프레임 gui 
        p = new JPanel();
        p.setLayout(null);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("영화 관리");
        setVisible(true);

        insert = new JButton("추가하기");
        insert.setLayout(null);
        insert.setBounds(210, 20, 150, 40);

        delete = new JButton("삭제하기");
        delete.setLayout(null);
        delete.setBounds(410, 20, 150, 40);

        back = new JButton("뒤로가기");
        back.setLayout(null);
        back.setBounds(686, 431, 100, 30);

        label1 = new JLabel("장르 : ");
        label1.setLayout(null);
        label1.setBounds(200, 80, 100, 30);

        label2 = new JLabel("영화 : ");
        label2.setLayout(null);
        label2.setBounds(380, 80, 100, 30);

        genre = new JTextField();
        genre.setLayout(null);
        genre.setBounds(245, 80, 125, 30);

        movie = new JTextField();
        movie.setLayout(null);
        movie.setBounds(425, 80, 125, 30);

        p.add(label1);
        p.add(genre);
        p.add(movie);
        p.add(label2);
        p.add(back);
        p.add(insert);
        p.add(delete);
        add(p);

       
        String[] colNames = {"장르", "영화 이름"};
        // 테이블에 출력할 데이터를 가지고있는 디폴트모델테이블
        dft = new DefaultTableModel(colNames, 0) {
            //수정 가능 여부를 리턴하는 메소드
            public boolean isCellEditable(int row, int column) {
                //0번 칼럼만 수정 불가능하도록 false를 리턴해주고
                if (column == 0 || column == 3) {
                    return false;
                } else {
                    //나머지는 모두 수정 가능하도록 true를 리턴한다.
                    return true;
                }

            }
        };

        table = new JTable(dft);
        // 스크롤 생성
        JScrollPane pane = new JScrollPane(table);

        pane.setBounds(185, 140, 400, 300);
        p.add(pane);
        // 테이블 셀에 수정작업이 일어났는지 감시할 리스너 등록
        setVisible(true);

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Home();
                setVisible(false);
            }
        });
    }

}
