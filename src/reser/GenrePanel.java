
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reser;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author USER
 */
public abstract class GenrePanel extends JPanel implements MovieFactory {
    String dbDriver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:mysql://127.0.0.1:3306/movie_db";
    String dbUser = "root";
    String dbPassword = "ll8055001!";
    Connection dbconn = null;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ll8055001!";

    private static final String SELECT_GENRES = "SELECT DISTINCT genre.g_name FROM movie INNER JOIN genre ON movie.g_id = genre.g_id INNER JOIN theater ON movie.t_id = theater.t_id WHERE theater.t_name = '%s'";

    private JLabel titleLabel;
    private JList<String> genreList;
    private JComboBox<String> genreComboBox;
    private DefaultListModel<String> genreModel;

    public GenrePanel(String theaterName) {
        
        
        titleLabel = new JLabel("장르를 선택해주세요:");
         ArrayList<String> genreNames = new ArrayList<>();
        //genreModel = new DefaultListModel<>();
        //genreList = new JList<>(genreModel);
        //JScrollPane scrollPane = new JScrollPane(genreList);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(String.format(SELECT_GENRES, theaterName))) {
            while (rs.next()) {
                //String genreName  = rs.getString("g_name");
                //genreModel.addElement(genreName);
                genreNames.add(rs.getString("g_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        String[] theaterArray = genreNames.toArray(new String[genreNames.size()]);
        
         // 극장 선택을 위한 JList 생성
        JList<String> genreList = new JList<>(theaterArray);
        genreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        genreList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedGenre = (String) genreList.getSelectedValue();
                    SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedGenre);

                    // 부모 패널에 현재 보여지고 있는 패널을 제거하고, 장르 선택 패널을 추가함
            Container parent = GenrePanel.this.getParent();
            Component currentPanel = parent.getComponent(0);
            parent.remove(currentPanel);
            parent.add(genrePanel);

            // 변경된 패널 구성을 반영
            parent.revalidate();
            parent.repaint();
                }
            }
        });

        add(titleLabel);
        add(new JScrollPane(genreList));
        
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == genreList) {
            String genreName = (String) genreList.getSelectedValue();

            // 선택한 극장을 이용하여 장르 선택 패널 생성
            SelectedMoviePanel moviePanel = new SelectedMoviePanel(genreName);

            // 부모 패널에 장르 선택 패널을 추가하고, 카드 레이아웃으로 전환
            this.getParent().add(moviePanel, "Movie");
            CardLayout cl = (CardLayout) this.getParent().getLayout();
            cl.show(this.getParent(), "Movie");
        }
    }

}
