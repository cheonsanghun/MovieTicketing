/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.observer;



/**
 *
 * @author cherr
 */

import Model.observer.Observer;
import javax.swing.JTextArea;

public class Reviewobserver implements Observer {
    private final JTextArea txtlog;

    public Reviewobserver(JTextArea txtlog) {
        this.txtlog = txtlog;
    }

    public void update(String rate, String review) {
        String reviewString = "∆Ú¡° : " + rate + " / ∏Æ∫‰ : " + review + "\n";
        txtlog.append(reviewString);
    }
}

