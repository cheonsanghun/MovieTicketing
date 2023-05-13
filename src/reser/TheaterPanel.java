/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reser;

/**
 *
 * @author USER
 */
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TheaterPanel extends JPanel implements MovieFactory, ActionListener {

    // 데이터베이스 연결 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ll8055001!";

    // 콤보박스에서 선택 가능한 극장 정보를 가져오기 위한 쿼리
    private static final String SELECT_THEATERS = "SELECT * FROM theater";

    // 극장 선택을 위한 레이블과 콤보박스
    private JLabel titleLabel;
    private JComboBox<String> theaterComboBox;

    public TheaterPanel() {

        
        // 극장 선택을 위한 레이블 생성
        titleLabel = new JLabel("영화관을 선택해주세요:");

        // 데이터베이스에서 극장 정보를 가져와서 리스트에 추가
        ArrayList<String> theaterNames = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM theater")) {
            while (rs.next()) {
                theaterNames.add(rs.getString("t_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] theaterArray = theaterNames.toArray(new String[theaterNames.size()]);

        // 극장 선택을 위한 JList 생성
        JList<String> theaterList = new JList<>(theaterArray);
        theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 극장 선택 레이블과 JList를 패널에 추가
        add(titleLabel);
        add(new JScrollPane(theaterList));

        // 극장 선택 JList의 액션 리스너 등록
        theaterList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            String selectedTheater = theaterList.getSelectedValue();

            // 선택한 극장을 이용하여 장르 선택 패널 생성
            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

            // 부모 패널에 현재 보여지고 있는 패널을 제거하고, 장르 선택 패널을 추가함
            Container parent = TheaterPanel.this.getParent();
            Component currentPanel = parent.getComponent(0);
            parent.remove(currentPanel);
            parent.add(genrePanel);

            // 변경된 패널 구성을 반영
            parent.revalidate();
            parent.repaint();
        }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == theaterComboBox) {
            String selectedTheater = (String) theaterComboBox.getSelectedItem();

            // 선택한 극장을 이용하여 장르 선택 패널 생성
            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

            // 부모 패널에 장르 선택 패널을 추가하고, 카드 레이아웃으로 전환
            this.getParent().add(genrePanel, "Genre");
            CardLayout cl = (CardLayout) this.getParent().getLayout();
            cl.show(this.getParent(), "Genre");
        }
    }

    @Override
    public TheaterPanel createTheaterPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GenrePanel createGenrePanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MoviePanel createMoviePanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}