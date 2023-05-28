/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.observer;



/**
 *
 * 옵저버 패턴
 */

import Model.observer.Observer;
import javax.swing.JTextArea;

public class Reviewobserver implements Observer {
    private final JTextArea txtlog;

    public Reviewobserver(JTextArea txtlog) {
        this.txtlog = txtlog;
    }

    public void update(String rate, String review) {
        String reviewString = "평점 : " + rate + " / 리뷰 : " + review + "\n";
        txtlog.append(reviewString);
    }
}

