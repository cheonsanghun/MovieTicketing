/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.observer.Reviewdata;
import Model.observer.Observer;
import View.NoticeBoardView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 *
 * @author kjbg4
 */
public class NoticeBoardController {
     final String DB_IP = "localhost";
		final String DB_PORT = "3306";
		final String DB_NAME = "test";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
    public ReviewData reviewData;
    private final NoticeBoardView noticeBoard;
    
    public NoticeBoardController(NoticeBoardView noticeBoard) {
         this.noticeBoard = noticeBoard;
        reviewData = new ReviewData();
    }

  

    public void registerObserver(Observer observer) {
        reviewData.registerObserver(observer);
    }

    public void saveReview(String rate, String review) {
          try (Connection conn = DriverManager.getConnection(DB_URL, "root", "12341234");
             Statement stmt = conn.createStatement()) {
            // INSERT 荑쇰━ ?ㅽ??
            String sql = "INSERT INTO review (rate, review) VALUES ('" + rate + "', '" + review + "')";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "리뷰가 저장되었습니다.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "리뷰 저장에 실패하였습니다 .");
        }
    }

    public void showAllReviews() {
   
   JTextArea txtlog = noticeBoard.getTxtlog();
       txtlog.setText("");
    
    try (Connection conn = DriverManager.getConnection(DB_URL, "root", "12341234");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT rate, review FROM review")) {
        while (rs.next()) {
            String rating = rs.getString("rate");
            String review = rs.getString("review");
           String reviewString = "평점 : " + rating + " / 리뷰 : " + review + "\n"; 
            txtlog.append(reviewString);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "리뷰 조회에 실패하였습니다..");
    }
    }  

    private static class ReviewData {

        public ReviewData() {
        }

        private void registerObserver(Observer observer) {
          
        }
    }
}