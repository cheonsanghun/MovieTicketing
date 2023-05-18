/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

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
    String dbUrl = "jdbc:mysql://127.0.0.1:3306/test";
    String dbUser = "root";
    String dbPassword = "12341234";    
    Connection dbconn = null;
    
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_MOVIES = "SELECT DISTINCT m.m_name  " +
             "FROM movie m " +
             "JOIN theater t ON m.t_id = t.t_id " +
             "JOIN genre g ON m.g_id = g.g_id " +
             "JOIN date d ON m.date_id = d.date_id " +
             "WHERE t.t_name = '%s' and g.g_name = '%s'";

    private JLabel titleLabel;
    private JList<String> movieList;
    String theater;
    String genre;
    
    public MoviePanel(String theaterName, String genreName) {
        theater = theaterName;
        genre = genreName;
        
        titleLabel = new JLabel("영화를 선택하세요:");
        //ArrayList<String> movieNames = new ArrayList<>();
        DefaultListModel<String> movieListModel = new DefaultListModel<>();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(String.format(SELECT_MOVIES,theater, genre))) {
            while (rs.next()) {
                //movieNames.add(rs.getString("t.t_name, g.g_name, m.m_name"));
                 String movieTitle = /*rs.getString("t.t_name") + ", " + rs.getString("g.g_name") + ", " + */rs.getString("m.m_name")/* + ", "+rs.getString("d.date_date")*/;
                 movieListModel.addElement(movieTitle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        JButton backButton = new JButton("뒤로 가기");
        backButton.setBounds(20, 320, 100, 30);
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
        add(backButton);
        

        movieList = new JList<>(movieListModel);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        movieList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedMovie = (String) movieList.getSelectedValue();
                    String selectdeTheater = theater;
                    String selectedGenre = genre;
                    SelectedSeatPanel  seatPanel = new SelectedSeatPanel(selectdeTheater,selectedGenre,selectedMovie);

                    
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
        add(new JScrollPane(movieList));
    }

    
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == movieList) {
            String selectedMovie = (String) movieList.getSelectedValue();
            String selectdeTheater = theater;
            String selectedGenre = genre;
            SelectedSeatPanel  seatPanel = new SelectedSeatPanel(selectdeTheater,selectedGenre,selectedMovie);

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
