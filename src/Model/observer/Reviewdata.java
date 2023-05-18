/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.observer;



import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
import java.util.List;
import java.util.ArrayList;
import Model.observer.Observer;

public class Reviewdata implements Subject {
    private List<Observer> observers;
    private String rate;
    private String review;
    
    public Reviewdata(){
        observers=new ArrayList<>();
    }
    
    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    public void removeObserver(Observer o){
        observers.remove(o);
    }
    
    public void notifyObservers(){
        for(Observer observer:observers){
            observer.update(rate, review);
        }
    }
    
    public void measurementsChanged(){
        notifyObservers();
    }
    
    public void setMeasurements(String rate,String review){
        this.rate=rate;
        this.review=review;
        notifyObservers();
    }

    void saveReviewToDatabase(String rate, String review) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setReview(String rate, String review) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
