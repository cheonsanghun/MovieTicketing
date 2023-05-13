/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reser;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author USER
 */
public abstract class MoviePanel extends JPanel implements MovieFactory {

     String dbDriver = "com.mysql.jdbc.Driver";
    String dbUrl = "jdbc:mysql://127.0.0.1:3306/movie_db";
    String dbUser = "root";
    String dbPassword = "ll8055001!";    
    Connection dbconn = null;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ll8055001!";

    private static final String SELECT_MOVIES = "SELECT movie.title FROM movie JOIN theater ON movie.theater_id = theater.id WHERE theater.name = '%s';";

    private JLabel titleLabel;
    private JList<String> movieList;
    private DefaultListModel<String> movieModel;

    public MoviePanel(String theaterName) {
        titleLabel = new JLabel("영화를 선택해주세요:");
        movieModel = new DefaultListModel<>();
        movieList = new JList<>(movieModel);
        movieList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        movieList.addListSelectionListener((ListSelectionListener) this);
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(String.format(SELECT_MOVIES, theaterName))) {
            while (rs.next()) {
                String movieTitle = rs.getString("title");
                movieModel.addElement(movieTitle);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        JScrollPane scrollPane = new JScrollPane(movieList);

        add(titleLabel);
        add(scrollPane);
    }

    
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (movieList.getSelectedIndex() == -1) {
                //No selection, do nothing
            } else {
                String selectedMovie = (String) movieList.getSelectedValue();
                System.out.println("Selected movie: " + selectedMovie);
            }
        }
    }
}
