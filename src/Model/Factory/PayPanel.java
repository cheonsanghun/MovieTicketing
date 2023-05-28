/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Factory;

import View.Login.LoginFrameView;
import View.Login.LoginSuccessView;
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

    private String uid, upw;
    private static String cardnumber;
    private static String id, pw;
private int seatR;
    private int seatC;
    
    private JLabel titleLabel, cardnumber1;
    private JPanel seatsPanel;
    private JButton backButton, addButton;

    private int s_row, s_col, theaterid, genreid, movieid;
    JPanel PayPanel;
    JFrame frame;
    private String theater;
    private String genre;
    private String movie;
    private String seatMarking;
    private JTextField card;

    String dbDriver = "org.mariadb.jdbc.Driver";
    String dbUrl = "jdbc:mariadb://113.198.234.132:9090/moviedb";
    String dbUser = "jbg";
    String dbPassword = "12341234";
    Connection dbconn = null;
    
    private static final String DB_URL = "jdbc:mariadb://113.198.234.132:9090/moviedb";
    private static final String DB_USER = "jbg";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_Pay = "SELECT * FROM seat s "
            + "JOIN theater t ON t.t_id = s.t_id "
            + "JOIN genre g ON g.g_id = s.g_id "
            + "JOIN movie m ON m.m_id = s.m_id "
            + "WHERE t.t_name = ? AND g.g_name = ? AND m.m_name = ? AND s.s_row = ? AND s.s_col = ?";

    public PayPanel(String theaterName, String genreName, String movieName,int seatRow , int seatCol) {
        
        PayPanel = new JPanel();
        setLayout(null);
        setSize(800, 500);
        setBackground(Color.white);

        titleLabel = new JLabel("결제 화면");
        titleLabel.setLayout(null);
        titleLabel.setBounds(335, 85, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 25));

        cardnumber1 = new JLabel("카드 번호 :");
        cardnumber1.setLayout(null);
        cardnumber1.setBounds(190, 150, 100, 40);
        cardnumber1.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 15));

        this.theater = theaterName;
        this.genre = genreName;
        this.movie = movieName;
        this.seatR = seatRow;
        this.seatC = seatCol;
        
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
                int result = JOptionPane.showConfirmDialog(null, "결제를 취소합니다", "확인", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    SwingUtilities.getWindowAncestor(PayPanel.this).dispose();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardnumber = card.getText();
                savecardnumSelection();
                if (cardnumber == " " || cardnumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "빈칸입니다. 카드 번호를 입력하세요.");
                    SwingUtilities.getWindowAncestor(PayPanel.this).setVisible(true);
                }
                 try ( Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  PreparedStatement pstmt = conn.prepareStatement("UPDATE seat SET cardnum = ? WHERE s_row = ? and s_col = ? and t_id = ? and g_id = ? and m_id = ? ")) {

            pstmt.setString(1, cardnumber);
            pstmt.setInt(2, s_row);
            pstmt.setInt(3, s_col);
            pstmt.setInt(4, theaterid);
            pstmt.setInt(5, genreid);
            pstmt.setInt(6, movieid);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "성공적으로 결제가 완료되었습니다.");
                UpdatePro(cardnumber);
                new LoginSuccessView();
            } if( rows<0) {
                  JOptionPane.showMessageDialog(null, "결제에 실패하였습니다..");
            }
          

            card.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            }
        });

        addButton.setActionCommand("add");
        add(titleLabel);
        add(card);
        add(addButton);
        add(backButton);
        add(cardnumber1);
        try ( Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  PreparedStatement pstmt = conn.prepareStatement(SELECT_Pay)) {
            pstmt.setString(1, theater);
            pstmt.setString(2, genre);
            pstmt.setString(3, movie);
            pstmt.setInt(4, seatR);
            pstmt.setInt(5, seatC);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                s_row = rs.getInt("s_row");
                s_col = rs.getInt("s_col");
                theaterid = rs.getInt("t_id");
                genreid = rs.getInt("g_id");
                movieid = rs.getInt("m_id");
                cardnumber = rs.getString("cardnum");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
                        System.out.println("결제 화면");

    }

    private void savecardnumSelection() {
        cardnumber = card.getText();
        LoginFrameView LoginFrame = new LoginFrameView();

        System.out.println("cardnumber: " + cardnumber);
        System.out.println("s_row: " + s_row);
        System.out.println("s_col: " + s_col);
        System.out.println("theater: " + theaterid);
        System.out.println("genre: " + genreid);
        System.out.println("movie: " + movieid);
       
    }

    public static void update(String uid, String upw) {

        id = uid;
        pw = upw;

    }

    private void UpdatePro(String cardnumber) {
        try ( Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  PreparedStatement pstmt = conn.prepareStatement("UPDATE profile SET cardnum = ? WHERE id = ? AND pw = ?")) {

            pstmt.setString(1, cardnumber);
            pstmt.setString(2, id);
            pstmt.setString(3, pw);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Data updated successfully.");

            } else {
                System.out.println("No rows were affected.");
            }
            card.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
