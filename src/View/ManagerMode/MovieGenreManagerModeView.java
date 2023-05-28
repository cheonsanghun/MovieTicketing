/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ManagerMode;

import Controller.LoginDto;
import Controller.ManagerModeController;
import Controller.MovieGenreController;
import Controller.MovieGenreDto;
import Model.MovieGenreDao;
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
 *  영화 관리 화면
 * @author kjbg4
 */
public class MovieGenreManagerModeView extends JFrame implements ActionListener, PropertyChangeListener {
    //스윙 필드 
    JPanel p;
    JButton insert, delete, back;
    List<MovieGenreDto> companys;
    DefaultTableModel dft;
    JTable table;
    JLabel label1, label2;
    JTextField genre, movie;

    MovieGenreManagerModeView() {
     
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
        insert.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        insert.setBackground(Color.white);

        delete = new JButton("삭제하기");
        delete.setLayout(null);
        delete.setBounds(410, 20, 150, 40);
        delete.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        delete.setBackground(Color.white);

        back = new JButton("뒤로가기");
        back.setLayout(null);
        back.setBounds(686, 431, 100, 30);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        back.setBackground(Color.white);

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
                if (column == 0 || column == 1) {
                    return false;
                } else {
                    //나머지는 모두 수정 가능하도록 true를 리턴한다.
                    return true;
                }

            }
        };
        table = new JTable(dft);
        MovieGenreController controller = new MovieGenreController(dft, table);
        // 스크롤 생성
        JScrollPane pane = new JScrollPane(table);

        pane.setBounds(185, 140, 400, 300);
        p.add(pane);
        table.addPropertyChangeListener(this);
        //테이블에 회원목록 추가하기
        showMembers();

        insert.addActionListener(this);
        delete.addActionListener(this);

        insert.setActionCommand("add");
        delete.setActionCommand("delete");

        // 테이블 셀에 수정작업이 일어났는지 감시할 리스너 등록
        setVisible(true);

        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ManagerModeSelectView mmv = new ManagerModeSelectView();
                setVisible(false);
            }
        });
        insert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("add")) {

                    String g_name = genre.getText();
                    String m_name = movie.getText();
                    
                   
                    controller.addAction(m_name, g_name);
                    dft.setRowCount(0);
                    showMembers();
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
    }

    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChange()");
        System.out.println(evt.getPropertyName());
        if (evt.getPropertyName().equals("tableCellEditor")) {
            //선택된 row 의 index 를 얻어와서 
            int index = table.getSelectedRow();
            //인덱스에 해당하는 model 에서 입력된 이름과 주소를 읽어온다. 
            String movie = (String) dft.getValueAt(index, 0); //2번째 인덱스의 주소를 읽어옴
            String genre = (String) dft.getValueAt(index, 1); //1번째 인덱스의 이름을 읽어옴

            //빌더 패턴 
            MovieGenreDto dto = new MovieGenreDto.Builder()
                    .setMovie(movie)
                    .setGenre(genre)
                    .build();

        }
    }

    //회원목록 전체 출력
    public void showMembers() {
        MovieGenreDao dao = new MovieGenreDao();
        companys = dao.getList();
        dft.setRowCount(0);
        for (MovieGenreDto tmp : companys) {
            Object[] row = {tmp.getGenre(), tmp.getMovie()};
            dft.addRow(row);
        }
    }

    //삭제 메서드
    private void deleteAction() {
        //선택된 row 의 인덱스를 얻어와서 
        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "삭제할 행을 선택해 주세요.");
        }
        if (index == -1) {
            return;
        }

        //DB 에서 삭제하고
        MovieGenreDto dto = new MovieGenreDto();
        dto.setGenre(companys.get(index).getGenre());
        dto.setMovie(companys.get(index).getMovie());
        new MovieGenreDao().delete(dto);
        //다시 출력
        dft.setRowCount(0);
        showMembers();

        //작업의 성공여부를 리턴 받는다. 
        boolean isSuccess = new MovieGenreDao().delete(dto);

        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "삭제 했습니다.");
        } else {
            JOptionPane.showMessageDialog(this, "저장 했습니다.");
        }

    }

    public void actionPerformed(ActionEvent e) {

    }

}
