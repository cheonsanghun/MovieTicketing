/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
public abstract class MoviePanel extends JPanel implements MovieFactory {

    String dbDriver = "org.mariadb.jdbc.Driver";
    String dbUrl = "jdbc:mariadb://113.198.234.132:9090/moviedb";
    String dbUser = "jbg";
    String dbPassword = "12341234";
    Connection dbconn = null;

    private static final String DB_URL = "jdbc:mariadb://113.198.234.132:9090/moviedb";
    private static final String DB_USER = "jbg";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_MOVIES = "SELECT DISTINCT m.m_name  "
            + "FROM movie m "
            + "JOIN theater t ON m.t_id = t.t_id "
            + "JOIN genre g ON m.g_id = g.g_id "
            + "JOIN date d ON m.date_id = d.date_id "
            + "WHERE t.t_name = '%s' and g.g_name = '%s'";

    private JLabel titleLabel;
    private JList<String> movieList;
     private JComboBox<String> moiveComboBox;
    DefaultListModel<String> movieListModel;
    String theater;
    String genre;
    ArrayList<String> genreNames = new ArrayList<>();

    public MoviePanel(String theaterName, String genreName) {
         setSize(800, 500);
        setLayout(null);
        setBackground(Color.white);
        
        theater = theaterName;
        genre = genreName;

        titleLabel = new JLabel("영화를 선택하세요 !");
        titleLabel.setLayout(null);
        titleLabel.setBounds(300, 5, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));

        //ArrayList<String> movieNames = new ArrayList<>();
        ArrayList<String> movieNames = new ArrayList<>();
        movieListModel = new DefaultListModel<>();
        movieList = new JList<>(movieListModel);
        movieList.setLayout(null);
        movieList.setBounds(280, 50, 250, 250);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format(SELECT_MOVIES, theater, genre))) {
            while (rs.next()) {
                //movieNames.add(rs.getString("t.t_name, g.g_name, m.m_name"));
                String movieTitle = /*rs.getString("t.t_name") + ", " + rs.getString("g.g_name") + ", " + */ rs.getString("m.m_name")/* + ", "+rs.getString("d.date_date")*/;
                movieListModel.addElement(movieTitle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JButton backButton = new JButton("뒤로 가기");
        backButton.setLayout(null);
        backButton.setBounds(290, 300, 150, 50);
        backButton.setBackground(Color.white);
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container parent = MoviePanel.this.getParent();
                Component currentPanel = parent.getComponent(0);
                parent.remove(currentPanel);
                parent.add(new GenrePanel(theater) {
                    @Override
                    public PayPanel creatPayPanel() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                });
                parent.revalidate();
                parent.repaint();
            }
        });


        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        movieList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedMovie = (String) movieList.getSelectedValue();
                    String selectdeTheater = theater;
                    String selectedGenre = genre;
                    SelectedSeatPanel seatPanel = new SelectedSeatPanel(selectdeTheater, selectedGenre, selectedMovie);

                    Container parent = MoviePanel.this.getParent();
                    Component currentPanel = parent.getComponent(0);
                    parent.remove(currentPanel);
                    parent.add(seatPanel);

                    parent.revalidate();
                    parent.repaint();
                }
            }
        });
        add(titleLabel);
        add(backButton);
        add(new JScrollPane(movieList));
        add(movieList);
      
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == movieList) {
            String selectedMovie = (String) movieList.getSelectedValue();
            String selectdeTheater = theater;
            String selectedGenre = genre;
            SelectedSeatPanel seatPanel = new SelectedSeatPanel(selectdeTheater, selectedGenre, selectedMovie);

            Container parent = this.getParent();
            parent.add(seatPanel, "Seat");
            CardLayout c3 = (CardLayout) parent.getLayout();
            c3.show(parent, "Seat");

            /*this.getParent().add(seatPanel, "Seat");
            CardLayout cl = (CardLayout) this.getParent().getLayout();
            cl.show(this.getParent(), "Seat");*/
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

    @Override
    public SeatPanel creatSeatPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
