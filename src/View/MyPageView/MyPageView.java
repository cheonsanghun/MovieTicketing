/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MyPageView;

import Controller.LoginDto;
import Controller.MyPageDto;
import Model.MyPageDao;
import Model.ProfileManagerModeDao;
import View.Login.LoginSuccessView;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kjbg4
 */
public class MyPageView extends JFrame implements PropertyChangeListener {

    JPanel p;
    JButton back;
    JLabel titleLabel, reseverok;
    DefaultTableModel dft;
    JTable table;
    List<MyPageDto> mypages;
    boolean isSuccess;

    public MyPageView() {
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(Color.white);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("회원가입 화면");

        back = new JButton("뒤로가기");
        back.setLayout(null);
        back.setBounds(686, 431, 100, 30);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        back.setBackground(Color.white);

        titleLabel = new JLabel("마이 페이지");
        titleLabel.setLayout(null);
        titleLabel.setBounds(317, 5, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));

        reseverok = new JLabel("예약 정보");
        reseverok.setLayout(null);
        reseverok.setBounds(333, 85, 200, 40);
        reseverok.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));

        String[] colNames = {"좌석 행", "좌석 열", "극장번 호", "장르 번호", "영화 번호", "결제 번호"};
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

        pane.setBounds(135, 125, 500, 300);
        p.add(pane);
        p.add(reseverok);
        p.add(titleLabel);
        p.add(back);
        add(p);
        table.addPropertyChangeListener(this);
        //테이블에 회원목록 추가하기
        showMembers();
        setVisible(true);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginSuccessView();

            }
        });
    }

    public static void main(String[] args) {
        new MyPageView();
    }

    //회원목록 전체 출력
    public void showMembers() {
        mypages = new MyPageDao().getList();
        for (MyPageDto tmp : mypages) {
            Object[] row = { tmp.getS_row(),tmp.getS_col(),tmp.getT_id(),tmp.getG_id(), tmp.getM_id(),tmp.getCardnum()};
            dft.addRow(row);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //선택된 row 의 index 를 얻어와서 
            int index = table.getSelectedRow();
            //인덱스에 해당하는 model 에서 입력된 이름과 주소를 읽어온다. 
            int cardnum = (int) dft.getValueAt(index, 0); //2번째 인덱스의 주소를 읽어옴
            int g_id = (int) dft.getValueAt(index, 1); //1번째 인덱스의 이름을 읽어옴
            int m_id = (int) dft.getValueAt(index, 2); //0번째 인덱스의 번호를 읽어옴
            int s_col = (int) dft.getValueAt(index, 3); //1번째 인덱스의 이름을 읽어옴
            int s_row = (int) dft.getValueAt(index, 4); //1번째 인덱스의 이름을 읽어옴
            int t_id = (int) dft.getValueAt(index, 5); //3번째 인덱스의 입사일을 읽어옴
          

            //빌더 패턴 
            MyPageDto dto = new MyPageDto.Builder()     
                    .sets_row(s_row)
                     .sets_col(s_col)
                     .sett_id(t_id)
                    .setg_id(g_id)
                    .setm_id(m_id)        
                    .setcardnum(cardnum)
                    .build();
        }

    }
  
}
