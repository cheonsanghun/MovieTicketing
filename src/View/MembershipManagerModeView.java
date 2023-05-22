/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ManagerModeController;
import Model.ProfileManagerModeDao;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Controller.LoginDto;
import Controller.MouseLisstenerController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MembershipManagerModeView extends JFrame implements ActionListener, PropertyChangeListener {

    JPanel p;
    JButton back, insert, delete, update;
    JTable table;
    DefaultTableModel dft;
    JLabel mainname, l1, l2, l3, l4, l5, l6, l7;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    List<LoginDto> companys;
 
    MembershipManagerModeView() {
        //관리자화면 프레임 gui 
        p = new JPanel();
        p.setLayout(null);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 가운데 배치
        setTitle("회원 정보 관리");
        setVisible(true);

        l1 = new JLabel("name");
        l2 = new JLabel("age");
        l3 = new JLabel("id");
        l4 = new JLabel("pw");
        l5 = new JLabel("phone");
        l6 = new JLabel("mail");
        l7 = new JLabel("address");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);
        t4 = new JTextField(10);
        t5 = new JTextField(10);
        t6 = new JTextField(10);
        t7 = new JTextField(10);

        l1.setBounds(67, 150, 60, 30);
        l2.setBounds(167, 150, 60, 30);
        l3.setBounds(271, 150, 60, 30);
        l4.setBounds(377, 150, 60, 30);
        l5.setBounds(490, 150, 60, 30);
        l6.setBounds(628, 150, 60, 30);
        l7.setBounds(755, 150, 60, 30);
        t1.setBounds(100, 150, 60, 30);
        t2.setBounds(191, 150, 60, 30);
        t3.setBounds(288, 150, 80, 30);
        t4.setBounds(398, 150, 80, 30);
        t5.setBounds(528, 150, 80, 30);
        t6.setBounds(658, 150, 80, 30);
        t7.setBounds(810, 150, 110, 30);
        mainname = new JLabel("회원정보");
        mainname.setBounds(465, 10, 200, 50);
        mainname.setLayout(null);

        back = new JButton("뒤로가기");
        back.setBackground(Color.white);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        back.setBounds(885, 631, 100, 30);
        back.setLayout(null);

        insert = new JButton("추가");
        insert.setBackground(Color.white);
        insert.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        insert.setBounds(280, 110, 100, 30);
        insert.setLayout(null);

        update = new JButton("수정");
        update.setBackground(Color.white);
        update.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        update.setBounds(430, 110, 100, 30);
        update.setLayout(null);

        delete = new JButton("삭제");
        delete.setBackground(Color.white);
        delete.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        delete.setBounds(580, 110, 100, 30);
        delete.setLayout(null);

        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(t5);
        p.add(t6);
        p.add(t7);
        p.add(mainname);
        p.add(back);
        p.add(insert);
        p.add(update);
        p.add(delete);
        add(p);

        String[] colNames = {"이름", "나이", "아이디", "비밀번호", "폰번호", "메일", "주소"};
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

        pane.setBounds(65, 200, 850, 400);
        p.add(pane);
        // 테이블 셀에 수정작업이 일어났는지 감시할 리스너 등록
        table.addPropertyChangeListener(this);
        //테이블에 회원목록 추가하기
        showMembers();
           
        
        table.addMouseListener((MouseListener) new MouseLisstenerController());
       
        insert.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        insert.setActionCommand("add");
        delete.setActionCommand("delete");
        update.setActionCommand("수정");

        insert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("add")) {
                     dft.setRowCount(0);
                       showMembers();
                       String name = t1.getText();
        String age = t2.getText();
        String id = t3.getText();
        String pw = t4.getText();
        String phone = t5.getText();
        String mail = t6.getText();
        String address = t7.getText();
        ManagerModeController mc = new ManagerModeController();
                    mc.addAction(name, age, id, pw, phone, mail, address);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("delete")) {
                    deleteAction();
                }
                
            }
        });
      
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FirstView home = new FirstView();
                home.setVisible(true);
                setVisible(false);
            }
        });
    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //선택된 row 의 index 를 얻어와서 
            int index = table.getSelectedRow();
            //인덱스에 해당하는 model 에서 입력된 이름과 주소를 읽어온다. 
            String name = (String) dft.getValueAt(index, 0); //2번째 인덱스의 주소를 읽어옴
            String age = (String) dft.getValueAt(index, 1); //1번째 인덱스의 이름을 읽어옴
            String id = (String) dft.getValueAt(index, 2); //0번째 인덱스의 번호를 읽어옴
            String pw = (String) dft.getValueAt(index, 3); //1번째 인덱스의 이름을 읽어옴
            String phone = (String) dft.getValueAt(index, 4); //1번째 인덱스의 이름을 읽어옴
            String mail = (String) dft.getValueAt(index, 5); //3번째 인덱스의 입사일을 읽어옴
            String address = (String) dft.getValueAt(index, 6); //3번째 인덱스의 입사일을 읽어옴
            //DB 에 수정 반영

            //빌더 패턴 
            LoginDto dto = new LoginDto.Builder()
                    .setName(name)
                    .setAge(age)
                    .setId(id)
                    .setPw(pw)
                    .setPhone(phone)
                    .setMail(mail)
                    .setAddress(address)
                    .build();
            new ProfileManagerModeDao().update(dto);
        }

    }
    //회원목록 전체 출력
    public void showMembers() {
        companys = new ProfileManagerModeDao().getList();
        for (LoginDto tmp : companys) {
            Object[] row = {tmp.getName(), tmp.getAge(), tmp.getId(), tmp.getPw(), tmp.getPhone(), tmp.getMail(), tmp.getAddress()};
            dft.addRow(row);
        }
    }

    //삭제 메서드
    private void deleteAction() {
        //선택된 row 의 인덱스를 얻어와서 
        int index = table.getSelectedRow();
         if( index <0 ) {
             JOptionPane.showMessageDialog(this,"삭제할 행을 선택해 주세요.");
        }
        if (index == -1) {
            return;
        }
       
        //DB 에서 삭제하고
        LoginDto dto = new LoginDto();
        dto.setPw(companys.get(index).getPw());
        new ProfileManagerModeDao().delete(dto);
        //다시 출력
        dft.setRowCount(0);
        showMembers();

        //작업의 성공여부를 리턴 받는다. 
        boolean isSuccess = new ProfileManagerModeDao().delete(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "삭제 했습니다.");
        } else {
            JOptionPane.showMessageDialog(this, "저장 했습니다.");
        }

    }

    public void actionPerformed(ActionEvent e) {

    }
}
