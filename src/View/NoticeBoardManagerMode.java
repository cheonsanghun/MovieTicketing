

package View;

/**
 *
 * @author cherr
 */

import Controller.LoginDto;
import DbConnect.DbConnect;
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

public class NoticeBoardManagerMode extends JPanel {
    
    JButton delete=new JButton("삭제");
    JTextField t1=new JTextField(10);
    JTextField t2=new JTextField(10);
    JLabel l1 = new JLabel("rate");
    JLabel l2 = new JLabel("review");
    java.util.List<LoginDto> companys;
    JTable table;
    DefaultTableModel dft;
    
    public NoticeBoardManagerMode() {
        JFrame frame = new JFrame("관리자 모드");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.getContentPane().setLayout(null);            
        
        frame.add(delete);
        delete.setBounds(460,40,100,30);
        
        String[] colNames = {"rate","review"};
        // 테이블에 출력할 데이터를 가지고있는 디폴트모델테이블
        dft = new DefaultTableModel(colNames, 0) {
            //수정 가능 여부를 리턴하는 메소드
            public boolean isCellEditable(int row, int column) {
                //0번 칼럼만 수정 불가능하도록 false를 리턴해주고
                if (column == 0) {
                    return false;
                } else {
                    //나머지는 모두 수정 가능하도록 true를 리턴한다.
                    return true;
                }

            }
        };
        
        table = new JTable(dft);
        // 스크롤 생성
        JScrollPane pane = new JScrollPane(table);
        
        pane.setBounds(40, 30, 400, 350);
        frame.add(pane);  
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String rate = table.getValueAt(selectedRow, 0).toString();
                    String review = table.getValueAt(selectedRow, 1).toString();
                    
                    // 데이터베이스에서 삭제
                    deleteFromDatabase(rate, review);

                    // JTable에서 삭제
                    dft.removeRow(selectedRow);
                }
            }
        });
        
        frame.setVisible(true);
        loadDataFromDatabase();
        
    }
    
    private void deleteFromDatabase(String rate, String review) {
        try {
            Connection conn = conn = new DbConnect().getConn();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM review WHERE rate = ? AND review = ?");
            stmt.setString(1, rate);
            stmt.setString(2, review);
                  int index = table.getSelectedRow();
            // 쿼리 실행
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
    
    private void loadDataFromDatabase() {
        try {
            // 데이터베이스 연결 설정
            Connection conn =conn = new DbConnect().getConn();
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


