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
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TheaterPanel extends JPanel implements MovieFactory, ActionListener {

    private static final String DB_URL = "jdbc:mariadb://113.198.234.132:9090/moviedb";
    private static final String DB_USER = "jbg";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_THEATERS = "SELECT * FROM theater";

    private JButton back;
    private JLabel titleLabel;
    private JComboBox<String> theaterComboBox;
    private JList<String> theaterList;

    public TheaterPanel() {
        setSize(800, 500);
        setLayout(null);
        setBackground(Color.white);
        titleLabel = new JLabel("극장을 선택하세요 !");
        titleLabel.setLayout(null);
        titleLabel.setBounds(300, 5, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));

        ArrayList<String> theaterNames = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM theater")) {
            while (rs.next()) {
                theaterNames.add(rs.getString("t_name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] theaterArray = theaterNames.toArray(new String[theaterNames.size()]);

        back = new JButton("뒤로가기");
        back.setLayout(null);
        back.setBounds(290, 300, 150, 50);
        back.setBackground(Color.white);
        back.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        theaterList = new JList<>(theaterArray);
        theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        theaterList.setLayout(null);
        theaterList.setBounds(280, 50, 250, 250);

        add(back);
        add(titleLabel);
        add(new JScrollPane(theaterList));
        add(theaterList);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "처음 화면으로 돌아갑니다.", "확인", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    SwingUtilities.getWindowAncestor(TheaterPanel.this).dispose();
                }
            }
        });
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
        return null;
    }

    @Override
    public GenrePanel createGenrePanel() {
        return null;
    }

    @Override
    public MoviePanel createMoviePanel() {
        return null;
    }

    @Override
    public SeatPanel creatSeatPanel() {
        return null;
    }

    @Override
    public PayPanel creatPayPanel() {
        return null;
    }
}
