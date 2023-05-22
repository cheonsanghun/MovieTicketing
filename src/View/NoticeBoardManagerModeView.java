

package View;

/**
 *
 * @author cherr
 */

import Controller.LoginDto;
import Controller.NoticeBoardManagerController;
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

public class NoticeBoardManagerModeView extends JPanel {
    
    JButton delete=new JButton("삭제");
    JTextField t1=new JTextField(10);
    JTextField t2=new JTextField(10);
    JLabel l1 = new JLabel("rate");
    JLabel l2 = new JLabel("review");
    java.util.List<LoginDto> companys;
    JTable table;
    DefaultTableModel dft;
    private NoticeBoardManagerController controller;
    
    public NoticeBoardManagerModeView() {
        controller = new NoticeBoardManagerController();
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
                    controller.deleteReview(rate, review);
                    
                    // JTable에서 삭제
                    dft.removeRow(selectedRow);
                }
            }
        });
        
        frame.setVisible(true);
        loadDataFromDatabase();
        
    }
     private void loadDataFromDatabase() {
        dft.setRowCount(0); // Clear the table data
        Object[][] data = controller.getReviewData();
        for (Object[] rowData : data) {
            dft.addRow(rowData);
        }
    }
}


