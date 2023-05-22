/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.LoginDto;
import DbConnect.DbConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NoticeBoardManagerDao {

   public Object[][] getReviewData() {
        Object[][] data = null;
        try {
            Connection conn = new DbConnect().getConn();
            PreparedStatement stmt = conn.prepareStatement("SELECT rate, review FROM review");
            ResultSet rs = stmt.executeQuery();

            // Get the row count
            int rowCount = 0;
            if (rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }

            // Create the data array
            data = new Object[rowCount][2];
            int i = 0;
            while (rs.next()) {
                String rate = rs.getString("rate");
                String review = rs.getString("review");
                data[i][0] = rate;
                data[i][1] = review;
                i++;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void deleteReview(String rate, String review) {
        try {
            Connection conn = new DbConnect().getConn();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM review WHERE rate = ? AND review = ?");
            stmt.setString(1, rate);
            stmt.setString(2, review);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "삭제 되었습니다");
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
