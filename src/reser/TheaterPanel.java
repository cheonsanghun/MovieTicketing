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

    // ?°ì?´í?°ë??´ì?? ?°ê²° ??ë³?
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";

    // ì½¤ë³´ë°??¤ì???? ???? ê°??¥í?? ê·¹ì?? ??ë³´ë?? ê°??¸ì?¤ê¸° ???? ì¿¼ë¦¬
    private static final String SELECT_THEATERS = "SELECT * FROM theater";

    // ê·¹ì?? ?????? ???? ???´ë?ê³? ì½¤ë³´ë°???
    private JLabel titleLabel;
    private JComboBox<String> theaterComboBox;

    public TheaterPanel() {

        
        // ê·¹ì?? ?????? ???? ???´ë? ????
        titleLabel = new JLabel("????ê´??? ?????´ì£¼?¸ì??:");

        // ?°ì?´í?°ë??´ì?¤ì???? ê·¹ì?? ??ë³´ë?? ê°??¸ì???? ë¦¬ì?¤í?¸ì?? ì¶?ê°?
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

        // ê·¹ì?? ?????? ???? JList ????
        final JList<String> theaterList = new JList<>(theaterArray);
        theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ê·¹ì?? ???? ???´ë?ê³? JListë¥? ?¨ë???? ì¶?ê°?
        add(titleLabel);
        add(new JScrollPane(theaterList));

        // ê·¹ì?? ???? JList?? ?¡ì?? ë¦¬ì?¤ë?? ?±ë?
        theaterList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
            String selectedTheater = theaterList.getSelectedValue();

            // ?????? ê·¹ì?¥ì?? ?´ì?©í???? ?¥ë¥´ ???? ?¨ë?? ????
            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

            // ë¶?ëª? ?¨ë???? ???? ë³´ì?¬ì?ê³? ???? ?¨ë???? ??ê±°í??ê³?, ?¥ë¥´ ???? ?¨ë???? ì¶?ê°???
            Container parent = TheaterPanel.this.getParent();
            Component currentPanel = parent.getComponent(0);
            parent.remove(currentPanel);
            parent.add(genrePanel);

            // ë³?ê²½ë?? ?¨ë?? êµ¬ì?±ì?? ë°???
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

            // ?????? ê·¹ì?¥ì?? ?´ì?©í???? ?¥ë¥´ ???? ?¨ë?? ????
            SelectedGenrePanel genrePanel = new SelectedGenrePanel(selectedTheater);

            // ë¶?ëª? ?¨ë???? ?¥ë¥´ ???? ?¨ë???? ì¶?ê°???ê³?, ì¹´ë?? ???´ì?????¼ë? ????
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