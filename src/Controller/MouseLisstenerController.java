/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * 마우스 리스너
 */
public class MouseLisstenerController extends MouseAdapter {

    public void mouseClicked(MouseEvent e) {
      
        if(e.getButton()==3){
             JOptionPane.showMessageDialog(null, "수정 했습니다.");
        }
    }
    
}
