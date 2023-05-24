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
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SeatPanel extends JPanel {

    private JLabel titleLabel;
    private JPanel seatsPanel;
    private JButton backButton;
    private JButton paymentButton;

    private String theater;
    private String genre;
    private String movie;
    private int s_row = -1;
    private int s_col = -1;

    private Vector<String> vector;

    private int t_id;
    private int g_id;
    private int m_id;

    String dbDriver = "org.mariadb.jdbc.Driver";
    String dbUrl = "jdbc:mariadb://127.0.0.1:3306/test";
    String dbUser = "root";
    String dbPassword = "12341234";
    Connection dbconn = null;

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12341234";

    private static final String SELECT_SEATS = "SELECT s.s_row , s.s_col , s.t_id, s.g_id, s.m_id FROM seat s "
            + "Join theater t on t.t_id = s.t_id "
            + "Join genre g on g.g_id = s.g_id "
            + "Join movie m on m.m_id = s.m_id "
            + "where t.t_name = '%s' and g.g_name = '%s' and m.m_name = '%s'";

    private static final String theaterIdQuery = "SELECT t_id FROM theater WHERE t_name = '%s'";
    private static final String genreIdQuery = "SELECT g_id FROM genre WHERE g_name = '%s'";
    private static final String movierIdQuery = "SELECT m_id FROM movie WHERE m_name = '%s'";

    private static final String theaterID = "SELECT t_id FROM seat  WHERE s_row = seletedRow and s_col = selectedCol";
    private static final String genreID = "SELECT g_id FROM seat WHERE s_row = seletedRow and s_col = selectedCol";
    private static final String movieID = "SELECT m_id FROM seat WHERE s_row = seletedRow and s_col = selectedCol";

    private static final String theaterN = "SELECT t_id FROM theater where t_name = '%s'";

    public SeatPanel(String theaterName, String genreName, String movieName) {
        seatsPanel = new JPanel();
        setLayout(null);
        setSize(800, 500);
        setBackground(Color.white);
        createSeats();
        theater = theaterName;
        genre = genreName;
        movie = movieName;

        titleLabel = new JLabel("좌석을 선택하세요");
        titleLabel.setLayout(null);
        titleLabel.setBounds(300, 5, 200, 40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));

        createSeats();

        paymentButton = new JButton("결제");
        paymentButton.setLayout(null);
        paymentButton.setBounds(130, 300, 150, 50);
        paymentButton.setBackground(Color.white);
        paymentButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        backButton = new JButton("뒤로 가기");
        backButton.setLayout(null);
        backButton.setBounds(290, 300, 150, 50);
        backButton.setBackground(Color.white);
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container parent = SeatPanel.this.getParent();
                Component currentPanel = parent.getComponent(0);
                parent.remove(currentPanel);
                parent.add(new MoviePanel(theater, genre) {
                    @Override
                    public PayPanel creatPayPanel() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                });
                parent.revalidate();
                parent.repaint();
            }
        });
        add(paymentButton);
        add(backButton);
       
        add(titleLabel);

        DefaultListModel<String> seats = new DefaultListModel<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(String.format(SELECT_SEATS, theater, genre, movie))) {
            while (rs.next()) {
                int row = rs.getInt("s.s_row");
                int col = rs.getInt("s.s_col");
                int theaterid = rs.getInt("s.t_id");
                int genre = rs.getInt("s.g_id");
                int movie = rs.getInt("s.m_id");
                s_row = row;
                s_col = col;
                t_id = theaterid;
                g_id = genre;
                m_id = movie;

                String SeatNum = rs.getString("s.s_row") + " , " + rs.getString("s.s_col");
                seats.addElement(SeatNum);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (s_row == -1 || s_col == -1) {
                    JOptionPane.showMessageDialog(null, "좌석을 선택해주세요.");
                } else {
                    if (isSeatAlreadyReserved(s_row, s_col, t_id, g_id, m_id)) {
                        JOptionPane.showMessageDialog(null, "이미 예매된 좌석입니다.");
                    } else {
                        JOptionPane.showMessageDialog(null, "결제화면으로 넘어갑니다.");
                        saveSeatSelection(s_row, s_col);

                        String selectdeTheater = theater;
                        String selectedGenre = genre;
                        String selectedMovie = movie;
                        String selectedSeat = SELECT_SEATS;
                        SelectedPayPanel PayPanel = new SelectedPayPanel(selectdeTheater, selectedGenre, selectedMovie, selectedSeat);
                        Container parent = SeatPanel.this.getParent();
                        Component currentPanel = parent.getComponent(0);
                        parent.remove(currentPanel);
                        parent.add(PayPanel);
                        parent.revalidate();
                        parent.repaint();

                    }
                }
            }
        });

    }

    SeatPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean isSeatAlreadyReserved(int row, int col, int theaterid, int genreid, int movieid) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM seat WHERE s_row = ? AND s_col = ? AND t_id = ? AND g_id = ? AND m_id = ?")) {
            stmt.setInt(1, s_row);
            stmt.setInt(2, s_col);
            stmt.setInt(3, t_id);
            stmt.setInt(4, g_id);
            stmt.setInt(5, m_id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createSeats() {
        int numRows = 5;
        int numCols = 10;

        for (int row = 1; row <= numRows; row++) {
            for (int col = 1; col <= numCols; col++) {
                int seatMarking = (row - 1) * numCols + col;
                String seatLabel = String.valueOf(seatMarking);
                JCheckBox seatCheckbox = new JCheckBox(seatLabel);
                seatCheckbox.setBounds((col - 1) * 50, (row - 1) * 50, 50, 50);
                seatCheckbox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JCheckBox selectedCheckbox = (JCheckBox) e.getSource();
                        String selectedSeat = selectedCheckbox.getText();
                        int selectedRow = (Integer.parseInt(selectedSeat) - 1) / numCols;
                        int selectedCol = (Integer.parseInt(selectedSeat) - 1) % numCols;

                        if (selectedRow == s_row && selectedCol == s_col) {
                            // 이미 선택된 좌석을 다시 선택한 경우, 선택 취소
                            selectedCheckbox.setSelected(false);
                            s_row = -1;
                            s_col = -1;
                            System.out.println("Seat deselected.");
                        } else {
                            s_row = selectedRow;
                            s_col = selectedCol;
                            System.out.println("Selected Seat: Row " + s_row + ", Column " + s_col);

                        }
                    }
                });
       
                add(seatCheckbox);
            }
        }
    }

    private void saveSeatSelection(int row, int col) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Theater ID 조회
            String theaterIdQuery = String.format("SELECT t_id FROM theater WHERE t_name = '%s'", theater);
            Statement theaterIdStatement = conn.createStatement();
            ResultSet theaterIdResult = theaterIdStatement.executeQuery(theaterIdQuery);
            int t_id = -1;
            if (theaterIdResult.next()) {
                t_id = theaterIdResult.getInt("t_id");
            } else {
                System.out.println("Theater not found: " + theater);
            }
            theaterIdResult.close();
            theaterIdStatement.close();

            // Genre ID 조회
            String genreIdQuery = String.format("SELECT g_id FROM genre WHERE g_name = '%s'", genre);
            Statement genreIdStatement = conn.createStatement();
            ResultSet genreIdResult = genreIdStatement.executeQuery(genreIdQuery);
            int g_id = -1;
            if (genreIdResult.next()) {
                g_id = genreIdResult.getInt("g_id");
            } else {
                System.out.println("Genre not found: " + genre);
            }
            genreIdResult.close();
            genreIdStatement.close();

            // Movie ID 조회
            String movieIdQuery = String.format("SELECT m_id FROM movie WHERE m_name = '%s'", movie);
            Statement movieIdStatement = conn.createStatement();
            ResultSet movieIdResult = movieIdStatement.executeQuery(movieIdQuery);
            int m_id = -1;
            if (movieIdResult.next()) {
                m_id = movieIdResult.getInt("m_id");
            } else {
                System.out.println("Movie not found: " + movie);
            }
            movieIdResult.close();
            movieIdStatement.close();

            // Seat 저장
            if (t_id != -1 && g_id != -1 && m_id != -1) {
                String insertQuery = "INSERT INTO seat (t_id, g_id, m_id, s_row, s_col) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
                preparedStatement.setInt(1, t_id);
                preparedStatement.setInt(2, g_id);
                preparedStatement.setInt(3, m_id);
                preparedStatement.setInt(4, row);
                preparedStatement.setInt(5, col);

                preparedStatement.executeUpdate();
                System.out.println("Selected seat is saved to the database.");

            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
