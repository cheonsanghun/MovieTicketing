/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

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


    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";


    private static final String SELECT_THEATERS = "SELECT * FROM theater";


    private JLabel titleLabel;
    private JComboBox<String> theaterComboBox;
    private JList<String> theaterList;

    public TheaterPanel() {

        

        titleLabel = new JLabel("극장을 선택하세요:");


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


        theaterList = new JList<>(theaterArray);
        theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        add(titleLabel);
        add(new JScrollPane(theaterList));


        theaterList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            String selectedTheater = theaterList.getSelectedValue();


            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);


            Container parent = TheaterPanel.this.getParent();
            Component currentPanel = parent.getComponent(0);
            parent.remove(currentPanel);
            parent.add(genrePanel);


            parent.revalidate();
            parent.repaint();
        }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == theaterList) {
            String selectedTheater = (String) theaterList.getSelectedValue();

            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

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

    @Override
    public SeatPanel creatSeatPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PayPanel creatPayPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}