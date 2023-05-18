/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.observer;

import Model.observer.Observer;

/**
 *
 * @author cherr
 */


public interface Subject {
    public void registerObserver(Observer o); //옵저버 등록
    public void removeObserver(Observer o);   //옵저버 제거
    public void notifyObservers();            // 상태 변화를 알려주는 옵저버
}


