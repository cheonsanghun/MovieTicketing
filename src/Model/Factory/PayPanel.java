/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

import java.awt.*;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author USER
 */
public class PayPanel extends JPanel {

    private JLabel titleLabel;
    private JPanel seatsPanel;
    private JButton backButton, addButton;
    private String cardnumber;
    private int s_row, s_col, theaterid, genreid, movieid;
    JPanel PayPanel;
    JFrame frame;
    private String theater;
    private String genre;
    private String movie;
    private String seatMarking;
    private JTextField card;

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_Pay = "SELECT * FROM seat s "
            + "JOIN theater t ON t.t_id = s.t_id "
            + "JOIN genre g ON g.g_id = s.g_id "
            + "JOIN movie m ON m.m_id = s.m_id "
            + "WHERE t.t_name = ? AND g.g_name = ? AND m.m_name = ?";

    public PayPanel(String theaterName, String genreName, String movieName, String seatMarking) {
        PayPanel = new JPanel();
        setLayout(null);
        setSize(800, 500);
        setBackground(Color.white);

        titleLabel = new JLabel("결제 화면");
        titleLabel.setLayout(null);
        titleLabel.setBounds(335, 85, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));

        this.theater = theaterName;
        this.genre = genreName;
        this.movie = movieName;
        this.seatMarking = seatMarking;

        card = new JTextField();
        card.setLayout(null);
        card.setBounds(290, 150, 220, 40);

        addButton = new JButton("결제하기");
        addButton.setLayout(null);
        addButton.setBackground(Color.white);
        addButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        addButton.setBounds(245, 225, 125, 45);

        backButton = new JButton("취소하기");
        backButton.setLayout(null);
        backButton.setBackground(Color.white);
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        backButton.setBounds(395, 225, 125, 45);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null, "결제를 취소합니다","확인", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
           SwingUtilities.getWindowAncestor(PayPanel.this).dispose();
        }
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savecardnumSelection();
            }
        });
        
        addButton.setActionCommand("add");
        add(titleLabel);
        add(card);
        add(addButton);
        add(backButton);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement pstmt = conn.prepareStatement(SELECT_Pay)) {
            pstmt.setString(1, theater);
            pstmt.setString(2, genre);
            pstmt.setString(3, movie);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                s_row = rs.getInt("s.s_row");
                s_col = rs.getInt("s.s_col");
                theaterid = rs.getInt("s.t_id");
                genreid = rs.getInt("s.g_id");
                movieid = rs.getInt("s.m_id");
                cardnumber = rs.getString("s.cardnum");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void savecardnumSelection() {
        String cardnumber = card.getText();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement pstmt = conn.prepareStatement("UPDATE seat SET cardnum = '%s' WHERE s.s_row = seatMarking and s.s_col = seatMarking and s.t_id = theaterName and s.g_id = genreName and s.m_id = movieName")) {

            pstmt.setString(1, cardnumber);
            pstmt.setInt(2, s_row);
            pstmt.setInt(3, s_col);
            pstmt.setInt(4, theaterid);
            pstmt.setInt(5, genreid);
            pstmt.setInt(6, movieid);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
