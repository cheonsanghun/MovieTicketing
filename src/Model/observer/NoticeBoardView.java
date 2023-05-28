/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.observer;

/**
 *
 * @author cherr
 */
import Model.observer.Reviewobserver;
import Model.observer.Reviewdata;
import View.Login.LoginSuccessView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import Model.observer.Observer;
import View.Login.LoginSuccessView;
/*
 사용자가 게시판을 사용할 수 있는 화면
*/
public class NoticeBoardView {
    //필드
    String array[]={"1","2","3","4","5"};
    JComboBox combox = new JComboBox(array);
    JButton btn1=new JButton("확인");
    JButton btn2=new JButton("글 전체");
    JTextField tf1=new JTextField(20);
    public JTextArea txtlog = new JTextArea(10,30);
    JButton back;
    private Reviewdata reviewData;
    private Reviewobserver reviewObserver;
    private NoticeBoardController controller;
    
    final String DB_IP = "113.198.234.132";
		final String DB_PORT = "9090";
		final String DB_NAME = "moviedb";
		final String DB_URL = 
				"jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;
    
    public NoticeBoardView(){
        //GUI 화면
          controller = new NoticeBoardController(this);
        final JFrame frame=new JFrame("게시판"); //
        frame.setSize(800, 500); // 
        frame.setLocationRelativeTo(null); //

        frame.getContentPane().setLayout(null); // 

        JLabel label1 = new JLabel("평점");
        JLabel label2 = new JLabel("리뷰");

        frame.add(label1);
        label1.setBounds(10, 10, 30, 20);

        frame.add(label2);
        label2.setBounds(10, 60, 30, 20);

        frame.getContentPane().add(combox);
        combox.setBounds(52, 10, 122, 30);
         frame.getContentPane().add(tf1);
         
        back = new JButton("뒤로가기");
        back.setLayout(null);
        back.setBounds(684,431,100,30);
        frame.getContentPane().add(back);
      
        tf1.setBounds(52, 60, 250, 70);

        frame.getContentPane().add(btn1);
        btn1.setBounds(40, 230, 122, 30);

        frame.getContentPane().add(btn2);
        btn2.setBounds(182, 230, 122, 30);

        frame.getContentPane().add(txtlog);
        txtlog.setBounds(330, 40, 450, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
        
        reviewData =  new Reviewdata();
        reviewObserver = new Reviewobserver(txtlog);
        reviewData.registerObserver((Observer) reviewObserver);
        
        // 확인 버튼을 클릭했을 때 발생하는 이벤트
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rate = (String) combox.getSelectedItem();
                String review = tf1.getText();
                
                if(review.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "내용을 입력하세요.","경고",JOptionPane.WARNING_MESSAGE);
                    System.err.println("enter text plz");
                }else {
                      
                    reviewData.setMeasurements(rate, review);
                  
                    controller.saveReview(rate, review);
            }
            }
        });

        // 게시글 전체를 보고 싶을 때 버튼을 클릭하면 발생하는 이벤트
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
                    controller.showAllReviews();
            }
        });
           back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                new LoginSuccessView();
                frame.setVisible(false);
            }
        });
    }
    
public JTextArea getTxtlog() {
    return txtlog;
}

}
