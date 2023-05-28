

package Model.observer;

/**
 *
 * @author cherr
 */

import Controller.LoginDto;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
    게시판 리뷰 및 평점 등록에 대한 DB 저장 쿼리문
*/
public class NoticeBoardDao extends JPanel {

    DefaultTableModel dft;
 
    
    private void deleteFromDatabase(String rate, String review) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mariadb://113.198.234.132:9090", "jbg", "12341234");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM review WHERE rate = ? AND review = ?");
            stmt.setString(1, rate);
            stmt.setString(2, review);
            
            // 쿼리 실행
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("데이터베이스에서 삭제되었습니다.");
            }

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadDataFromDatabase() {
        try {
            // 데이터베이스 연결 설정
            Connection conn = DriverManager.getConnection("jdbc:mariadb://113.198.234.132:9090", "jbg", "12341234");
            PreparedStatement stmt = conn.prepareStatement("SELECT rate, review FROM review");

            // 쿼리 실행 및 결과 가져오기
            ResultSet rs = stmt.executeQuery();

            // 결과를 JTable에 추가
            while (rs.next()) {
                String rate = rs.getString("rate");
                String review = rs.getString("review");

                Object[] rowData = { rate, review };
                dft.addRow(rowData);
            }

            // 자원 해제
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    
}


