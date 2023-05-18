/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model.Factory;

//import reser.TheaterPanel;
//import reser.GenrePanel;

import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author USER
 */
public class Reser {

   private static JFrame frame;
    private static JPanel mainPanel;

    public static void main(String[] args) {
      // JFrame ????
        JFrame frame = new JFrame("øµ»≠ øπ∏≈ Ω√Ω∫≈€");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // TheaterPanel ???? Î∞? Ï∂?Í∞?
        TheaterPanel theaterPanel = new TheaterPanel();
        frame.getContentPane().add(theaterPanel);
        
        // JFrame ?¨Í∏∞ Î∞? ??Ïπ? ?§Ï?? ?? Î≥¥Ï?¥Î??Î°? ??
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}
}