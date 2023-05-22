/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.NoticeBoardManagerDao;

/**
 *
 * @author Admin
 */
public class NoticeBoardManagerController {
     private NoticeBoardManagerDao model;


    public NoticeBoardManagerController() {
        model = new NoticeBoardManagerDao();
    }

    public Object[][] getReviewData() {
        return model.getReviewData();
    }

    public void deleteReview(String rate, String review) {
        model.deleteReview(rate, review);
    }
}
