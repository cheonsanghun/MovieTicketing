/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Proxy;

/**
 *
 * 프록시 패턴
 */
public class GuardProxy implements Guard {
     private final String PW = "010203";


    @Override
    public boolean check(String input) {
        if (input.equals(PW)) {
            return true;
        } else {
            return false;
        }    }
}
